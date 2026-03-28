# Sanbot User Guide

Sanbot is your personal task management chatbot that helps you organize your to-dos, deadlines, and events efficiently. Simply chat with Sanbot using natural commands to manage your tasks seamlessly.

## Quick Start

1. Start chatting with Sanbot by typing any of the commands below
2. Type `bye` when you're done to close the application
3. Your tasks are automatically saved locally

![Sanbot Interface](Ui.png)

## Command Format Notes

* Words in `UPPER_CASE` are parameters you need to provide. e.g. in `todo TASK_DESCRIPTION`, you replace `TASK_DESCRIPTION` with your actual task.
* Items in angle brackets `< >` are required parameters that must be provided.
* Date format must be `dd-MM-yyyy HH:mm` (e.g., `25-12-2024 14:30` for December 25, 2024, at 2:30 PM).

## Features

### Viewing All Tasks

Lists all your current tasks with their status and details.

**Command:** `list`

### Adding To-Do Tasks

Creates a simple task without any time constraints.

**Command:** `todo <TASK_DESCRIPTION>`

### Adding Tasks with Deadlines

Creates a task that must be completed by a specific date and time.

**Command:** `deadline <TASK_DESCRIPTIONn> /by <dd-MM-yyyy HH:mm>`

### Adding Event Tasks

Creates a task that occurs within a specific time period.

**Command:** `event <TASK_DESCRIPTION> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>`

### Marking Tasks as Done

Marks a task as completed using its position in the list.

**Command:** `mark <TASK NO.>`

### Marking Tasks as Not Done

Marks a previously completed task as incomplete.

**Command:** `unmark <TASK NO.>`

### Deleting Tasks

Permanently removes a task from your list.

**Command:** `delete <TASK NO.>`

### Finding Tasks

Searches for tasks containing a specific keyword (case-insensitive).

**Command:** `find <KEYWORD>`

### Saving and Exiting

Saves your current task list locally and closes the Sanbot window.

**Command:** `bye`

## Task Status Indicators

* `[T]` - To-do task
* `[D]` - Deadline task
* `[E]` - Event task
* `[X]` - Task completed
* `[ ]` - Task not completed yet

## Tips

* Task numbers change when you delete tasks, so always check with `list` first
* Use the `find` command to quickly locate specific tasks in a long list
* Your tasks are automatically saved upon exit, so you won't lose your progress between sessions