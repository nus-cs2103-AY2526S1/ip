# PoopieMeow User Guide

PoopieMeow is a command-line task management application that helps you keep track of your todos, deadlines, and events. It provides an intuitive interface for managing your tasks with features like task marking, searching, and chronological sorting.

## Quick Start

1. **Run the application**: `./gradlew run`
2. **Add your first task**: `todo read book`
3. **View all tasks**: `list`
4. **Mark task as done**: `mark 1`
5. **Exit the application**: `bye`

## Features

### Adding Tasks

PoopieMeow supports three types of tasks:

1. **Todos** - Simple tasks without dates
2. **Deadlines** - Tasks with a due date and time
3. **Events** - Tasks with a start and end time

#### Commands:
- `todo DESCRIPTION` - Add a todo task
- `deadline DESCRIPTION /by DATETIME` - Add a deadline task
- `event DESCRIPTION /from START_TIME /to END_TIME` - Add an event task

#### Examples:
```
todo read book
deadline submit report /by 2024-12-31 2359
event team meeting /from 2024-01-15 1400 /to 2024-01-15 1500
```

#### Date/Time Format:
- Use format: `yyyy-mm-dd hhmm` (24-hour format)
- Examples: `2024-12-31 2359`, `2024-01-15 1400`

### Viewing Tasks

#### List All Tasks
- `list` - Display all tasks in your list

#### Find Tasks by Keyword
- `find KEYWORD` - Search for tasks containing a specific keyword
- Search is case-insensitive and matches partial text

#### Examples:
```
find book
find meeting
find 2024
```

#### Show Tasks on Specific Date
- `show yyyy-mm-dd` - Display tasks scheduled for a specific date
- Shows deadlines due on that date and events occurring on that date

#### Examples:
```
show 2024-12-31
show 2024-01-15
```

### Managing Tasks

#### Mark Tasks as Done
- `mark TASK_NUMBER` - Mark a task as completed
- Task numbers are shown in the list (1, 2, 3, etc.)

#### Mark Tasks as Not Done
- `unmark TASK_NUMBER` - Mark a task as not completed

#### Delete Tasks
- `delete TASK_NUMBER` - Remove a task from your list

#### Sort Tasks Chronologically
- `sort` - Sort all tasks by date/time (earliest first)
- Todos without specific dates appear at the end

### Task Types and Display

#### Todo Tasks
- Display: `[T][✓] DESCRIPTION` (done) or `[T][✗] DESCRIPTION` (not done)
- Example: `[T][✓] read book`

#### Deadline Tasks
- Display: `[D][✓] DESCRIPTION (by: MMM d yyyy h:mm a)` (done) or `[D][✗] DESCRIPTION (by: MMM d yyyy h:mm a)` (not done)
- Example: `[D][✗] submit report (by: Dec 31 2024 11:59 PM)`

#### Event Tasks
- Display: `[E][✓] DESCRIPTION (from: MMM d yyyy h:mm a to: MMM d yyyy h:mm a)` (done) or `[E][✗] DESCRIPTION (from: MMM d yyyy h:mm a to: MMM d yyyy h:mm a)` (not done)
- Example: `[E][✗] team meeting (from: Jan 15 2024 2:00 PM to: Jan 15 2024 3:00 PM)`

### Data Persistence

- All tasks are automatically saved to `data/tasks.txt`
- Tasks persist between application sessions
- If the data file is corrupted, the application will start with an empty task list

### Error Handling

The application provides helpful error messages for common issues:

- **Empty descriptions**: "The description cannot be empty!"
- **Invalid task numbers**: "Task number X does not exist. Please enter a valid task number (1 to Y)."
- **Invalid date format**: "Please provide date in the format: yyyy-mm-dd"
- **Unknown commands**: "I don't understand 'command'. Please try a valid command!"

### Command Summary

| Command | Description | Example |
|---------|-------------|---------|
| `todo DESCRIPTION` | Add a todo task | `todo read book` |
| `deadline DESCRIPTION /by DATETIME` | Add a deadline task | `deadline submit report /by 2024-12-31 2359` |
| `event DESCRIPTION /from START /to END` | Add an event task | `event meeting /from 2024-01-15 1400 /to 2024-01-15 1500` |
| `list` | Show all tasks | `list` |
| `mark NUMBER` | Mark task as done | `mark 1` |
| `unmark NUMBER` | Mark task as not done | `unmark 1` |
| `delete NUMBER` | Delete a task | `delete 1` |
| `find KEYWORD` | Search for tasks | `find book` |
| `show DATE` | Show tasks on date | `show 2024-12-31` |
| `sort` | Sort tasks chronologically | `sort` |
| `bye` | Exit application | `bye` |

## Technical Details

### Requirements
- Java 11 or higher
- Gradle build system

### Building and Running
```bash
# Build the project
./gradlew build

# Run the application
./gradlew run

# Run tests
./gradlew test
```

### File Structure
```
src/main/java/poopiemeow/
├── PoopieMeow.java          # Main application class
├── TaskList.java            # Task list management
├── exception/
│   └── EmptyDescriptionException.java
├── parser/
│   └── Parser.java          # Command parsing
├── storage/
│   └── Storage.java         # File I/O operations
├── task/
│   ├── Task.java           # Abstract task class
│   ├── Todo.java           # Todo task implementation
│   ├── Deadline.java       # Deadline task implementation
│   └── Event.java          # Event task implementation
└── ui/
    └── Ui.java             # User interface
```

## Getting Help

If you encounter any issues or need assistance:

1. Check the error messages displayed by the application
2. Ensure you're using the correct command format
3. Verify date/time formats match the required pattern
4. Make sure task numbers exist before using mark/unmark/delete commands

## Version History

- **v1.0** - Initial release with basic task management, search functionality, and chronological sorting
