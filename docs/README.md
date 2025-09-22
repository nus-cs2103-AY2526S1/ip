# Olaf User Guide

Welcome to **Olaf**! Olaf will serve as your personal task management
companion and tool.

This guide will show you how to interact with Olaf using the commands 
and what outputs to expect.

// Product screenshot goes here
## Product Screenshot
![User Interface](Ui.png)


*This shows the full GUI window of Olaf, including product name and 
example commands*

// Product intro goes here
## Introduction

Olaf helps your organise your tasks, tasks with deadlines and events.
You can do multiple things, including:

- Add, remove and list tasks.
- Mark tasks as done or undone.
- Search for tasks by keywords.
- Get consistent, formatted messages for all actions.

All outputs follow a consistent and friendly format that is easy to understand.

---

## Features

### 1. Adding a Todo Task

**Input Command:** 
`todo <description>`

**Example**
`todo sleep`

**Expected Output**
````
Understood. I have added this task for you:
[T][] sleep
You currently have a total of 1 task in your list.
````



### 2. Adding deadlines

**Command** 
`deadline <description> /by dd/mm/yyyy HHmm`

**Example**
`deadline submission of assignment /by 10/10/2025`

**Expected Output**
````
Undersootd. I have added this task for you:
  [D][] submission of assignment(by: 10 Oct 2025 6:00pm)
You currently have a total of 2 tasks in your list.
````

### 3. Adding an event 

**Command** 
`event <description> /from dd/mm/yyyy HHmm /to dd/mm/yyyy HHmm`

**Example**
`event hangout /from 10/10/2025 1800 /to 10/10/2025 1900`

**Expected Output**
````
Understood. I have added this task for you:
  [E][] hangout(from: 10 Oct 2025 6:00pm to: 10 Oct 2025 7:00pm)
You currently have a total of 3 tasks in your list.
````

### 4. Deleting a task

**Command**
`delete <taskid>`

**Example**
`delete 3`

**Expected Output**
````
Noted. I have removed this task for you:
  [E][] hangout(from: 10 Oct 2025 6:00pm to: 10 Oct 2025 7:00pm)
You currently have a total of 2 tasks in your list.
````

### 5. Marking a task as done

**Command:**  
`done <taskid>`

**Example:**  
`done 1`

**Expected Output**
````
Nicely done! I have marked this task as done:
  [T][X] sleep
````

### 6. Unmarking a task as undone

**Command:**  
`undone <taskid>`

**Example:**  
`undone 1`

**Expected Output**
````
Alright, I have marked this task as undone:
  [T][] sleep
````

### 7. Listing all tasks

**Command:**  
`list`

**Expected Output**
````
Here are the tasks you have in your list:
1.[T][] sleep
2.[D][] submission of assignment (by: 10 Oct 2025 6:00pm)
````

### 8. Finding tasks by keyword

**Command:**  
`find <keyword>`

**Example:**  
`find sleep`

**Expected Output**
````
Here are the matching tasks in your list:
1.[T][] sleep
````


## Tips 

- Commands are **case-sensitive**. Please follow exactly what the guide gives.
- Always include **task index numbers** for commands like `done`, `mark`, `unmark` and `delete`.
The task index numbers should be in order of addition. If unsure, just use list to confirm the task id number.
- Use the exact date format <dd/mm/yyyy> when adding deadlines/events.
- The GUI shows all tasks clearly, with status `[]` for not done and `[X]` for done.
- `[D]`, `[E]`, `[T]` denotes type `Deadline`, `Event`, `Todo` respectively. 