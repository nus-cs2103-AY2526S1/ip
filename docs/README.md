# Momo User Guide

![Momo UI Screenshot Placeholder](Ui.png)

Momo is a **command-line based task manager** that helps you organize your todos, deadlines, and events efficiently. You can quickly add, track, and manage tasks using simple commands.

---

## Table of Contents

* [Quick Start](#quick-start)
* [Features](#features)
    * [Adding Todos](#adding-todos)
    * [Adding Deadlines](#adding-deadlines)
    * [Adding Events](#adding-events)
    * [Listing Tasks](#listing-tasks)
    * [Marking Tasks as Done](#marking-tasks-as-done)
    * [Unmarking Tasks](#unmarking-tasks)
    * [Deleting Tasks](#deleting-tasks)
    * [Finding Tasks](#finding-tasks)
    * [Help](#help)
    * [Exiting Momo](#exiting-momo)
    * [Saving and Loading Data](#saving-and-loading-data)
    * [Advanced: Editing `data/momo.txt` Directly](#advanced-editing-datamomotxt-directly)
* [Command Summary](#command-summary)

---

## Quick Start

1. **Install Java**  
   Ensure you have **Java 17 or above** installed on your computer.

   * **Mac users:** Ensure you have the exact **Java 17 JDK with JavaFX (Azul Zulu distribution)**.

1. **Download Momo**  
   Download the latest `.jar` file from [here](https://github.com/tohyanhui/ip/releases/tag/v0.3).

1. **Set up a home folder**  
   Copy the `.jar` file to the folder you want to use as Momo's home directory.

1. **Run Momo**  
   Open a terminal, navigate (`cd`) to the folder containing the `.jar` file, and run: `java -jar momo.jar`

---

## Features

### Adding Todos

Use the `todo` command to add simple tasks without a specific date or time.

**Format:**
`todo <description>`

**Example:**
`todo read chapter 1 of textbook`

**Expected Output:**

```
Got it. I've added this task:
  [T][ ] read chapter 1 of textbook
Now you have 1 tasks in the list.
```

---

### Adding Deadlines

Use the `deadline` command to add tasks with a due date.

**Format:**
`deadline <description> /by <yyyy-MM-dd HHmm>`

**Example:**
`deadline CS2103T Assignment /by 2025-09-25 2359`

**Expected Output:**

```
Got it. I've added this task:
  [D][ ] CS2103T Assignment (by: Sep 25 2025, 11:59pm)
Now you have 2 tasks in the list.
```

---

### Adding Events

Use the `event` command to add tasks that occur within a specific time frame.

**Format:**
`event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`

**Example:**
`event hackathon /from 2025-09-20 0900 /to 2025-09-21 1800`

**Expected Output:**

```
Got it. I've added this task:
  [E][ ] hackathon (from: Sep 20 2025, 9:00am to: Sep 21 2025, 6:00pm)
Now you have 3 tasks in the list.
```

---

### Listing Tasks

Use the `list` command to display all tasks in your current task list.

**Format:**
`list`

**Example:**
`list`

**Expected Output:**

```
Here are the tasks in your list:
1.[T][ ] read chapter 1 of textbook
2.[D][ ] CS2103T Assignment (by: Sep 25 2025, 11:59pm)
3.[E][ ] hackathon (from: Sep 20 2025, 9:00am to: Sep 21 2025, 6:00pm)
```

---

### Marking Tasks as Done

Use the `mark` command to mark a task as completed.

**Format:**
`mark <task number>`

**Example:**
`mark 2`

**Expected Output:**

```
Nice! I've marked this task as done:
  [D][X] CS2103T Assignment (by: Sep 25 2025, 11:59pm)
```

---

### Unmarking Tasks

Use the `unmark` command to mark a task as not done.

**Format:**
`unmark <task number>`

**Example:**
`unmark 2`

**Expected Output:**

```
OK, I've marked this task as not done yet:
  [D][ ] CS2103T Assignment (by: Sep 25 2025, 11:59pm)
```

---

### Deleting Tasks

Use the `delete` command to remove a task from your list.

**Format:**
`delete <task number>`

**Example:**
`delete 3`

**Expected Output:**

```
Noted. I've removed this task:
  [E][ ] hackathon (from: Sep 20 2025, 9:00am to: Sep 21 2025, 6:00pm)
Now you have 2 tasks in the list.
```

---

### Finding Tasks

Use the `find` command to search for tasks containing a specific keyword.

**Format:**
`find <keyword>`

**Example:**
`find Assignment`

**Expected Output:**

```
Here are the matching tasks in your list:
1.[D][ ] CS2103T Assignment (by: Sep 25 2025, 11:59pm)
```

_Note: The `find` command is **case-sensitive**. e.g. `assignment` will not match `Assignment`_

---


### Help

Use the `help` command to display a list of available commands and usage instructions.

**Format:**
`help`

**Example:**
`help`

**Expected Output:**

```
Here are the available commands:
1.list                  - Show all tasks
2.todo <description>    - Add a todo task
3.deadline <description> /by <yyyy-MM-dd HHmm> - Add a deadline
4.event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm> - Add an event
5.mark <task number>    - Mark a task as done
6.unmark <task number>  - Mark a task as not done
7.delete <task number>  - Delete a task
8.find <keyword>        - Find tasks containing the keyword
9.help                  - Show this help message
10.bye                  - Exit the application
```

---

### Exiting Momo

Use the `bye` command to exit the application.

**Format:**
`bye`

**Example:**
`bye`

**Expected Output:**

```
Bye. Hope to see you again soon!
```

_Note: The "Bye. Hope to see you again soon!" message is generally not shown. In rare cases, such as on very slow systems, it may appear briefly._

---

### Saving and Loading Data

Momo automatically **saves your tasks** to a file so that your data is not lost when you exit.

- All tasks are stored in a text file located at: `[JAR file location]/data/momo.txt`
- When you restart Momo, it will automatically **load tasks** from this file.

**Example (file contents):**

```
T | 0 | read chapter 1 of textbook
D | 1 | CS2103T Assignment | 2025-09-25 2359
E | 0 | hackathon | 2025-09-20 0900 | 2025-09-21 1800
```

Here:
- `T`, `D`, `E` represent Todo, Deadline, and Event respectively.
- `0` means not done, `1` means done.

---

### Advanced: Editing `data/momo.txt` Directly

For advanced users, you can manually edit the file located at: `[JAR file location]/data/momo.txt` using any text editor to add, modify, or remove tasks.

⚠️ **Important Notes:**

* Follow the exact format used in the file (e.g., `D | 0 | description | yyyy-MM-dd HHmm`).
* Incorrect formatting will cause Momo to **discard all saved tasks** and default to a fresh state.
* Always close Momo before editing the file. Changes made while Momo is running **will not appear in the app** and **may be overwritten after the next command**.

---

## Command Summary

| Command                          | Format                                                              | Example                                                     | Description                                                 |
|----------------------------------|---------------------------------------------------------------------|-------------------------------------------------------------|-------------------------------------------------------------|
| [`todo`](#adding-todos)          | `todo <description>`                                                | `todo read chapter 1 of textbook`                           | Add a simple task without a date/time                       |
| [`deadline`](#adding-deadlines)  | `deadline <description> /by <yyyy-MM-dd HHmm>`                      | `deadline CS2103T Assignment /by 2025-09-25 2359`           | Add a task with a due date                                  |
| [`event`](#adding-events)        | `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>` | `event hackathon /from 2025-09-20 0900 /to 2025-09-21 1800` | Add a task with a start and end time                        |
| [`list`](#listing-tasks)         | `list`                                                              | `list`                                                      | Display all tasks in the list                               |
| [`mark`](#marking-tasks-as-done) | `mark <task number>`                                                | `mark 2`                                                    | Mark a task as completed                                    |
| [`unmark`](#unmarking-tasks)     | `unmark <task number>`                                              | `unmark 2`                                                  | Mark a task as not completed                                |
| [`delete`](#deleting-tasks)      | `delete <task number>`                                              | `delete 3`                                                  | Remove a task from the list                                 |
| [`find`](#finding-tasks)         | `find <keyword>`                                                    | `find Assignment`                                           | Search tasks containing a specific keyword (case-sensitive) |
| [`help`](#help)                  | `help`                                                              | `help`                                                      | Show all available commands and usage                       |
| [`bye`](#exiting-momo)           | `bye`                                                               | `bye`                                                       | Exit Momo                                                   |