# Yuan Chatbot User Guide

Welcome to **Yuan**!  
Yuan is your personal task management chatbot that helps you organize your todos, deadlines, and events efficiently. With simple commands, you can manage all your tasks in one place.

---

## Getting Started

When you first start Yuan, you'll see your current tasks displayed.  
If you have no tasks, Yuan will show you an empty list.

Type `help` at any time to see all available commands.

---

## Features

### 1. Adding Tasks

#### Add a Todo Task
Add simple tasks that need to be completed.

**Command:**
``
todo <description>
``

**Examples:**
```
todo Buy groceries
todo Complete project report
```


#### Add a Deadline Task
Add tasks with specific due dates and times.

**Command:**
``
deadline <description> /by <date>
``

**Examples:**
```
deadline Submit assignment /by 25/12/2023
deadline Pay bills /by 31/1/2024
```


#### Add an Event
Add events that occur within a specific time period.

**Command:**
``
event <description> /from <start_date> /to <end_date>
``

**Examples:**
```
event Team meeting /from 22/12/2023 /to 22/12/2023
event Conference /from 15/1/2024 /to 17/1/2024
```


---

### 2. Viewing Tasks

#### Show All Tasks
Display your complete task list with task numbers and status.

**Command:**
``
list
``

**Task Display Legend:**
- `[T]` for Todo tasks
- `[D]` for Deadline tasks
- `[E]` for Event tasks
- `[X]` indicates completed tasks
- `[ ]` indicates pending tasks

---

### 3. Managing Tasks

#### Mark Task as Done
Mark a specific task as completed using its task number.

**Command:**
``
mark <task_number>
``

**Examples:**
```
mark 1
mark 2
```

#### Unmark Task
Change a completed task back to pending status.

**Command:**
``
unmark <task number>
``

**Examples:**
```
unmark 1
unmark 2
```


#### Delete Task
Permanently remove a task from your list.

**Command:**
``
delete 1
delete 5
``

**Examples**
```
delete 1
delete 5
```

---

### 4. Finding Tasks

#### Search Tasks
Find tasks that contain specific keywords in their descriptions.

**Command:**
``
find <keyword>
``

**Examples**
```
find meeting
find project
find groceries
```


---

### 5. Getting Help

#### View Available Commands
Display the complete list of available commands with their syntax.

**Command:**
``
help
``


---

## Date Format Guidelines

- Always use the format `d/M/yyyy` for dates.

**Examples of valid dates:**
```
1/1/2024 (January 1, 2024)
25/12/2023 (December 25, 2023)
31/5/2024 (May 31, 2024)
```
