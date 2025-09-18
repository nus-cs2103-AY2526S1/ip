# Tony User Guide

![Screenshot of an example Tony UI](/docs/Ui.png)

Tony chatbot is a virtual assistant that helps you to manage your tasks.

## Features

> **Notes about command format:**
> * All commands should be in lowercase
> * Commands can only be used once per input


### Adding tasks: `todo`

Adds a task to the task list.

Example: `todo Walk the dog`

Tony will output the following message:

```
Alright, just let one of my machines do it for you:
  [T][ ] Walk the dog
Now you have 1 tasks in the list.
```

### Adding deadlines: `deadline`

Adds a task that shows you its deadline.

Format 1: `event <name> /by <date>`

Format 2: `event <name> /by <date> <time>`

Example 1: `deadline Fix door /by 12-12-2025`

Example 2: `deadline Assignment 1 /by 12-12-2025 4:00PM`

Tony will output the following message:

Example 1:
```
Alright, just let one of my machines do it for you:
  [D][ ] Fix door (by: 12 Dec 25)
Now you have 2 tasks in the list.
```
Example 2:
```
Alright, just let one of my machines do it for you:
  [D][ ] Assignemnt 1 (by: 12 Dec 25 4:00PM)
Now you have 2 tasks in the list.
```

### Adding events: `event`

Adds an event that shows you its start and end.

Format 1: `event <name> /from <date> /to <date>`

Format 2: `event <name> /from <date> <time> /to <date> <time>`

Example 1: `event Christmas party /from 24-12-2025 4:00PM /to 24-12-2025 10:00PM`

Example 2: `event Family vacation /from 12-12-2025 /to 18-12-2025`

Tony will output the following message:

Example 1:

```
Alright, just let one of my machines do it for you:
  [E][ ] Christmas party (from: 24 Dec 25 4:00PM to: 24 Dec 25 10:00PM)
Now you have 3 tasks in the list.
```
Example 2:
```
Alright, just let one of my machines do it for you:
  [E][ ] Family vacation (from: 12 Dec 25 to: 18 Dec 25)
Now you have 3 tasks in the list.
```

### Show list: `list`

Shows the list of task.

Example: `list`

Tony will output the following message:

```
JARVIS, show them their list of tasks:.
  1: [T][ ] Walk the dog
  2: [D][ ] Assignment 1 (by: 12 Dec 25 4:00PM)
  3: [E][ ] Christmas party (from: 24 Dec 25 4:00PM to: 24 Dec 25 10:00PM)
```


### Mark/Unmark tasks: `mark` `unmark`

Marks/Unmarks a task in the task list.

Format 1: `mark <task index>`

Format 2: `unmark <task index>`

Example 1: `mark 1`

Example 2: `unmark 1`

Tony will output the following message:

Example 1:
```
Done. Look at you, being efficient.
  [T][X] Walk the dog
```
Example 2:
```
Unmarked. Happens to the best of us.
  [T][ ] Walk the dog
```

### Delete task: `delete`

Deletes a task from the task list.

Format: `delete <task index>`

Example: `delete 1`

Tony will output the following message:

Example 1:
```
Overachieveing might not be for everybody.
  [T][ ] Walk the dog
```


### Find task: `find`

Finds all tasks that contains the user input.

Format: `find <keyword>`

Example 1: `find Assignment`

Example 2: `find home`

Tony will output the following message:

Example 1:
```
That didn't take long.
  1: [D][ ] Assignment 1 (by: 12 Dec 25 4:00PM)
```
Example 2:
```
Well, I tried my best.
```

### Show task: `show`

Shows all events that fall on a certain date.

Format: `show <date>`

Example 1: `show 24-12-2025`

Example 2: `show 11-12-2025`

Tony will output the following message:

Example 1:
```
Looks like you've got some things on your plate.
  1: [E][ ] Christmas party (from: 24 Dec 25 4:00PM to: 24 Dec 25 10:00PM)
```
Example 2:
```
You're as busy as a rock.
```

### Exit chatbot: `bye`

Ends the program and closes the chatbot window.

Example: `bye`

Tony will output the following message:

```
I’m powering down. Don’t break anything while I’m gone.
```

## Command summary

Action | Format, Examples
--------|------------------
**Add Task** | `todo <task>` <br> e.g., `todo Walk the dog`
**Add Deadline Task** | `todo <task> /by <date + time(optional)>` <br> e.g., `deadline assignment /by 23-11-2025 11:59PM`
**Add Event** | `event <event> /from <date + time(optional)> /to <date + time(optional)>` <br> e.g., `event project meeting /from 12-09-2025 2:00PM /to 12-09-2025 4:00PM`
**Mark** | `mark <index>`
**Unmark** | `unmark <index>`
**Delete** | `delete index`<br> e.g., `delete 3`
**Show Tasks on Date** | `show <date>`<br> e.g.,`show 11-06-2025`
**Find** | `find <keyword>`<br> e.g., `find work`
**List** | `list`
**Exit** | `bye`
