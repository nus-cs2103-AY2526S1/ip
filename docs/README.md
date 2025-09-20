# Dobby User Guide

**Dobby** is a desktop task management app, optimized for use via a Command Line Interface (CLI) while still offering a Graphical User Interface (GUI). If you can type fast, Dobby can help you manage your tasks faster than traditional GUI apps.

---

## Quick start

Ensure you have **Java 17 or above** installed on your computer.

1. Download the latest `.jar` file.
2. Copy the file to the folder you want to use as the home folder for Dobby.
3. Open a command terminal, `cd` into the folder containing the jar file, and run:

   ```bash
   java -jar dobby.jar


4. A GUI will appear within a few seconds. Note that the app may contain some sample tasks.
5. Type commands into the command box and press Enter to execute them (e.g., `help`).

---

## Features

\:information\_source: **Notes about the command format:**

* Words in `UPPER_CASE` are parameters supplied by the user.

    * e.g., in `todo DESCRIPTION`, `DESCRIPTION` is replaced with your task description.
* Items in square brackets `[]` are optional.
* Parameters can usually be supplied in any order unless otherwise stated.
* Extraneous parameters for commands that do not require them will be ignored.

---

### Viewing help : `help`

Shows the list of available commands and usage guidance.

**Format:**

```text
help
```

---

### Adding a ToDo task : `todo`

Adds a simple task without a date or time.

**Format:**

```text
todo DESCRIPTION
```

**Example:**

```text
todo Buy groceries
```

---

### Adding a Deadline task : `deadline`

Adds a task with a specific due date and time.

**Format:**

```text
deadline DESCRIPTION /by yyyy-MM-dd HHmm
```

**Example:**

```text
deadline Submit report /by 2025-09-30 2359
```

---

### Adding an Event task : `event`

Adds a task that occurs between a start and end time.

**Format:**

```text
event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm
```

**Example:**

```text
event Team meeting /from 2025-09-20 1400 /to 2025-09-20 1500
```

---

### Marking a task as done : `mark`

Marks a task as completed.

**Format:**

```text
mark TASK_NUMBER
```

**Example:**

```text
mark 2
```

---

### Marking a task as not done : `unmark`

Marks a task as incomplete.

**Format:**

```text
unmark TASK_NUMBER
```

**Example:**

```text
unmark 2
```

---

### Deleting a task : `delete`

Removes a task from your list.

**Format:**

```text
delete TASK_NUMBER
```

**Example:**

```text
delete 3
```

---
### Finding tasks by keyword : `find`

Displays all tasks in your task list that matches the keyword.

**Format:**

```text
find <keyword>
```

---

### Listing all tasks : `list`

Displays all tasks in your task list.

**Format:**

```text
list
```

---

### Exiting the program : `bye`

Closes the app.

**Format:**

```text
bye
```

---


## Command summary

| Command                                                             | Description             |
| ------------------------------------------------------------------- | ----------------------- |
| `list`                                                              | Display all tasks       |
| `todo <DESCRIPTION>`                                                | Add a ToDo task         |
| `deadline <DESCRIPTION> /by <yyyy-MM-dd HHmm>`                      | Add a Deadline task     |
| `event <DESCRIPTION> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>` | Add an Event task       |
| `mark <TASK_NUMBER>`                                                | Mark a task as done     |
| `unmark <TASK_NUMBER>`                                              | Mark a task as not done |
| `delete <TASK_NUMBER>`                                              | Delete a task           |
| `help`                                                              | Show this help message  |
| `bye`                                                               | Exit the application    |

```