# CharSiew User Guide 
Welcome to CharSiew, a friendly chatbot that helps you manage your tasks with simple commands.

## Installation
Make sure you have **Java 17 (or later)** installed:
```
java -version
```
Download the latest ```CharSiew.jar``` from the Releases section.

Run the chatbot with:

```
java -jar CharSiew.jar
```

## Getting Started
Once you launch CharSiew, you can type commands into the input box and press Enter to interact with it.

- Commands are case-sensitive

- Extra spaces before/after a command are ignored

- Dates must be in yyyy-mm-dd format

To see a summary of all commands anytime, type: ```help```

## Features
### 1. List all tasks
- Shows all tasks currently saved.
- Command: ```list```
- Example output:

```
Here are the tasks in your list:
1. [T][ ] buy milk
2. [D][ ] submit report (by: 2025-09-20)
```
### 2. Add a To-do task
- Adds a simple task without deadlines.
- Command:```todo TASK_NAME```
- Example:```todo buy milk```

### 3. Add a Deadline task
- Adds a task with a due date.
- Command: ```deadline TASK_NAME /by yyyy-mm-dd```
- Example:```deadline submit report /by 2025-09-20```

### 4. Add an Event
- Adds an event with a start and end date.
- Command: ```event TASK_NAME /from yyyy-mm-dd /to yyyy-mm-dd```
- Example:```event project meeting /from 2025-09-20 /to 2025-09-21```

### 5. Mark a task as done
- Marks a task as completed.
- Command: ```mark TASK_INDEX```
- Example: ```mark 2```

### 6. Unmark a task
- Marks a task as not done. 
- Command: ```unmark TASK_INDEX```
- Example:```unmark 2```

### 7. Delete a task
- Deletes a task permanently.
- Command: ```delete TASK_INDEX```
- Example: ```delete 3```

### 8. Find a task
- Finds a task with given keyword
- Command: ```find TASK_KEYWORD```
- Example: ```find meeting```

### 9. Undo last command
- Reverts the most recent change (e.g., undo delete, undo mark).
- Commnad: ```undo```

### 10. Exit the app
- Closes CharSiew.
- Command: ```bye```

## Error Handling
- Invalid command → ```I'm not too sure what that means...```

## Example Session
```
todo buy milk
deadline submit report /by 2025-09-20
event project meeting /from 2025-09-20 /to 2025-09-21
list
mark 1
undo
delete 2
bye
```