# Lucid User Guide

![Product Screenshot](./Ui.png)

Lucid is a chatbot with a GUI that helps you manage simple daily tasks. It allows creating, viewing, deleting tasks, as 
well as marking them as complete and not complete. Users can also search for tasks by name. Task data is saved to an 
automatically created file for storage.


## Quick start
Prerequisites
- Java version 17  

How to run
1. Download the latest jar file from [here](https://github.com/bearkerb/ip/releases)
2. Copy the file to the folder you wish to use as the home folder
3. Open a terminal, navigate to the folder with the jar file and use `java -jar lucid.jar` to run the application
4. Type your command in the message box at the bottom and press enter to execute it.
5. You're good to go!


## Features
- Simple GUI
- Add different task types (Todo, Deadline, Event)
- View all tasks
- Mark tasks as complete or not complete
- Delete tasks
- Find tasks

## Notes about command format
- The chatbot does not accept the use of the `|` character.
- Extraneous parameters will not be ignored, and will not be accepted.
- Parameters for commands like `event ... /from ... /to ...` must be in the correct order.
- Dates `yyyy-mm-dd` can be substituted with `yyyy-mm-dd-xxxx` to specify times.


## Getting help
Displays a message showing all available commands and their usages  
Format: `help`
## Adding todos: `todo`
Format: `todo <name>`  
Example: `todo read book` adds a todo called `read book` to the task list.

## Adding deadlines: `deadline`
Format: `deadline <name> /by <yyyy-mm-dd> (or <yyyy-mm-dd-xxxx)`  
Example: `deadline return book /by 2025-09-20-1200` adds a deadline called `return book` with a due date of 
`20th September 2025 1200` to the task list.

## Adding events: `event`
Format: `event <name> /from <yyyy-mm-dd> /to <yyyy-mm-dddd> (<yyyy-mm-dd-xxxx> can be used to specify timings>`  
Example: `event math tutorial /from 2025-09-18-0800 /to 2025-09-18-0900` adds an event called `math tutorial` starting on `18th September 2025 0800` and ending at `18th September 2025 0900`  

## Showing all tasks: `list`
Shows all tasks in the task list  
Format: `list`

## Completing a task: `mark`
Marks a specified task as complete  
Format: `mark <index>`
- The task must not be completed yet
- The index must be a positive integer, and less than or equal to number of tasks currently in the list
- The index refers to the number shown beside the task when using the `list` command

## Un-completing a task: `unmark`
Marks a specified task as not complete  
Format: `unmark <index>`
- The task must be completed
- The index must be a positive integer, and less than or equal to number of tasks currently in the list
- The index refers to the number shown beside the task when using the `list` command


## Deleting a task: `delete`
Deletes the task at the specified index from the task list  
Format: `delete <index>`
- The index must be a positive integer, and less than or equal to number of tasks currently in the list
- The index refers to the number shown beside the task when using the `list` command

## Finding tasks: `find`
Finds all tasks containing a specified keyword  
Format: `find <keyword>` searches and prints all tasks that contain the `keyword` substring

## Closing the application: `bye`
Closes the application with a goodbye message  
Format: `bye`

# AI Usage
ChatGPT - 18 September 2025
- Refactor readData() in Storage class for better SLAP
- Refactor and improve JUnit tests 
