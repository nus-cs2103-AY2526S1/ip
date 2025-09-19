# Jackbot

Welcome to **Jackbot**, your personal task management chatbot.  
Jackbot helps you keep track of todos, deadlines, and events using either a **JavaFX GUI** or a **command-line interface (CLI)**.

---

## 🚀 Getting Started

### Run in GUI Mode (Default)
```bash
java -cp out Launcher
```
- Opens a user-friendly window.  
- Tasks are loaded from `./tasks.txt` by default.  

### Run in CLI Mode
```bash
java -cp out Launcher --cli
```
- Starts the console version of Jackbot.  
- Useful for environments without a graphical interface.  

### Specify a Custom Task File
```bash
java -cp out Launcher --file=mytasks.txt
```
- Loads tasks from `mytasks.txt`.  
- Works in both GUI and CLI modes (combine with `--cli`).  

---

## 📂 Loading Tasks from File

- At startup, Jackbot automatically **loads all existing tasks** from the file you specify.  
- If no file is given, it defaults to `./tasks.txt`.  
- If the file does not exist, Jackbot will create it on first save.  

Examples:
```bash
# Use default tasks file
java -cp out Launcher

# Load from a custom file
java -cp out Launcher --file=work.txt

# CLI mode with custom file
java -cp out Launcher --cli --file=school.txt
```

---

## ✨ Features

### Add Tasks
- **Todo**
  ```bash
  todo Buy groceries
  ```
- **Deadline**
  ```bash
  deadline Submit report /by 2025-09-30
  ```
- **Event**
  ```bash
  event Team meeting /at 2025-09-21 14:00-16:00
  ```

### View Tasks
```bash
list
```
Shows all current tasks with their status.

### Update Task Status
- **Mark task as done**
  ```bash
  mark 2
  ```
- **Unmark task**
  ```bash
  unmark 2
  ```

### Delete a Task
```bash
delete 3
```

### Find Tasks
```bash
find report
```
Searches tasks containing the keyword.

---

## 💾 Saving & Loading

- Jackbot automatically saves all tasks to the chosen file.  
- When you relaunch the app, tasks are reloaded from that file.  

---

## ❌ Exiting

- **CLI**: type `bye` and press Enter  
- **GUI**: close the application window  

---

## 🛠 Troubleshooting

- Ensure you are running **Java 11+**.  
- If using `--file=path`, check that the file path is valid and writable.  
- On first run, the file is created automatically if it doesn’t exist.  

---
