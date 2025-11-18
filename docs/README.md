# Lee Kuan Yew User Guide


![Product Screenshot](Ui.png)

Lee Kuan Yew is a personal chatbot designed to help you manage your tasks, set reminders, and stay organized throughout your day.
This chatbot will ignite pratriotic fervor in you, with a personality and quotes inspired by Singapore's founding father, Lee Kuan Yew.

With its intuitive interface and powerful features, Lee Kuan Yew makes it easy to keep track of your to-do list and never miss an important deadline.
It will help you stay organized and motivated, while also providing a touch of inspiration from one of Singapore's most iconic leader


## Getting Started
**Running the Application**
1. Download the JAR file from releases.
2. Open your terminal and navigate to the directory where the JAR file is located.
3. Run the application using the command: 
    ```
    java -jar leekuanyew.jar
    ```

## Features
- **Task Management**: Add, delete, and view tasks with ease.
- **User-Friendly Interface**: Simple and intuitive design for easy navigation.
- **Inspirational Quotes**: Receive motivational quotes from Lee Kuan Yew to keep you
- on track.
- **Persistent Storage**: All your tasks and reminders are saved automatically.

## Commands
Here are some of the commands you can use to interact with Lee Kuan Yew:

### 📝 Adding tasks:

---
- `todo <task_description>`: Adds a to-do task.
- `deadline <task_description> /yyyy-mm-dd`: Adds a task with a deadline.
- `event <task_description> /<start_time> /<end_time>`: Adds an event task.

### ⏰ Updating deadlines:

---
- `update deadline <task_number> /<new_deadline>`: Updates the deadline of a deadline task.

### 🗑️ Deleting tasks:

---
- `delete <task_number>`: Deletes the specified task.

### ✅ Marking tasks as done:

---
- `mark <task_number>`: Marks the specified task as completed.

### ❎ Unmarking tasks as done:

---
- `unmark <task_number>`: Marks the specified task as not completed.

### 📋 Viewing tasks:

---
- `list`: Displays all tasks with their status.

### 💾 Saving tasks:

---
- `save`: Saves the current list of tasks to a file.

### 🔍 Finding tasks:

---
- `find <keyword>`: Searches for tasks containing the specified keyword.


