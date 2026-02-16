
# Kane: Your Personal Task Management Assistant

Welcome to **Kane**!  
Kane is a desktop application designed to help you manage your tasks with a simple and clean graphical user interface (GUI). It allows you to keep track of your **to-dos, deadlines, and events** right from your desktop.

---

## 🚀 How to Run the Application

1. Open your terminal or command prompt.  
2. Navigate to the project's root directory.  
3. Run the following command:

```bash
./gradlew run
````

The **Kane GUI window** will appear, ready for you to enter commands.

---

## ✨ Features at a Glance

| Command    | Description                              | Format                                                          |
| ---------- | ---------------------------------------- | --------------------------------------------------------------- |
| `todo`     | Adds a simple to-do task.                | `todo [description]`                                            |
| `deadline` | Adds a task with a deadline.             | `deadline [description] /by yyyy-MM-dd HHmm`                    |
| `event`    | Adds an event with start & end time.     | `event [description] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm` |
| `list`     | Shows all tasks in your list.            | `list`                                                          |
| `mark`     | Marks a task as completed.               | `mark [task number]`                                            |
| `unmark`   | Marks a task as not completed.           | `unmark [task number]`                                          |
| `delete`   | Removes a task from your list.           | `delete [task number]`                                          |
| `find`     | Searches for tasks containing a keyword. | `find [keyword]`                                                |
| `bye`      | Exits the application.                   | `bye`                                                           |

---

## 📖 Command Guide with Examples

### 1. Adding a To-Do

**Command:**

```text
todo read a book
```

**Response:**

```text
Got it. I've added this task:
  [T][ ] read a book
Now you have 1 task in the list.
```

---

### 2. Adding a Deadline

**Command:**

```text
deadline submit report /by 2024-09-30 2359
```

**Response:**

```text
Got it. I've added this task:
  [D][ ] submit report (by: Sep 30 2024, 11:59 PM)
Now you have 2 tasks in the list.
```

---

### 3. Adding an Event

**Command:**

```text
event project meeting /from 2024-10-05 1400 /to 2024-10-05 1600
```

**Response:**

```text
Got it. I've added this task:
  [E][ ] project meeting (from: Oct 05 2024, 2:00 PM to: Oct 05 2024, 4:00 PM)
Now you have 3 tasks in the list.
```

---

### 4. Listing All Tasks

**Command:**

```text
list
```

**Response:**

```text
Here are the tasks in your list:
1. [T][ ] read a book
2. [D][ ] submit report (by: Sep 30 2024 23:59)
3. [E][ ] project meeting (from: Oct 05 2024 14:00 to: Oct 05 2024 16:00)
```

---

### 5. Marking a Task as Done

**Command:**

```text
mark 2
```

**Response:**

```text
Nice! I've marked this task as done:
  [D][X] submit report (by: Sep 30 2024, 11:59 PM)
```

---

### 6. Unmarking a Task

**Command:**

```text
unmark 2
```

**Response:**

```text
OK, I've marked this task as not done yet:
  [D][ ] submit report (by: Sep 30 2024, 11:59 PM)
```

---

### 7. Deleting a Task

**Command:**

```text
delete 1
```

**Response:**

```text
Noted. I've removed this task:
  [T][ ] read a book
Now you have 2 tasks in the list.
```

---

### 8. Finding Tasks

**Command:**

```text
find meeting
```

**Response:**

```text
Here are the matching tasks in your list:
  [E][ ] project meeting (from: Oct 05 2024, 2:00 PM to: Oct 05 2024, 4:00 PM)
```

---

### 9. Exiting the Application

**Command:**

```text
bye
```
**Response:**

```text
Goodbye. Hope to see you again soon!
```

Or simply **close the GUI window**. Your tasks will be saved automatically.

---


