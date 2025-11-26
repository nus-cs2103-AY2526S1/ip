# Aries User Guide

Aries is a task manager with a friendly JavaFX chat GUI.
It supports **todos**, **deadlines**, **events**, listing and searching tasks, persistence to disk, and a clean exit via bye. Duplicate tasks are detected by a generated task key so you don’t accidentally add the same thing twice.

![UI](./Ui.png)

## Quick start
1. Ensure you have Java 17 or above installed in your Computer.
    Mac users: Ensure you have the precise JDK version prescribed here.
2. Download the latest .jar file from here.
3. Copy the file to the folder you want to use as the home folder for your task manager.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar aries.jar command to run the application. A GUI similar to the above should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it. e.g. typing list and pressing Enter will show the existing task.
Some example commands you can try:
- list : Lists all added task.
- todo : add a todo task.
- deadline : add a task with specific time 
- event : add an event with a ranging of time
- find : find a list of tasks that contain the keyword
- mark : mark the task as done
- unmark : mark the task as not done
- delete : delete a task with the given index
- bye : exit the program
Refer to the Features below for details of each command.

# Features

## Add Task
We can add task according to their types, **to do, deadline** and **event**.

1. Todo tasks
- only with a description of the task
- Command format: `todo <description>`
- Example: todo read book
- Expected output: 
```
Got it. I've added this task:
[T] [ ] read book
Now you have 1 tasks in the list.
```

2. Deadline tasks
- task with description and a specific deadline
- Command format: `deadline <description> /by <yyyy-mm-dd HHmm>`
- 'dd mm yyyy' can be in any sequence seperated by a symbol `. / -`
- time after date is optional but should be in HHmm (24-hour) and preceded by a space
- Example: deadline CS2103T ip deadline /by 20-09-2025 0900
- Expected output:
```
Got it. I've added this task:
[D] [ ] CS2103T ip deadline (by: SEP 20 2025, 9:00AM)
Now you have 2 tasks in the list.
```

3. Event tasks
- tasks with description and range of time
- Command format: `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm`
- `dd mm yyyy` can be in any sequence seperated by a symbol `. / -`
- time after date is optional but should be in HHmm (24-hour) and preceded by a space
- Example: event CS2103T group meeting /from 24-09-2025 0900 /to 24-09-2025 1100
- Expected output:
```
Got it. I've added this task:
[E] [ ] CS2103T ip deadline (from: SEP 24 2025, 9:00AM) (to: SEP 24 2025, 11:00AM)
Now you have 3 tasks in the list.
```

## Show the task list

- list down all previously added task according to their order
- Command format: `list`
- Expected output:
```
Here are the tasks in your list:
1. [T] [ ] read book
2. [D] [ ] CS2103T ip deadline (by: SEP 20 2025, 9:00AM)
3. [E] [ ] CS2103T ip deadline (from: SEP 24 2025, 9:00AM) (to: SEP 24 2025, 11:00AM)
```

## Delete task

- delete the task with the given index
- Command format: `delete <index>`
- Example: delete 3
- Expected output:
```
Noted. I've removed this task:
[E] [ ] CS2103T ip deadline (from: SEP 24 2025, 9:00AM) (to: SEP 24 2025, 11:00AM)
Now you have 2 tasks in the list.
```

## Mark task

- Given a specific index, mark the task with X to indicate done
- Command format: `mark <index>`
- Example: mark 1
- Expected output:
```
Nice! I've marked this task as done:
[T] [X] read book
```

## Unmark task

- Given a specific index, unmark the task with '' to indicate not done
- Command format: `unmark <index>`
- Example: unmark 1
- Expected output:
```
OK, I've marked this task as not done yet:
[T] [ ] read book
```

## Find task

- find the task that contain the specific keyword
- if the keyword not found, it will say no matching tasks found.
- Command format: `find <keyword>`
- Example: find read
- Expected output:
```
Here are the matching tasks in your list:
1. [T] [ ] read book
```

## Exit bot

- exits the current bot
- display a farewell message and wait for 1 second to close the chatbot
- Command format: `bye`
- Expected output:
```
Goodbye! Hope to see you again soon!
```

## GUI notes

- Keyboard scrolling: User can scroll the chat panel using your keyboard.
- Auto-scroll: Chat will be redirected to the bottom once user send a new query.

## Error handling

- **Invalid command**, **duplicate task enter** and **unexpected** runtime error will be caught by aries.
- Aries will then throw an aries exception.
- Aries will tell what user should follow according to the error message.

## Tips
- your data lives in 'data/aries_task.ser'. Back up this data if you plan to move the chatbot.
- your data will be saved instantly once you entered the query.
