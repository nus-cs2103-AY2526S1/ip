# Woop User Guide

<img alt="wooplogo" src="Ui.png" style="border-radius: 8px; width:400px;"/>

The cutest chatbot in the world! (Will probably swim into rocks too)
## Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
   - [Adding a Todo Task: `todo`](#adding-a-todo-task-todo)
   - [Adding a Deadline Task: `deadline`](#adding-a-deadline-task-deadline)
   - [Adding an Event Task: `event`](#adding-an-event-task-event)
   - [Listing All Tasks: `list`](#listing-all-tasks-list)
   - [Marking a Task as Done: `mark`](#marking-a-task-as-done-mark)
   - [Unmarking a Task: `unmark`](#unmarking-a-task-unmark)
   - [Deleting a Task: `delete`](#deleting-a-task-delete)
   - [Finding Tasks by Keyword: `find`](#finding-tasks-by-keyword-find)
   - [Tagging a Task: `tag`](#tagging-a-task-tag)
   - [Exiting the Application: `bye`](#exiting-the-application-bye)
   - [Saving the data](#saving-the-data)

## Quick Start
1. Ensure you have Java 17 or above installed in your Computer.
2. Mac users: Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
3. Download the latest .jar file from [here](https://github.com/rizrn/ip/releases/tag/A-Release).
4. Copy the file to the folder you want to use as the home folder for your Woop companion.
   Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command 
   to run the application.

## Features

> Notes about command format:
> - Words in UPPER_CASE are the parameters to be supplied by the user.
>   e.g. in `todo NAME`, NAME is a parameter which can be used as `todo read book`.
> - DATE should be in the format YYYY-MM-DD, e.g. `2023-10-31`.
> - Do not put spaces in front of any commands.
> - Parameters should be separated by a single space.
> - Extraneous parameters for commands that do not take in parameters (such as list and bye) will be ignored.
> - All indexes are 1-based, e.g. the first task is task 1, the second task is task 2, and so on.

### Adding a Todo Task: `todo`
Adds a todo task to the task list.\
Format: `todo DESCRIPTOR`\
Examples: 
- `todo read book`
- `todo repair car`

### Adding a Deadline Task: `deadline`
Adds a deadline task to the task list.\
Format: `deadline DESCRIPTOR /by DATE`\
Examples:
- `deadline submit report /by 2025-10-31`
- `deadline pay rent /by 2025-11-01`

### Adding an Event Task: `event`
Adds an event task to the task list.\
Format: `event DESCRIPTOR /from TIME /to TIME`\
Examples:
- `event project meeting /from 14:00 /to 16:00`
- `event birthday party /from tmr 5pm /to tmr 6pm`

### Listing All Tasks: `list`
Displays all tasks in the task list with their status (done or not done) and tags.\
Format: `list`

### Marking a Task as Done: `mark`
Marks a task as done based on its index in the task list.\
Format: `mark INDEX`\
Example: `mark 2` marks the second task as done.

### Unmarking a Task: `unmark`
Unmarks a task as not done based on its index in the task list.\
Format: `unmark INDEX`\
Example: `unmark 2` marks the second task as not done.

### Deleting a Task: `delete`
Deletes a task from the task list based on its index.\
Format: `delete INDEX`\
Example: `delete 3` deletes the third task.

### Finding Tasks by Keyword: `find`
Finds and lists all tasks that contain the specified keyword in their description.\
Format: `find KEYWORD`\
Example: 
- `find book` lists all tasks with "book" in their description.
- `find meeting` lists all tasks with "meeting" in their description.

### Tagging a Task: `tag`
Adds a tag to a task based on its index in the task list.\
Format: `tag INDEX TAG`\
Example: 
- `tag 1 urgent` adds the tag "urgent" to the first task.
- `tag 2 ` including 1 whitespace after `INDEX` removes any tag from the second task.

### Exiting the Application: `bye`
Exits the application.\
Format: `bye`

### Saving the data
Woop automatically saves all your tasks in a file named `data.txt` in the data directory
after every command that modifies the task list.\
The data file is created automatically if it does not exist. Do not modify this file manually as it may corrupt the data.
