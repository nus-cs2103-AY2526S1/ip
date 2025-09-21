# Oreo User Guide

![Oreo GUI](../docs/Ui.png)

Oreo is a task management application designed to help users organize and track their tasks efficiently. This guide provides an overview of the key features and functionalities of Oreo.

---

## Features

- [**List all tasks**: `list`](#list-all-tasks-list)
- [**Mark a task as completed**: `mark`](#mark-a-task-as-completed-mark)
- [**Unmark a task as not completed**: `unmark`](#unmark-a-task-as-not-completed-unmark)
- [**Add a todo task**: `todo`](#add-a-todo-task-todo)
- [**Add a deadline task**: `deadline`](#add-a-deadline-task-deadline)
- [**Add an event task**: `event`](#add-an-event-task-event)
- [**Delete a task**: `delete`](#delete-a-task-delete)
- [**Find tasks by keyword**: `find`](#find-tasks-by-keyword-find)
- [**Set a note to a task**: `setnote`](#set-a-note-to-a-task-setnote)
- [**View the note of a task**: `getnote`](#view-the-note-of-a-task-getnote)
- [**Exit the application**: `bye`](#exit-the-application-bye)

### **List all tasks**: `list`
Lists out all the tasks in the task list along with their status (completed or not completed) and any associated deadline or event dates.

Format: `list`

```
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] submit assignment (by: Sep 24 2025)
3.[E][ ] recess week (from: Sep 22 2025 to: Sep 26 2025)
```

### **Mark a task as completed**: `mark`
Marks a specified task as completed.

Format: `mark <task_number>`

Example: `mark 2`
```
Here are the tasks in your list:
1.[T][X] read book
2.[D][X] submit assignment (by: Sep 24 2025)
3.[E][ ] recess week (from: Sep 22 2025 to: Sep 26 2025)
```

### **Unmark a task as not completed**: `unmark`
Unmarks a specified task as not completed.

Format: `unmark <task_number>`

Example: `unmark 1`
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][X] submit assignment (by: Sep 24 2025)
3.[E][ ] recess week (from: Sep 22 2025 to: Sep 26 2025)
```

### **Add a todo task**: `todo`
Adds a new todo task to the task list.

Format: `todo <task_name>`

Example: `todo study for exam`
```
Got it. I've added this task:
[T][ ] study for exam
Now you have 4 tasks in the list.
```

### **Add a deadline task**: `deadline`
Adds a new deadline task to the task list with a specified due date.

Format: `deadline <task_name> /by <due_date>`

> Date format: `dd-MM-yyyy`

Example: `deadline submit report /by 01-10-2025`
```
Got it. I've added this task:
[D][ ] submit report (by: Oct 01 2025)
Now you have 5 tasks in the list.
```

### **Add an event task**: `event`
Adds a new event task to the task list with specified start and end dates.

Format: `event <task_name> /from <start_date> /to <end_date>`
> Date format: `dd-MM-yyyy`

Example: `event conference /from 15-11-2025 /to 17-11-2025`
```
Got it. I've added this task:
[E][ ] conference (from: Nov 15 2025 to: Nov 17 2025)
Now you have 6 tasks in the list.
```

### **Delete a task**: `delete`
Deletes a specified task from the task list.

Format: `delete <task_number>`

Example: `delete 1`
```
Noted. I've removed this task:
[T][ ] read book
Now you have 5 tasks in the list.
```

### **Find tasks by keyword**: `find`
Finds and lists all tasks that contain the specified keyword in their name.

Format: `find <keyword>`

Example: `find submit`
```
Here are the matching tasks in your list:
1.[D][X] submit assignment (by: Sep 24 2025)
2.[D][ ] submit report (by: Oct 01 2025)
```

### **Set a note to a task**: `setnote`
Sets a note for a specified task.

Format: `setnote <task_number> <note_content>`

Example: `setnote 3 CS2100 midterm exam`
```
Noted. I've added the note to this task:
[T][ ] study for exam
```

### **View the note of a task**: `getnote`
Retrieves and displays the note associated with a specified task.

Format: `getnote <task_number>`

Example: `getnote 3`
```
Here is the note for this task:
CS2100 midterm exam
```

### **Exit the application**: `bye`
Exits the Oreo application.

Format: `bye`

```
Bye. Hope to see you again soon!
```
