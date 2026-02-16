# Yang User Guide

![Ui.png](Ui.png)

Yang is your personal chatbot assistant that helps you manage tasks efficiently.  
This guide explains how to use Yang and its key features.
## Getting Started
1. Launch the application:
    - CLI: run `java -jar yang.jar`
    - GUI: run the `Main` class in IntelliJ or `./gradlew run`
2. Yang will wait for your commands.

### 1. Add a Todo
Adds a simple task without date/time.  
**Usage:** todo buy bubble tea

```
expected output:
Perfect! I've added this task:
[T][ ] buy bubble tea
Now you have 1 tasks in the list.
```

### 2. Add a Deadline
Adds a task with a deadline date.  
**Usage:** deadline return book /by 2025-09-20

```
expected output:
Perfect! I've added this task:
[D][ ] return book (by: Sep 20 2025)
Now you have 2 tasks in the list.
```

### 3. Add an Event
Adds a task that occurs on a specific date.  
**Usage:** event project meeting /at 2025-12-05
```
expected output:
Perfect! I've added this task:
[E][ ] project meeting (at: Dec 5 2025)
Now you have 3 tasks in the list.
```

### 4. List Tasks
Shows all tasks in your list.  
**Usage:** list

```
expected output:
Here are the tasks in your list:
1. [T][ ] buy bubble tea
2. [D][ ] return book (by: Sep 20 2025)
3. [E][ ] project meeting (at: Dec 5 2025)
```

### 5. Mark and Unmark
Marks a task as done or not done.  
**Usage:** mark 1
unmark 1
```
expected output: 
YAY! Task completed. You are doing a wonderful job!!
  [T][X] buy bubble tea
```
```
expected output: 
Understood, this task is not done yet:
  [T][ ] buy bubble tea
```

### 6. Delete a Task
Removes a task from the list.  
**Usage:** delete 1
```
expected output: 
Understood. I've removed this task:
  [T][ ] buy bubble tea
Now you have 2 tasks in the list.
```

### 7. Find Tasks
Search for tasks by keyword.  
**Usage:** find book
```
expected output: 
Here are the matching tasks in your list:
1. [D][ ] return book (by: Sep 20 2025)
```

### 8. Exit
Exit chatbot
**Usage:** bye
```
expected output: 
GUI will be closed.
```

