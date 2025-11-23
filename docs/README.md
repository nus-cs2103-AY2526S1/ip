# Clam User Guide

![Image of Clam chatbot GUI](Ui.png)

Clam is a personal task manager for anyone to use! It's designed to help you keep track of your tasks and deadlines in a simple and efficient way.

It can:
- Manage tasks in three categories: ToDo, Deadline, Event
- List all tasks
- Mark tasks as done
- Delete tasks
- Find tasks based on a provided search query
- Save your tasks to a local file

## Getting help

Shows a message explaining the commands that can be used in Clam.

Command Format: `help [name of command (optional)]`

## Displaying the task list

Lists all the tasks in your task list.

Command Format: `list`

## Adding todos

Adds a todo task to your task list.

Command Format: `todo [description]`

Sample Output:
```
Got it. I've added this task:
    [T][ ] read book
Now you have 1 tasks in the list.
``` 

## Adding deadlines

Adds a deadline task to your task list.

Command Format: `deadline [description] /by [due date]`

`[due date]` should be in the format `YYYY-MM-DD` (e.g., `2023-12-31`).

Sample Output:
```
Got it. I've added this task:
    [D][ ] submit assignment (by: Dec 31 2023)
Now you have 2 tasks in the list.
```

## Adding events

Adds an event task to your task list.

Command Format: `event [description] /from [start date] /to [end date]`

`[start date]` and `[end date]` should be in the format `YYYY-MM-DD` (e.g., `2023-12-31`).

Sample Output:
```
Got it. I've added this task:
    [E][ ] book fair (from: Dec 20 2023 to: Dec 21 2023)
Now you have 3 tasks in the list.
```

## Marking tasks as done/undone

Marks a task as done or undone using the index provided.

Command Format: `mark [index]` or `unmark [index]`

Sample Output:
```
Nice! I've marked this task as done:
    [T][X] read book
``` 

## Finding tasks

Finds tasks that match the provided search query. The result will be displayed as a list. The search is case-sensitive.

Command Format: `find [search query]`

Sample Output:
```
Here are the matching tasks in your list:
1.[T][X] read book
2.[E][ ] book fair (from: Dec 20 2023 to: Dec 21 2023)
```

## Deleting tasks

Deletes a task from your task list using the index provided.

Command Format: `delete [index]`

Sample Output:
```
I've deleted this task:
    [T][X] read book
Now you have 2 tasks in the list.
```

## Exiting the program

Exits the program.

Command Format: `bye`

## Printing debug info (for developers)

Prints debug information to help with development and troubleshooting.

Currently provides:
- Java version of the app instance
- File path of the save file

Command Format: `debug`