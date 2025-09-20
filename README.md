# Lyra

> "Your mind is for having ideas, not holding them." – David Allen ([source](https://gettingthingsdone.com/))

**Lyra** frees your mind of having to remember things you need to do. It's:

- **text-based**
- *easy to learn*
- ~~FAST~~ **SUPER FAST** to use

All you need to do is:

1. Clone this repository or download the latest release
2. Run `./gradlew run` for the GUI version or `./gradlew runShadow` for the command line version
3. Add your tasks using simple commands
4. Let it manage your tasks for you 😉

And it is **FREE**!

## Features

- [x] Managing tasks (todos, deadlines, events)
- [x] Data persistence (automatic saving/loading)
- [x] Search functionality
- [x] Mark tasks as done/not done
- [x] Delete tasks
- [x] Both command-line and GUI interfaces
- [ ] Reminders (coming soon)

If you are a Java programmer, you can use it to practice Java too. Here's the main method:

```java
public class Lyra {
    public static void main(String[] args) {
        new Lyra("data/lyra.txt").run();
    }
}
```

## Quick Start

### Building the Project
```bash
# Build the project
./gradlew build

# Run the GUI version
./gradlew run

# Run the command line version
./gradlew runShadow

# Run tests
./gradlew test
```

## Setting up in IntelliJ

**Prerequisites:** JDK 17, update IntelliJ to the most recent version.

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into IntelliJ as follows:
   1. Click `Open`
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults
3. Configure the project to use **JDK 17** (not other versions) as explained [here](https://jetbrains.com/help/idea/sdk.html#set-up-jdk)<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option
4. After that, locate the `src/main/java/lyra/Lyra.java` file, right-click it, and choose `Run Lyra.main()`. If the setup is correct, you should see something like the below as the output:

```
Hello! I'm Lyra
What can I do for you?
```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## Usage

### Command Line Interface
To use Lyra from the command line, run the application and type commands like:

- `todo buy groceries` - adds a new todo task
- `deadline submit report /by 2024-12-31` - adds a deadline task
- `event team meeting /from 2024-12-25 /to 2024-12-26` - adds an event task
- `list` - shows all tasks
- `mark 1` - marks task 1 as done
- `unmark 1` - marks task 1 as not done
- `delete 1` - deletes task 1
- `find meeting` - searches for tasks containing "meeting"
- `bye` - exits the application

### GUI Interface
Lyra also includes a modern JavaFX GUI interface. To run the GUI version:

1. Run `./gradlew run` from the project root
2. Use the text field at the bottom to enter commands
3. Click "Send" or press Enter to execute commands

The GUI provides the same functionality as the command line interface with a more user-friendly interface.

## Contributing

Feel free to contribute to this project by submitting pull requests or opening issues! 🚀
