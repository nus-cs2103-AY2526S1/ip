# Aurora User Guide

![Aurora Chatbot Screenshot](docs/screenshot.png)

Aurora is a simple chatbot application designed to help users manage tasks via a command-line or GUI interface.  
It supports adding todos, deadlines, events, tagging, searching, and more.
Tasks are automatically saved after sessions and loaded before sessions. 

## Command Format

- Words in _UPPER_CASE_ are parameters to be supplied by the user.
    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is the parameter.
- Commands support both Date and Datetime format, 
you must follow one of the below formats for Aurora to correctly parse your Deadline or Event datetime.
- Supported Date formats:
  - `yyyy-MM-dd`
  - `dd/MM/yyyy`
  - `ddMMyy`
- Supported Datetime formats:
  - `yyyy-MM-dd HHmm`
  - `yyyy-MM-dd HH:mm`
  - ``yyyy-MM-dd`T`HH:mm``
  - `yyyy-MM-dd HH:mm:ss`
  - `dd/MM/yyyy HHmm`
  - `dd/MM/yyyy HH:mm`
  - `dd/MM/yyyy HH:mm:ss`

## Available commands:

- `todo DESCRIPTION`
- `deadline DESCRIPTION /by: DEADLINE`
- `event DESCRIPTION /from: START /to: END`
- `list`
- `mark TASK_NUMBER`
- `delete TASK_NUMBER`
- `find KEYWORD`
- `tag TASK_NUMBER TAG`
- `untag TASK_NUMBER TAG`
- `help`

## Adding todos

Adds a simple todo task to your list. 

Example: `todo Read a book`

Outcome:
```
Added Read a book as a task into your list.
```

## Adding deadlines

Adds a task with a deadline to your list

Example: `deadline Math quiz /by: 121225`

Outcome:
```
Added Math quiz as a task into your list.
```

## Adding events

Adds an event with a start and end time to your list.

Example: `event Project meeting /from: 121225 /to: 131225`

Outcome:
```
Added Project meeting as a task into your list.
```


## List

Displays all tasks currently in your list.

Command: `list`

Example Outcome:
```
Here are the tasks in your list:
1. [T][ ] Read a book
2. [D][ ] Math quiz (by: Dec 12 2025)
3. [E][ ] Project meeting (from: Dec 12 2025 to: Dec 13 2025)
```

## Mark

Marks a task as completed.

Example: `mark 1`

Outcome:
```
Nice! I've marked this task as completed.
[T][X] Read a book
```

## Delete

Removes a task from your list.

Example `delete 1`

Outcome:
```
Noted. I've removed this task:
[T][X] Read a book
Now you have 2 tasks in your list.
```

## Finding tasks

Finds all tasks containing the given keywords. Works for finding tags as well.

Example: `find Math`

Outcome:
```
Here are the matching tasks in your list:
1.[D][ ] Math quiz (by: Dec 12 2025)
```

## Tagging tasks

Adds a tag to tasks for easier categorisation.

Example: `tag 1 math`

Outcome: 
```
Great. I've tagged this task as math.
[D][ ] Math quiz #math (by: Dec 12 2025)
```

## Untagging

Removes tag from a task.

Example: `untag 1 math`

Outcome:
```
Great. This task is no longer tagged math.
[D][ ] Math quiz (by: Dec 12 2025)
```

## Help

List out all available commands and formats for user.

Command: `help`
