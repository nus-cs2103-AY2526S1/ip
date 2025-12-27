# SmartStelle User Guide
## Introduction

SmartStelle is a simple task management chatbot with a clean JavaFX GUI.  
It helps you track tasks like **ToDos**, **Deadlines**, and **Events** right from your desktop.

You can:
- Add, view, mark, unmark, delete, and search tasks
- Automatically save your tasks to disk
- Get a friendly chatbot-style interface to manage your tasks

---

## Features

### Adding Deadlines

Adds a task with a description and a due date/time.

**Format**
deadline description /by yyyy-MM-ddTHH:mm

**Example**
deadline CS2103T project /by 2025-09-21T23:59

**Expected outcome**
added new task: CS2103T project. Now you have 3 tasks in the list.

### Adding Events

Adds a task with a description, start time, and end time.

**Format**
event <description> /from yyyy-MM-ddTHH:mm /to yyyy-MM-ddTHH:mm

**Example**
event team meeting /from 2025-09-22T10:00 /to 2025-09-22T12:00

**Expected outcome**
added new task: team meeting. Now you have 3 tasks in the list.
---
### Adding ToDos

Adds a simple task with only a description.

**Format**
todo description

**Example**
todo buy milk

**Expected outcome**
added new task: buy milk. Now you have 5 tasks in the list.

---

### Listing all tasks

Shows all tasks currently saved.

**Format**
list

**Expected outcome**
[T][ ] buy milk
[D][ ] CS2103T project (by 21 Sep 2025 23:59)
[E][ ] team meeting (from 22 Sep 2025 10:00 to 12:00)

---

### Marking / Unmarking tasks

Marks or unmarks a task as completed.

**Format**
mark task number \
unmark task number

**Example**
mark 2

**Expected outcome**
Yay! I've marked this task as done:
[D][X] CS2103T project (by 21 Sep 2025 23:59)

---

### Deleting tasks

Deletes a task from the list.

**Format**
delete <task number>

**Example**
delete 1

**Expected outcome**
Okay, I've removed this task:
[T][ ] buy milk
Now you have 4 tasks in the list.
---

### Finding tasks by keyword

Searches for tasks containing a given keyword.

**Format**
find <keyword>

**Example**
find project

**Expected outcome**
[D][X] CS2103T project (by 21 Sep 2025 23:59)


---

## Saving and Loading

- SmartStelle automatically saves your tasks to `data/tasks.txt`
- When you reopen the app, all tasks are loaded from this file

---

## Exiting the App

To close the app, type: bye \
SmartStelle will say goodbye and exit the program.

---

## Notes

- Date/time must be in this exact format: `yyyy-MM-ddTHH:mm`  
  Example: `2025-09-21T23:59`
- Task numbers (for mark/unmark/delete) are 1-based, as shown in the `list` command.






