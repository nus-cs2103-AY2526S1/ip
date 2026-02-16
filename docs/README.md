# Taro User Guide

Welcome to **Taro**, your personal assistant chatbot!

This guide will help you get started quickly and explain how to use all the important features of Taro.

---

## Quick Start

1. Ensure you have **Java 17** installed on your computer.
2. Download the **latest** release of **Taro** from [GitHub Releases](https://github.com/stefuiii/ip/releases).
3. Run Taro by double-clicking the JAR file, or by using the command line:

   ```
   java -jar taro.jar
   ```
4. You should see a greeting message:

   ```
   Hello! I'm Taro
   Your personal assistant bot
   What can I do for you?
   ```

---

## Command Summary

| Command      | Format                                      | Example                                         |
| ------------ |---------------------------------------------|-------------------------------------------------|
| **Exit**     | `bye`                                       | `bye`                                           |
| **List**     | `list`                                      | `list`                                          |
| **Todo**     | `todo DESCRIPTION`                          | `todo read book`                                |
| **Deadline** | `deadline DESCRIPTION /by DATE`             | `deadline return book /by 2025-09-14`           |
| **Event**    | `event DESCRIPTION /from DATE TIME/to TIME` | `event project /from 2025-09-15 16:00/to 18:00` |
| **Mark**     | `mark TASK_NUMBER`                          | `mark 2`                                        |
| **Unmark**   | `unmark TASK_NUMBER`                        | `unmark 2`                                      |
| **Delete**   | `delete TASK_NUMBER`                        | `delete 3`                                      |
| **Find**     | `find KEYWORD`                              | `find book`                                     |
| **Reminder** | `reminder`                                  | `reminder`                                      |

---

## Features

### 1. Exiting the program

**Format:**

```
bye
```

Ends the program.

---

### 2. Viewing the task list

**Format:**

```
list
```

Displays all tasks you have added so far.

---

### 3. Adding a Todo task

**Format:**

```
todo DESCRIPTION
```

Example:

```
todo read book
```

---

### 4. Adding a Deadline task

**Format:**

```
deadline DESCRIPTION /by DATE
```

Example:

```
deadline return book /by 2025-09-14
```

---

### 5. Adding an Event task

**Format:**

```
event DESCRIPTION /from DATE /to DATE
```

Example:

```
event project meeting /from 2025-09-15 16:00/to19:00
```

---

### 6. Marking a task as done

**Format:**

```
mark TASK_NUMBER
```

Example:

```
mark 2
```

---

### 7. Unmarking a task

**Format:**

```
unmark TASK_NUMBER
```

Example:

```
unmark 2
```

---

### 8. Deleting a task

**Format:**

```
delete TASK_NUMBER
```

Example:

```
delete 3
```

---

### 9. Finding tasks by keyword

**Format:**

```
find KEYWORD
```

Example:

```
find book
```

---

### 10. Reminders

**Format:**

```
reminder
```

Displays upcoming deadlines and events **in 3 days**.

---

## Notes

* Extra spaces in commands will be ignored.
* Task numbers refer to the numbers shown in the `list` command.
* Dates should be entered in `YYYY-MM-DD` format.
* Times should be entered in `HH:MM` format.
* If you enter a wrong command, Taro will show an error message in **red** and suggest the correct format.

---

## FAQ

**Q: What happens if I type the wrong command?**

A: Taro will show an error message in red and explain the expected format.

**Q: Where are my tasks saved?**

A: Tasks are stored in a local file (`data/taro.txt`) and will be loaded automatically the next time you run Taro.

**Q: Can I use multiple spaces or tabs between words?**

A: Taro will trim extra spaces, but the recommended format is to use a single space.



