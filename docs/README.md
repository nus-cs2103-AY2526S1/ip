# Tinkerton User Guide

Tinkerton is a simple task manager that helps you keep track of your tasks, deadlines, and events. You can add, mark, unmark, delete, list, find, and show tasks by date using intuitive commands.

---

## Features & Commands

### 1. Add a ToDo

Adds a simple task with a name.

**Usage:**

```
todo <task name>
```

**Example:**

```
todo read book
```

**Expected Output:**

```
Got it, I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

---

### 2. Add a Deadline

Adds a task with a deadline.

**Usage:**

```
deadline <task name> /by <yyyy-MM-dd HHmm>
```

**Example:**

```
deadline submit assignment /by 2025-09-15 2359
```

**Expected Output:**

```
Got it, I've added this task:
[D][ ] submit assignment (by: 15 September 2025 23:59)
Now you have 2 tasks in the list.
```

---

### 3. Add an Event

Adds an event with a start and end time.

**Usage:**

```
event <event name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
```

**Example:**

```
event project meeting /from 2025-09-16 1400 /to 2025-09-16 1600
```

**Expected Output:**

```
Got it, I've added this task:
[E][ ] project meeting (from: 16 September 2025 14:00 to: 16 September 2025 16:00)
Now you have 3 tasks in the list.
```

---

### 4. List All Tasks

Shows all tasks in your list.

**Usage:**

```
list
```

**Expected Output:**

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit assignment (by: 15 September 2025 23:59)
3. [E][ ] project meeting (from: 16 September 2025 14:00 to: 16 September 2025 16:00)
```

---

### 5. Mark a Task as Done

Marks a task as completed.

**Usage:**

```
mark <task number>
```

**Example:**

```
mark 2
```

**Expected Output:**

```
Nice! I've marked this task as done:
[D][X] submit assignment (by: 15 September 2025 23:59)
```

---

### 6. Unmark a Task

Marks a task as not completed.

**Usage:**

```
unmark <task number>
```

**Example:**

```
unmark 2
```

**Expected Output:**

```
OK, I've marked this task as not done yet:
[D][ ] submit assignment (by: 15 September 2025 23:59)
```

---

### 7. Delete a Task

Deletes a task from your list.

**Usage:**

```
delete <task number>
```

**Example:**

```
delete 1
```

**Expected Output:**

```
Noted, I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.
```

---

### 8. Find Tasks by Keyword

Finds tasks containing a specific keyword.

**Usage:**

```
find <keyword>
```

**Example:**

```
find assignment
```

**Expected Output:**

```
Here are the matching tasks in your list:
1. [D][ ] submit assignment (by: 15 September 2025 23:59)
```

---

### 9. Show Tasks on a Specific Date

Shows tasks occurring on a specific date.

**Usage:**

```
show /on <yyyy-MM-dd>
```

**Example:**

```
show /on 2025-09-15
```

**Expected Output:**

```
Here are the tasks on that day
1. [D][ ] submit assignment (by: 15 September 2025 23:59)
```

---

### 10. Exit

Exits the application.

**Usage:**

```
bye
```

**Expected Output:**

```
Bye. Hope to see you again soon!
```

---

## Notes

- Dates and times must be in the format `yyyy-MM-dd HHmm` (e.g., `2025-09-15 2359`).
- Task numbers refer to the order shown in the `list` command.
