# 👾 ByteBot

![ByteBot](Ui.png)
> *Organize yourself, one task at a time*

**ByteBot** is a task management application to help you stay productive


## Quickstart

1. Ensure you have Java 17 installed.
2. Download the latest bytebot.jar file from the releases page [here](https://github.com/lhurr/ip/releases).
3. Run `java -jar bytebot.jar` in your terminal.
4. A chat window will appear where you can start typing commands.
5. Type a command and press Enter to execute it.


## Features

### List all tasks
Show all tasks in the list. It will be shown order it is added. Details like Index
- **Format**: `list`
- **Example**: `list`


### Add: Todo
Add a simple todo task with a description. By default it is not done.
- **Format**: `todo DESCRIPTION`
- **Example**: `todo study`


### Add deadlines: Deadline
Add a task that has a due date and time.
- **Format**: `deadline DESCRIPTION /by <d/M/yyyy HHmm>`
- **Example**
    - `deadline CS2100 /by 01/01/2026 1000`
    - `deadline CS2103 /by 05/05/2026 1200`

### Add events: Event
Add an event with a start and end date/time.
- **Format**: `event DESCRIPTION /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>`
- **Example**:
    - `event meeting /from 02/01/2025 1400 /to 02/01/2025 1500`
    - `event teaching /from 02/01/2025 1600 /to 02/01/2025 1900`


### Mark tasks as done
Mark a task as done by its number shown in `list`. Marked tasks will show a [X] symbol, and tasks that are already marked will stay marked
- **Format**: `mark TASK_NUMBER`
- **Example**: `mark 1`


### Unmark task as not done
Mark a task as not done by its number shown in `list`. Unmark tasks will show a [ ] symbol, and tasks that are already unmarked will stay marked 
- **Format**: `unmark TASK_NUMBER`
- **Example**: `unmark 1`


### Deleting tasks
Remove a task by its number shown in `list`.
- **Format**: `delete TASK_NUMBER`
- **Example**: `delete 3`


### Finding tasks
Search for tasks with descriptions that contain the given keyword.
- **Format**: `find KEYWORD`
- **Example**: `find report`


### Sort tasks
Display tasks in a sorted view.
- **Format**:
  - `sort deadline` or `sort deadlines` - show only deadlines, sorted by due date
  - `sort all` - show all tasks grouped by type (deadlines sorted by due date)
- **Examples**: `sort deadlines`, `sort all`


### Bye
Exit the application.
- **Format**: `bye`
- **Example**: `bye`


## FAQ

### What is the difference between a Deadline and an Event?

- Deadline: A task with a single due date/time.
- Event: A task that spans a time range with a start and end.

## Command summary


| Command | Format | Example |
|---|---|---|
| Todo | `todo <description>` | `todo study` |
| Deadline | `deadline <description> /by <d/M/yyyy HHmm>` | `deadline CS2100 /by 01/01/2026 1000` |
| Event | `event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>` | `event Meeting /from 02/01/2025 1400 /to 02/01/2025 1500` |
| List | `list` | `list` |
| Mark | `mark <task_number>` | `mark 1` |
| Unmark | `unmark <task_number>` | `unmark 1` |
| Delete | `delete <task_number>` | `delete 3` |
| Find | `find <keyword>` | `find report` |
| Sort deadlines | `sort deadline` or `sort deadlines` | `sort deadlines` |
| Sort all | `sort all` | `sort all` |
| Bye | `bye` | `bye` |

