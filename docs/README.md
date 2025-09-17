# Boop User Guide

![Boop Ui Image](Ui.png)

Boop is a desktop app for managing tasks, optimized for use via a **Command Line Interface (CLI)** while retaining GUI benefits. If you can type fast, Boop makes task management efficient, fun, and engaging.

## Quick start

1.  Ensure you have **Java 17 or above** installed on your computer.
2.  Download the latest Boop `.jar` file from [Releases](https://github.com/EugeneOYZ1203n/ip/releases).
3.  Open a command terminal, `cd` into the folder with the downloaded `.jar` file, and run:

    ```bash
    java -jar boop.jar
    ```

4.  A GUI similar to the screenshot above should appear.
5.  Type commands into the command box and press **Enter** to execute.

    Example commands you can try:

    - `list` : Lists all tasks.
    - `todo Finish CS2103T iP` : Adds a new Todo task.
    - `deadline Submit report /by 2025-09-30` : Adds a Deadline task.
    - `event Party /from 18:00 /to 23:00` : Adds an Event task.
    - `undo` : Undoes the last action.
    - `bye` : Exits Boop.

## Features

**Notes about the command format**:

- Words in `UPPER_CASE` are parameters.  
   Example: in `todo TASK`, `TASK` is a parameter.
- Commands are case-sensitive.

- Aliases are provided for some flags, they will be included in the format as `/flag/f` where both `/flag` and `/f` are valid
  Example: `deadline Submit report /by 2025-09-30` and `deadline Submit report /b 2025-09-30` are both valid

- Index numbering starts from 1. When in doubt use `list` and follow the numbering given there

---

### Adding a todo: `todo`

Adds a simple todo task.

**Format:**

`todo TASK`

**Examples:**

`todo  Finish  reading  book`
`todo  Write  AI.md  file`

**Expected outcome:**

`Affirmative. Task  recorded: [T][ ]  Finish  reading  book  Now  you  have  3  tasks  in  the  list.`

---

### Adding a deadline: `deadline`

Adds a task with a deadline.

**Format:**

`deadline TASK /by/b  DATE`

**Examples:**

`deadline Submit report /by 2025-09-30`
`deadline CS2103T assignment /b 2025-10-15`

**Expected outcome:**

`Acknowledged. Deadline  registered: [D][ ]  Submit  report (by: 2025-09-30) Now  you  have  4  tasks  in  the  list.`

---

### Adding an event: `event`

Adds a task with a start and end time.

**Format:**

`event TASK /from/f START /to/t  END`

**Examples:**

`event Team meeting /from  10:00 /to  12:00  event Party /f  18:00 /t  23:00`

**Expected outcome:**

`Confirmed. Event  scheduled: [E][ ]  Party (from: 18:00  to: 23:00) Now  you  have  5  tasks  in  the  list.`

---

### Listing all tasks: `list`

Shows a list of all tasks saved.

**Format:**

`list`

---

### Finding tasks: `find`

Filters tasks that contain the specified keyword(s).

**Format:**

`find KEYWORD`

**Example:**

`find report`

**Expected outcome:**

`Here  are  the  matching  tasks  in  your  list: 1. [D][ ]  Submit  report (by: 2025-09-30)`

---

### Marking a task: `mark`

Marks the specified task as completed.

**Format:**

`mark INDEX`

**Example:**

`mark  1`

**Expected outcome:**

`Affirmative. Task marked as done:
	[T][X] Finish reading book`

---

### Unmarking a task: `unmark`

Marks the specified task as not done yet.

**Format:**

`unmark INDEX`

**Example:**

`unmark  1`

---

### Deleting a task: `delete`

Deletes the task at the specified index.

**Format:**

`delete  INDEX`

**Example:**

`delete  2`

---

### Undoing the last action: `undo`

Reverts the most recent action (add, delete, mark, unmark).

**Format:**

`undo`

**Example:**

`undo`

**Expected outcome:**

`Affirmative. Previous  action  undone: [D][ ]  Submit  report (by: 2025-09-30) Now  you  have  3  tasks  in  the  list.`

---

### Exiting the program: `bye`

Exits Boop.

**Format:**

`bye`

## Saving the data

Boop automatically saves tasks after every change. There is no need to save manually.

## Editing the data file

Tasks are saved locally in a text file. Advanced users may edit the file directly, but caution is advised — incorrect edits may corrupt the data.

## FAQ

**Q: How do I transfer my tasks to another computer?**  
A: Install Boop on the new machine and replace its save file with the one from your old machine.
