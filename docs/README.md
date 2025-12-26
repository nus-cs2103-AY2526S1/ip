# Chunky Task Manager - User Guide

## Table of Contents
1. [Getting Started](#getting-started)
2. [Basic Commands](#basic-commands)
3. [Task Types](#task-types)
4. [Managing Tasks](#managing-tasks)
5. [Task Statistics](#task-statistics)
6. [Data Storage](#data-storage)
7. [Troubleshooting](#troubleshooting)

## Getting Started

Chunky is a task management application that helps you organize your todos, deadlines, and events. The application features both a command-line interface and a graphical user interface (GUI).

### Launching the Application
- **GUI Mode**: Run the application through your IDE or execute the JAR file
- **Console Mode**: Use the command-line interface for text-based interaction

## Basic Commands

### Adding Tasks
Use these commands to add different types of tasks:

```
todo [description]
deadline [description] /by [date]
event [description] /from [start] /to [end]
```

### Viewing Tasks
```
list
```
Displays all your tasks with their current status and details.

### Exiting
```
bye
```
Saves your tasks and exits the application.

## Task Types

### Todo Tasks
Simple tasks without specific timing requirements.
- **Format**: `todo [description]`
- **Example**: `todo buy groceries`
- **Display**: `[T][ ] buy groceries`

### Deadline Tasks
Tasks with a specific due date or time.
- **Format**: `deadline [description] /by [date]`
- **Example**: `deadline submit report /by 2024-12-01`
- **Display**: `[D][ ] submit report (by: Dec 1 2024)`

### Event Tasks
Tasks with both start and end times.
- **Format**: `event [description] /from [start] /to [end]`
- **Example**: `event team meeting /from 2pm /to 4pm`
- **Display**: `[E][ ] team meeting (from: 2pm to: 4pm)`

## Managing Tasks

### Marking Tasks as Complete
```
mark [task number]
```
Marks the specified task as completed.
- **Example**: `mark 1`
- **Result**: `[T][X] buy groceries`

### Unmarking Tasks
```
unmark [task number]
```
Marks a completed task as incomplete.
- **Example**: `unmark 1`
- **Result**: `[T][ ] buy groceries`

### Deleting Tasks
```
delete [task number]
```
Permanently removes a task from your list.
- **Example**: `delete 2`

### Finding Tasks
```
find [keyword]
```
Searches for tasks containing the specified keyword.
- **Example**: `find meeting`
- Shows all tasks containing "meeting"

## Task Statistics

The GUI displays real-time statistics at the bottom of the window:

### Task Counter
- Shows total number of tasks
- Displays completed task count
- Shows remaining tasks
- **Format**: "Tasks: X total | Y completed | Z remaining"

### Statistics Updates
The counter automatically updates when you:
- Add new tasks
- Mark tasks as complete
- Unmark tasks
- Delete tasks

## Data Storage

### Automatic Saving
- Tasks are automatically saved when you exit with `bye`
- Data is stored in `Chunky.txt` file
- No manual save command required

### File Format
Tasks are stored in a structured format:
- `T | 0 | description | location` (Todo)
- `D | 1 | description | deadline | location` (Deadline)
- `E | 0 | description | start | end | location` (Event)

The second field indicates completion status (0 = incomplete, 1 = complete).

