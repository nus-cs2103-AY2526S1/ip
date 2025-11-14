# Baymax User Guide

Welcome to **Baymax**, your personal task management companion!  
This guide explains how to interact with Baymax using commands and what output to expect.

---

## Product Screenshot

![User Interface](Ui.png)

*Full GUI window of Baymax. The product name should be visible in the title bar.*

---

## Introduction

Baymax helps you organize your tasks, deadlines, and events. You can:

- Add, remove, and list tasks.
- Mark tasks as done or undone.
- Search for tasks by keyword.
- Get consistent, formatted messages for all actions.

All outputs follow a friendly, bordered format.

---

## Features

### 1. Adding a Todo Task

**Command:**
`todo <description>`

**Example:**
`todo Buy groceries`

**Expected Output:**
````
___________________________________________________  
Task successfully added. I will monitor it with care:  
[T][ ] Buy groceries  
Now you have 1 tasks in the list.
___________________________________________________  

````
---
### 2. Adding a Deadline

**Command:**  
`deadline <description> /by <dd/mm/yyyy>`

**Example:**  
`deadline Submit report /by 20/09/2025`

**Expected Output:**
````
___________________________________________________  
Task successfully added. I will monitor it with care:  
[D][ ] Submit report (by: Sep 20 2025)  
Now you have 2 tasks in the list.
___________________________________________________  
````
---

### 3. Marking a Task as Done

**Command:**  
`done <taskid>`

**Example:**  
`done 1`

**Expected Output:**
````
___________________________________________________  
Task successfully marked. I will monitor it with care:  
[T][X] Buy groceries
___________________________________________________  
````
---

### 4. Unmarking a Task

**Command:**  
`undone <taskid>`

**Example:**  
`undone 1`

**Expected Output:**
````
___________________________________________________  
Task successfully unmarked. I will monitor it with care:  
[T][ ] Buy groceries
___________________________________________________  
````
---

### 5. Removing a Task

**Command:**  
`delete <taskid>`

**Example:**  
`delete 1`

**Expected Output:**
````
___________________________________________________  
Task removed, your task is now lighter:  
[T][ ] Buy groceries  
Now you have 1 tasks in the list.
___________________________________________________  
````
---

### 6. Listing All Tasks

**Command:**  
`list`

**Expected Output:**
````
___________________________________________________  
Here are the tasks in your list:
1.[T][X] Buy groceries
2.[D][ ] Submit report (by: Sep 20 2025)
___________________________________________________  
````
---

### 7. Finding Tasks by Keyword

**Command:**  
`find <keyword>`

**Example:**  
`find groceries`

**Expected Output:**
````
___________________________________________________  
Here are the matching tasks in your list:
1.[T][X] Buy groceries
___________________________________________________  
````
---

### 8. Adding an Event

**Command:**  
`event <description> /from <dd/mm/yyyy> /to <dd/mm/yyyy>`

**Example:**  
`event Team meeting /from 21/09/2025 /to 22/09/2025`

**Expected Output:**
````
___________________________________________________  
Task successfully added. I will monitor it with care:  
[E][ ] Team meeting (from: Sep 21 2025 to: Sep 22 2025)  
Now you have 3 tasks in the list.
___________________________________________________  
````
---

## Tips

- Commands are **case-sensitive**.
- Always include **task index numbers** for commands like `done`, `mark`, `unmark` and `delete`.
- Use the exact date format <dd/mm/yyyy> when adding deadlines/events.
- The GUI shows all tasks clearly, with status `[ ]` for not done and `[X]` for done.
- `[D]`, `[E]`, `[T]` denotes type `Deadline`, `Event`, `Todo` respectively. 
