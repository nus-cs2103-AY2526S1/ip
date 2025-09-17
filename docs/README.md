# King User Guide

> I long to accomplish a great and noble task, but it is my chief duty to accomplish small tasks as if they were great
> and noble. ~ Helen
> Keller ([source](https://www.goodreads.com/quotes/299032-i-long-to-accomplish-a-great-and-noble-task-but))

![Image of GUI of King Chatbot](Ui.png)

The King is there to assist you with your *small-task management 24/7* ⏱️. With the following features:

- Manage todo tasks, deadlines and events
- Search for tasks
- Track completion
- Sort by priority

## Getting help: `help`

Shows a message explaining the commands that can be used with the King.

Command Format: `help`

## Displaying all tasks: `list [/sorted]`

Lists all the tasks in your task list. Adding `/sorted` returns your task list sorted by priority.

Command format: `list` or `list /sorted`

## Adding tasks: `todo`, `deadline`, `event`

The King allows you to add three types of tasks: todos, deadlines and events.

- Todos are basic tasks with a priority.
- Deadlines are tasks that have to be completed by a date.
- Events are tasks that have a certain period.

Command Format:

- Todo format: `todo DESCRIPTION /priority PRIORITY`
- Deadline format: `deadline DESCRIPTION /priority PRIORITY /by YYYY-MM-DD`
- Event format: `event DESCRIPTION /priority PRIORITY /from YYYY-MM-DD /to YYYY-MM-DD`

Sample Output:

```
It is decreed! I have added this task to thy realm: 
[E][ ][Very High] SoC Career Fair (from: 23 Oct 2025 to: 10 Jan 2026) 
Thou now hold 5 tasks in thy service.
```

Examples:

- `todo CS2103 iP /priority M`
- `deadline CS2103 tP /priority H /by 2025-12-10`
- `event SoC Career Fair /priority VH /from 2025-10-23 /to 2026-01-10`

## Finding tasks by deadline: `due`

The King allows you to search for tasks by deadline too.

Command Format: `due YYYY-MM-DD`

Sample Output:

```
Behold! These are the tasks that dwell within thy service: 
1. [D][ ][Low] Submit CS2103 iP (by: 31 Dec 2025) 
```

Examples:

- `due 2025-11-28`

## Finding tasks by description: `find`

The King allows you to search for tasks with multiple string matching.

Command Format: `find SEARCH_1 [SEARCH_2...]`

Sample Output:

```
Lo! These tasks dost match thy search:
1. [D][ ][Low] Submit CS2103 iP (by: 31 Dec 2025) 
4. [E][X][Very High] Submit CS2103 tP (by: 31 Dec 2025)
5. [E][ ][High] SoC Career Fair (from: 23 Nov 2025 to: 31 Dec 2025)
Thou now hold 5 tasks in thy service.
```

Examples:

- `find SoC CS2103`

## Mark / Unmark completion: `mark`, `unmark`

The King lets you mark the completion of any type of task at the index specified.

Command Format:

- `mark INDEX`
- `unmark INDEX`

Sample Output:

```
Tasks decreed for 31 Dec 2025 are as follows:
3. [D][X][Low] Submit CS2103 iP (by: 31 Dec 2025)
```

Example:

- `mark 3`

## Delete task: `delete`

The King lets you delete tasks you no longer need.

Command Format:

- `delete INDEX`

Sample Output:

```
By my command, this task is struck from the royal record.
[E][ ][High] SoC Career Fair (from: 23 Nov 2025 to: 23 Dec 2025)
Thou now hold 4 tasks in thy service.
```

Example:

- `delete 5`

## Clear tasks: `clear`

The King lets you clear all tasks from your task list.

Command Format:

- `clear`

Sample Output:

```
By my will, all 4 tasks have been swept clean from thy list.
```

## Exiting the program: `bye`

Exits the conversation with King.

Command Format:

- `bye`

