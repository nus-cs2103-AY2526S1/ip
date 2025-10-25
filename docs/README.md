# Dwight – Your Assistant (to the) Regional Manager

> “Whenever I'm about to do something, I think, ‘Would an idiot do that?’ and if they would, I do not do that thing.” – Dwight K. Schrute

Dwight is a Java-based task manager inspired by *The Office*.  
It helps you manage tasks efficiently in a **Command Line Interface (CLI)**, with personality-packed responses from Dwight himself.

<p align="center">
  <img src="images/Ui.png" width="30%" />
  <img src="images/Ui2.png" width="30%" />
  <img src="images/Ui3.png" width="30%" />
</p>

---

## 📖 Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Usage](#usage)
   - [Adding Tasks](#adding-tasks)
   - [Listing Tasks](#listing-tasks)
   - [Marking and Unmarking Tasks](#marking-and-unmarking-tasks)
   - [Deleting Tasks](#deleting-tasks)
   - [Finding Tasks](#finding-tasks)
4. [Saving and Loading](#saving-and-loading)
5. [Command Summary](#command-summary)
6. [FAQ](#faq)

---

## 🚀 Quick Start

1. **Ensure you have Java 17 or above** installed:
   ```bash
   java -version
   ```

2. **Clone this repo**:
   ```bash
   git clone https://github.com/lim-james/ip.git
   ```

3. **Navigate into the project folder**:
   ```bash
   cd ip
   ```

4. **Build the JAR** with Gradle:
   ```bash
   ./gradlew shadowJar
   ```

5. **Run Dwight**:
   ```bash
   java -jar build/libs/dwight.jar
   ```

---

## ✨ Features

- Add **ToDo, Deadline, and Event** tasks  
- List all tasks with numbering  
- Mark and unmark task as completed by index
- Delete task by index
- Search for tasks description by keyword  
- Automatic save and load from storage  
- Personality-driven responses by Dwight  

---

## 📚 Usage

### Adding Tasks
```bash
new todo Buy milk
new deadline Submit report /by 20 Sep 2025
new event Company retreat /from 1 Oct 2025 /to 5 Oct 2025
```

### Listing Tasks
```bash
list
```

### Marking and Unmarking Tasks
```bash
mark 2
unmark 2
```

### Deleting Tasks
```bash
delete 3
```

### Finding Tasks
```bash
find report
```

---

## 💾 Saving and Loading

- Tasks are saved automatically after every command.
- When Dwight starts, it loads the existing tasks from storage.  
- If the save file is missing or corrupted, Dwight skips that line.

---

## 📝 Command Summary

| Command                                   | Description                          |
|-------------------------------------------|--------------------------------------|
| `new todo <desc>`                         | Add a ToDo task                      |
| `new deadline <desc> /by <date>`          | Add a Deadline task                  |
| `new event <desc> /from <date> /to <date>`| Add an Event task                    |
| `list`                                    | List all tasks                       |
| `mark <index>`                            | Mark a task as done                  |
| `unmark <index>`                          | Unmark a task                        |
| `delete <index>`                          | Delete a task                        |
| `find <keyword>`                          | Search tasks containing the keyword  |
| `bye`                                     | Exit Dwight                          |

---

## ❓ FAQ

**Q: Where are tasks saved?**  
A: In a local save file (`./dwight-2103-ip.txt` by default).

**Q: What happens if I delete the save file?**  
A: Dwight will create a new one and start with an empty list.

**Q: Does Dwight really insult Jim?**  
A: Yes. Frequently.

---

Enjoy using Dwight, and remember: *Identity theft is not a joke, Jim!*

