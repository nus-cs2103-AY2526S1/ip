# TaskLynx User Guide
TaskLynx is your reliable partner for keeping track of your tasks and activities.

Here's a look at TaskLynx:

![](Ui.png)

## User Command Guide

TaskLynx provides an in-app command guide which displays information on the valid command usages. 
Type `help` in the app to access this guide whenever you need to refer to it.

## Adding tasks

TaskLynx tracks 3 types of tasks:
- todo tasks that support a description
- deadline tasks that support a description and a deadline
- event tasks that support a description, a start time and an end time

TaskLynx also lets you attach an optional priority to tasks!

Example: 
```
todo taska 
deadline taskb /by 2025-11-11
event taskc /from 2025-11-11 /to 2025-11-13
todo priority five task /p 5
```

```
[0][T][I] taska (id:1)
[0][D][I] taskb (by: Nov 11 2025 00:00) (id:2)
[0][E][I] taskc (from: Nov 11 2025 00:00 to: Nov 13 2025 00:00) (id:3)
[5][T][I] priority five task (id:4)
```

## Listing tasks

TaskLynx allows you to view your tasks, based on:
- name using `/key`
- date using `/on`
- status using `/status`
- type using `/type`
- priority using `/p`
- id using `/id`
- all using `/all`

Example:
```
list /key a
list /status complete
list /all
```

```
Here are all tasks containing keyword "a":
[0][T][I] taska (id:1)

Here are all complete tasks:
(No tasks yet)

Here are all tasks:
[0][T][I] taska (id:1)
[0][D][I] taskb (by: Nov 11 2025 00:00) (id:2)
[0][E][I] taskc (from: Nov 11 2025 00:00 to: Nov 13 2025 00:00) (id:3)
[5][T][I] priority five task (id:4)
```

## Marking, Unmarking, Deleting tasks

TaskLynx also lets you:
- Mark tasks as complete
- Unmark tasks as incomplete
- Delete tasks from the list

Using the same variety of search modifiers as mentioned under "Listing tasks"!

Example:
```
mark /id 1
unmark /type todo
delete /on 2025-11-12
```

```
Marked all tasks with id 1:
[0][T][C] taska (id:1)

Unmarked all todo tasks:
[0][T][I] taska (id:1)

Removed all tasks occurring on Nov 12 2025 00:00: 
[0][E][I] taskc (from: Nov 11 2025 00:00 to: Nov 13 2025 00:00) (id:3)
```

## Saving and Loading tasks

TaskLynx allows you to save your tasks to a data file and retrieve them anytime.
TaskLynx also does this automatically when closing and opening the app, respectively.

Example:
```
save
reload
```

```
Kept all your tasks!
Caught all your tasks!
```

## Precise search

For advanced users, TaskLynx lets you chain search modifiers for more specificity in your actions!

Example:
```
list /p 0 /type deadline /status incomplete /key b /on 2025-11-11 /id 2
```

```
Here are all matching tasks:
[0][D][I] taskb (by: Nov 11 2025 00:00) (id:2)
```