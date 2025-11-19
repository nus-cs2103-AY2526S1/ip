# Coffee User Guide

Coffee is a simple desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).  
If you can type fast, Coffee helps you get your task management done faster than traditional GUI apps.

- Quick start
- Features
    - Viewing help : help
    - Adding a ToDo : todo
    - Adding a Deadline : deadline
    - Adding an Event : event
    - Listing tasks : list
    - Marking/Unmarking tasks : mark / unmark
    - Deleting tasks : delete
    - Finding tasks : find
    - Exiting the program : bye
    - Error handling
    - Data storage
- Command summary

---

## Quick start

1. Ensure you have Java 17 or above installed on your computer.
2. Download the latest `coffee.jar` file from the releases page.
3. Copy the file to the folder you want to use as the home folder for Coffee.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the command:

   ```
   java -jar coffee.jar
   ```

5. Type commands in the command box and press Enter to execute them.  
   e.g., typing `help` and pressing Enter will show the help instructions.
6. Some example commands you can try:
    - `todo read book` → Adds a ToDo task.
    - `deadline submit report /by 2024-06-10 1800` → Adds a Deadline task.
    - `event team meeting /from 2024-06-11 1400 /to 2024-06-11 1500` → Adds an Event.
    - `list` → Lists all tasks.
    - `bye` → Exits the application.

Refer to the **Features** section below for details of each command.

---

## Features

:information_source: Notes about the command format:

- Words in `UPPER_CASE` are parameters supplied by the user.  
  e.g., in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
- Items in square brackets are optional.
- Parameters can be in any order **only where clearly supported**.
- Extraneous parameters for commands that do not take parameters (such as `list`, `bye`) will be ignored.  
  e.g., `list 123` will be interpreted as `list`.

---

### Adding a ToDo : `todo`

Adds a simple task without time.

**Format:**  
`todo DESCRIPTION`

**Example:**  
`todo read book`

**Expected Output:**
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

---

### Adding a Deadline : `deadline`

Adds a task with a deadline.

**Format:**  
`deadline DESCRIPTION /by yyyy-MM-dd HHmm`

**Example:**  
`deadline submit report /by 2024-06-10 1800`

**Expected Output:**
```
Got it. I've added this task:
[D][ ] submit report (by: 2024-06-10 1800)
Now you have 2 tasks in the list.
```

Note: Duplicate deadlines at the same date/time are not allowed.

---

### Adding an Event : `event`

Adds an event with a start and end time.

**Format:**  
`event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`

**Example:**  
`event team meeting /from 2024-06-11 1400 /to 2024-06-11 1500`

**Expected Output:**
```
Got it. I've added this task:
[E][ ] team meeting (from: 2024-06-11 1400 to: 2024-06-11 1500)
Now you have 3 tasks in the list.
```

Note: Events cannot overlap in time. Input format is validated.

---

### Listing tasks : `list`

Shows all tasks in the list.

**Format:**  
`list`

---

### Marking/Unmarking tasks : `mark` / `unmark`

Marks a task as done or not done.

**Format:**
- `mark INDEX`
- `unmark INDEX`

---

### Deleting tasks : `delete`

Deletes one or more tasks by index.

**Format:**  
`delete INDEX [INDEX ...]`

- The index must be a positive integer.
- Multiple indices can be provided.
- Invalid indices will show an error.

---

### Finding tasks : `find`

Searches for tasks containing a keyword.

**Format:**  
`find KEYWORD`

**Example:**  
`find report`

---

### Exiting the program : `bye`

Exits the program.

**Format:**  
`bye`

---

### Error handling

- Invalid date/time formats show a clear error.
- Overlapping events and duplicate deadlines are prevented.
- Invalid indices for delete/mark/unmark show an error.

---

### Data storage

Coffee saves tasks automatically to `data/tasks.txt` after any command that changes the data.  
Tasks are loaded from the file on startup.

---

## Example session

```
todo read book
deadline submit report /by 2024-06-10 1800
event team meeting /from 2024-06-11 1400 /to 2024-06-11 1500
list
mark 2
delete 1
find report
bye
```

---

## FAQ

**Q: How do I transfer my data to another computer?**  
A: Install the app in the other computer and copy over the `data/tasks.txt` file from your old Coffee home folder.

---


## Command summary

| Action     | Format & Example |
|------------|------------------|
| ToDo       | `todo DESCRIPTION`<br>e.g., `todo read book` |
| Deadline   | `deadline DESCRIPTION /by yyyy-MM-dd HHmm`<br>e.g., `deadline submit report /by 2024-06-10 1800` |
| Event      | `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`<br>e.g., `event team meeting /from 2024-06-11 1400 /to 2024-06-11 1500` |
| List       | `list` |
| Mark       | `mark INDEX`<br>e.g., `mark 2` |
| Unmark     | `unmark INDEX`<br>e.g., `unmark 2` |
| Delete     | `delete INDEX [INDEX ...]`<br>e.g., `delete 1 3` |
| Find       | `find KEYWORD`<br>e.g., `find report` |
| Exit       | `bye` |

---

## Contributors

Dong Jun