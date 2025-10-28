#!/usr/bin/env python3
"""
GitHub PR Checker Script
Проверяет размер PR в зависимости от типа и обновляет описание epic PR
"""

import argparse
import sys
from typing import List, Dict, Optional
from github import Github, GithubException, Auth


class PRType:
    """Типы PR и их лимиты по строкам"""
    FEATURE = ("feature", 300)
    REFACTOR = ("refactor", 400)
    BUGFIX = ("bugfix", 150)
    EPIC = ("epic", None)  # Без лимита


def parse_arguments():
    """Парсинг аргументов командной строки"""
    parser = argparse.ArgumentParser(
        description='GitHub PR Checker - проверка размера PR и работа с epic'
    )
    parser.add_argument(
        '--token',
        required=True,
        help='GitHub Access Token'
    )
    parser.add_argument(
        '--repo',
        required=True,
        help='Repository в формате owner/repo (например: username/calculator)'
    )
    parser.add_argument(
        '--pr-number',
        type=int,
        default=None,
        help='Номер Pull Request (необязательно для --list-members)'
    )
    parser.add_argument(
        '--check-size',
        action='store_true',
        help='Проверять размер PR'
    )
    parser.add_argument(
        '--update-epic',
        action='store_true',
        help='Обновить описание epic PR'
    )
    parser.add_argument(
        '--list-members',
        action='store_true',
        help='Вывести список участников проекта'
    )
    
    return parser.parse_args()


def get_pr_type(branch_name: str) -> tuple:
    """
    Определяет тип PR по имени ветки
    
    Args:
        branch_name: Имя ветки (например: feature/my-feature)
    
    Returns:
        tuple: (тип, лимит_строк)
    """
    branch_lower = branch_name.lower()
    
    if 'epic/' in branch_lower:
        return PRType.EPIC
    elif 'feature/' in branch_lower:
        return PRType.FEATURE
    elif 'refactor/' in branch_lower:
        return PRType.REFACTOR
    elif 'bugfix/' in branch_lower or 'fix/' in branch_lower:
        return PRType.BUGFIX
    else:
        # По умолчанию считаем feature
        return PRType.FEATURE


def list_project_members(gh, repo_name: str):
    """
    Выводит список всех участников проекта и их роли
    
    Args:
        gh: GitHub connection object
        repo_name: Имя репозитория (owner/repo)
    """
    print("\n" + "=" * 60)
    print("СПИСОК УЧАСТНИКОВ ПРОЕКТА")
    print("=" * 60)
    
    try:
        repo = gh.get_repo(repo_name)
        collaborators = repo.get_collaborators()
        
        members_list = list(collaborators)
        
        if not members_list:
            print("Участники не найдены")
            return
        
        for member in members_list:
            try:
                permission = repo.get_collaborator_permission(member)
                
                print(f"\n{member.login}")
                if member.name:
                    print(f"   Name: {member.name}")
                
                # Показываем email если доступен, иначе GitHub noreply
                if member.email:
                    print(f"   Email: {member.email}")
                else:
                    print(f"   Email: {member.login}@users.noreply.github.com (private)")
                
                print(f"   Роль: {permission}")
                print(f"   Profile: {member.html_url}")
            except Exception as e:
                print(f"Ошибка получения данных: {e}")
        
        print("\n" + "=" * 60)
        print(f"Всего участников: {len(members_list)}")
        print("=" * 60 + "\n")
        
    except GithubException as e:
        print(f"Ошибка получения участников: {e}")
        sys.exit(1)


def check_pr_size(gh, repo_name: str, pr_number: int):
    """
    Проверяет размер PR и падает с ошибкой, если превышен лимит
    
    Args:
        gh: GitHub connection object
        repo_name: Имя репозитория
        pr_number: Номер pull request
    """
    print("\n" + "=" * 60)
    print("ПРОВЕРКА РАЗМЕРА PULL REQUEST")
    print("=" * 60)
    
    try:
        repo = gh.get_repo(repo_name)
        pr = repo.get_pull(pr_number)
        
        # Получаем информацию о PR
        source_branch = pr.head.ref
        target_branch = pr.base.ref
        title = pr.title
        
        print(f"\nPR Info:")
        print(f"   Title: {title}")
        print(f"   Source: {source_branch}")
        print(f"   Target: {target_branch}")
        
        # Определяем тип PR
        pr_type, line_limit = get_pr_type(source_branch)
        print(f"   Type: {pr_type[0]}")
        
        # Для epic PR нет лимита
        if pr_type == PRType.EPIC:
            print(f"Epic PR - лимит строк не применяется")
            print("=" * 60 + "\n")
            return
        
        print(f"   Limit: {line_limit} lines")
        
        # Получаем изменения
        files = pr.get_files()
        total_lines = 0
        
        print(f"\nChanged files:")
        for file in files:
            file_lines = file.additions + file.deletions
            total_lines += file_lines
            
            print(f"   {file.filename}: +{file.additions} -{file.deletions} (total: {file_lines})")
        
        print(f"\nTotal changes: {total_lines} lines")
        print(f"   Limit: {line_limit} lines")
        
        # Проверяем лимит
        if total_lines > line_limit:
            print(f"\nFAILED: PR размер {total_lines} строк превышает лимит {line_limit} для типа '{pr_type[0]}'")
            print("=" * 60 + "\n")
            sys.exit(1)
        else:
            print(f"\nPASSED: PR размер в пределах лимита")
            print("=" * 60 + "\n")
    
    except GithubException as e:
        print(f"Ошибка получения PR: {e}")
        sys.exit(1)


