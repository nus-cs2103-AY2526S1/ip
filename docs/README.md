# Jimmy User Guide

![](docs/Ui.png)
Jimmy is a fast and efficient task management application that helps you organize your todos, deadlines, and events through a clean command-line interface. Perfect for users who prefer keyboard-driven productivity.

## 🚀 Quick Start
1. Download the latest jar release file

2. Copy the file to your preferred folder

3. Open a terminal in that folder and run: java -jar Jimmy.jar

4. Type commands to manage your tasks

5. Type "bye" to close the application

## 💡Features
Jimmy has a variety of features! 

- Add and delete tasks!
  - Todos
  - Events
  - Deadlines

- Mark tasks as done

- Find tasks with keywords

- Tag tasks with custom tags

## ❓Guide 

### 📜 Display your task list!

Display all your current tasks!

Command: ``list``

Expected Output (with sample tasks):

```
Here are the tasks in your list:
1. [D][] work (by: Aug 8 2025 14:00)
2. [T][] exercise 
```

### 📋 Adding todos

Add a simple task without any date/time! 

Example Command: ``todo work``

Expected Output:

```
Got it. I've added this task:
[T][] work
Now you have 1 task in the list.
```

### ⏰ Adding deadlines
Add a task with a specific due date!

Example Command: ``deadline work /by 2025-08-08 1400``

Expected ouput:
```
Got it. I've added this task:
[D][] work (by: Aug 8 2025 14:00)
Now you have 1 task in the list.
```

### 🎯 Adding events
Add a task with a specific due date and end date!

Example Command: ``event work /from 2025-08-08 1400 /to 2025-08-08 1500``

Expected ouput:
```
Got it. I've added this task:
[E][] work (from: Aug 8 2025 14:00 to Aug 8 2025 15:00)
Now you have 1 task in the list.
```

### ✅ Mark your tasks as done!
Mark your tasks you're finished with a task!

Example Command: ``mark 1``

### 🔄 Unmark your tasks as done!
Unmark a task as completed!

Example Command: ``unmark 1``

### 🗑️ Delete your tasks!
Delete a task!

Example Command: ``delete 1``

### 📌 Tag your tasks!

Tag your tasks with custom tags!

Example Command: ``tag 1 homework``

### ➖ Untag your tasks!

Untag your tasks!

Example Command: ``untag 1``

### 🔍 Find your tasks!

Find your tasks with keywords!

Example Command: ``find work``

### 🚪 Leaving?
Exit the chatbot!

Command: ``bye``