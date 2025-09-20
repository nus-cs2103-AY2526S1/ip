# Note User Guide

## Product screenshot
![Ui.png](Ui.png)

Note is a simple task management application that helps you keep track of your:
Todos, Deadlines, and Events.
You can add tasks, mark them as done, search for tasks, and save your tasks to disk automatically.
All data is stored in data/duke.txt so that your tasks persist between sessions.

## General Notes About Commands

Words in < > are parameters you must supply.
Example: todo \<description\> — you must provide a description.

Items in [ ] are optional.
Example: <description> [t/TAG]

Parameters can be entered in any order if the command allows multiple fields.

Extra parameters for commands that do not take any (like help, list, or bye) are ignored.

## Date and Time Format

All dates and times must follow this format:

d/M/yyyy HHmm


d → day of the month (1–31)

M → month (1–12)

yyyy → year (4 digits)

HHmm → 24-hour time (e.g., 1400 for 2:00 PM)

Example: 19/9/2025 2359 represents 19 September 2025, 11:59 PM.

## Commands
### 1. todo

Adds a task without any date/time.

Format:
todo \<description\>

Example:
todo Finish CS2103T project

### 2. deadline

Adds a task with a deadline.

Format:
deadline \<description\> /by \<d/M/yyyy HHmm\>

Example:
deadline Submit report /by 19/9/2025 2359

### 3. event

Adds a task with a start and end time.

Format:
event \<description\> /from \<d/M/yyyy HHmm\> /to \<d/M/yyyy HHmm\>

Example:
event Team meeting /from 20/9/2025 1400 /to 20/9/2025 1600

### 4. list

Displays all tasks in your task list.

Format:
list

Example Output:

Here are the tasks in your list:

1.[T][ ] Finish CS2103T project

2.[D][ ] Submit report (by: Sep 19 2025, 11:59 pm)

3.[E][ ] Team meeting (from: Sep 20 2025, 2:00 pm to: Sep 20 2025, 4:00 pm)


### 5. mark

Marks a task as done.

Format:
mark \<index\>


Example:
mark 2

### 6. unmark

Marks a task as not done.

Format:
unmark \<index\>


Example:
unmark 2

### 7. delete

Deletes a task from your list.

Format:
delete \<index\>

Example:
delete 1

### 8. find

Searches for tasks containing a keyword.

Format:
find \<keyword\>


Example:
find report


Output:

Here are the matching tasks in your list:

1.[D][ ] Submit report (by: Sep 19 2025, 11:59 pm)

### 9. help

Displays a list of all available commands.

Format:
help

### 10. bye

Exits the application.

Format:
bye

## Notes and Tips

Task numbering: Indexes used in mark, unmark, delete correspond to the numbers shown in the list command.

Automatic saving: All tasks are saved automatically to data/duke.txt. No manual saving is needed.

Invalid entries: Tasks with invalid date/time formats will be skipped during loading.

Command order: Parameters can be in any order for commands that accept multiple arguments (not for date/time fields).