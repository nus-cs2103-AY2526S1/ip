# DukeGPT User Guide

DukeGPT is a personal assistant chatbot that helps you manage tasks, notes, and more, with a friendly graphical interface.

![Product Screenshot](https://raw.githubusercontent.com/siangjun2/ip/master/docs/Ui.png)

---

# Features

## 1. Add Tasks
You can add different types of tasks:
- **Todo:**
  Format:
  ```
  todo {description}
  ```
  Example:
  ```
  todo read book
  ```
  Expected output:
  ```
  Added: [T][ ] read book
  ```

- **Deadline:**  
  Format:
  ```
  deadline {description} /by {yyyy-mm-dd}
  ```
  Example:
  ```
  deadline submit assignment /by 2025-09-30
  ```
  Expected output:
  ```
  Added: [D][ ] submit assignment (by: Sep 30 2025)
  ```

- **Event:**  

  Format:
  ```
  event {description} /from {yyyy-mm-dd} /to {yyyy-mm-dd}
  ```
  Example:
  ```
  event team meeting /from 2025-10-01 /to 2025-10-02
  ```
  Expected output:
  ```
  Added: [E][ ] team meeting (from: Oct 1 2025 to: Oct 2 2025)
  ```

---

### 2. List Tasks

Lists all current tasks.

Example:
```
list
```
Expected output:
```
Here are your tasks:
1. [T][ ] read book
2. [D][X] submit assignment (by: Sep 30 2025)
3. [E][ ] team meeting (from: Oct 1 2025 to: Oct 2 2025)
```

---

### 3. Mark/Unmark Tasks

Mark task at specified index as done:
```
mark 1
```
Expected output:
```
Nice! I've marked this task as done:
[T][X] read book
```

Unmarking task at specified index:
```
unmark 1
```
Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] read book
```

---

### 4. Delete Tasks

Delete a task by its number:
```
delete 2
```
Expected output:
```
Noted. I've removed this task:
[D][ ] submit assignment (by: Sep 30 2025)
```

---

### 5. Find Tasks

Find tasks containing a keyword:
```
find book
```
Expected output:
```
Here are the matching tasks:
1. [T][ ] read book
```

---

### 6. Notes Feature
Temporary notes that does not persist between usages of DukeGPT.

Add a note:
```
note add Buy milk
```
Expected output:
```
Note added: Buy milk
```

List notes:
```
note list
```
Expected output:
```
Your notes:
1. Buy milk
```

Delete a note:
```
note delete 1
```
Expected output:
```
Note deleted: Buy milk
```

---

### 7. Exit

Exit the application:
```
bye
```
Expected output:
```
Bye. Hope to see you again soon!
```

---

## GUI Features

- Chat-like interface with user and DukeGPT avatars.
- Scrollable dialog history.
- Input box for commands.
- Window title and icon.
- Resizable window.

---

## Getting Started

1. Download and run the application.
2. Enter commands in the input box.
3. View responses in the chat window.

---

## Supported Commands

| Command         | Description                          |
|-----------------|--------------------------------------|
| todo            | Add a todo task                      |
| deadline        | Add a deadline task                  |
| event           | Add an event task                    |
| list            | List all tasks                       |
| mark/unmark     | Mark/unmark a task                   |
| delete          | Delete a task                        |
| find            | Find tasks by keyword                |
| note add        | Add a note                           |
| note list       | List all notes                       |
| note delete     | Delete a note                        |
| bye             | Exit the application                 |
