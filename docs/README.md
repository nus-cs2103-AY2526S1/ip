# 🔎 Focus — Task Management Assistant 🔎

![Ui Screenshot](/docs/Ui.png)

Focus is a lightweight desktop app for managing tasks, built for speed and clarity.  
It combines the **efficiency of keyboard commands** with the **friendliness of a GUI chatbot**, making task management both fast and enjoyable.

---

## Table of Contents
- [Getting Started](#getting-started)
- [How to Use](#how-to-use)
  - [Adding Tasks](#adding-tasks)
  - [Viewing Tasks](#viewing-tasks)
  - [Updating Tasks](#updating-tasks)
  - [Finding & Deleting](#finding--deleting)
  - [Tagging](#tagging)
  - [Exiting](#exiting)
- [Example Session](#example-session)
- [Error Handling](#-error-handling)
- [Storage Persistence](#storage-persistence)
- [Command Reference](#command-reference)

---

## Getting Started

1. Install **Java 17 or above**.
2. Download the latest release from the <a href="https://github.com/Angmar2722/ip/releases" target="_blank">releases</a> page.
3. Place `focus.jar` in a folder of your choice.
4. Run in ```java -jar focus.jar``` in terminal.
5. Start typing commands in the input box. Press **enter** or click the **Send** button at the bottom of the pane to execute commands.

---

## How to Use

### Adding Tasks
- **To Do task type**: `todo DESCRIPTION`  
  Example usage:
  ```
  todo read book
  ```

- **Deadline**: `deadline DESCRIPTION /by yyyy-MM-dd HHmm`  
  Example:
  ```
  deadline submit report /by 2025-10-15 1800
  ```

- **Event**: `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`  
  Example:
  ```
  event team meeting /from 2025-10-20 1000 /to 2025-10-20 1130
  ```

---

### Viewing Tasks
- **List all**: `list`  
  Shows all tasks with status icons (tasks marked done have a X next to them).

---

### Updating Tasks
- **Mark as done**: `mark INDEX [INDEX …]`
- **Unmark as not done**: `unmark INDEX [INDEX …]`  
  Supports multiple indices:
  ```
  mark 1 2 3
  unmark 1 2 3
  ```

---

### Tagging
- **Tag**: `tag INDEX #DESCRIPTION`
  Currently limited to one tag per task.

---

### Finding & Deleting
- **Find by keyword**: `find KEYWORD`
- **Delete**: `delete INDEX`

---

### Exiting
- Simply type in `bye`

---

## Example Session
```
todo read book
deadline submit report /by 2025-10-15 1800
event project sync /from 2025-10-20 1000 /to 2025-10-20 1130
list
mark 2
unmark 2
find report
delete 3
bye
```
---

## ⚠ Error Handling
Worry not, Focus will guide you:
- Error messages will be displayed by Focus with suggested fixes
- Errors will be displayed in light red text bubbles to distinguish from ordinary commands

---

## Storage Persistence
- Tasks are saved automatically to `data/focus.txt` after any modification.
- On startup, Focus loads tasks from the same file.

---

## Command Reference

| Action   | Format | Example |
|----------|--------|---------|
| ToDo     | `todo DESCRIPTION` | `todo read book` |
| Deadline | `deadline DESCRIPTION /by yyyy-MM-dd HHmm` | `deadline submit report /by 2025-10-15 1800` |
| Event    | `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm` | `event project meeting /from 2025-10-20 1000 /to 1130` |
| List     | `list` | — |
| Mark     | `mark INDEX [INDEX …]` | `mark 1 2 3` |
| Unmark   | `unmark INDEX [INDEX …]` | `unmark 2` |
| Delete   | `delete INDEX` | `delete 3` |
| Find     | `find KEYWORD` | `find report` |
| Find     | `tag INDEX #DESCRIPTION` | `tag 2 #meeting` |
| Exit     | `bye` | — |

---