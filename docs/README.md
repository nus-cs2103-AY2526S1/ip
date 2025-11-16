# Tuesday User Guide

![Tuesday Screenshot](docs/UI.png)  
*A modern, lightweight task manager chatbot with a simple GUI.*

---

## Introduction

Tuesday is a **task management chatbot** built with Java and JavaFX.  
It helps you keep track of your **todos, deadlines, and events** through natural text commands.  
The GUI is styled to be compact and user-friendly, so you can focus on managing tasks quickly.

---

## Features

### Adding a todo
Adds a simple task that does not have a specific date or time.

**Format:**
```
todo DESCRIPTION
```

**Example:**
```
todo Buy groceries
```

**Expected outcome:**
```
Got it! I've added this task:
  [T][ ] Buy groceries
Now you have 1 task in your list.
```

---

### Adding a deadline
Adds a task with a deadline.

**Format:**
```
deadline DESCRIPTION /by DD-MM-YYYY HHmm
```

**Example:**
```
deadline Submit report /by 25-09-2025 2359
```

**Expected outcome:**
```
Got it! I've added this task:
  [D][ ] Submit report (BY 25 Sep 2025 - 11:59PM)
Now you have 2 tasks in your list.
```

---

### Adding an event
Adds an event with a start and end time.

**Format:**
```
event DESCRIPTION /from DD-MM-YYYY HHmm /to DD-MM-YYYY HHmm
```

**Example:**
```
event Team meeting /from 26-09-2025 1000 /to 26-09-2025 1130
```

**Expected outcome:**
```
Got it! I've added this task:
  [E][ ] Team meeting (FROM 26 Sep 2025 - 10:00AM TO 26 Sep 2025 - 11:30AM)
Now you have 3 tasks in your list.
```

---

### Finding tasks
Finds tasks that contain the given keyword.

**Format:**
```
find KEYWORD
```

**Example:**
```
find report
```

**Expected outcome:**
```
Here are the matching tasks in your list:
  1. [D][ ] Submit report (BY 25 Sep 2025 - 11:59PM)
```

---

### Listing all tasks
Shows all tasks currently in the task list.

**Format:**
```
list
```

**Example:**
```
list
```

**Expected outcome:**
```
Here are your tasks:
  1. [T][ ] Buy groceries
  2. [D][ ] Submit report (BY 25 Sep 2025 - 11:59PM)
  3. [E][ ] Team meeting (FROM 26 Sep 2025 - 10:00AM TO 26 Sep 2025 - 11:30AM)
```

---

### Marking and unmarking tasks
Marks a task as done or marks it as not done.

**Format:**
```
mark INDEX
unmark INDEX
```

**Example:**
```
mark 1
```

**Expected outcome:**
```
Nice! I've marked this task as done:
  [T][X] Buy groceries
```

---

### Deleting tasks
Deletes a task from the list.

**Format:**
```
delete INDEX
```

**Example:**
```
delete 2
```

**Expected outcome:**
```
Noted. I've removed this task:
  [D][ ] Submit report (BY 25 Sep 2025 - 11:59PM)
Now you have 2 tasks in your list.
```

---

### Sorting tasks
Sorts tasks either by type or by time.

**Format:**
```
sort-type /by TYPE
sort-time /by TYPE
```

**Example:**
```
sort-type /by todo
sort-time /by deadline
```

---

### Exiting the program
Exits the program.

**Format:**
```
bye
```

**Expected outcome:**
```
Bye. Hope to see you again soon!
```



