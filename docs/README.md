# Cheryl Chatbot User Guide

Welcome to **Cheryl**, your personal task management chatbot.  
Cheryl helps you keep track of tasks, deadlines, and events directly from the command line or GUI.

---

## Getting Started

1. **Download & Run**
    - Clone this repository and build the project with Gradle/IntelliJ.
    - Run the program using:
      ```
      java -jar cheryl.jar
      ```
    - The chatbot will create/save tasks in a `data/task.txt` file.

2. **Saving & Loading**
    - Tasks are automatically saved after every change.
    - On restart, tasks are loaded back from the file.

---

## Features

### 1. Add To-Do
Add a simple task without a date.


### 2. Add Deadline
Add a task with a due date.

Accepted date formats:
- `yyyy-MM-dd` (preferred)
- `dd-MM-yyyy`
- `dd/MM/yyyy`

### 3. Add Event
Add an event with a start and end date.


### 4. List Tasks
Show all tasks in your list.


### 5. Mark/Unmark Tasks
Mark a task as done:
Unmark a task:


### 6. Delete Task
Remove a task from your list.


### 7. Find Tasks
Search for tasks by keyword.


### 8. View Schedule
Show all tasks occurring on a given date.


### 9. Exit
Quit the chatbot.


---

## Notes
- Task indices (e.g., for `mark`, `unmark`, `delete`) are **1-based**.
- Tasks are saved in `data/task.txt`. If the file is corrupted, Cheryl starts with an empty list.
- Cheryl supports both **CLI** and **GUI** mode.

---

## Example Session
Hello! I'm Cheryl.
What can I do for you?

todo read book
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.

deadline return book /by 2025-10-01
Got it. I've added this task:
[D][ ] return book (by: Oct 1 2025)
Now you have 2 tasks in the list.

list
1.[T][ ] read book
2.[D][ ] return book (by: Oct 1 2025)

mark 1
Nice! I've marked this task as done:
[T][X] read book

bye
Bye. Hope to see you again soon!


---

## Acknowledgements
- Based on the [iP project template](https://github.com/se-edu).
- AI assistance was used (e.g., GitHub Copilot, ChatGPT) to improve code quality and documentation.
