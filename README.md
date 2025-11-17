# Bara-Bara - User Guide

Hello there! I'm Bara-Bara, your friendly capybara assistant! I'm here to help you stay organized while keeping things nice and chill. Just type your command and let's get things done!

---

## Quick start

1. Ensure you have Java **17 or above** installed on your Computer.
2. Download the latest `bara.jar` file from the releases page.
3. Copy the file to the folder you want to use as the home folder for Bara-Bara.
4. Open a command terminal, `cd` into the folder containing the jar file, and use `java -jar bara-bara.jar` to run the application.
5. Type commands in the command box and press Enter to execute them.

Some example commands you can try:
- `list`: Lists all tasks
- `todo read book`: Adds a todo task
- `deadline return book /by 2024-12-25 23:59`: Adds a deadline task
- `event party /from 2024-12-31 20:00 /to 2024-12-31 23:59`: Adds an event
- `bye`: Exits the app

---

## Features

### 📝 Adding Tasks

#### **Adding a Todo Task** : `todo`
Adds a simple todo task without any date/time.

**Format**: `todo DESCRIPTION`

**Example**:
- `todo borrow book`
- `todo read chapter 5`

**Response**:
```
Got it. I've added this task: [T][ ] borrow book
Now you have 5 tasks in the list.
```

#### **Adding a Deadline Task** : `deadline`
Adds a task with a specific deadline.

**Format**: `deadline DESCRIPTION /by yyyy-mm-dd hh:mm`

**Example**:
- `deadline return book /by 2024-06-15 17:00`
- `deadline submit report /by 2024-12-25 23:59`

**Response**:
```
Got it. I've added this task: [D][ ] return book (by: Jun 15 2024, 5:00 PM)
Now you have 6 tasks in the list.
```

#### **Adding an Event** : `event`
Adds an event with specific start and end times.

**Format**: `event DESCRIPTION /from yyyy-mm-dd hh:mm /to yyyy-mm-dd hh:mm`

**Example**:
- `event project meeting /from 2024-08-06 14:00 /to 2024-08-06 16:00`
- `event birthday party /from 2024-12-31 20:00 /to 2024-12-31 23:59`

**Response**:
```
Got it. I've added this task: [E][ ] project meeting (from: Aug 6 2024, 2:00 PM to: 4:00 PM)
Now you have 7 tasks in the list.
```

### 📋 Viewing Tasks

#### **Listing all tasks** : `list`
Shows all tasks with their status, dates, and tags.

**Format**: `list`

**Example**: `list`

**Response**:
```
Here are the tasks in your list:
1.[T][X] read book #leisure
2.[D][ ] return book (by: Jun 15 2024, 5:00 PM) #urgent
3.[E][ ] project meeting (from: Aug 6 2024, 2:00 PM to: 4:00 PM) #work #meeting
4.[T][X] join sports club
5.[T][ ] borrow book #library
```

### 🛠️ Managing Tasks

#### **Marking a task as done** : `mark`
Marks a specific task as completed.

**Format**: `mark INDEX`

**Example**: `mark 3`

**Response**: `Nice! I've marked this task as done: [E][X] project meeting (from: Aug 6 2024, 2:00 PM to: 4:00 PM) #work #meeting`

#### **Unmarking a task** : `unmark`
Marks a completed task as not done.

**Format**: `unmark INDEX`

**Example**: `unmark 1`

**Response**: `OK, I've marked this task as not done yet: [T][ ] read book #leisure`

#### **Deleting a task** : `delete`
Removes a task from your list.

**Format**: `delete INDEX`

**Example**: `delete 4`

**Response**: `Noted. I've removed this task: [T][X] join sports club. Now you have 4 tasks in the list.`

### 🏷️ Tagging Tasks

#### **Adding tags to a task** : `tag`
Adds one or more tags to a specific task.

**Format**: `tag INDEX #TAG1 [#TAG2]...`

**Example**:
- `tag 5 #important #school`
- `tag 2 #priority`

**Response**: `Great! I've added tags to this task: [T][ ] borrow book #library #important #school`

#### **Removing tags from a task** : `dtag`
Removes specific tags from a task.

**Format**: `dtag INDEX #TAG1 [#TAG2]...`

**Example**:
- `dtag 5 #school`
- `dtag 3 #work #meeting`

**Response**: `OK! I've removed those tags: [T][ ] borrow book #library #important`

### 🔍 Finding Tasks

#### **Finding tasks by keyword** : `find`
Searches for tasks containing specific keywords.

**Format**: `find KEYWORD`

**Example**:
- `find book`
- `find meeting`

**Response**:
```
Here are the matching tasks in your list:
2.[D][ ] return book (by: Jun 15 2024, 5:00 PM) #urgent
5.[T][ ] borrow book #library #important
```

### 👋 Exiting the Program

#### **Exiting the program** : `bye`
Saves your tasks and exits the application.

**Format**: `bye`

**Example**: `bye`

**Response**: `Bye! Hope to see you again soon!`

---

## FAQ

**Q: How do I know which task number to use for mark/unmark/delete?**
A: Use the `list` command first to see all tasks with their numbers.

**Q: What date format should I use?**
A: Use `yyyy-mm-dd hh:mm` format (e.g., `2024-12-25 23:59`).

**Q: Can I add multiple tags to a task?**
A: Yes! Use `tag 1 #important #work #urgent` to add multiple tags at once.

**Q: How do I remove all tags from a task?**
A: Use `dtag` with all the tags currently on the task, or you can't remove all tags at once currently.

---

## Command Summary

| Action | Format | Examples |
|--------|--------|----------|
| **Add Todo** | `todo DESCRIPTION` | `todo read book` |
| **Add Deadline** | `deadline DESCRIPTION /by yyyy-mm-dd hh:mm` | `deadline return book /by 2024-06-15 17:00` |
| **Add Event** | `event DESCRIPTION /from yyyy-mm-dd hh:mm /to yyyy-mm-dd hh:mm` | `event meeting /from 2024-08-06 14:00 /to 2024-08-06 16:00` |
| **List** | `list` | `list` |
| **Mark** | `mark INDEX` | `mark 3` |
| **Unmark** | `unmark INDEX` | `unmark 1` |
| **Delete** | `delete INDEX` | `delete 2` |
| **Add Tags** | `tag INDEX #TAG1 [#TAG2]...` | `tag 1 #important #work` |
| **Remove Tags** | `dtag INDEX #TAG1 [#TAG2]...` | `dtag 1 #work` |
| **Find** | `find KEYWORD` | `find book` |
| **Exit** | `bye` | `bye` |

---

## Saving the Data

Bara-Bara data is saved automatically after any command that changes the data. There is no need to save manually. Your tasks are stored in a data file in the same directory as the application.

**Note**: If you edit the data file directly and make incorrect changes, Bara-Bara might start with an empty task list. It's recommended to backup the data file before editing.

---

Enjoy using Bara-Bara! Remember: stay as chill as a capybara in a warm spring! 