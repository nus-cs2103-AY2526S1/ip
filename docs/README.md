# Ryuji User Guide

![Screenshoot of GUI for Ryuji](https://BoredFurry.github.io/ip/Ui.png)

# Ryuji - A Task Management Chatbot

Ryuji is a task management chatbot that allows users to interact with their to-do lists via a simple chat interface. Users can add, delete, mark, unmark, and search for tasks, all through text-based commands. The bot supports various task types like `ToDo`, `Deadline`, and `Event`, and can persist tasks across sessions in a CSV file.

## Features

- **Task Management**: Add, delete, mark/unmark tasks.
- **Search**: Find tasks by labels or descriptions.
- **Task Persistence**: Tasks are saved to a CSV file so that they persist across sessions.
- **User Interaction**: A chat-like interface for interacting with the bot.

## Commands

Ryuji supports the following commands:

- `list` – View all tasks.
- `todo <description>` – Add a new ToDo task.
- `deadline <description>` – Add a new Deadline task.
- `event <description>` – Add a new Event task.
- `mark <task number>` – Mark a task as completed.
- `unmark <task number>` – Unmark a completed task.
- `delete <task number>` – Delete a task from the list.
- `find <keyword>` – Search for tasks containing a specific keyword.
- `help` – Display a help message with command usage.
- `bye` – Exit the application.

## Setup

### Prerequisites

- **Java 8+**: Make sure you have Java installed. You can check your Java version with:

    ```bash
    java -version
    ```
- Ideally have Java 17 installed.

- **IDE**: You can use any Java IDE, such as IntelliJ IDEA, Eclipse, or Visual Studio Code, but this project has been built with Intellij in mind.

### 1. Clone the Repository

To clone the repository and set up the project locally:

```bash
git clone https://github.com/your-username/ryuji.git
cd ryuji

```
### 2. Install Jar file

If you don't want to build the application from source, you can download a pre-built **JAR file** (Java Archive) for this application. The JAR file contains all the necessary code and dependencies to run the application.

1. Go to the [releases page](https://github.com/BoredFurry/ip/releases) of the project (replace with actual URL).
2. Download the latest release `.jar` file (e.g., `ryuji-chatbot-1.0.jar`).
3. Save the `.jar` file to a directory on your machine where you can easily access it.

---
## Build and Run

### 1. Import into your IDE or Build from Command Line

- If you're using an IDE like **IntelliJ IDEA** or **Eclipse**, simply **import the project** as a Gradle project.
- Alternatively, you can **build the project** from the command line using **Gradle**.

### 2. Running the Application

### Using Gradle

1. **Clone the repository** or download the source code.

   If you are using Git, clone the repository:
    ```bash
    git clone https://github.com/BoredFurry/ip.git
    cd ip
    ```

2. **Build the application** using Gradle.

   If you don't have Gradle installed, use the **Gradle Wrapper** to build the project:
    ```bash
    ./gradlew build
    ```

   On **Windows**:
    ```bash
    gradlew.bat build
    ```

   This will compile the code, run tests, and generate the JAR file. After building, you will find the JAR file in the `build/libs` directory (e.g., `build/libs/ryuji-chatbot-1.0.jar`).

3. **Run the application** using the following command:
    ```bash
    java -jar build/libs/ip.jar
    ```

4. **Interacting with the chatbot**: Once the application is running, a GUI window will open, and you can interact with the chatbot as described in the [Usage](#usage) section.

---

### Using the JAR File

If you downloaded the JAR file from the releases page, you can run it directly without needing to build the project.

1. **Download the JAR file** (as described in the [Setup](#setup) section).
2. Open a terminal or command prompt and navigate to the directory where the JAR file is located.
3. Run the application with the following command:
    ```bash
    java -jar ryuji-chatbot-1.0.jar
    ```

4. **Interacting with the chatbot**: Once the application is running, a GUI window will open, and you can interact with the chatbot as described in the [Usage](#usage) section.

---

### Notes on Running the JAR File

- When running the JAR file, make sure that you have **Java 11** or higher installed on your system.
- If you are running into any issues related to Java versions, please ensure your environment variable `JAVA_HOME` is set correctly.
- If you encounter any issues running the JAR file, you may want to check the logs or console output for any error messages. You can try troubleshooting by checking your Java version or dependencies.

---
### 3. Task Storage

- The application will automatically create a `tasks.csv` file if it doesn't already exist. This file will store all the tasks you add through the bot.

- The CSV file will be saved in the root directory of the project.

- If the file doesn't exist, the bot will initialize an empty task list for the session.

### 4. Interface Overview

- When you first run the application, it will show a **welcome message** and prompt you to interact with the bot.
- The interface includes a **task list** and a **user input field** where you can type commands.


## Usage

After running the application, you will interact with the **Ryuji chatbot** through a graphical user interface (GUI). Below is a breakdown of how to use the application:

### 1. Initial Startup

When you first launch the application, the **welcome message** will appear, along with a prompt asking you:

This marks the beginning of your interaction with Ryuji. You can now enter your commands.

### 2. Available Commands

Here is a list of all the available commands you can use in the chatbot:

#### Task Management Commands

- **list**: Displays all the tasks currently in the task list.
- **todo [task description]**: Adds a **ToDo** task with the specified description.
- **deadline [task description] /by [date]**: Adds a **Deadline** task with the specified description and due date.
- **event [task description] /at [time/place]**: Adds an **Event** task with the specified description and date/time.
- **delete [task number]**: Deletes the task at the specified position.
- **mark [task number]**: Marks the task at the specified position as completed.
- **unmark [task number]**: Unmarks the task at the specified position as incomplete.

#### Search Command

- **find [keyword]**: Searches for tasks that contain the specified keyword in their descriptions.

#### Help and Exit

- **help**: Displays a help message with a list of all available commands.
- **bye**: Exits the application. A farewell message will appear and the program will terminate after a brief delay.

### 3. How to Interact with the Bot

- Type a command into the **input field** at the bottom of the window.
- Press the **send button** or hit **Enter** to send the command.
- The bot will process the command and respond with the result:
    - If you add a task, it will confirm that the task has been added.
    - If you mark or unmark a task, it will show the updated task status.
    - If you delete a task, it will confirm that the task has been removed.
    - If you search, it will show the tasks that match your search term.

### 4. Sample Usage

#### Adding a ToDo Task

To add a **ToDo** task, type:
```
todo Buy Groceries
```

This will add the task "Buy groceries" to the task list.

```
[T][ ] Buy groceries
```

#### Adding a Deadline Task

To add a **Deadline** task, type:

```
deadline return book /by Monday 3pm
```
This will add the task "return book" to the task list and indicate the time "(by: Monday 3pm)".

```
[D][ ] return book (by: Monday 3pm)
```

#### Adding a Event Task

To add a **Event** task, type:

```
event JMOF /from January 9th 2026 /to January 11th 2026
```
This will add the task "JMOF" to the task list and indicate the duration "(from: January 9th 2026 to: January 11th 2026)".

```
[E][ ] JMOF (from: January 9th 2026 to: January 11th 2026)
```

#### Marking a task
To mark a task, type:
```
mark 1
```
this will mark the first task as complete

```
[T][X] Buy groceries
```

#### Unmarking a task
To unmark a task, type:
```
unmark 1
```
this will unmark the first task

```
[T][ ] Buy groceries
```

#### Display current task list
To display the current tasks you have, type:
```
list
```
this will show your current task list

```
1. [T][ ] Buy groceries
2. [D][ ] return book (by: Monday 3pm)
3. [E][ ] JMOF (from: January 9th 2026 to: January 11th 2026)
```

#### Delete a task
To delete a task, type:
```
delete 1
```
this will delete the first task. to check the result, call list
```
list
```

```
1. [D][ ] return book (by: Monday 3pm)
2. [E][ ] JMOF (from: January 9th 2026 to: January 11th 2026)
```

#### Finding a task
To find a task, type:
```
find {search term}
```
this will bring up all tasks that contain that search term
```
find JMOF
```
```
1.[E][ ] JMOF (from: January 9th 2026 to: January 11th 2026)
```

#### Getting help
If you require further assistance, you may type:
```
help
```
and a help message will appear explaining the available functions
```
Welcome to RyujiCafe, here is the menu:
    In this cafe, you can keep track of tasks so that you can focus on doing them
    Types of tasks I can assist with:
    ToDos
        type: todo make coffee
        result: [T][ ] make coffee
        
    Deadlines
        type: deadline return book /by 2025-09-15 1600
        result: [D][ ] return book (by 2025-09-15 1600)
        
    Events
        type: event JMOF /from 2026-01-09 /to 2026-01-11
        result: [E][ ] JMOF (from 2026-01-09 to 2026-01-11)
        
    Commands to help you with your list of tasks:
    Mark
        type: mark 1
        result: [T][ ] return book -> [T][X] return book
        
    Unmark
        type: unmark 1
        result: [T][X] return book -> [T][ ] return book
        
    List
        type: list
        result:
        1. [T][ ] make coffee
        2. [D][ ] return book (by 2025-09-15 1600)
        3. [E][ ] JMOF (from 2026-01-09 to 2026-01-11)
        
    Delete
        type delete 1
        result: first item will be removed from the list
        
    Find\n" +
        type: find book
        result: 1. [D] return book (by 2025-09-15)
        
    Exit
        type: exit
        result: ends the chat with Ryuji
        
    Go ahead and try some of these commands";
 ```
#### Exiting the Ryuji Application
Once you are done and wish to exit, you may type:
```
bye
```
this will allow Ryuji to say farewell and the application will close
```
Bye. Hope to see you again soon!
```

## Be sure to try out all the other commands Ryuji has to offer!