# Romidas User Guide

![Romidas Screenshot](Ui.png)

Romidas is a **personal task management chatbot** that helps you keep track of your todos, deadlines, and events. Romidas uses a simple Gui to interact with you and automatically saves your tasks to ensure you never lose your data. :smile

Romidas is optimized for users who prefer typing commands over clicking buttons, allowing you to manage your tasks quickly and efficiently.

## Adding deadlines

Add a task with a specific deadline to your task list.

**Format:** `deadline DESCRIPTION /by DATE`

`DATE` must be in the format `yyyy-MM-dd`

**Examples:**
* `deadline submit report /by 2023-12-25`
* `deadline project presentation /by 2023-11-30`

**Expected output:**
```
Got it. I've added this task:
[D][ ] submit report (by: 2023-12-25)
Now you have 2 tasks in your list.
```

## Adding todos

Add a simple todo task to your task list.

**Format:** `todo DESCRIPTION`

**Examples:**
* `todo read book`
* `todo buy groceries`
* `todo complete assignment`

**Expected output:**
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in your list.
```


## Adding events

Adds an event with a specific time period to your task list.

**Format:** `event DESCRIPTION /from START_TIME /to END_TIME`

`START_TIME` and `END_TIME` can be in any format you prefer (e.g., `2pm`, `14:00`, `Mon 2pm`)

**Examples:**
* `event team meeting /from 2pm /to 4pm`
* `event conference /from Mon 9am /to Fri 5pm`
* `event workshop /from 2023-12-01 10:00 /to 2023-12-01 12:00`

**Expected output:**
```
Got it. I've added this task:
[E][ ] team meeting (from: 2pm to: 4pm)
Now you have 3 tasks in your list.
```


## Listing all tasks

Shows all tasks in your task list with their completion status.

**Format:** `list`

**Examples:**
* `list`

**Expected output:**
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit report (by: 2023-12-25)
3.[E][ ] team meeting (from: 2pm to: 4pm)
```


## Marking tasks as done

Marks a specific task as completed.

**Format:** `mark TASK_NUMBER`

`TASK_NUMBER` refers to the index shown in the task list

**Examples:**
* `mark 1`
* `mark 2`

**Expected output:**
```
Nice! I've marked this task as done:
[D][X] submit report (by: 2023-12-25)
```


## Deleting tasks

Deletes a specific task from your task list.

**Format:** `delete TASK_NUMBER`

`TASK_NUMBER` refers to the index shown in the task list

**Examples:**
* `delete 1`
* `delete 3`

**Expected output:**
```
Noted. I've removed this task:
[E][ ] team meeting (from: 2pm to: 4pm)
Now you have 2 tasks in your list.
```


## Finding tasks

Finds all tasks that contain a specific keyword in their description.

**Format:** `find KEYWORD`

Search is case-insensitive and partial matches are supported

**Examples:**
* `find book`
* `find meeting`
* `find report`

**Expected output:**
```
Here are the matching tasks in your list:
1.[T][ ] read book
```

