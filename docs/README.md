# Jackbot User Guide

![Product Screenshot](docs/Ui.png)

Jackbot is a lightweight task manager with a **JavaFX GUI** (default) and a **CLI**. Add todos, deadlines, and events; list, find, mark/unmark, and delete tasks. Data is persisted to a task file (default `./tasks.txt`), and you can choose a different file via `--file`.

---

## Running Jackbot

- GUI (default)
    ./gradlew run

- CLI
    ./gradlew run --args="--cli"

- Choose a different data file (works in GUI or CLI)
    # GUI with custom file
    ./gradlew run --args="--file /path/to/mytasks.txt"

    # CLI with custom file
    ./gradlew run --args="--cli --file /path/to/mytasks.txt"

> If `--file` is omitted, Jackbot uses `./tasks.txt`. If the file doesn’t exist yet, it’s created on first save.

---

## Managing Tasks

### Add tasks

#### Add Todo
Usage
    todo <description>

Example
    todo read book

Expected outcome
    ____________________________________________________________

    Got it. I've added this task
      [T][ ] read book
    Now you have 1 tasks in the list.
    ____________________________________________________________

Tips & Notes
- Use concise, searchable descriptions (helps `find` later).
- Todos have no time component—use deadline or event if you need dates.

#### Add Event
Usage
    event <description> /from yyyy-MM-dd HH:mm:ss /to yyyy-MM-dd HH:mm:ss

Example
    event team sync /from 2025-10-01 09:00:00 /to 2025-10-01 10:00:00

Expected outcome
    ____________________________________________________________

    Got it. I've added this task
      [E][ ] team sync (from: 2025-10-01T09:00 to: 2025-10-01T10:00)
    Now you have 2 tasks in the list.
    ____________________________________________________________

Tips & Notes
- Date-time format is yyyy-MM-dd HH:mm:ss.
- Ensure /from precedes /to, and that the end time is after the start time.
- If times are malformed or missing, you’ll see a clear error message.

#### Add Deadline
Usage
    deadline <description> /by yyyy-MM-dd HH:mm:ss

Examples
    deadline return book /by 2025-12-31 23:59:59
    deadline submit report /by 2025-10-01 09:00:00

Expected outcome
    ____________________________________________________________

    Got it. I've added this task
      [D][ ] return book (by: 2025-12-31T23:59:59)
    Now you have 3 tasks in the list.
    ____________________________________________________________

Tips & Notes
- Date-time format is yyyy-MM-dd HH:mm:ss.
- Missing or malformed /by produces an error; fix the format and retry.

---

## Listing Tasks

Usage
    list

Expected outcome
    ____________________________________________________________

    Your previous entries:
    1. [T][ ] read book
    2. [E][ ] team sync (from: 2025-10-01T09:00 to: 2025-10-01T10:00)
    3. [D][ ] return book (by: 2025-12-31T23:59:59)
    ____________________________________________________________

Tips & Notes
- Indices are 1-based and used by mark, unmark, and delete.
- The list reflects the current, in-memory order of tasks.

---

## Mark & Unmark

Usage
    mark <index>
    unmark <index>

Examples
    mark 1
    unmark 1

Expected outcomes
    ____________________________________________________________

    Nice, I've marked this task as done:
      [T][X] read book
    ____________________________________________________________

    ____________________________________________________________

    OK, I've marked this task as not done:
      [T][ ] read book
    ____________________________________________________________

Tips & Notes
- <index> must refer to an existing task from list.
- You’ll get “Task not found” if the index is out of range.

---

## Delete

Usage
    delete <index>

Example
    delete 2

Expected outcome
    ____________________________________________________________

    Noted. I've removed this task:
      [E][ ] team sync (from: 2025-10-01T09:00 to: 2025-10-01T10:00)
    Now you have 2 tasks in the list.
    ____________________________________________________________

Tips & Notes
- Deletion is immediate and persisted to your task file.
- Use list first to confirm the right index.

---

## Find

Usage
    find <keyword>

Example
    find book

Expected outcome
    ____________________________________________________________

    Here are the matching tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: 2025-12-31T23:59:59)
    ____________________________________________________________

Tips & Notes
- Case-insensitive substring matching on task descriptions.
- If no matches are found, you’ll see:
      Here are the matching tasks in your list:
      (none)

---

## Exit

Usage
    bye

Expected outcome
    ____________________________________________________________

    Bye. Hope to see you again soon!
    ____________________________________________________________

Tips & Notes
- In the GUI, the input field is disabled after bye.
- All changes are already saved by the time you exit.

---
