# Khat User Guide

Welcome to Khat, your personal task management chatbot!
This guide will help you get started with using Khat to manage your tasks efficiently.
Khat is a simple, friendly chatbot that helps you manage your tasks from the GUI.

---

## Quick Start

1. Ensure you have Java 17 or above installed in your Computer.

2. Download the latest .jar file from [here](https://github.com/katrinaykt/ip/releases/tag/v0.3).

3. Copy the file to the folder you want to use as the home folder for your Khat chatbot.

4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar khat.jar command to run the application.
   A GUI similar to the below should appear in a few seconds.
   
   ![Khat UI Screenshot](./Ui.png)

5. Start typing commands to manage your tasks!
6. Refer to the [Features](#features) section below for a list of commands you can use.

---

## Features

### 1. Adding Todos

Add a simple task to your list.

**Usage:**  
`todo <task description>`

**Example:**  
`todo read book`

**Expected outcome:**  
Khat adds the todo and confirms:
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

### 2. Adding Deadlines

Add a task with a specific deadline.
Date and time must be in the format "dd-MM-yyyy" or "dd-MM-yyyy HHmm".

**Usage:**  
`deadline <task description> /by <date or date time>`

**Example:**  
`deadline submit report /by 16-09-2025`

**Expected outcome:**  
Khat adds the deadline task and confirms:
```
Got it. I've added this task:
[D][ ] submit report (by: 16 Sep 25)
Now you have 2 tasks in the list.
```

### 3. Adding Events

Add a task that happens at a specific time.
Date and time must be in the format "dd-MM-yyyy" or "dd-MM-yyyy HHmm".

**Usage:**  
`event <task description> /from <start> /to <end>`

**Example:**  
`event team meeting /from 05-05-2025 1700 /to 1800`

**Expected outcome:**  
Khat adds the event and confirms:
```
Got it. I've added this task:
[E][ ] team meeting (from: 05 May 25 5:00pm to: 6:00pm)
Now you have 3 tasks in the list.
```

### 4. Listing Tasks

Show all your tasks with their status.

**Usage:**  
`list`

**Expected outcome:**  
Khat displays your current tasks:
```
List of tasks:
1. [T][ ] read book
2. [D][ ] submit report (by: 16 Sep 25)
3. [E][ ] team meeting (from: 05 May 25 5:00pm to: 6:00pm)
```

### 5. Marking Tasks as Done

Mark a task as completed.

**Usage:**  
`mark <task number>`

**Example:**  
`mark 2`

**Expected outcome:**  
Khat marks the task as done:
```
Nice! I've marked this task as done:
[D][X] submit report (by: 16 Sep 25)
```
### 6. Unmarking Tasks
Mark a task as not completed.

**Usage:**
`unmark <task number>`

**Example:**
`unmark 2`

**Expected outcome:**
Khat marks the task as not done:
```
OK, I've marked this task as not done yet:
[D][ ] submit report (by: 16 Sep 25)
```

### 7. Deleting Tasks

Remove a task from your list.

**Usage:**
`delete <task number>`

**Example:**
`delete 3`

**Expected outcome:**
Khat deletes the task and confirms:
```
Ok, I've removed this task:
[E][ ] team meeting (from: 05-05-2025 5pm to: 6pm)
There are 2 remaining tasks.
```
### 8. Find Tasks on a specific date
Search for deadline tasks on a specific date.

**Usage:**
`date <date>`
**Example:**
`date 16-09-2025`
**Expected outcome:**
Khat lists matching deadline tasks:
```
Here are the tasks on 2025-09-05:
1. [D][ ] submit report (by: 16 Sep 25)
```

### 9. Finding Tasks
Search for tasks containing a specific keyword.

**Usage:**
`find <keyword>`

**Example:**
`find book`

**Expected outcome:**
Khat lists matching tasks:
```
Here are the matching tasks in your list with keyword book:
1. [T][ ] read book
```

### 10. Exiting the Application
Exits the application.

**Usage:**
`bye`

**Expected outcome:**
Khat says goodbye and exits:
```
Bye. Hope to see you again soon!
```