# Alfred User Guide

> "Why do we fall, sir? So that we may learn to pick ourselves up." - Alfred Pennyworth, Batman Begins

**Alfred is will be your most reliable companion. As dependable as he is for Bruce Wayne himself.**
he is:
* fast
* simple to work with
  and most importantly, _helps YOU get things done._

features:
[  ] manage tasks and track their completion
[  ] set deadlines and timings
[  ] reminders

there is no time to waste. download alfred today.

## Adding deadlines

Add a task with a deadline by using the `by` keyword followed by the date and time.

Example: `deadline get milk by 2024-09-20`

Upon successful addition, you will see the following message:
```
This task has been successfully added:
[D][ ] get milk (by: Sep 20 2024)
```

## Adding Todo tasks

Add a todo task by using the `todo` command followed by the task description.

Example: `todo read a book`

Upon successful addition, you will see the following message:
```
This task has been successfully added:
[T][ ] read a book
```
## Adding Event tasks

Add an event task by using the `event` command followed by the task description and the event date and time.

Example: `event team meeting at 2024-09-25 14:00`

Upon successful addition, you will see the following message:
```
This task has been successfully added:
[E][ ] team meeting (at: Sep 25 2024 14:00)
```

## Listing tasks

List all tasks by using the `list` command.

Example: `list`

This will display all your tasks in the following format:
```
Your Tasks:
1.[T][ ] read a book
2.[D][ ] get milk (by: Sep 20 2024)
```

## Marking tasks as done

Mark an **undone** task as done by using the `done` command followed by the task number.

Example: `done 1`

This will mark the first task as done and display the following message:
```
Task has been marked as done, Master Bruce.
[T][X] read a book
```

## Marking tasks as not done

Mark a **done** task as not done by using the `undone` command followed by the task number.

Example: `undone 1`

This will mark the first task as not done and display the following message:
```
Task has been marked as not done, Master Bruce.
[T][ ] read a book
```
## Deleting tasks

Delete a task by using the `delete` command followed by the task number.

Example: `delete 2`

This will delete the second task and display the following message:
```
Noted, Master Bruce. This task has been successfully deleted.
[D][ ] get milk (by: Sep 20 2024)
There are 5 tasks left.
```

## Sorting tasks according to date

Sort tasks by their dates using the `sort` command.

Example: `sort`

This will sort your tasks by date and display the following message:
```
Your tasks have been sorted by date.
```

## Finding tasks by keyword

Find tasks containing a specific keyword using the `find` command followed by the keyword.

Example: `find book`

This will display all tasks containing the keyword "book":
```
These are the tasks that match your search, sir
1.[T][ ] read a book
```
