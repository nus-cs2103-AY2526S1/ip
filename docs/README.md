# 📖 Alice Task Manager

Alice is a personal task management assistant with both **CLI** and **GUI** support.  
You can add tasks, mark them as done, delete them, and even set deadlines and events.  
All your tasks are saved automatically into a file (`duke.txt`) so you don’t lose progress between sessions.

![How the chat will look like!](Ui.png)
---

## ✨ Features

### 1. Add Tasks
- **Todo**
  ```bash
  todo read book

Adds a simple task with no date/time attached.

- **Deadline**
    ```bash
    deadline return book /by 12/12/2019 1800
Adds a task that must be completed before a specific date/time.

- **Event**
    ```bash
    event project meeting /from 12/12/2019 1400 /to 12/12/2019 1600
Adds a task with a start and end time.

### 2. List
    list
Displays all the tasks currently stored.

### 3. Mark and Unmark
- **Mark task #2 as done**
    ```bash
    mark 2
- **Mark task #2 as not done**
    ```bash
    unmark 2
  
### 4. Delete Tasks
    delete 3
Deletes task #3 from the list

### 5. Save and Load
- All tasks are saved in a file called alice.txt
- When you restart Alice, your previous tasks are automatically loaded back

### 6. Exit
    bye
Exits Alice (closes the program if you are running the GUI)

🚀 How to Run
Prerequisites

- Java 17

- Gradle (if not bundled with IntelliJ)

### Running in IntelliJ

1. Clone this repository into IntelliJ.

2. Build the project using Gradle.

3. Run the Main class to start Alice.

### Running from JAR

1. Build the JAR file:
    ```
    ./gradlew clean shadowJar

The JAR will be located in build/libs/.

2. Run the JAR:
    ```
    java -jar build/libs/alice.jar