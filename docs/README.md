# Airy User Guide

------
Welcome to your very own personal Task Manager chat bot, Airy!
Airy is here to ensure you never miss any tasks, deadlines or events!

![Airy GUI](Ui.png)

## Getting Started

1. Download the .jar file found [here](https://github.com/Leumu/ip/releases)
2. Launch the application!

## Features
### 1. Add a Todo task
- Type `todo TASK_NAME` into the bot
  - E.g. `todo borrow book`
  - This will create a todo task

### 2. Add a Deadline task
- Type `deadline TASK_NAME /by DATE`
    - E.g. `deadline return book /by 2025-12-13`
    - Note DATE has to be `yyyy-MM-dd` format
    - This will create a Deadline task with the specific deadline

### 3. Add an Event task
- Type `Event TASK_NAME /from DATE /to DATE` into the bot
    - E.g. `event book club /from 2025-10-13 /to 2025-10-14`
    - Note DATE has to be `yyyy-MM-dd` format
    - This will create a Event task with the specified dates

### 4. List created tasks
- Type `list`
  - This will display all existing tasks

### 4. Mark and Unmark the task
- Type `mark INDEX`
  - E.g. `mark 3` where I have a task in index 3 when I do `list`
  - This will mark the task as done by adding a [X] to it
- Type `unmark INDEX`
  - Likewise this unmarks the task

### 5. Delete a task
- Type `delete INDEX`
  - E.g. `delete 3` where I have a task in index 3 when I do `list`
  - This will delete the task
  - Note this action is irreversible


## Example usage
```
todo borrow book
deadline return book /by 2025-12-13
event book club /from 2025-10-13 /to 2025-10-14
list
mark 1
unmark 1
delete 3
bye
```

Enjoy using Airy!
