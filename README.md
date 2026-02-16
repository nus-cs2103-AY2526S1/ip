# 🤖 TronaldDump - Task Management Chatbot

> **"I HATE DEMOCRATS! IF YOU WANT TO ADD A TASK, I ONLY UNDERSTAND TODO, EVENT, AND DEADLINE TASKS!"** - _TronaldDump Bot_

A **Java-based task management chatbot** with a unique personality inspired by a certain former president. This application allows users to manage their tasks through a command-line interface with some... _interesting_ error messages.

## 🚀 Features

### Core Functionality

- **Task Management**: Create, list, mark, unmark, and delete tasks
- **Multiple Task Types**: Support for `todo`, `event`, and `deadline` tasks
- **Persistent Storage**: Tasks are automatically saved to and loaded from a file
- **Interactive CLI**: Real-time command processing with immediate feedback

### Task Types Supported

- **Todo**: Simple tasks without time constraints
- **Event**: Tasks with specific event times
- **Deadline**: Tasks with due dates

## 📋 Available Commands

The following commands are supported by TronaldDump:

1. **`todo <description>`** - Create a new todo task
2. **`event <description> /at <time>`** - Create a new event task
3. **`deadline <description> /by <date>`** - Create a new deadline task
4. **`list`** - Display all tasks
5. **`mark <index>`** - Mark a task as completed
6. **`unmark <index>`** - Mark a task as incomplete
7. **`delete <index>`** - Remove a task from the list
8. **`find <keyword>`** - Search for tasks containing the keyword
9. **`bye`** - Exit the application

## 💻 Example Interaction

```bash
# Example interaction with TronaldDump
________________________________

GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA
WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?

________________________________

> todo Buy groceries
________________________________

OKAY. I HAVE ADDED THIS TASK:
 [T] [ ] Buy groceries
NOW YOU HAVE 3 TASKS IN THE LIST.

________________________________

> deadline Submit report /by 2024-12-31
________________________________

OKAY. I HAVE ADDED THIS TASK:
 [D] [ ] Submit report (by: Dec 31 2024)
NOW YOU HAVE 4 TASKS IN THE LIST.

________________________________

> list
________________________________

HERE ARE THE TASKS IN YOUR LIST:
1. [T] [ ] eat
2. [T] [X] sleep
3. [T] [ ] Buy groceries
4. [D] [ ] Submit report (by: Dec 31 2024)
________________________________

> mark 1
________________________________

GOOD JOB! TASK 1 IS MARKED AS DONE.

[T] [X] eat
________________________________

> bye
________________________________

THANK YOU FOR YOUR ATTENTION TO THIS MATTER!

________________________________
```

## ✅ Development Tasks

- [x] Implement basic task management functionality
- [x] Add persistent storage with file I/O
- [x] Create command factory pattern
- [x] Implement all task types (Todo, Event, Deadline)
- [x] Add error handling with custom exceptions
- [x] Create comprehensive test suite
- [ ] Add GUI interface

## 🔧 Configuration

The application automatically creates a `data/tronalddump.txt` file to store tasks persistently. No additional configuration is required.

**Note**: This chatbot has a _unique_ personality. Don't take the error messages too seriously! 😄

For more information, visit the [project repository](https://github.com/ziheng119/ip).
