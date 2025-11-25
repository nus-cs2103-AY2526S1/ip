# Hachiware User Guide

![Hachiware Screenshot](docs/Ui.png)

## 📖 Introduction
Hachiware is a personal task management application with a friendly GUI, 
designed to help you keep track of your tasks efficiently. It supports adding, 
deleting, and viewing tasks, with automatic storage so your data persists across sessions.

The app features a simple and intuitive interface, with a cat-themed welcome message for a
friendly start to your productivity.

## ✨ Features
## Add a ToDo
Adds a simple task without a date.

Usage:
todo <task description>

Example: `todo (homework)`

Expected output:
```
Got it. I've added this task:
[T][ ] (Priority: UNTAGGED) read book
Now you have 1 task in the list.
```

## Add a Deadline
Add a task with a due date. 

Usage:
deadline <task description> /by <yyyy-mm-dd>

Example: `deadline assignment /by 2025-09-30`

Expected output:
```
Got it. I've added this task:
[D][ ] (Priority: UNTAGGED) assignment (by: 2025-09-30)
Now you have 2 tasks in the list.
```


## Add an Event
Add a task that occurs across a duration.

Usage:
event <task description> /from <yyyy-mm-dd HH:mm> /to <yyyy-mm-dd HH:mm>

Example: `event meeting /from 2025-09-30 12:00 /to 2025-09-30 14:00`

Expected output:
```
Got it. I've added this task:
[E][ ] (Priority: UNTAGGED) meeting (from: 2025-09-30 12:00 to: 2025-09-30 14:00)
Now you have 3 tasks in the list.
```

## Mark a Task as Done
Mark a specific task as completed.

Usage:
mark <task number>

Example: `mark 2`

Expected output:
```
Nice! I've marked this task as done:
[D][X] assignment (by: 2025-09-30)
```

## Mark a Task as not Done
Unmark a specific task.

Usage:
unmark <task number>

Example: `unmark 2`

Expected output:
```
OK, I've marked this task as not done yet:
[D][X] assignment (by: 2025-09-30)
```

## Delete a Task
Remove a task from the list.

Usage:
delete <task number>

Example: `delete 2`

Expected output:
```
Noted. I've removed this task:
[D][X] assignment (by: 2025-09-30)
Now you have 2 tasks in the list.
```

## Set priority level 
Set a priority level (HIGH, MEDIUM, LOW, UNTAGGED).
UNTAGGED is default and has lowest priority.

Usage:
priority <task number> <level>

Example: `priority 2 high`

Expected output:
```
Priority set to HIGH for task:
[D][X] assignment (by: 2025-09-30)
```

## List tasks
View all current tasks in the list.

Usage:
list

Example: `list`

Expected output:
```
Here are the tasks in your list:
1. [T][ ] homework
2. [D][ ] assignment (Priority: HIGH) (by: 2025-09-30)
```