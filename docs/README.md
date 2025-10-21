## User Guide

Geegar is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) within a friendly GUI. 
If you can type fast, Geegar will then be your partner in crime when it comes to managing todos, deadlines, and events 
fast and effectively.

This documentation used Cursor to follow closely the documentation style similiar to the
AB3 User Guide's features section. See reference: [AB-3 User Guide — Features](https://se-education.org/addressbook-level3/UserGuide.html#features).

- Quick start
- Features
  - Listing tasks: list
  - Adding a todo: todo
  - Adding a deadline: deadline
  - Adding an event: event
  - Marking a task as done: mark
  - Unmarking a task: unmark
  - Deleting a task: delete
  - Finding tasks by keyword: find
  - Viewing schedule on a date: on
  - Updating an existing task: update
  - Exiting the app: bye/exit
  - Saving the data
  - Editing the data file
- Command summary

## Quick start

1. Ensure you have Java `17` or above installed on your computer.  
   Mac users: ensure you have the precise JDK version prescribed by your setup.
2. Build or download the latest `geegar.jar` (e.g., located at `build/libs/geegar.jar`).
3. Open a terminal, `cd` into the folder containing the jar, then run:  
   `java -jar geegar.jar`
4. A GUI should appear. Type commands into the input box and press Enter to execute.
5. Some example commands you can try:
   - `list` — lists all tasks
   - `todo read CS2103T textbook` — adds a todo task
   - `deadline submit iP /by 27/8/2025 2359` — adds a deadline
   - `event team sync /from 27/8/2025 1800 /to 27/8/2025 1900` — adds an event
   - `bye` — exits the app

---

## Features

Notes about the command format:

- Words in UPPER_CASE are parameters to be supplied by you.  
  e.g., in `mark INDEX`, `INDEX` is a parameter which can be used as `mark 2`.
- Items in square brackets are optional.  
  e.g., optional variations will be documented as needed.
- Parameters are space-separated.  
  e.g., `deadline DESCRIPTION /by d/M/yyyy HHmm`.
- Index refers to the number shown in the current displayed task list, starting from 1.

### Listing tasks: `list`

Shows all tasks currently in the list.

Format: `list`

### Adding a todo: `todo`

Adds a todo task.

Format: `todo DESCRIPTION`

Examples:

- `todo read CS2103T textbook`

### Adding a deadline: `deadline`

Adds a deadline task with a due date and time.

Format: `deadline DESCRIPTION /by d/M/yyyy HHmm`

Examples:

- `deadline submit iP /by 27/8/2025 2359`

### Adding an event: `event`

Adds an event task with a start and end date/time.

Format: `event DESCRIPTION /from d/M/yyyy HHmm /to d/M/yyyy HHmm`

Examples:

- `event team sync /from 27/8/2025 1800 /to 27/8/2025 1900`

### Marking a task as done: `mark`

Marks the specified task as done.

Format: `mark INDEX`

Examples:

- `list` followed by `mark 1` marks the 1st task in the list as done.

### Unmarking a task: `unmark`

Marks the specified task as not done.

Format: `unmark INDEX`

Examples:

- `list` followed by `unmark 1` marks the 1st task as not done.

### Deleting a task: `delete`

Deletes the specified task.

Format: `delete INDEX`

Examples:

- `list` followed by `delete 2` deletes the 2nd task in the list.

### Finding tasks by keyword: `find`

Finds tasks whose description contains the given keyword (case-insensitive, substring match).

Format: `find KEYWORD`

Examples:

- `find ip` returns tasks with descriptions containing “ip”, “IP”, etc.

### Viewing schedule on a date: `on`

Lists deadlines due on, and events occurring on, the given date.

Format: `on d/M/yyyy`

Examples:

- `on 27/8/2025`

### Updating an existing task: `update`

Updates a task’s description and/or dates depending on its type.

Formats:

- Todo: `update INDEX NEW_DESCRIPTION`
- Deadline: `update INDEX NEW_DESCRIPTION /by d/M/yyyy HHmm`
- Event: `update INDEX NEW_DESCRIPTION /from d/M/yyyy HHmm /to d/M/yyyy HHmm`

Examples:

- `update 1 read CS2103T textbook chapter 2`
- `update 2 submit iP v2 /by 28/8/2025 2359`
- `update 3 project sync /from 28/8/2025 1800 /to 28/8/2025 1900`

### Exiting the app: `bye` or `exit`

Exits the app.

Format: `bye`  
Alternative commands: `exit`, `quit`

### Saving the data

Task data are saved to disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Task data are saved as a text file at `data/tasks.txt` (relative to the working directory). Advanced users can edit this file directly, but do so with care; invalid edits may cause tasks to load incorrectly on the next run.

---

## Command summary

| Action       | Format, Examples                                                                                                                                           |
| ------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------- |
| List         | `list`                                                                                                                                                     |
| Add Todo     | `todo DESCRIPTION` e.g., `todo read CS2103T textbook`                                                                                                      |
| Add Deadline | `deadline DESCRIPTION /by d/M/yyyy HHmm` e.g., `deadline submit iP /by 27/8/2025 2359`                                                                     |
| Add Event    | `event DESCRIPTION /from d/M/yyyy HHmm /to d/M/yyyy HHmm` e.g., `event team sync /from 27/8/2025 1800 /to 27/8/2025 1900`                                  |
| Mark         | `mark INDEX` e.g., `mark 1`                                                                                                                                |
| Unmark       | `unmark INDEX` e.g., `unmark 1`                                                                                                                            |
| Delete       | `delete INDEX` e.g., `delete 2`                                                                                                                            |
| Find         | `find KEYWORD` e.g., `find ip`                                                                                                                             |
| Schedule     | `on d/M/yyyy` e.g., `on 27/8/2025`                                                                                                                         |
| Update       | `update INDEX NEW_DESCRIPTION` or `update INDEX NEW_DESCRIPTION /by d/M/yyyy HHmm` or `update INDEX NEW_DESCRIPTION /from d/M/yyyy HHmm /to d/M/yyyy HHmm` |
| Exit         | `bye` (also accepts `exit`, `quit`)                                                                                                                        |
