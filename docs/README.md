# Crysis Heir Activity Sentre Hepdesk (CHASH) User Guide

## Introduction

CHASH is a typing-focused chatbot that lets you use mem as your personal assistant 
to remember a variety of tasks through a nice graphical interface!

![Sample of some CHASH commands being ran](https://github.com/x3zapper/ip/blob/master/docs/Ui.png)

## Features

### Help

- Prints the CHASH Help information

Usage: `help`\
Output: 
```
Crysis Heir Activity Sentre Hepdesk Commands:
HELP - Print this help message
BYE - Exit this program
LIST - List all tasks currently stored
MARK - Mark a task as done
UNMARK - Mark a task as not done
TODO - Create a TODO task with description
DEADLINE - Create a DEADLINE task with description (needs /by WHEN)
EVENT - Create a EVENT task with description (needs /from START /to END)
DELETE - Delete a task
FIND - Find a task based on its description
Thank you for joining the crysis warz
```

---

### Listing all stored tasks

- Prints all stored tasks to the user interface

Usage: `list`\
Output:
- 3 tasks stored
```
Here are the tasks in your list:
1. [D][ ] def (by: today)
2. [E][ ] abc (from: today to: tmr)
3. [E][ ] def (from: today or tmr to: next year bro)
```
- No tasks stored
```
No tasks in your list!
```

---

### Marking a task as complete

- In CHASH, new tasks are by default treated as incomplete
- With this feature, you can indicate that the task has been complete
- A completed task will have a `[X]` before its description
- Note: `TASK_NUMBER` starts from 1., following the task listing

Usage: `mark TASK_NUMBER`\
Example: `mark 1`\
Output:
```
Nice! I've marked this task as done:
  [D][X] def (by: today)
```

---

### Marking a task as incomplete

- Indicate that a task is incomplete
- An uncompleted task will have a `[ ]` before its description
- Note: `TASK_NUMBER` starts from 1., following the task listing

Usage: `unmark TASK_NUMBER`\
Example: `unmark 2`\
Output:
```
OK, I've marked this task as not done yet:
  [E][ ] abc (from: today to: tmr)
```

---

### Creating a TODO task

- Creates a simple TODO task with a description

Usage: `todo TASK_DESCRIPTION`\
Example: `todo abc`\
Output:
```
Got it. I've added this task:
  [T][ ] abc
Now you have 1 tasks in the list.
```

---

### Creating a DEADLINE task

- Creates a task that must be done before a specific date or time
- Requires the `/by` keyword followed by the deadline
- Support ISO-8601 DateTime formats for `DEADLINE` (e.g. `2022-10-18T23:59` prints as `Oct 18 2022`)

Usage: `deadline TASK_DESCRIPTION /by DEADLINE`\
Example: `deadline def /by today`\
Output:
```
Got it. I've added this task:
  [D][ ] def (by: today)
Now you have 2 tasks in the list.
```
Example: `deadline ghi /by 2022-10-18T00:00`\
Output:
```
Got it. I've added this task:
  [D][ ] ghi (by: Oct 18 2022)
Now you have 3 tasks in the list.
```

---

### Creating an EVENT task

- Creates an event that spans a start and end time
- Requires `/from` and `/to` keywords

Usage: `event TASK_DESCRIPTION /from START /to END`\
Example: `event abc /from today /to tmr`\
Output:
```
Got it. I've added this task:
  [E][ ] abc (from: today to: tmr)
Now you have 4 tasks in the list.
```

---

### Deleting a task

- Removes a task from the stored list using its number
- If the index is invalid, CHASH reports an error
- Note: `TASK_NUMBER` starts from 1., following the task listing

Usage: `delete TASK_NUMBER`\
Example: `delete 2`\
Output:
```
Noted. I've removed this task:
  [T][ ] abc
Now you have 4 tasks in the list.
```
Invalid Example: `delete -1`\
Output:
```
Invalid index: -1
```

---

### Finding tasks by keyword

- Searches for tasks whose descriptions contain the keyword

Usage: `find KEYWORD`\
Example: `find today`\
Output:
```
Here are the matching tasks in your list:
1. [D][ ] def (by: today)
2. [E][ ] abc (from: today to: tmr)
3. [E][ ] def (from: today or tmr to: next year bro)
```

---

### Exiting CHASH

- Closes the application gracefully

Usage: `bye`\
Output:
```
Bye. Hope to see you again soon!
```
