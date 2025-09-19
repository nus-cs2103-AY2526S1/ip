# John User Guide

![Product screenshot](./Ui.png)

John is a lightweight, fast task manager that works in both **JavaFX GUI** and **CLI**. It supports **to-dos, deadlines, events, search, marking/unmarking, deletion**, and **postponing**. Data is saved automatically to a simple text file.

---

## Getting Started

* **Requirements:** Java 11+
* **Run (GUI):**

  ```bash
  java -cp out john.core.Main
  ```
* **Run (CLI):**

  ```bash
  java -cp out john.core.John
  ```
* **Default storage:** `./data.txt` (next to the app).
  Override with `-Djohn.data=/path/to/tasks.txt`.

---

## Adding deadlines

Adds a task with a **due date/time**.

* **Command format:**

  ```
  deadline <description> /by <dd/MM/yyyy HH:mm:ss>
  ```
* **What it does:** Creates a deadline and saves it.

**Examples**

* `deadline Submit report /by 05/09/2025 16:30:45`
* `deadline CS2103 iP v1.0 /by 01/10/2025 23:59:00`

**Expected outcome**

```
Got it. I've added this task:
[D][ ] Submit report (by: 05/09/2025 16:30:45)
Now you have 1 tasks in the list.
```

> ⚠️ Date/time format is strict: `dd/MM/yyyy HH:mm:ss` (e.g., `05/09/2025 16:30:45`).

---

## Adding events

Adds a task with a **start** and **end** time.

* **Command format:**

  ```
  event <description> /from <dd/MM/yyyy HH:mm:ss> /to <dd/MM/yyyy HH:mm:ss>
  ```

**Example**

```
event Project demo /from 05/09/2025 09:00:00 /to 05/09/2025 10:00:00
```

**Expected outcome**

```
Got it. I've added this task:
[E][ ] Project demo (from: 05/09/2025 09:00:00 to: 05/09/2025 10:00:00)
Now you have 2 tasks in the list.
```

---

## Adding to-dos

Adds a simple task with **no time**.

* **Command format:**

  ```
  todo <description>
  ```

**Example**

```
todo Buy milk
```

**Expected outcome**

```
Got it. I've added this task:
[T][ ] Buy milk
Now you have 3 tasks in the list.
```

---

## Managing tasks (mark, unmark, delete, list)

* **List all tasks**

  ```
  list
  ```

  **Example output**

  ```
  1. [D][ ] Submit report (by: 05/09/2025 16:30:45)
  2. [E][ ] Project demo (from: 05/09/2025 09:00:00 to: 05/09/2025 10:00:00)
  3. [T][ ] Buy milk
  ```

* **Mark as done / not done (1-based index)**

  ```
  mark <task-number>
  unmark <task-number>
  ```

  **Example**

  ```
  mark 1
  ```

  **Expected**

  ```
  Nice! I've marked this task as done:
  [D][X] Submit report (by: 05/09/2025 16:30:45)
  ```

* **Delete a task**

  ```
  delete <task-number>
  ```

  **Expected**

  ```
  Noted. I've removed this task:
  [T][ ] Buy milk
  Now you have 2 tasks in the list.
  ```

---

## Finding tasks

Search by substring (case-insensitive) in the task description.

* **Command format:**

  ```
  find <substring>
  ```

**Example**

```
find demo
```

**Expected**

```
1. [E][ ] Project demo (from: 05/09/2025 09:00:00 to: 05/09/2025 10:00:00)
```

---

## Postponing (rescheduling)

* **Deadlines:** set a new `/by` time

  ```
  postpone <task-number> /by <dd/MM/yyyy HH:mm:ss>
  ```

* **Events:** set both `/from` and `/to`

  ```
  postpone <task-number> /from <dd/MM/yyyy HH:mm:ss> /to <dd/MM/yyyy HH:mm:ss>
  ```

**Examples**

```
postpone 1 /by 06/09/2025 18:00:00
postpone 2 /from 07/09/2025 10:00:00 /to 07/09/2025 11:00:00
```

**Expected**

```
Noted. I've updated this task:
[D][ ] Submit report (by: 06/09/2025 18:00:00)
```

```
Noted. I've updated this task:
[E][ ] Project demo (from: 07/09/2025 10:00:00 to: 07/09/2025 11:00:00)
```

---

## Exiting

* **Command:** `bye`
  In the GUI, John shows the farewell message and then closes the window shortly after.

---

## Data & storage

* Tasks are saved **automatically** after changes.
* Default path: `./data.txt` (next to the app).
  Override with `-Djohn.data=/path/to/tasks.txt`.

---

## Troubleshooting

* **“Please provide a valid task number …”** → The index is out of bounds; check `list`.
* **“Invalid date/time. Expected: dd/MM/yyyy HH\:mm\:ss”** → Fix the format (e.g., `05/09/2025 16:30:45`).
* **File permissions** → Ensure the configured path is writable.

---

## Keyboard cheatsheet (CLI)

1. Type a command (e.g., `todo Read book`).
2. Press **Enter**.
3. Use `list` frequently to confirm changes.

Happy tasking!
