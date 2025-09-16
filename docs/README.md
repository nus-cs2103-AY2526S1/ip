# Rafayel User Guide

![Rafayel Chatbot](Ui.png)

> "Your mind is for having ideas, not holding them." - David Allen

Rafayel will help free your mind from remembering every little task. It's designed to be **simple**, **efficient** and **fast**, so you can focus on what truly matters. Rafayel will remind you of overdue tasks and upcoming deadlines, ensuring you never miss important commitments.

## Quick Start

1. Ensure you have Java 11 or above installed
2. Download the latest version of Rafayel from [here](https://github.com/zjaoyuki/rafayel/releases)
3. Copy the file to the folder you want to use as the home folder for Rafayel
4. Open a command terminal, navigate to the folder, and run `java -jar rafayel.jar`
5. Type a command and press Enter to execute it. Some examples you can try:
    - `list`: Lists all tasks loaded from local storage.
    - `todo borrow book`: Creates a Todo task.
    - `deadline return book /by 01-12-2024 18:00`: Creates a Deadline task.
    - `mark 1`: Marks the first task as done.
    - `delete 1`: Deletes the first task.
    - `remind`: Find out what tasks are due soon or overdue.
6. Refer to the Features section below for details of all available commands

## Features 

### Adding a Todo Task: `TODO`
Adds a simple todo task without any date/time.

Format: `todo DESCRIPTION`

Example: `todo borrow book`

```
I've added this task to my collection:
  [T][ ] borrow book
Now you have 4 tasks in the list.
```

### Adding a Deadline: `DEADLINE`
Adds a task with a specific due date.

Format: `deadline DESCRIPTION /by YYYY-MM-DD HH:mm`

Example: `deadline return book /by 2024-12-01 18:00`

```
I've added this task to my collection:
  [D][ ] return book (by: Dec 1 2024 18:00)
Now you have 5 tasks in the list.
```

### Adding an Event: `EVENT`
Adds a task with a start and end date/time.

Format: `event DESCRIPTION /from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm`

Example: `event project meeting /from 2024-12-01 14:00 /to 2024-12-01 18:00`

```
I've added this task to my collection:
  [E][ ] project meeting (from: Dec 1 2024 14:00 to: Dec 1 2024 18:00)
Now you have 6 tasks in the list.
```

### Listing All Tasks: `LIST`
Displays all tasks in your list with their current status.

Format: `list`

```
Behold, the magnificent collection of tasks you've burdened me with:

1. [T][ ] borrow book
2. [D][ ] return book (by: Dec 1 2024 18:00)
3. [E][ ] project meeting (from: Dec 1 2024 14:00 to: Dec 1 2024 18:00)
4. [D][X] do homework (by: Dec 24 2025 12:24)

...I suppose some of them are worthy of my time.
```

### Marking a Task as Done: `MARK`
Marks a specific task as completed.

Format: `mark TASK_NUMBER`

Example: `mark 2`

```
Finally, I was waiting forever for that.
I've marked this task as completed to my impeccable standards:
『[D][X] return book (by: Dec 1 2024 18:00)』
```

### Unmarking a Task: `UNMARK`
Marks a completed task as not done yet.

Format: `unmark TASK_NUMBER`

Example: `unmark 4`

```
Hm, I'll trust you have a good reason for this delay.
I've marked this task as regrettably left unfinished:
『[D][ ] do homework (by: Dec 24 2025 12:24)』
```

### Deleting a Task: `DELETE`
Removes a task from your list.

Format: `delete TASK_NUMBER`

Example: `delete 1`

```
Okay, I've removed this task from our gallery:
 『 [T][ ] borrow book 』
We are now left with 3 tasks. A more curated collection, I suppose.
```

### Finding Tasks: `FIND`
Searches for tasks containing a specific keyword.

Format: `find KEYWORD`

Example: `find book`

```
After sifting through the sands of your endless list, I found these. You owe me:
 1. [D][X] return book (by: Dec 1 2024 18:00)
```

### Getting Reminders: `REMIND`
Shows overdue tasks and tasks due in the next 24 hours.

Format: `remind`

```
Reminders:

OVERDUE TASKS:
 1. [D][ ] return book (by: Dec 1 2024 18:00)
 2. [E][ ] project meeting (from: Dec 1 2024 14:00 to: Dec 1 2024 18:00)
```

### Exiting the Program: `BYE`
Saves all tasks and exits the application.

Format: `bye`

```
Bye, come back soon, don't make me wait too long .-.
```

## Automatic Reminders

Rafayel will automatically show you reminders of overdue tasks and upcoming deadlines (within 24 hours) each time you start the application as well as every 5 minutes afterwards, ensuring you're always aware of your most pressing commitments. 

## Saving the Data

Rafayel automatically saves your tasks to a local file after every command. The data is stored in the same folder as the application in a folder named `data` and the file is named `rafayel.txt`. There's no need to manually save your data.

## FAQ

**Q: How do I transfer my data to another computer?**
A: Install Rafayel on the other computer and copy the `tasks.txt` file from your old computer to the new one.

**Q: Can I edit the data file directly?**
A: We don't recommend editing the data file directly as it may corrupt your data. Use the commands provided instead.

**Q: What date/time format should I use?**
A: Use the format `MMM d yyyy HH:mm` (e.g., Jan 8 2025 18:00)
Rafayel also accepts `yyyy/MM/dd HH:mm` (e.g. 2025/12/28 13:00) as well as `dd-MM-yyyy HH:mm` (e.g. 24/11/2025 15:00)

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| List | `list` | `list` |
| Todo | `todo DESCRIPTION` | `todo read book` |
| Deadline | `deadline DESCRIPTION /by dd-MM-yyyy HH:mm` | `deadline assignment /by 2025-12-05 23:59` |
| Event | `event DESCRIPTION /from dd-MM-yyyy HH:mm /to dd-MM-yyyy HH:mm` | `event concert /from 01-12-2025 14:00 /to 01-12-2025 18:00` |
| Mark | `mark TASK_NUMBER` | `mark 3` |
| Unmark | `unmark TASK_NUMBER` | `unmark 3` |
| Delete | `delete TASK_NUMBER` | `delete 3` |
| Find | `find KEYWORD` | `find book` |
| Remind | `remind` | `remind` |
| Bye | `bye` | `bye` |