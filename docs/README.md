# Sengernest User Guide

![Product Screenshot Placeholder](Ui.png)

## Introduction 

Sengernest is a task management application that helps you keep track of your **todos**, **deadlines**, and **events**.  
It supports persistent storage, so your tasks will be saved automatically and reloaded the next time you launch the app.  
The app uses a simple command-line interface, making it fast and lightweight to use.

---

## Adding todos

You can add tasks to be done using the `todo` command.

Format: todo \<description>

Example: `todo read a book`

The task will be added to your list with the todo's type and description:

```
Your List:
1. [T][ ] read a book
```
## Adding deadlines

You can add tasks that must be completed before a specific date and time using the `deadline` command.

Format: deadline \<description> /by <yyyy-MM-dd HHmm>

Example: `deadline submit report /by 2025-09-20 2359`

The task will be added to your list with the deadline's type, description, date and time:

```
Your List:
1. [D][ ] submit report (by: Sep 20 2025 11:59pm)
```

## Adding events 

You can add tasks that start and end at a specific date and time using the `event` command.

Format: event \<description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>

Example: `event cs2103T tutorial /from 2025-09-20 1200 /to 2025-09-20 1300`

The task will be added to your list with the event's type, description, date and time:

```
Your List:
1. [E][ ] cs2103T tutorial (from: Sep 20 2025 12:00pm to Sep 20 2025 1:00pm)
```

## Adding duplicate tasks 

Sengernest will prevent you from adding new duplicate tasks with the same task description and date and time (for
events and deadlines) by throwing an error message.

Example: `event cs2103T tutorial /from 2025-09-20 1200 /to 2025-09-20 1300`

An error message will be shown:

```
[Error] This task already exists!
```

## Deleting tasks

You can delete tasks in your list using the `delete` command.

Format: delete \<task number>

Example: `delete 1`

The task will be deleted and the updated list will be shown (assuming you only had one task in the list):

```
Your List is empty!
```

## Marking tasks as done

You can mark tasks as done by using the `mark` command.

Format: mark \<task number>

Example: `mark 1`

The task will be marked as done and the updated list will be shown:

```
Your List:
1. [T][X] read a book
```

## Unmarking tasks 

You can unmark tasks by using the `unmark` command.

Format: unmark \<task number>

Example: `unmark 1`

The task will be unmarked and the updated list will be shown:

```
Your List:
1. [T][] read a book
```

## Finding tasks

You can find tasks by keywords using the `find` command.

Format: find \<keywords>

Example: `find read`

Tasks matched with the keywords will be shown:

```
Here are the matching tasks:
1. [T][] read a book
```

## Listing tasks

You can see your task list by keywords using the `list` command.

Format: list

Example: `list`

Tasks matched with the keywords will be shown:

```
Your List:
1. [T][] read a book
```

