# Whchenyicn User Guide

![Ui](Ui.png)
Whchenyicn is a simple task management chatbot with a GUI.  
It helps you keep track of todos, deadlines, and events, and comes with reminders and search features.  
It runs on Java 17 and stores your tasks automatically between sessions.

## 🚀 Quick Start Guide

1. Ensure you have **Java 17** or above installed on your computer.
2. Download the latest `whchenyicn.jar` file from the *releases* page of this repository.
3. To run the application, double-click the `whchenyicn.jar` file or use your terminal:
4. The application will automatically create a save file in the same folder to store your tasks.
5. You can now start using Whchenyicn by typing commands (see *Feature Overview* for command details).

---
    
## ✨ Feature Overview

Whchenyicn supports the following features:

- [Add Todos](#adding-todos) — `todo <description>`
- [Add Deadlines](#adding-deadlines) — `deadline <description> /by YYYY-MM-DD`
- [Add Events](#adding-events) — `event <description> /from <date/time> /to <date/time>`
- [List Tasks](#listing-tasks) — `list`
- [Mark and Unmark Tasks](#marking-and-unmarking-tasks) — `mark <n>`, `unmark <n>`
- [Delete Tasks](#deleting-tasks) — `delete <n>`
- [Find Tasks](#finding-tasks) — `find <keyword>`
- [Reminders](#reminders) — `reminder <days>`
- [Exit](#exiting) — `bye`

## Adding todos

Adds a simple task with just a description.

Example: `todo read book`

**Expected outcome:**
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

## Adding deadlines

Adds a task with a description and a deadline.

**Example:** `deadline return book /by 2025-09-30`

**Expected outcome:**

```
Got it. I've added this task:
[D][ ] return book (by: 2025-09-30)
Now you have 2 tasks in the list.
```

## Adding events

Adds a task with a description and an event time.

**Example:** `event project meeting /from Monday 2pm /to Tuesday 2pm`

**Expected outcome:**
```
Got it. I've added this task:
[E][ ] project meeting (from: Monday 2pm to: Tuesday 2pm)
Now you have 3 tasks in the list.
```
---

## Listing tasks

Shows all tasks currently in your list.

**Example:** `list`

**Expected outcome:**
```
1.[T][ ] read book
2.[D][ ] return book (by: 2025-09-30)
3.[E][ ] project meeting (at: Monday 2pm)
```
## Marking and unmarking tasks

Marks or unmarks a task in the list by its number.

**Examples:**

```
mark 2
unmark 2
```
**Expected outcomes:**
```
Nice! I've marked this task as done:
[D][X] return book (by: 2025-09-30)

OK, I've marked this task as not done yet:
[D][ ] return book (by: 2025-09-30)
```
## Deleting tasks

Removes a task from the list by its number.

**Example:** `delete 1`

**Expected outcome:**
```
This task has been removed
[T][ ] read book
2 tasks left in the list.
```
## Finding tasks

Finds tasks that contain a given keyword.

**Example:** `find book`

**Expected outcome:**
```
Here are the matching tasks in your list:
1.[D][ ] return book (by: 2025-09-30)
```
## Reminders

Shows tasks due within the given number of days.

**Example:** `reminder 7`

**Expected outcome:**
```
Here are tasks due within 7 days:
[D][ ] return book (by: 2025-09-30)
```
## Exiting

Closes the chatbot.

**Example:** `bye`

**Expected outcome:**
```
Bye! Hope to see you again soon!
```
## Error Handling

Whchenyicn handles common errors gracefully:

- **Unknown command** → Shows: “Invalid Command”
- **Missing description** (e.g. `todo `) → Asks you to provide a description.
- **Invalid or missing date** → Reminds you to use the correct format (`YYYY-MM-DD` or valid time).
- **Task number out of range** → Tells you the number must be between 1 and the size of your list.
- **Storage errors** → Creates a new save file if the old one is missing or corrupted.
- **Empty keyword** → Prompts you to provide a keyword or a valid number of days.  
