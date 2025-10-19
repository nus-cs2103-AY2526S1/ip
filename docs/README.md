# Reboot User Guide

![Reboot.png](Reboot.png)

### **Reboot**

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))

Reboot frees your mind of having to remember things you need to do. It's,
- user friendly
- easy to learn
- _SUPER FAST_ to use

All you need to do is,

1.  download the reboot.jar from [here](https://github.com/ronniefun1/ip/releases/tag/A-Jar).
2.  copy the jar file into an empty folder.
3.  open a command window in that folder.
4.  run the command `java -jar "reboot.jar"`
5.  add you tasks
6.  let it manage your tasks for you 😉

And it is **FREE**!

Features:

- [x] Managing tasks
- [x] Adding recurring tasks
- [ ] Managing deadlines
- [ ] Reminders (coming soon!)

## Adding a todo: `todo`

Adds a todo to be tracked by Reboot.

Format: todo DESCRIPTION [/recurring RECURRENCE]

Example: 
- `todo math hw`
- `todo math hw /recurring weekly`

## Adding a deadline: `deadline`

Adds a deadline to be tracked by Reboot.

Format: deadline DESCRIPTION /by DUE DATE [/recurring RECURRENCE]

Example:
- `deadline math hw /by 31 10 2025`
- `deadline math hw /by 31 10 2025 /recurring weekly`

## Adding a event: `event`

Adds a event to be tracked by Reboot.

Format: event DESCRIPTION /from START DATE /to END DATE [/recurring RECURRENCE]

Example: 
- `event project meeting /from 31 10 2025 1400 /to 31 10 2025 1600`
- `event project meeting /from 31 10 2025 1400 /to 31 10 2025 1600 /recurring weekly`

## Viewing your tasks: `list`

Shows a list of all tasks in your task list.

Format: `list`

## Clearing your tasks: `clear`

Clears your task list.

Format: `clear`

## Deleting a task: `delete`

Deletes a task from your task list.

Format: `delete TASK INDEX`

## Finding a task: `find`

Searches the task list with the given keyword and outputs a list of task with the keyword.

Format: `find KEYWORD`

## Marking a task: `mark`

Marks a task as completed.

Format: `mark TASK INDEX`

## Unmarking a task: `unmark`

Marks a task as incomplete.

Format: `unmark TASK INDEX`

## Exiting Reboot: `bye`

Exits Reboot.

Format: `bye`