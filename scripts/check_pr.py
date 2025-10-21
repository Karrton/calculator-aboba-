#!/usr/bin/env python3
"""
GitHub PR Checker Script
Проверяет размер PR в зависимости от типа и обновляет описание epic PR
"""

import argparse
import sys
from typing import List, Dict, Optional
from github import Github, GithubException


class PRType:
    FEATURE = ("feature", 300)
    REFACTOR = ("refactor", 400)
    BUGFIX = ("bugfix", 150)
    EPIC = ("epic", None)


def parse_arguments():
    """Парсинг аргументов командной строки"""
    parser = argparse.ArgumentParser(
        description='GitLab MR Checker - проверка размера MR и работа с epic'
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
        '--list-members',
        action='store_true',
        help='Вывести список участников проекта'
    )
    parser.add_argument(
        '--mr-iid',
        required=True,
        type=int,
        help='IID текущего Merge Request'
    )
    parser.add_argument(
        '--check-size',
        action='store_true',
        help='Проверять размер MR'
    )
    
    return parser.parse_args()

def get_mr_type(branch_name: str) -> tuple:
    """
    Определяет тип MR по имени ветки
    
    Args:
        branch_name: Имя ветки (например: feature/my-feature)
    
    Returns:
        tuple: (тип, лимит_строк)
    """
    branch_lower = branch_name.lower()
    
    if 'epic/' in branch_lower:
        return MRType.EPIC
    elif 'feature/' in branch_lower:
        return MRType.FEATURE
    elif 'refactor/' in branch_lower:
        return MRType.REFACTOR
    elif 'bugfix/' in branch_lower or 'fix/' in branch_lower:
        return MRType.BUGFIX
    else:
        # По умолчанию считаем feature
        return MRType.FEATURE

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
            permission = repo.get_collaborator_permission(member)
            
            print(f"\n{member.name or member.login} (@{member.login})")
            print(f"   Email: {member.email or 'N/A'}")
            print(f"   Роль: {permission}")
            print(f"   Profile: {member.html_url}")
        
        print("\n" + "=" * 60)
        print(f"Всего участников: {len(members_list)}")
        print("=" * 60 + "\n")
        
    except GithubException as e:
        print(f"Ошибка получения участников: {e}")
        sys.exit(1)

def check_mr_size(gl, project_id: int, mr_iid: int):
    """
    Проверяет размер MR и падает с ошибкой, если превышен лимит
    
    Args:
        gl: GitLab connection object
        project_id: ID проекта
        mr_iid: IID merge request
    """
    print("\n" + "=" * 60)
    print("ПРОВЕРКА РАЗМЕРА MERGE REQUEST")
    print("=" * 60)
    
    try:
        project = gl.projects.get(project_id)
        mr = project.mergerequests.get(mr_iid)
        
        # Получаем информацию о MR
        source_branch = mr.source_branch
        target_branch = mr.target_branch
        title = mr.title
        
        print(f"\n📋 MR Info:")
        print(f"   Title: {title}")
        print(f"   Source: {source_branch}")
        print(f"   Target: {target_branch}")
        
        # Определяем тип MR
        mr_type, line_limit = get_mr_type(source_branch)
        print(f"   Type: {mr_type[0]}")
        
        # Для epic MR нет лимита
        if mr_type == MRType.EPIC:
            print(f"Epic MR - лимит строк не применяется")
            print("=" * 60 + "\n")
            return
        
        print(f"   Limit: {line_limit} lines")
        
        # Получаем изменения
        changes = mr.changes()
        total_lines = 0
        
        print(f"\n📊 Changed files:")
        for change in changes['changes']:
            file_path = change['new_path']
            diff = change['diff']
            
            # Считаем добавленные и удаленные строки
            added = diff.count('\n+')
            removed = diff.count('\n-')
            file_lines = added + removed
            total_lines += file_lines
            
            print(f"   {file_path}: +{added} -{removed} (total: {file_lines})")
        
        print(f"\n📈 Total changes: {total_lines} lines")
        print(f"   Limit: {line_limit} lines")
        
        # Проверяем лимит
        if total_lines > line_limit:
            print(f"\nFAILED: MR размер {total_lines} строк превышает лимит {line_limit} для типа '{mr_type[0]}'")
            print("=" * 60 + "\n")
            sys.exit(1)
        else:
            print(f"\nPASSED: MR размер в пределах лимита")
            print("=" * 60 + "\n")
    
    except gitlab.exceptions.GitlabGetError as e:
        print(f"❌ Ошибка получения MR: {e}")
        sys.exit(1)

def main():
    """Главная функция"""
    args = parse_arguments()
    
    print(f"Repository: {args.repo}")
    
    # Подключаемся к GitHub
    try:
        gh = Github(args.token)
        user = gh.get_user()
        print(f"Успешное подключение к GitHub (user: {user.login})")
    except Exception as e:
        print(f"Ошибка подключения к GitHub: {e}")
        sys.exit(1)
    
    # Выполняем запрошенные действия
    if args.list_members:
        list_project_members(gh, args.repo)


if __name__ == "__main__":
    main()