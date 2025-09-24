# SOFI - Smart Task Manager

![SOFI Logo](docs/Ui.png)

**SOFI** (Smart Organizer for Intelligent Tasks) is a powerful task management application that helps you organize and track your daily tasks, deadlines, and events. Built with Java and JavaFX, SOFI provides both command-line and graphical user interfaces for maximum flexibility.

## Features

- ✅ **Task Management**: Create and manage todos, deadlines, and events
- 🏷️ **Tagging System**: Organize tasks with custom tags
- 🔍 **Smart Search**: Find tasks quickly with keyword search
- 💾 **Data Persistence**: Automatic saving and loading of tasks
- 🎨 **Dual Interface**: Command-line and GUI versions available
- 🛡️ **Error Handling**: Graceful handling of errors and data corruption

## Quick Start

### Command Line Interface
```bash
./gradlew run
```

### Graphical User Interface
```bash
# Switch to GUI mode
sed -i '' 's/mainClass.set("sofi.SOFI")/mainClass.set("sofi.Launcher")/' build.gradle
./gradlew run
```

## Installation

### Prerequisites
- **JDK 17** or higher
- **Gradle** (included via wrapper)

### Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Jaredee123/ip.git
   cd ip
   ```

2. **Build the project**:
   ```bash
   ./gradlew build
   ```

3. **Run the application**:
   ```bash
   ./gradlew run
   ```

## Usage

### Basic Commands

| Command | Description | Example |
|---------|-------------|---------|
| `todo DESCRIPTION` | Add a todo task | `todo read book` |
| `deadline DESCRIPTION /by TIME` | Add a deadline task | `deadline submit report /by 2024-12-31` |
| `event DESCRIPTION /from START /to END` | Add an event task | `event meeting /from Mon 2pm /to Mon 3pm` |
| `list` | Show all tasks | `list` |
| `mark NUMBER` | Mark task as done | `mark 1` |
| `unmark NUMBER` | Mark task as not done | `unmark 1` |
| `delete NUMBER` | Delete a task | `delete 1` |
| `find KEYWORD` | Search tasks | `find work` |
| `tag NUMBER TAG` | Add tag to task | `tag 1 work` |
| `untag NUMBER TAG` | Remove tag from task | `untag 1 work` |
| `bye` | Exit application | `bye` |

### Example Session

```
Hello! I'm SOFI
What can I do for you?

> todo Complete project proposal
Got it. I've added this task:
   [T][ ] Complete project proposal
Now you have 1 task(s) in the list.

> deadline Submit report /by 2024-12-31
Got it. I've added this task:
   [D][ ] Submit report (by: Dec 31 2024, 11:59 pm)
Now you have 2 task(s) in the list.

> tag 1 work
Nice! I've tagged this task with #work:
   [T][ ] Complete project proposal #work

> list
Here are the tasks in your list:
1. [T][ ] Complete project proposal #work
2. [D][ ] Submit report (by: Dec 31 2024, 11:59 pm)

> bye
Bye. Hope to see you again soon!
```

## Project Structure

```
ip/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── sofi/
│   │   │   │   ├── SOFI.java          # Main application class
│   │   │   │   ├── Launcher.java      # GUI launcher
│   │   │   │   ├── MainApp.java       # JavaFX application
│   │   │   │   ├── MainWindow.java    # GUI controller
│   │   │   │   ├── DialogBox.java     # Chat dialog component
│   │   │   │   ├── Parser.java        # Command parser
│   │   │   │   ├── Ui.java            # User interface
│   │   │   │   ├── Task.java          # Base task class
│   │   │   │   ├── Todo.java          # Todo task
│   │   │   │   ├── Deadline.java      # Deadline task
│   │   │   │   ├── Event.java         # Event task
│   │   │   │   ├── TaskList.java      # Task collection
│   │   │   │   ├── Storage.java       # Data persistence
│   │   │   │   └── SofiException.java # Custom exception
│   │   │   └── ...
│   │   └── resources/
│   │       ├── images/                # GUI images
│   │       └── view/                  # FXML files
│   └── test/
│       └── java/                      # Unit tests
├── docs/
│   ├── README.md                      # User guide
│   └── Ui.png                         # Screenshot
├── data/
│   └── sofi.txt                       # Task data file
└── build.gradle                       # Build configuration
```

## Development

### Running Tests
```bash
./gradlew test
```

### Building JAR
```bash
./gradlew shadowJar
```

### Code Quality
The project includes comprehensive error handling, input validation, and follows Java best practices.

## Documentation

- **[User Guide](docs/README.md)** - Comprehensive user documentation
- **[Live Demo](https://jaredee123.github.io/ip/)** - GitHub Pages documentation

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is part of an educational exercise and is available for learning purposes.

## Acknowledgments

- Built as part of the CS2103T Software Engineering course
- Inspired by the need for efficient task management
- Thanks to the Java and JavaFX communities for excellent tooling

---

**SOFI - Your intelligent task management companion** 🤖

*Keep organized, stay productive, and never miss a deadline again!*