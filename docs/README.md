# Jack User Guide

Welcome to **Jack**, your friendly personal task manager chatbot!

Jack helps you keep track of your todos, deadlines, and events, all from a simple chat interface. This guide will help you get started and make the most of Jack’s features.

---

## Quick Start

If you are an end user: you do not need to clone or build the source. A pre-built, runnable JAR is available on the project's Releases page — download the latest release directly and run the JAR.

1. Download the released jar
   - Visit the project's Releases page and download the JAR asset from the latest release: https://github.com/naresh2478/ip/releases (for example the asset may be named `jackApp.jar`).

2. Run the application
   - From a terminal (macOS / Linux / Windows PowerShell):
     ```bash
     java -jar path/to/jackApp.jar
     ```
     or, if the jar is in your current directory:
     ```bash
     java -jar ./jackApp.jar
     ```
   - Or simply double-click the jar file in your file manager (macOS / Windows) to launch the app (requires Java to be installed and the jar to be a runnable jar).

Notes for end users
- You do not need to download or build the project source — only the released runnable JAR is required.
- Make sure Java 11+ is installed on the target machine.

---

## Features

### 1. Add a Todo
- **Command:** `todo <description>`
- **Example:** `todo read book`

### 2. Add a Deadline
- **Command:** `deadline <description> /by <yyyy-MM-dd>`
- **Example:** `deadline submit report /by 2025-09-30`

### 3. Add an Event
- **Command:** `event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>`
- **Example:** `event project meeting /from 2025-10-01 /to 2025-10-01`

> Note: `event` uses `/from` and `/to` separators. The current implementation accepts simple date strings (yyyy-MM-dd) for start and end; avoid including time-of-day in the form `HH:mm` unless your application explicitly supports it.

### 4. List All tasks
- **Command:** `list`
- **Description:** Shows all your tasks with their status.

### 5. Mark a Task as Done
- **Command:** `mark <task number>`
- **Example:** `mark 2`

### 6. Unmark a Task
- **Command:** `unmark <task number>`
- **Example:** `unmark 2`

### 7. Delete a Task
- **Command:** `delete <task number>`
- **Example:** `delete 3`

### 8. Find tasks by Keyword
- **Command:** `find <keyword>`
- **Example:** `find book`

### 9. Sort Deadlines Chronologically
- **Command:** `sortdeadlines`
- **Description:** Sorts all your deadlines by date.

### 10. Exit Jack
- **Command:** `bye`

---

## Command Summary Table

| Command        | Format/Example                              | Description                       |
|----------------|---------------------------------------------|-----------------------------------|
| todo           | todo read book                              | Add a todo task                   |
| deadline       | deadline submit report /by 2025-09-30       | Add a deadline task               |
| event          | event project meeting /from 2025-10-01 /to 2025-10-01  | Add an event                      |
| list           | list                                        | List all tasks                    |
| mark           | mark 2                                      | Mark task 2 as done               |
| unmark         | unmark 2                                    | Unmark task 2                     |
| delete         | delete 3                                    | Delete task 3                     |
| find           | find book                                   | Find tasks with 'book'            |
| sortdeadlines  | sortdeadlines                               | Sort deadlines by date            |
| bye            | bye                                         | Exit the app                      |

---

## Tips
- Deadlines must be in the format `yyyy-MM-dd` (e.g., 2025-09-30).
- Events use `/from` and `/to` and generally accept simple date strings `yyyy-MM-dd` for start/end.
- Task numbers refer to the number shown in the `list` command.
- Jack saves your tasks automatically.

---

## Troubleshooting
- **Invalid command?** Jack will show an error message. Check your command format.
- **app not starting?** Ensure you have Java 11 or above installed.
- **Still stuck?** Check the [project README](../README.md) or contact the developer.

---

Enjoy being productive with **Jack**!
