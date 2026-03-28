# Alex User Guide

## First Look
![Alex UI](https://raw.githubusercontent.com/Prateek2030s/ip/refs/heads/master/docs/Ui.png)

Alex is a friendly task assistant that helps you keep track of tasks via a simple chat-style interface. You can add three types of tasks, list and find them, mark/unmark completion, delete tasks, and even create your own aliases for commands.

---

## Features

- **Three task types**: `todo`, `deadline`, `event`
- **List tasks**: See everything you’ve added
- **Find tasks**: Search by keyword
- **Mark / Unmark**: Track completion status
- **Delete tasks**: Remove items you no longer need
- **Aliases**: Create shorthand for any command, and view all aliases
- **Greetings & exit**: `hello` and `bye`

---

## Getting started

- Ensure you have Java 17 installed.
- Run the application (GUI launcher provided):

```bash
# Windows (PowerShell)
./gradlew.bat run

# macOS/Linux
./gradlew run
```

- Build a runnable JAR:

```bash
# Windows (PowerShell)
./gradlew.bat shadowJar

# macOS/Linux
./gradlew shadowJar
```

- Run the JAR:

```bash
# Windows (PowerShell)
java -jar build/libs/alex.jar

# macOS/Linux
java -jar build/libs/alex.jar
```

A GUI window with title as Alex should launch. Type commands into the input box and press Enter.

---

## Command summary

- **hello**: Greet Alex
- **list**: Show all tasks
- **todo <description>**: Add a ToDo
- **deadline <description> /by <YYYY-MM-DD>**: Add a Deadline
- **event <description> / <from> / <to>**: Add an Event
- **mark <task-number>**: Mark a task as done
- **unmark <task-number>**: Mark a task as not done
- **delete <task-number>**: Delete a task
- **find <keyword>**: Search tasks by keyword
- **alias roll**: Show all current aliases
- **alias <command> <alias-word>**: Create an alias for a command
- **bye**: Exit the app

> Tip: You can create aliases, e.g. `alias todo t` lets you type `t Finish IP` to add a todo.

---

## Usage details and examples

### Greeting the bot : `hello`
Shows a greeting from Alex.

Format: `hello`

Example:

```text
hello
```

Expected:

```text
Hello, I'm Alex. What do you want from me
```

---

### Listing all tasks : `list`
Shows a list of all tasks.

Format: `list`

Example:

```text
list
```

Expected:

```text
Here are the tasks in your list:
1.[T][ ] Read book
2.[D][ ] Project submission (by: Sep 30 2025)
```

---

### Adding a ToDo : `todo`
Adds a ToDo task.

Format: `todo DESCRIPTION`

Examples:

- `todo Read book`

Expected:

```text
Ok, I've added this task: [T][ ] Read book
Watch out, you have 3 tasks left.
```

---

### Adding a Deadline : `deadline`
Adds a task with a due date.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Examples:

- `deadline Project submission /by 2025-09-30`

Expected:

```text
Ok, I've added this task: [D][ ] Project submission (by: Sep 30 2025)
Watch out, you have 4 tasks left.
```

Notes:
- Date must be in `YYYY-MM-DD`. It is displayed as `MMM d yyyy`.

---

### Adding an Event : `event`
Adds an event with start and end.

Format: `event DESCRIPTION / FROM / TO`

Examples:

- `event Team meeting / 2025-10-01 14:00 / 2025-10-01 15:00`

Expected:

```text
Ok, I've added this task: [E][ ] Team meeting (from: 2025-10-01 14:00 to: 2025-10-01 15:00)
Watch out, you have 5 tasks left.
```

---

### Marking a task as done : `mark`
Marks a task as done by its number in the list.

Format: `mark INDEX`

Examples:

- `mark 2`

Expected:

```text
Nice! I've marked this task as done:
[D][X] Project submission (by: Sep 30 2025)
```

Notes:
- `INDEX` refers to the numbering shown by `list`. It must be a positive integer.

---

### Unmarking a task : `unmark`
Marks a task as not done by its number in the list.

Format: `unmark INDEX`

Examples:

- `unmark 2`

Expected:

```text
Ok, I've marked this task as not done yet:
[D][ ] Project submission (by: Sep 30 2025)
```

---

### Deleting a task : `delete`
Deletes a task by its number in the list.

Format: `delete INDEX`

Examples:

- `delete 3`

Expected:

```text
Ok, I've deleted this task: [T][ ] Read book
Watch out, you have 3 tasks left.
```

Notes:
- `INDEX` refers to the numbering shown by `list`. It must be a positive integer.

---

### Finding tasks by keyword : `find`
Finds tasks containing the given keyword (substring match).

Format: `find KEYWORD`

Examples:

- `find book`

Expected:

```text
Here are the matching tasks in your list:
1.[T][ ] Read book
```

---

### Viewing aliases : `alias roll`
Shows all current aliases.

Format: `alias roll`

Examples:

- `alias roll`

Expected:

```text
Here is your alias roll:
list: l
todo: t
... (and so on)
```

---

### Creating an alias : `alias`
Creates a new alias for a command.

Format: `alias COMMAND ALIAS_WORD`

Examples:

- `alias todo t`

Expected:

```text
Nice! I have added this alias:
todo: t
```

Notes:
- Once created, you can use the alias anywhere you would use the original command.

---

### Exiting the app : `bye`
Exits the application.

Format: `bye`

Examples:

- `bye`

Expected:

```text
Need to leave is it?
Goodbye then, see you again soon!
```

---

## Data storage

- Tasks are saved to `data/alex.txt`.
- Aliases are saved to `data/alias.txt`.
- Data is loaded on startup. If files are missing, Alex starts with empty lists and continues normally.

---

## Notes

- Date parsing for `deadline` expects `YYYY-MM-DD` and displays in `MMM d yyyy` (e.g., `Sep 30 2025`).
- Task numbers in `mark`, `unmark`, and `delete` refer to the numbering shown in `list`.
- Error messages are shown if input is invalid (e.g., missing fields or out-of-range task number).

---

## Formatting reference

This README follows GitHub Markdown basics. For more, see the GitHub docs: [Basic writing and formatting syntax](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax).

* This readme was created with the help of Cursor
