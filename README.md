# TsundereChan User Guide

![Product Screenshot](/docs/Ui.png)

TsundereChan is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI).
If you like getting verbally abused while managing your daily tasks, this is the app for you!


## Features
- Words in `<UPPER_CASE>` are the parameters to be supplied by users
- all inputs are non-case sensitive.


## Adding todos: `todo`

Adds a todo task to the list.  
Duplicate tasks (tasks with same name) will be rejected.


Format: `todo <TASK_NAME>`

Example: 
- `todo homework`
- `TODO workout`


## Adding deadlines: `deadline`

Adds a deadline task to the list.  
Time must be in the format of `yyyy-mm-dd HH:mm`  
Duplicate tasks (tasks with same name and time) will be rejected.

Format: `deadline <TASK_NAME> /by <TIME>`

Example:
- `deadline homework /by 2025-09-21 23:59`
- `DEADLINE ASSIGNMENT /BY 2025-10-22 11:59`


## Adding events: `event`

Adds an event task to the list.   
Time must be in the format of `yyyy-mm-dd HH:mm`  
Duplicate tasks (tasks with same name and time) will be rejected.

Format: `deadline <TASK_NAME> /from <TIME> /to <TIME>`  
**Parameters' order may not be switched around.**

Example:
- `event CCA /from 2025-09-21 18:00 /to 2025-09-21 20:00`
- `EVENT PRESENTATION /FROM 2025-09-22 15:00 /TO 2025-09-22 16:00`


## Listing all tasks: `list`

Shows a list of all tasks added so far.

Format: `list`


## Marking task as complete: `mark`

Marks a specified task as complete.

Format: `mark <INDEX>`
- Marks the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

Example:
- `mark 2` marks the 2nd task in the list


## Unmarking task as incomplete: `unmark`

Unmarks a specified task as incomplete.

Format: `unmark <INDEX>`
- Unmarks the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

Example:
- `unmark 2` unmarks the 2nd task in the list


## Deleting a task: `delete`

Deletes a specified task from the list.

Format: `delete <INDEX>`
- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

Example:
- `delete 2` deletes the 2nd task in the list


## Finding a task: `find`

Finds all tasks whose name contains the given keywords.

Format: `find <KEYWORD/S>`

- The search is case-insensitive e.g `HOMEWORK` will match `homework`
- The order of the keywords matter e.g.`assignment submission` will not match `submission assignment`
- Only the task name is searched.
- Starting characters of each word will be matched 
- e.g. `home` will match `homework`, `CCA` will match `do CCA activites`

Example: `find home` returns `homework` and `take home assignment`


## Exiting the program: `bye`

Exits the program.

Format: `bye`


## Saving data
All data is automatically saved into hard disk after any command that changes data.  
There is no need to save manually.


## Credits
App icon: taken from https://www.reddit.com/r/Kurisutina/comments/m4d441/embarrassed_kurisu/  
User profile picture: taken from https://wall.alphacoders.com/big.php?i=745340  
User profile picture secondary: taken from https://in.pinterest.com/pin/416794140489777976/  
TsundereChan profile picture: taken from https://hipwallpaper.com/view/kurisu-makise-wallpapers/23781


