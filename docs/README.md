# Cookie User Guide

![Screenshot of the Cookie Task Manager Gui](Ui.png)

Cookie is a state-of-the-art task manager.  
It's designed to make keeping track of your tasks a walk in the park.

## Quick Start
1. Ensure you have Java 17 installed.
2. Download the latest `cookie.jar` file from this repository.
3. Run the `java -jar cookie.jar` command in the folder containing `cookie.jar`.
4. You'll see the following welcome message:
```
Hey there! My name is Cookie
How can I help you?
```
5. Start inputting commands!

## Adding a todo: todo

A todo is a task without a date / time attached.

Format: `todo <description>`

Example: `todo sleep`

Example successful output:

```
A todo, got it! I've added this task: [T][ ] sleep
Now you have 1 task in the list.
```

## Adding a deadline: deadline

A deadline is a task with a due date / time attached.

Format: `deadline <description> /by <yyyy-MM-dd HHmm> `

Example: `deadline wake up /by 2025-10-31 1900`

Example successful output:

```
A deadline, got it! I've added this task: [D][ ] wake up (by: Oct 31 2025 1900)  
Now you have 2 tasks in the list.
```

## Adding an event: event

An event is a task with a start and end date / time attached.

Format: `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`

Example: `event run /from 2025-10-31 1900 /to 2025-10-31 2000`

Example successful output:

```
An event, got it! I've added this task: [E][ ] run (from: Oct 31 2025 1900 to: Oct 31 2025 2000)  
Now you have 3 tasks in the list.
```

## Listing all tasks: list

Displays all current tasks.

Format: `list`

Example: `list`

Example successful output:

```
1. [T][ ] sleep
2. [D][ ] wake up (by: Oct 31 2025 1900) 
3. [E][ ] run (from: Oct 31 2025 1900 to: Oct 31 2025 2000)
```

## Marking tasks: mark

Marks specified task as completed.

Format: `mark <task number>`

Example: `mark 1`

Example successful output:

```
Great! I've marked this task as done:
[T][X] sleep
```

## Unmarking tasks: unmark

Marks specified task as incomplete.

Format: `unmark <task number>`

Example: `unmark 1`

Example successful output:

```
Alright. I've unmarked this task as not done yet:
[T][ ] sleep
```

## Deleting tasks: delete

Deletes specified task from the list of tasks.

Format: `delete <task number>`

Example: `delete 1`

Example successful output:

```
Alright. I've deleted this task:
[T][ ] sleep
Now you have 2 tasks in the list.
```

## Finding tasks: find

Finds all tasks that have the specified keyword.

Format: `find <keyword>`

Example: `find run`

Example successful output:

```
Here are the tasks that match!
1. [T][ ] run
2. [E][ ] running with Jack (from: Oct 31 2025 1900 to: Oct 31 2025 2000)
```

## Updating tasks: update

Update a task in the list of tasks

Format: `update <task number> <description> <by date / time> <from date / time> <to date / time>`

All fields except the task number are optional. At least one of them should be provided.

Example: `update 2 /from 2025-10-31 1800`

Example successful output:

```
Great! I've updated Task 2:
[E][ ] running with Jack (from: Oct 31 2025 1800 to: Oct 31 2025 2000)
```

## Exiting the program: bye

Exit the program

Format: `bye`

Example: `bye`

Example successful output:

```
Bye. Hope to see you again soon!
```