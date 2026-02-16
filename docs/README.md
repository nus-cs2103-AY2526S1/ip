# Nicholas User Guide

Nicholas Chatbot is a task management designed to help you stay organized and productive. It keeps track of your to-dos, deadlines and events in one place.

- [Quick Start](#quick-start)
- [Adding a todo task: `todo`](#adding-a-todo-task-todo)
- [Listing all tasks: `list`](#listing-all-tasks-list)
- [Finding all tasks containing any keyword: `find`](#finding-all-tasks-containing-any-keyword-find)
- [Deleting a task: `delete`](#deleting-a-task-delete)
- [Adding deadlines: `deadline`](#adding-deadlines-deadline)
- [Adding events: `event`](#adding-events-event)
- [Marking task: `mark`](#marking-task-mark)
- [Unmarking task: `unmark`](#unmarking-task-unmark)
- [Exiting the program: `bye`](#exiting-the-program-bye)
- [Saving the task data](#saving-the-task-data)
- [Command Summary](#command-summary)

## Quick Start

Using IntelliJ

1. Find the project in the project explorer

2. Go to the src folder and locate the java file in the main folder

3. Locate the nicholas folder and right click the Nicholas file and select Run Nicholas.main()

4. The program should create a GUI.

5. Now you can interact with the program through the GUI

## Adding a todo task: todo

Adds a normal todo task with no deadlines
Format: todo TASK

words in UPPER_CASE are the parameters.

Example: 
- todo return book


## Listing all tasks: list

Shows a list of all tasks along with their status
Format: list

## Finding all tasks containing any keyword: find
Finds tasks which contain any of the given keywords.

Format: find KEYWORD

**The search is case-sensitive**

Examples:
- find boo
- find book

## Deleting a task: delete
Deletes the specific task. **Irreversible**.

Format: delete INDEX

Deletes the task at the specified INDEX. The index refers to the index number in the current tasklist.

Examples:
- delete 1  
Deletes the 1st task in the task list.

## Adding deadlines: deadline
Adds a deadline task to the task list.

Format: deadline TASK /by DATE
- date should be of the format yyyy-MM-dd HH:mm

Examples:
- deadline return book /by 2025-10-03 03:30

## Adding events: event
Adds an event task to the task list

Format: event TASK /from DATE /to DATE
- date should be of the format yyyy-MM-dd HH:mm

Example:
- event buy book /from 2025-10-03 03:30 /to 2025-10-04 03:30

## Marking task: mark
Marks a task as complete

Format: mark INDEX

Index refers to the index in the current task list

Example: 
- mark 1    
This will mark the first task in the task list as complete.

## Unmarking task: unmark
Unmarks a task as incomplete.

Format: unmark INDEX

Index refers to the index in the current task list.

Example: 
- unmark 1  
This will unmark the first task in the task list as incomplete.

## Exiting the program: bye
Exists the program

Format: bye

## Saving the task data

The tasks are saved automatically in a text file after any command that changes the data.   
There is no need to save manually.

## Command Summary

| Command  | Format                                 |
|----------|----------------------------------------|
| todo     | todo return book                       |
| list     | list                                   |
| find     | find KEYWORD                           |
| delete   | delete INDEX                           |
| deadline | deadline TASK /by DATETIME             |
| event    | event TASK /from DATETIME /to DATETIME |
| mark     | mark INDEX                             |
| unmark   | unmark INDEX                           |
| bye      | bye                                    |

