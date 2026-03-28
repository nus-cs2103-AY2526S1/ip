# Dadbot

Welcome to the user guide for the Duke-inspired chatbot Dad, for CS2103T iP.

### Quick Start

- Ensure you have Java 17 or above
- Download the latest jar from the [Releases](https://github.com/JordanLow/ip/releases) tab
- Simply execute the jar

## Features

### Tasks: Todo, Deadline, Event

- `todo [task]` to add a Todo task
- `deadline [task] /by YYYY-MM-DD` to add a Deadline task
- `event [task] /from YYYY-MM-DD /to YYYY-MM-DD` to add an Event task

### List and Find

- `list` to list all tasks
- `find [keywords]` to list all tasks matching your search term

### Mark and Unmark

- `mark [index]` to mark a task at the given index as complete
- `unmark [index]` to unmark a previously marked task at the given index

### Delete and Undo

- `delete [index]` to remove a task at the given index
- `undo` to undo the previous command
