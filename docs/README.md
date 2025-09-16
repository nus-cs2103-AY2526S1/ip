# Friday Task Manager

Friday is an intelligent personal task management application that helps you organize your todos, deadlines, and events efficiently. Built with Java and JavaFX, Friday provides both a clean GUI and robust command-line interface for managing your daily tasks.

## ✨ Features

- **Multiple Task Types**: Create todos, deadlines with due dates, and events with time ranges
- **Smart Search**: Find tasks by keywords, completion status, or task type
- **Intuitive Commands**: Natural language commands with intelligent error handling and suggestions
- **Data Persistence**: Automatic saving and loading of tasks from local storage
- **Cross-Platform**: Runs on Windows, macOS, and Linux
- **Rich GUI**: Modern JavaFX interface with dialog-based interactions

### 🚀 Available Commands

| Command | Description | Example |
|---------|-------------|---------|
| `todo <description>` | Add a todo task | `todo buy groceries` |
| `deadline <description> /by <date>` | Add a deadline task | `deadline submit report /by 2023-12-25` |
| `event <description> /from <start> /to <end>` | Add an event | `event team meeting /from 2pm /to 3pm` |
| `list` | Show all tasks | `list` |
| `find <keyword>` | Search tasks by keyword | `find groceries` |
| `done` | Show completed tasks | `done` |
| `pending` | Show incomplete tasks | `pending` |
| `mark <number>` | Mark task as completed | `mark 1` |
| `unmark <number>` | Mark task as incomplete | `unmark 1` |
| `delete <number>` | Delete a task | `delete 2` |
| `help` | Show detailed help | `help` |
| `bye` | Exit the application | `bye` |

## 🛠️ Setup Instructions

### Prerequisites
- **JDK 17** or higher
- **IntelliJ IDEA** (recommended) or any Java IDE

### Installation

1. **Clone or download** this repository to your local machine
2. **Open IntelliJ IDEA** (if not in welcome screen, click `File` > `Close Project`)
3. **Import the project**:
   - Click `Open`
   - Select the project directory and click `OK`
   - Accept any default prompts
4. **Configure JDK**:
   - Set project to use **JDK 17** ([Setup Guide](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk))
   - Set **Project language level** to `SDK default`

### Running the Application

#### GUI Mode (Recommended)
```bash
./gradlew run
```

#### Command Line Mode
1. Build the project: `./gradlew build`
2. Run: `java -jar build/libs/friday.jar`

#### From IDE
- Locate `src/main/java/friday/GUI/Launcher.java`
- Right-click and select `Run Launcher.main()`

## 📁 Project Structure

```
src/
├── main/
│   ├── java/friday/
│   │   ├── GUI/           # JavaFX GUI components
│   │   ├── task/          # Task classes (ToDo, Deadline, Event)
│   │   ├── Command.java   # Command enumeration
│   │   ├── Friday.java    # Main application class
│   │   ├── Parser.java    # Command parser
│   │   ├── Storage.java   # File I/O operations
│   │   ├── TaskList.java  # Task management
│   │   └── Ui.java        # User interface handler
│   └── resources/
│       ├── images/        # GUI images
│       └── view/          # FXML files
├── test/java/             # Unit tests
└── data/Friday.txt        # Task storage file (auto-created)
```

## 🧪 Testing

Run all tests:
```bash
./gradlew test
```

View test reports:
```bash
open build/reports/tests/test/index.html
```

### Test Coverage
- **53 comprehensive tests** covering all components
- **Unit tests** for task creation, parsing, storage, and UI
- **Integration tests** for command processing
- **Edge case testing** for error handling and validation

## 📊 Usage Examples

```
> todo buy groceries
Got it. I've added this task:
[T] [ ] buy groceries
Now you have 1 task(s) in the list.

> deadline submit CS2103T project /by 2023-12-25
Got it. I've added this task:
[D] [ ] submit CS2103T project (by: Dec 25 2023)
Now you have 2 task(s) in the list.

> list
Here are your tasks:
1. [T] [ ] buy groceries
2. [D] [ ] submit CS2103T project (by: Dec 25 2023)

> mark 1
Nice! I've marked this task as done:
[T] [X] buy groceries

> find project
Here are the matching tasks in your list:
1. [D] [ ] submit CS2103T project (by: Dec 25 2023)
```

## 🔧 Technical Details

- **Language**: Java 17
- **GUI Framework**: JavaFX
- **Build Tool**: Gradle
- **Testing**: JUnit 5
- **Architecture**: MVC pattern with command-based parsing
- **Data Format**: Plain text with structured format for easy portability

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is part of the CS2103T Software Engineering course and is for educational purposes.

---

**Note**: Keep the `src/main/java` folder structure intact as it's required by Gradle and other build tools.
