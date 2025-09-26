# Winnie User Guide

Winnie is a task management chatbot that helps you organize your todos, deadlines, and events. You can interact with Winnie through both command-line interface (CLI) and graphical user interface (GUI).

## Quick Start

1. Start the application
2. Type commands to manage your tasks
3. Type `bye` to exit

## Features

### Adding Tasks

#### Adding Todos
Add simple tasks without any time constraints.

**Format:** `todo DESCRIPTION`

**Example:** `todo read book`

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

#### Adding Deadlines
Add tasks that need to be completed by a specific date/time.

**Format:** `deadline DESCRIPTION /by DATE_TIME`

**Example:** `deadline submit assignment /by 2023-12-25 23:59`

```
Got it. I've added this task:
  [D][ ] submit assignment (by: Dec 25 2023, 23:59)
Now you have 2 tasks in the list.
```

#### Adding Events
Add events that occur during a specific time period.

**Format:** `event DESCRIPTION /from START_TIME /to END_TIME`

**Example:** `event team meeting /from 2023-12-20 14:00 /to 2023-12-20 16:00`

```
Got it. I've added this task:
  [E][ ] team meeting (from: Dec 20 2023, 14:00 to: Dec 20 2023, 16:00)
Now you have 3 tasks in the list.
```

### Managing Tasks

#### Listing All Tasks
View all your current tasks.

**Format:** `list`

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit assignment (by: Dec 25 2023, 23:59)
3.[E][ ] team meeting (from: Dec 20 2023, 14:00 to: Dec 20 2023, 16:00)
```

#### Marking Tasks as Done
Mark a task as completed.

**Format:** `mark TASK_NUMBER`

**Example:** `mark 1`

```
Nice! I've marked this task as done:
  [T][X] read book
```

#### Unmarking Tasks
Mark a completed task as not done.

**Format:** `unmark TASK_NUMBER`

**Example:** `unmark 1`

```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

#### Deleting Tasks
Remove a task from your list.

**Format:** `delete TASK_NUMBER`

**Example:** `delete 2`

```
Noted. I've removed this task:
  [D][ ] submit assignment (by: Dec 25 2023, 23:59)
Now you have 2 tasks in the list.
```

### Advanced Features

#### Finding Tasks
Search for tasks containing specific keywords.

**Format:** `find KEYWORD`

**Example:** `find book`

```
Here are the matching tasks in your list:
1.[T][ ] read book
```

#### Snoozing Tasks
Postpone a task to a later date/time.

**Format:** `snooze TASK_NUMBER NEW_DATE_TIME`

**Example:** `snooze 1 2023-12-26 10:00`

```
I've snoozed this task:
  [T][ ] read book (snoozed until: Dec 26 2023, 10:00)
```

#### Exiting the Application

**Format:** `bye`

```
Bye. Hope to see you again soon!
```

## Task Symbols

- `[T]` - Todo task
- `[D]` - Deadline task
- `[E]` - Event task
- `[ ]` - Task not completed
- `[X]` - Task completed

## Date/Time Format

Use the format: `YYYY-MM-DD HH:MM`

**Examples:**
- `2023-12-25 14:30`
- `2024-01-01 09:00`

## Tips

- Task numbers start from 1
- Commands are case-insensitive
- Your tasks are automatically saved and will be restored when you restart the application
