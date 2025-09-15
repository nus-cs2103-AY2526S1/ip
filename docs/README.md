# BeeBong User Guide

![Product screenshot](Ui.png)

# Meet **BeeBong** 
![Beebong](images/Beebong.png)

BeeBong is my friendly little chatbot that decided being a ~~serious~~ productivity assistant was boring.  
Instead, it buzzes around and *bonks* me with reminders when I forget my tasks.

---

## What BeeBong Does
- Keeps track of your todos 📋
- Reminds you with a cheerful *bonk* 🛎️
- Tries to be helpful (but sometimes just goofy)

---

##  How BeeBong Handles Tasks (Step-by-Step)
1. You tell BeeBong your task.
2. BeeBong goes *bzzz* and stores it.
3. Later, it *bonks* you on the head if you haven’t finished it.

---

## Command Guide:
| Command    | Description                               |
| ---------- | ----------------------------------------- |
| `todo`     | Add a task without a date or time         |
| `deadline` | Add a task with a specific deadline       |
| `event`    | Add an event with start and end date/time |
| `mark`     | Mark a task as completed                  |
| `unmark`   | Mark a task as incomplete                 |
| `delete`   | Remove a task from your list              |
| `list`     | Show all tasks                            |
| `find`     | Search tasks by keyword                   |
| `help`     | Show help guide                           |
| `bye`      | Exit the chatbot                          |

## 1. `todo`
 Adds a task without a date or time.

**Usage:**  `todo <task description>`

**Sample Input:**  `todo read book`

**Sample Output:**  `[T][ ] read book`

## 2. `deadline`

Adds a task that must be completed by a specific date and time.

**Usage:**  `deadline <task description> /by <dd/MM/yyyy HH:mm>`

**Sample Input:**  `deadline return book /by 20/08/2025 18:00`

**Sample Output:**  `[D][ ] return book (by: 20 Aug 2025 18:00)`

## 3. `event`

Adds a task with a start and end date/time.

**Usage:**  `event <event description> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>`

**Sample Input:**  `event project meeting /from 21/08/2025 14:00 /to 21/08/2025 16:00`

**Sample Output:**  `[E][ ] project meeting (from: 21 Aug 2025 14:00 to: 21 Aug 2025 16:00)`

## 4. `mark`

Marks a task in your list as done.

**Usage:**  `mark <task number>`

**Sample Input:**  `mark 1`

**Sample Output:**  `[T][X] read book`

## 5. `unmark`

Reverts a task’s status to incomplete.

**Usage:**  `unmark <task number>`

**Sample Input:**  `unmark 1`

**Sample Output:**  `[T][ ] read book`

## 6. `delete`

Removes a task from your list.

**Usage:**  `delete <task number>`

**Sample Input:**  `delete 2`

**Sample Output:**  `[D][ ] return book (by: 20 Aug 2025 18:00)`

## 7. `list`

Lists all current tasks.

**Usage:**  `list`

**Sample Input:**  `list`

**Sample Output:**  `1. [T][ ] read book 2. [E][ ] project meeting (from: 21 Aug 2025 14:00 to: 21 Aug 2025 16:00)`

## 8. `find`

Shows all tasks containing a specific keyword.

**Usage:**  `find <keyword>`

**Sample Input:**  `find project`

**Sample Output:**  `1. [E][ ] project meeting (from: 21 Aug 2025 14:00 to: 21 Aug 2025 16:00)`

## 9. `help`

Displays the list of available commands.

**Usage:**  `help`

**Sample Input:**  `help`

**Sample Output:**  `todo <desc>, deadline <desc> /by <date>, event <desc> /from <start> /to <end>, mark <task number>, unmark <task number>, delete <task number>, list, find <keyword>, help, bye`

## 10. `bye`

Ends the session.

**Usage:**  `bye`

**Sample Input:**  `bye`

**Sample Output:**  `Bye! Hope to see you again soon!`

---

> “BeeBong may not solve all your problems,  
> but it will *definitely* remind you about them.” — *Sun Tzu, Art of War*

🧊  Want to see BeeBong in action? Check out the [BeeBong Repository](https://github.com/Gareth2YuSheng/ip)

---
