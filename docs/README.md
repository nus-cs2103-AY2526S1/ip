## nailongbot User Guide

nailongbot is a simple task manager chatbot. It helps you keep track of your tasks via commands typed into the app.

This guide covers installation, basic usage, and all available features with examples and expected outputs.

### Quick start

- Ensure you have Java 17 installed.
- Download the release JAR (`ip.jar` or `nailongbot.jar`).
- Run from a terminal in the JAR directory:

```bash
java -jar ip.jar
```

You should see the welcome message and a prompt. Type commands and press Enter.

### Data storage

- Tasks are saved automatically to `data/saved_tasks.txt` in the app directory when the app exits normally.
- On next start, nailongbot loads tasks from that file. Corrupted lines are skipped.

### Command overview

- `list`: Show all tasks
- `todo <DESCRIPTION>`: Add a todo task
- `deadline <DESCRIPTION> /by <WHEN>`: Add a deadline (WHEN format: d/M/yyyy HHmm)
- `event <DESCRIPTION> /from <START> /to <END>`: Add an event (START/END format: d/M/yyyy HHmm)
- `mark <INDEX>`: Mark a task as done
- `unmark <INDEX>`: Mark a task as not done
- `delete <INDEX>`: Delete a task
- `find <KEYWORDS>`: Find tasks containing keyword(s)
- `show <d/M/yyyy>`: Show tasks occurring on a specific date
- `sort`: Sort tasks (by upcomming task)
- `bye`: Saves tasks and closes the app

Notes:

- `INDEX` is 1-based in the UI. Internally it is converted to 0-based.
- Dates for `show` use day/month/year, e.g. `28/8/2025`.
- `WHEN`, `START`, `END` must use format `d/M/yyyy HHmm`, e.g. `28/8/2025 1800` for 6:00 PM.

---

## Features

### List tasks: `list`

Shows all tasks in the list in their current order.

Example:

```text
list
```

Expected output (format may vary):

```text
________________________________
[1] [T][ ] read book
[2] [D][ ] submit paper (by: 2025-08-28 23:59)
[3] [E][ ] conference (from: 2025-08-28 09:00 to: 2025-08-29 17:00)
________________________________
```

### Add a todo: `todo DESCRIPTION`

Adds a todo task.

Example:

```text
todo read book
```

Expected output:

```text
________________________________
Added: [T][ ] read book
You now have 1 task in the list.
________________________________
```

### Add a deadline: `deadline DESCRIPTION /by WHEN`

Adds a deadline task with a due time/date.

Example:

```text
deadline submit paper /by 28/8/2025 2359
```

Expected output:

```text
________________________________
Added: [D][ ] submit paper (by: 28/8/2025 2359)
You now have 2 tasks in the list.
________________________________
```

### Add an event: `event DESCRIPTION /from START /to END`

Adds an event spanning a start and end time.

Example:

```text
event conference /from 28/8/2025 0900 /to 29/8/2025 1700
```

Expected output:

```text
________________________________
Added: [E][ ] conference (from: 28/8/2025 0900 to: 29/8/2025 1700)
You now have 3 tasks in the list.
________________________________
```

### Mark a task: `mark INDEX`

Marks the task at the given 1-based index as done.

Example:

```text
mark 1
```

Expected output:

```text
________________________________
Nice! I have marked this task as done:
[T][X] read book
________________________________
```

### Unmark a task: `unmark INDEX`

Marks the task at the given 1-based index as not done.

Example:

```text
unmark 1
```

Expected output:

```text
________________________________
OK, I have marked this task as not done yet:
[T][ ] read book
________________________________
```

### Delete a task: `delete INDEX`

Removes the task at the given 1-based index.

Example:

```text
delete 2
```

Expected output:

```text
________________________________
Removed: [D][ ] submit paper (by: 28/8/2025 2359)
You now have 2 tasks in the list.
________________________________
```

### Find tasks: `find KEYWORDS`

Finds tasks whose description contains the keyword(s). Matching details depend on implementation (typically case-insensitive substring match).

Example:

```text
find conf
```

Expected output:

```text
________________________________
[1] [E][ ] conference (from: 28/8/2025 0900 to: 29/8/2025 1700)
________________________________
```

### Show tasks on a date: `show d/M/yyyy`

Lists deadlines due on the given date and events occurring on that date.

Example:

```text
show 28/8/2025
```

Expected output when tasks exist:

```text
________________________________
[D][ ] submit paper (by: 28/8/2025 2359)
[E][ ] conference (from: 28/8/2025 0900 to: 29/8/2025 1700)
________________________________
```

When no tasks/events fall on that date:

```text
________________________________
No tasks/events found on 28/8/2025
________________________________
```

### Sort tasks: `sort`

Sorts tasks. The exact criteria and output depend on the app’s `SortCommand` implementation (e.g., by date then description). Use `list` after `sort` to view the new order.

Example:

```text
sort
list
```

### Exit: `bye`

Saves your tasks and exits the app.

Example:

```text
bye
```

Expected:

```text
________________________________
Bye. Hope to see you again soon!
________________________________
```

---

## Error handling and tips

- If you omit required parts, nailongbot will show a helpful message, e.g., `Error: Please specify - deadline [description] /by [time]`.
- Use the exact tokens `/by`, `/from`, `/to` in `deadline` and `event` commands.
- Indices are positive integers as shown by `list` (1, 2, 3, ...).
- Extra spaces are generally tolerated between the command and arguments.

## FAQ

- Where is my data? → In `data/saved_tasks.txt` relative to where you run the app.
- How do I reset? → Exit the app, then delete `data/saved_tasks.txt`. Start the app again.
