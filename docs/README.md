# Simon User Guide

![Product Screenshot](docs/Ui.png)

Simon is a simple, interactive task management chatbot application. It helps you keep track of your tasks, deadlines, events, and more, all through a friendly chat-based interface. Simon supports both command-line and GUI (JavaFX) modes.

---

## Features

### Adding Tasks

#### Todo
Add a basic task with a description.

**Usage:**  
```
todo <description>
```
**Example:**  
```
todo read book
```
**Expected Output:**  
```
Added: [T][ ] read book
```

#### Deadline
Add a task with a deadline.

**Usage:**  
```
deadline <description> /by <date>
```
**Example:**  
```
deadline submit report /by 2025-09-30
```
**Expected Output:**  
```
Added: [D][ ] submit report (by: Sep 30 2025)
```

#### Event
Add an event with a start and end time.

**Usage:**  
```
event <description> /from <start> /to <end>
```
**Example:**  
```
event project meeting /from 2025-09-20 /to 2025-09-20
```
**Expected Output:**  
```
Added: [E][ ] project meeting (from: Sep 20 2025 to: Sep 20 2025)
```

---

### Listing Tasks

View all tasks in your list.

**Usage:**  
```
list
```
**Expected Output:**  
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit report (by: Sep 30 2025)
3. [E][ ] project meeting (from: Sep 20 2025 to: Sep 20 2025)
```

---

### Marking/Unmarking Tasks

Mark a task as done or undo it.

**Usage:**  
```
mark <task index>
unmark <task index>
```
**Example:**  
```
mark 2
```
**Expected Output:**  
```
Nice! I've marked this task as done:
[D][X] submit report (by: Sep 30 2025)
```

---

### Deleting Tasks

Remove a task from your list.

**Usage:**  
```
delete <task index>
```
**Example:**  
```
delete 1
```
**Expected Output:**  
```
Noted. I've removed this task:
[T][ ] read book
```

---

### Finding Tasks

Search for tasks containing a keyword.

**Usage:**  
```
find <keyword>
```
**Example:**  
```
find report
```
**Expected Output:**  
```
Here are the matching tasks in your list:
1. [D][ ] submit report (by: Sep 30 2025)
```

---

### Viewing Tasks on a Specific Date

**Usage:**  
```
on <date>
```
**Example:**  
```
on 2025-09-20
```
**Expected Output:**  
```
Tasks on Sep 20 2025:
[E][ ] project meeting (from: Sep 20 2025 to: Sep 20 2025)
```

---

### Help

Show a list of available commands.

**Usage:**  
```
help
```
**Expected Output:**  
```
Available commands:
list - List all tasks
todo <description> - Add a todo
deadline <description> /by <date> - Add a deadline
event <description> /from <start> /to <end> - Add an event
mark <index> - Mark a task as done
unmark <index> - Unmark a task
delete <index> - Delete a task
find <keyword> - Find tasks
on <date> - List tasks on a date
bye - Exit the app
help - Show this help message
```

---

### Exiting

Quit the application.

**Usage:**  
```
bye
```
**Expected Output:**  
```
Goodbye! Hope to see you again soon!
```

---

## Getting Started

1. Ensure you have Java 11 or above installed.
2. Clone this repository.
3. Run the app using the provided launcher or main class.

---

## Saving and Loading

- Tasks are automatically saved to a local file and loaded when you start the app.

---

## GUI Mode

- Simon features a JavaFX-based graphical interface.
- All commands can be entered in the chat window.
- Responses and tasks are displayed in a friendly dialog format.

---

## Support

If you encounter any issues or have suggestions, please open an issue in this repository.

---