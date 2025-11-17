# Pingu User Guide

![](Ui.png)

Pingu is a **task management application** that helps you organize your todos, deadlines, and events. Built with Java, it features persistent storage and an intuitive command interface for managing your daily tasks.

## Features

- **Three task types**: Todo, Deadline, and Event tasks
- **Persistent storage**: Automatically saves tasks to file
- **Search functionality**: Find tasks by keyword
- **Simple commands**: Easy-to-remember command syntax
- **Status tracking**: Mark tasks as done or undone

## Getting Started

1. Run the application: `./gradlew run`
2. Start adding tasks and managing your schedule!

## Adding Todos

Create simple todo tasks without any time constraints.

Example: `todo read book`

Adds a basic task to your list that you can mark as complete later.

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

## Adding Deadlines

Create tasks with specific deadlines using the `/by` keyword.

Example: `deadline submit report /by 2/12/2019 1800`

Adds a task with a deadline date and time. The date format is `d/M/yyyy HHmm`.

```
Got it. I've added this task:
  [D][ ] submit report (by: Dec 02 2019, 6:00 PM)
Now you have 2 tasks in the list.
```

## Adding Events

Create tasks that span a time period using `/from` and `/to` keywords.

Example: `event team meeting /from 2/12/2019 1400 /to 2/12/2019 1600`

Adds an event with both start and end times.

```
Got it. I've added this task:
  [E][ ] team meeting (from: Dec 02 2019, 2:00 PM to: Dec 02 2019, 4:00 PM)
Now you have 3 tasks in the list.
```

## Viewing Tasks

Display all your tasks in a numbered list.

Example: `list`

Shows all tasks with their types, completion status, and details.

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit report (by: Dec 02 2019, 6:00 PM)
3.[E][ ] team meeting (from: Dec 02 2019, 2:00 PM to: Dec 02 2019, 4:00 PM)
```

## Marking Tasks

Mark tasks as completed using their list number.

Example: `mark 1`

Marks the first task in your list as done.

```
Nice! I've marked this task as done:
  [T][X] read book
```

## Unmarking Tasks

Mark completed tasks as incomplete again.

Example: `unmark 1`

Changes a completed task back to incomplete status.

```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

## Deleting Tasks

Remove tasks from your list permanently.

Example: `delete 2`

Removes the second task from your list.

```
Noted. I've removed this task:
  [D][ ] submit report (by: Dec 02 2019, 6:00 PM)
Now you have 2 tasks in the list.
```

## Finding Tasks

Search for tasks containing specific keywords.

Example: `find book`

Searches through all task descriptions for the specified keyword (case-insensitive).

```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
```

## Data Persistence

Your tasks are automatically saved to `data/pingu.txt` after every change. The application will:

- Create the data directory if it doesn't exist
- Load your previous tasks when you restart the application
- Handle corrupted data gracefully with warning messages

## Exiting the Application

End your session and return to the command line.

Example: `bye`

Saves all tasks and closes the application.

```
Bye. Hope to see you again soon!
```

## Command Summary

| Command    | Format                                        | Description               |
| ---------- | --------------------------------------------- | ------------------------- |
| `todo`     | `todo <description>`                          | Add a basic task          |
| `deadline` | `deadline <description> /by <date time>`      | Add task with deadline    |
| `event`    | `event <description> /from <start> /to <end>` | Add event with time range |
| `list`     | `list`                                        | Show all tasks            |
| `mark`     | `mark <task number>`                          | Mark task as done         |
| `unmark`   | `unmark <task number>`                        | Mark task as not done     |
| `delete`   | `delete <task number>`                        | Remove task               |
| `find`     | `find <keyword>`                              | Search tasks by keyword   |
| `bye`      | `bye`                                         | Exit application          |

## Date Format

All dates and times use the format: `d/M/yyyy HHmm`

Examples:

- `2/12/2019 1800` (December 2nd, 2019 at 6:00 PM)
- `15/1/2020 0900` (January 15th, 2020 at 9:00 AM)
