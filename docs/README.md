# Jaiden User Guide

![Product Screenshot](Ui.png)

Jaiden is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, Jaiden can get your tasks management tasks done
faster than traditional GUI apps.

## Listing all tasks: `list`

Shows a list of all tasks in Jaiden.

Format: `list`

## Locating tasks by name: `find`

Finds tasks whose names contain the given keyword.

Format: `find KEYWORD`
- The search is case-sensitive.
- Only the name is searched.

Example:
- `find read`

## Viewing schedule by date: `view`

Shows a list of tasks on specified date.

Format: `view DATE`
- Shows only deadline and event tasks.
- DATE has to be in YYYY-MM-DD format.

Example:
- `view 2025-09-19`

## Adding todo: `todo`

Adds a todo task to Jaiden.

Format: `todo NAME`
- NAME can be anything.
- Spaces are allowed but leading and trailing spaces will be removed.

Example:
- `todo read`

## Adding deadline: `deadline`

Adds a deadline task to Jaiden.

Format: `deadline NAME /by DEADLINE`
- NAME cannot contain the word "/by".
- Spaces are allowed but leading and trailing spaces will be removed.
- DEADLINE has to be in YYYY-MM-DD format.

Example:
- `deadline read /by 2025-09-19`

## Adding event: `event`

Adds an event task to Jaiden.

Format: `event NAME /from DATE /to DATE`
- NAME cannot contain the word "/from" or "/to".
- Spaces are allowed but leading and trailing spaces will be removed.
- DATE has to be in YYYY-MM-DD format.

Example:
- `event read /from 2025-08-11 /to 2025-09-19`

## Marking as done: `mark`

Marks the specified task as done in Jaiden.

Format: `mark INDEX`
- Marks the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …

Example:
- `mark 1`

## Unmarking as done: `unmark`

Unmarks the specified task as done in Jaiden.

Format: `unmark INDEX`
- Unmarks the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …

Example:
- `unmark 1`

## Deleting a task: `delete`

Deletes the specified task from Jaiden.

Format: `delete INDEX`
- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …

Example:
- `delete 1`

## Exiting the program: `bye`

Exits the program.

Format: `bye`

## Saving the data

Jaiden data are saved in the hard disk automatically after any command. There is no need to save
manually.