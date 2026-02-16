# Betty User Guide

![Ui.png](Ui.png)
**Betty** is a simple, interactive task manager chatbot built in Java. 
It helps you keep track of your todos, deadlines, and events, with support for priorities and persistent storage. 
With Betty, you can easily manage your tasks from the command line with a simple, 
intuitive interface.

## Features
- **Add Tasks**: Add todos, deadlines, and events.
- **List Tasks**: View all your tasks.
- **Mark/Unmark Tasks**: Mark tasks as done or not done.
- **Delete Tasks**: Remove tasks from your list.
- **Find Tasks**: Search for tasks by keyword.
- **Set Priority**: Assign low, medium, or high priority to tasks.
- **Persistent Storage**: Tasks are saved to disk and loaded automatically.
- **Exit**: End the session with `bye`.

### Getting started
Prerequisites:
- Java 17 or higher
- Gradle

### Setup
GitHub Repository:
1. Clone the repository: `git clone https://github.com/Jerrytys/ip.git`
2. Navigate to the project directory: `cd ip`
3. Build the project: `./gradlew build`
4. Run the application: `./gradlew run`

JAR file:
1. Download the latest `betty.jar` from the releases page.
2. Open a terminal and navigate to the directory containing `betty.jar`.
3. Run the application: `java -jar betty.jar`

## Commands
### Add a Todo
Add a todo task to your list.
Example: `todo read book`

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

### Add a Deadline
Add a task with a deadline.
Example: `deadline submit report /by 2024-12-01`    
```
Got it. I've added this task:
    [D][ ] submit report (by: Dec 1 2024)
Now you have 2 tasks in the list.
```

### Add an Event
Add an event with a date and time.
Example: `event team meeting /from 2024-11-15 /to 2024-11-15`
```
Got it. I've added this task:
    [E][ ] team meeting (from: Nov 15 2024 to: Nov 15 2024)
Now you have 3 tasks in the list.
```

### List Tasks
Display all tasks in your list.
Example: `list`
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit report (by: Dec 1 2024)
3.[E][ ] team meeting (from: Nov 15 2024 to: Nov 15 2024)
```

### Mark a Task as Done
Mark a task as completed.
Example: `mark 1`
```
Nice! I've marked this task as done:
  [T][X] read book
``` 
### Unmark a Task
Mark a task as not done.
Example: `unmark 1`
```
OK, I've marked this task as not done yet:
  [T][ ] read book
```
### Delete a Task
Remove a task from your list.
Example: `delete 2`
```
Noted. I've removed this task:
  [D][ ] submit report (by: Dec 1 2024)
Now you have 2 tasks in the list.
```
### Find Tasks
Search for tasks containing a specific keyword.
Example: `find book`
```
Here are the matching tasks in your list:
1.[T][ ] read book
```
### Set Task Priority
Assign a priority level to a task.
Example: `priority 1 high`
```
Got it. I've set the priority of this task:
  [T][ ] read book (priority: high)
```
### Exit the Application
End your session with Betty.
Example: `bye`
```
Bye. Hope to see you again soon!
```

## Data Storage
- Tasks are saved in a file named `betty.txt` located in the `data` directory
- On startup, Betty loads tasks from this file if it exists.

## License
This project is used for educational purposes.