def update_epic_description(gh, repo_name: str, pr_number: int):
    """
    Обновляет описание epic PR, добавляя ссылки на связанные PR
    
    Args:
        gh: GitHub connection object
        repo_name: Имя репозитория
        pr_number: Номер epic pull request
    """
    print("\n" + "=" * 60)
    print("ОБНОВЛЕНИЕ ОПИСАНИЯ EPIC PR")
    print("=" * 60)
    
    try:
        repo = gh.get_repo(repo_name)
        epic_pr = repo.get_pull(pr_number)
        
        # Проверяем, что это epic PR
        source_branch = epic_pr.head.ref
        if 'epic/' not in source_branch.lower():
            print(f"PR #{pr_number} не является epic PR (ветка: {source_branch})")
            print("=" * 60 + "\n")
            return
        
        print(f"\nEpic PR: #{pr_number}")
        print(f"   Branch: {source_branch}")
        print(f"   Title: {epic_pr.title}")
        
        # Ищем все PR, которые мержатся в epic ветку
        all_prs = repo.get_pulls(state='all', base=source_branch)
        
        pr_list = list(all_prs)
        
        if not pr_list:
            print(f"\nНе найдено PR, направленных в {source_branch}")
            print("=" * 60 + "\n")
            return
        
        print(f"\nНайдено связанных PR: {len(pr_list)}")
        
        # Формируем описание
        description_lines = [
            f"# Epic: {epic_pr.title}",
            "",
            "## Связанные Pull Requests",
            ""
        ]
        
        for pr in pr_list:
            # Пропускаем сам epic PR
            if pr.number == pr_number:
                continue
            
            if pr.state == "closed" and pr.merged:
                status = "merged"
                state_text = "merged"
            elif pr.state == "closed":
                status = "closed"
                state_text = "closed"
            else:
                status = "open"
                state_text = "open"
            
            pr_url = pr.html_url
            pr_title = pr.title
            
            description_lines.append(
                f"- [{status}] [#{pr.number} - {pr_title}]({pr_url}) - `{state_text}`"
            )
            
            print(f"   {status} #{pr.number}: {pr_title} ({state_text})")
        
        # Добавляем оригинальное описание, если оно было
        original_body = epic_pr.body or ""
        if original_body and "## Связанные Pull Requests" not in original_body:
            description_lines.append("")
            description_lines.append("---")
            description_lines.append("")
            description_lines.append("## Оригинальное описание")
            description_lines.append("")
            description_lines.append(original_body)
        
        new_description = "\n".join(description_lines)
        
        # Обновляем описание PR
        epic_pr.edit(body=new_description)
        
        print(f"\nОписание epic PR обновлено!")
        print("=" * 60 + "\n")
    
    except GithubException as e:
        print(f"Ошибка обновления epic PR: {e}")
        sys.exit(1)


def main():
    """Главная функция"""
    args = parse_arguments()
    
    print("\nGitHub PR Checker")
    print(f"Repository: {args.repo}")
    if args.pr_number:
        print(f"PR Number: {args.pr_number}")
    
    # Подключаемся к GitHub
    try:
        auth = Auth.Token(args.token)
        gh = Github(auth=auth)
        
        # Проверяем подключение через репозиторий
        repo = gh.get_repo(args.repo)
        print(f"Успешное подключение к GitHub (repo: {repo.full_name})")
    except Exception as e:
        print(f"Ошибка подключения к GitHub: {e}")
        sys.exit(1)
    
    # Выполняем запрошенные действия
    if args.list_members:
        list_project_members(gh, args.repo)
    
    if args.check_size:
        if not args.pr_number:
            print("Ошибка: для --check-size требуется --pr-number")
            sys.exit(1)
        check_pr_size(gh, args.repo, args.pr_number)
    
    if args.update_epic:
        if not args.pr_number:
            print("Ошибка: для --update-epic требуется --pr-number")
            sys.exit(1)
        update_epic_description(gh, args.repo, args.pr_number)
    
    print("Все проверки пройдены успешно!\n")


if __name__ == "__main__":
    main()