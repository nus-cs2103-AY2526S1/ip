# ChoiceBot User Guide

![Demonstration of App](Ui.png)

## What's ChoiceBot?
ChoiceBot saves you from having to remember all the things you need to do so you can focus on what's important to you. It's

- intuitive to use
- user-friendly
- **FREE**

## Features

### Adding todos
**Action**: Adds a simple todo task

Example: `todo soccer`

Expected outcome:
```
Got it. I've added this task:
    [T][] soccer
Now you have 1 tasks in the list.
```

### Adding events
**Action**: Adds a task with a start and end date

Example: `event concert /from 2025-10-23 19:00 /to 2025-10-23 21:00`

Expected outcome:
```
Got it. I've added this task:
    [E][] concert (from: 2025-10-23 19:00 to: 2025-10-23 21:00)
Now you have 2 tasks in the list.
```

### Adding deadlines

**Action**: Adds a task with a due date

Example: `deadline assignment /by 2025-12-10`

Expected outcome:
```
Got it. I've added this task:
    [D][] assignment (by: Dec 10 2025)
Now you have 3 tasks in the list.
```

### Mark tasks
**Action**: Marks a saved task as done

Example: `mark 2`

Expected outcome:
```
Nice! I've marked this task as done:
[E][X] concert (from: 2025-10-23 19:00 to: 2025-10-23 21:00)
```

### Unmark tasks
**Action**: Unmarks a task

Example: `unmark 2`

Expected outcome:
```
Ok, I've unmarked the following task for you:
    [E][] concert (from: 2025-10-23 19:00 to: 2025-10-23 21:00)
```

### Delete tasks
**Action**: Deletes a task from saved tasks

Example: `delete 1`

Expected outcome:
```
Noted. I have removed the following task:
    [T][] soccer
Now you have 2 tasks in the list.
```

### List tasks
**Action**: Displays all saved tasks in a list

Example: `list`

Expected outcome:
```
Here are the tasks in your list:
    1. [D][] assignment (by: Dec 10 2025)
    2. [E][] concert (from: 2025-10-23 19:00 to: 2025-10-23 21:00)
```

### Find tasks
**Action**: Finds a task with a specific string

Example: `find assignment`

Expected outcome:
```
Here are the matching tasks in your list:
    1.[D][] assignment (by: Dec 10 2025)
```

### Bye
**Action**: Exits ChoiceBot

Example: `bye`

Expected outcome:
```
Thanks for stopping by!
See you again!
```
