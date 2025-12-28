# Yapper User Guide

![Ui](https://bacenl.github.io/ip/Ui.png)

Welcome to Yapper, a CLI-based task manager application!

## Adding Todos

Add a **todo** task.

Example: `todo do homework (optional: /tag homework)`

Expected Outcome:
```
Got it. I've added this task:
[T][ ] do homework #tag: homework
Now you have 1 tasks in the list.
```

## Adding deadlines

Add a **deadline** task that has a due date.

Note: Dates to be entered as YYYY-MM-DD

Example: `deadline do homework /by 2025-12-11 (optional: /tag homework)`

Expected Outcome:
```
Got it. I've added this task:
[D][ ] do homework (by: 11 Dec 2025) #tag: homework
Now you have 2 tasks in the list.
```

## Adding event

Add an **event** task that has a to and from date.

Note: Dates to be entered as YYYY-MM-DD

Example: `event career fair /from 2025-12-11 /to 2025-12-13 (optional: /tag job)`

Expected Outcome:
```
Got it. I've added this task:
[E][ ] career fair (from: 11 Dec 2025 to: 13 Dec 2025) #tag: job
Now you have 3 tasks in the list.
```

## Marking tasks

Mark a task as done.

Note: 1-indexing used. 

Example: `mark 1`

Expected Outcome:
```
Nice! I have marked this task as done:
[T][X] do homework #tag: homework
```

## Unmarking tasks

Mark a task as not done.

Note: 1-indexing used. 

Example: `unmark 1`

Expected Outcome:
```
Ok, I have marked this task as not done yet:
[T][ ] do homework #tag: homework
```

## Listing tasks

Lists all current tasks.

Example: `list`

Expected Outcome:
```
Here are the tasks in your list:
1. [T][ ] do homework #tag: homework
2. [D][ ] do homework (by: 11 Dec 2025) #tag: homework
3. [E][ ] career fair (from: 11 Dec 2025 to: 13 Dec 2025)
```

## Deleting tasks

Delete a task given an index.

Note: 1-indexing used. 

Example: `delete 1`

Expected Outcome:
```
Noted. I have removed this task:
[T][ ] do homework #tag: homework
Now you have 2 tasks in the list.
```

## Finding tasks

Find a task that contains a keyword.

Example: `find career`

Expected Outcome:
```
Here are the matching tasks in your list:
1. [E][ ] career fair (from: 11 Dec 2025 to: 13 Dec 2025) #tag: job
```
