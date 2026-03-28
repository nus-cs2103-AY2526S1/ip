# Timmy User Guide

Welcome to **Timmy** – your personal assistant that helps you record and manage your tasks through a simple command-line interfac.  

This guide will walk you through the main features and usage so you can start right away.

---

## Getting Started

1. **Run Timmy**  
   Make sure you have Java 17 or above installed.
   
   Then, download the latest .jar file.

   Open a command terminal, `cd` into the folder you put the jar file in, and enter `java -jar Timmy.jar` to run the application.



2. **First interaction**
   Once started, the chatbot will greet you and wait for your input. Type a command and press **Enter**.

---

## Features

### 1. Add Tasks

You can add different types of tasks to the chatbot:

* **Todo**:

  ```text
  todo Read Book
  ```
* **Deadline** (with `by`):

  ```text
  deadline Submit Assignment /by 30/10/2025
  ```
* **Event** (with `at`):

  ```text
  event Sports Festival /from 01/10/2025 /to 09/10/2025
  ```

### 2. List Tasks

View all tasks currently stored:

```text
list
```

### 3. Mark/Unmark as Done

Mark or unmark a task as completed by its index number:

```text
mark 2
unmark 2
```

### 4. Delete Tasks

Remove a task permanently:

```text
delete 3
```

### 5. Clear all Tasks

Remove all tasks permanently:

```text
clear
```

### 6. Find Tasks

Search for tasks by keyword:

```text
find book
```

### 7. Save & Load

All tasks are automatically saved to a file (`data/storage.txt`) and reloaded the next time you start the program. You don’t have to worry about losing your progress.

### 8. Archive Tasks

Archive tasks into a file (`data/archive-{}.txt`):

```text
archive
```

Tasks are removed from Timmy's memory once archived. To restore an archive, replace the contents of `data/storage.txt` with the archive file.

---

## Command Summary

| Command Example                       | What it does                    |
|---------------------------------------| ------------------------------- |
| `todo read book`                      | Adds a todo task                |
| `deadline return book /by 30/9/2025`  | Adds a deadline task            |
| `event project meeting /at 1/10/2025` | Adds an event task              |
| `list`                                | Lists all tasks                 |
| `mark 2`                              | Marks task #2 as done           |
| `unmark 2`                            | Unmarks task #2 as done         |
| `delete 3`                            | Deletes task #3                 |
| `clear`                               | Deletes all tasks               |
| `find book`                           | Finds tasks with "book" in them |
| `archive`                             | Archives existing tasks         |
| `bye`                                 | Exits the chatbot               |

---

## Exiting the Program

To quit, simply type:

```text
bye
```

Your tasks will be saved automatically.

---

## Support

If you encounter issues, please check:

* That you are running on Java 17 or later.
