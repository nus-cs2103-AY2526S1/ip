# Marvin User Guide

![Marvin GUI](Ui.png)

Marvin, named after [Marvin the Paranoid Android](https://en.wikipedia.org/wiki/Marvin_the_Paranoid_Android), is your
ultra-intelligent, turing-tested, sentient but slightly depressed digital assistant whose (not so) glad to help you with
your to-dos, events, deadlines, and just generally getting your life in order!

# Quick Start Guide
1. Ensure you have Java 17 or above installed on your machine.
2. Download the latest JAR file that you'd like to use
   - CLI Users: [Here](https://github.com/Harjun751/ip/releases/tag/v0.2)
   - GUI Users: [Here](https://github.com/Harjun751/ip/releases/tag/v0.2)
3. Copy the JAR file to your desired directory.
4. Open a terminal, and run using `java -jar marvin.jar`

Usage of marvin is the same on both GUI and CLI versions, so pick the version you prefer!

# Features

## Overview
- [Adding Todos](#adding-todos-todo)
- [Adding Deadlines](#adding-deadlines-deadline)
- [Adding Events](#adding-events-event)
- [Do After](#setting-tasks-to-be-done-after-do)
- [Marking a Task](#marking-a-task-as-done-mark)
- [Unmarking a Task](#unmarking-a-task-as-done-unmark)
- [Listing Tasks](#listing-all-tasks-list)
- [Deleting Tasks](#deleting-a-task-delete)
- [Finding Tasks](#finding-tasks-by-query-find)
- [Exiting](#exiting-the-program-bye)

## Adding Todos: `todo`
Adds a task that can be marked as done.

Format: `todo [name]`

Example: `todo finish my work`

CLI Output:
```
---Marvin says ------------------------------------------------------------------
| Fine. I’ve added ‘finish my work’ to your endless list of pointless chores.  |
| Not that it will make the slightest difference to the universe—or me.        |
| You have 1 task(s) in the list.                                              |
---User replies -----------------------------------------------------------------
↳
```

GUI Output:
![GUI Todo Example](images/todo_gui_output.png)


## Adding Deadlines: `deadline`
Adds a task that can be marked as done and has a deadline.

Format: `deadline [name] /by [date] [time]`

> **NOTE**: Date format is `dd/mm/yyyy` and time format is `HHmm` (24-hour format). 

Example: `deadline submit report /by 20/09/2025 2359`

CLI Output:
```
---Marvin says ------------------------------------------------------------------
| Fine. I’ve added ‘submit report’ to your endless list of pointless chores.   |
| Not that it will make the slightest difference to the universe—or me.        |
| You have 3 task(s) in the list.                                              |
---User replies -----------------------------------------------------------------
```

GUI Output:
![GUI Deadline Example](images/deadline_gui_output.png)


## Adding Events: `event`
Adds a task that can be marked as done and has a start and end date.

Format: `event [name] /from [date] [time] /to [date] [time]`

> **NOTE**: Date format is `dd/mm/yyyy` and time format is `HHmm` (24-hour format).

Example: `event career fair /from 23/09/2025 0900 /to 24/09/2025 1800`

CLI Output:
```
---Marvin says ------------------------------------------------------------------
| Fine. I’ve added ‘career fair’ to your endless list of pointless chores.     |
| Not that it will make the slightest difference to the universe—or me.        |
| You have 3 task(s) in the list.                                              |
---User replies -----------------------------------------------------------------
↳
```
GUI Output:
![GUI Event Example](images/event_gui_output.png)

## Setting tasks to be done after: `do`
Makes a task dependent on another task being done first.

> **NOTE**: A task dependent on another can only be marked after the task that it is dependent on is marked done.

Format: `do [index] /after [index]`

Example: `do 3 /after 2`

CLI Output:
```
---Marvin says ------------------------------------------------------------------
| done lol                                                                     |
---User replies -----------------------------------------------------------------
↳
```
GUI Output:
![GUI Do After Example](images/doafter_gui_output.png)

## Marking a task as done: `mark`
Marks a task as done based on the provided index. 

> **NOTE**: The index has to match the index shown in the `list` command.
> An index may include sub-indexes, e.g. `2.1` to refer to the first sub-task of task 2.

Format: `mark [index]`

Example: `mark 2`

CLI Output:
```
---Marvin says ------------------------------------------------------------------
| Fine, done.                                                                  |
| [D][X] submit report (by: 20-09-2025, 11PM)                                  |
---User replies -----------------------------------------------------------------
↳
```
GUI Output:
![GUI Mark Example](images/mark_gui_output.png)

## Unmarking a task as done: `unmark`
Marks a task as incomplete based on the provided index.

> **NOTE**: 
> The index has to match the index shown in the `list` command.
> An index may include sub-indexes, e.g. `2.1` to refer to the first sub-task of task 2.

Format: `unmark [index]`

Example: `unmark 2`

CLI Output:
```
---Marvin says ------------------------------------------------------------------
| Fine, done.                                                                  |
| [D][ ] submit report (by: 20-09-2025, 11PM)                                  |
---User replies -----------------------------------------------------------------
↳
```
GUI Output:
![GUI Mark Example](images/mark_gui_output.png)


## Listing all tasks: `list`
Lists all tasks in the current list.

Format: `list`

CLI Output:
```
---Marvin says ------------------------------------------------------------------
| Here's your list of chores.                                                  |
| Another tiny monument to futility, carefully recorded by me.                 |
| 1. [T][ ] finish my work                                                     |
| 2. [D][ ] submit report (by: 20-09-2025, 11PM)                               |
| ---->2.1 [E] [ ] career fair (from: 23-09-2025, 9AM to: 24-09-2025, 6PM)     |
---User replies -----------------------------------------------------------------
↳
```

GUI Output:
![GUI List Example](images/list_gui_output.png)

## Deleting a task: `delete`
Deletes a task based on the provided index.

Format: `delete [index]`

> **NOTE**: 
> The index has to match the index shown in the `list` command.
> An index may include sub-indexes, e.g. `2.1` to refer to the first sub-task of task 2.

Example: `delete 2.1`

CLI Output:
```
---Marvin says ------------------------------------------------------------------
| I've removed the task.                                                       |
| [E] [ ] career fair (from: 23-09-2025, 9AM to: 24-09-2025, 6PM)              |
| Now you have 2 tasks and absolutely nothing will change.                     |
---User replies -----------------------------------------------------------------
↳
```

GUI Output:
![GUI Delete Example](images/delete_gui_output.png)

## Finding tasks by query: `find`
A case-sensitive search for tasks that contain the query string. Matches for any substring in contacts.

> **WARNING**: 
> Known Limitation - only finds tasks that are not subtasks themselves.

Format: `find [query]`

Example: `find work`

CLI Output:
```
---Marvin says ------------------------------------------------------------------
| I've dredged through your list. Here.                                        |
| 1. [T][ ] finish my work                                                     |
---User replies -----------------------------------------------------------------
↳
```

GUI Output:
![GUI Find Example](images/find_gui_output.png)

## Exiting the program: `bye`
Exits the program on CLI.

Format: `bye`

CLI output:
```
---Marvin says ------------------------------------------------------------------
| Goodbye. Another fleeting moment lost to eternity.                           |
--------------------------------------------------------------------------------
```

# Saved Data
Data is persisted in the `save.mrvn` file that will be in the directory you run Marvin from.
