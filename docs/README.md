# Benn User Guide

> Benn is your personal chatbot assistant that helps you manage tasks, deadlines, and events — right from the command line.

---

## Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
  - [Adding a Todo Task](#adding-a-todo-task)
  - [Adding a Deadline Task](#adding-a-deadline-task)
  - [Adding an Event Task](#adding-an-event-task)
  - [Listing All Tasks](#listing-all-tasks)
  - [Marking and Unmarking Tasks](#marking-and-unmarking-tasks)
  - [Deleting a Task](#deleting-a-task)
  - [Finding a Task](#finding-a-task)
  - [Viewing Help](#viewing-help)
  - [Exiting Benn](#exiting-benn)
4. [Saving and Loading Data](#saving-and-loading-data)
5. [Command Summary](#command-summary)

---

## Introduction

Benn is a text-based task manager built with **Java 17**.  
It helps you keep track of your todos, deadlines, and events through simple typed commands.  
Your data is automatically saved and restored between sessions, so you never lose your progress.

---

## Quick Start

1. **Ensure Java 17 or above is installed**

   You can verify this by running:
   java -version

2. **Clone the repository**
   git clone https://github.com/btjm123/ip.git
   cd ip

3. **Build Benn**
   ./gradlew shadowJar

4. **Run Benn**
   java -jar ./build/libs/benn.jar

You should see Benn’s greeting appear in your terminal.

---

## Features

### Adding a Todo Task
Adds a simple task without any date or time.

**Format:**
todo DESCRIPTION

**Example:**
todo read book

**Expected Output:**
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.

---

### Adding a Deadline Task
Adds a task that must be completed by a specific date and time.

**Format:**
deadline DESCRIPTION /by DD/MM/YYYY HH:MM


**Example:**
deadline submit report /by 25/12/2025 23:59


**Expected Output:**
Got it. I've added this task:
[D][ ] submit report (by: 25/12/2025 23:59)
Now you have 2 tasks in the list.


---

### Adding an Event Task
Adds a task that spans a start and end time.

**Format:**
event DESCRIPTION /from DD/MM/YYYY HH:MM /to DD/MM/YYYY HH:MM


**Example:**
event project meeting /from 20/09/2025 14:00 /to 20/09/2025 16:00


**Expected Output:**
Got it. I've added this task:
[E][ ] project meeting (from: 20/09/2025 14:00 to: 20/09/2025 16:00)
Now you have 3 tasks in the list.

---

### Listing All Tasks
Displays all tasks currently in your list.

**Format:**
list

**Example Output:**
Here are the tasks in your list:

[T][X] read book

[D][ ] submit report (by: 25/12/2025 23:59)

[E][ ] project meeting (from: 20/09/2025 14:00 to: 20/09/2025 16:00)

---

### Marking and Unmarking Tasks
Marks a task as done or undone using its index (1-based).

**Format:**
mark INDEX
unmark INDEX

**Example:**
mark 1

**Expected Output:**
Nice! I've marked this task as done:
[T][X] read book

---

### Deleting a Task
Removes a task from the list permanently.

**Format:**
delete INDEX

**Example:**
delete 2


**Expected Output:**
Noted. I've removed this task:
[D][ ] submit report (by: 25/12/2025 23:59)
Now you have 2 tasks in the list.

---

### Finding a Task
Searches for all tasks containing a given keyword.

**Format:**
find KEYWORD

**Example:**
find report

**Expected Output:**
Here are the matching tasks in your list:

[D][ ] submit report (by: 25/12/2025 23:59)

---

### Viewing Help
Displays all available commands and usage examples.

**Format:**
help

**Expected Output:**
Here are the available commands:

list
Show all tasks in the list.

todo DESCRIPTION
Add a todo task.

deadline DESCRIPTION /by DD/MM/YYYY HH:MM
Add a deadline task.

event DESCRIPTION /from DD/MM/YYYY HH:MM /to DD/MM/YYYY HH:MM
Add an event task.

mark INDEX
Mark the task as done.

unmark INDEX
Mark the task as not done.

delete INDEX
Delete the task.

find KEYWORD
Find tasks containing the keyword.

bye
Exit the program.

help
Show this help message.

---

### Exiting Benn
Ends the session and displays a goodbye message.

**Format:**
bye

**Expected Output:**
bye, hope to see you soon!

---

## Saving and Loading Data

- All tasks are **automatically saved** to a data file on your disk after every change.
- When you start Benn again, your previous tasks are **restored automatically**.

You don’t have to manually save your work — Benn handles it for you.

---

## Command Summary

| Command | Description | Example |
|----------|--------------|----------|
| `todo DESCRIPTION` | Adds a todo task | `todo buy groceries` |
| `deadline DESCRIPTION /by DATE TIME` | Adds a deadline task | `deadline submit report /by 25/12/2025 23:59` |
| `event DESCRIPTION /from DATE TIME /to DATE TIME` | Adds an event task | `event hackathon /from 10/01/2026 09:00 /to 11/01/2026 18:00` |
| `list` | Lists all tasks | `list` |
| `mark INDEX` | Marks task as done | `mark 1` |
| `unmark INDEX` | Marks task as not done | `unmark 1` |
| `delete INDEX` | Deletes a task | `delete 2` |
| `find KEYWORD` | Finds tasks containing the keyword | `find report` |
| `help` | Shows help message | `help` |
| `bye` | Exits Benn | `bye` |

---

✅ **Tips**
- Dates and times must follow the format `DD/MM/YYYY HH:MM`.
- Commands are **case-insensitive** (e.g. `Todo`, `TODO`, or `todo` all work).
- Use `help` anytime if you forget a command.