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
    
    return parser.parse_args()

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