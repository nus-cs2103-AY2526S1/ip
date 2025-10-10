# Hawker Uncle User Guide

![Screenshot of Hawker Uncle](/data/images/DaDuke.png)

## What is Hawker Uncle
**Hawker Uncle** is a personal task management application that will help you manage your tasks effectively. You can categorize your tasks into ToDos, Deadlines, and Events. You will also be able to add, delete, mark tasks ad done, find tasks, list all tasks, and get reminders for upcoming deadlines and events.

## Goal
**Hawker Uncle** enables users to track and manage tasks with deadlines and events while providing helpful reminders.

## Features
Here are some features that **Hawker Uncle** have and how to navigate them effectively.

### Adding ToDos
#### Feature
Add a general ToDo task without a speicific deadline.
#### Mechanism
Use the `todo` keyword followed by the task description.
#### Demonstration
Example command:
```
todo buy groceries
```
Expected output:
```
Got it. I've added this task.
   [T][ ] buy groceries
Now you have 1 task in the list.
```
![Screenshot of Hawker Uncle](/data/images/ToDoDemonstration.png)

### Adding Deadlines
#### Feature
Add a deadline task to the task list.
#### Mechanism
Use the `deadline` keyword followed by the task description and due time. The application creates a `Deadline` task. Note the input format for the deadline.
#### Demonstration
Example command:
```
deadline submit report /by 2025-09-20 2359
```
Expected output:
```
Got it. I've added this task.
   [D][ ] submit report (by: 20 September 2025 11PM)
Now you have 1 task in the list.
```
![Screenshot of Hawker Uncle](/data/images/DeadlineDemonstration.png)

### Adding Events
#### Feature
Add an event with a start and end time.
#### Mechanism
Use the `event` keyword followed by the task description , start time, and end time. The application creates a `Event` task. Note the input format for the start and end time.
#### Demonstration
Example command:
```
event team meeting /from 2025-09-20 1400 /to 2025-09-20 1500
```
Expected output:
```
Got it. I've added this task.
   [E][ ] team meeting (from: 20 September 2025 2PM to: 20 September 2025 3PM)
Now you have 1 task in the list.
```
![Screenshot of Hawker Uncle](/data/images/EventDemonstration.png)

### Listing All Tasks
#### Feature
View all tasks in your list.
#### Mechanism
Use the `list` command.
#### Demonstration
Example command:
```
list
```
Expected output:
```
Here are the tasks in your list:
1. [T][] buy groceries
2. [D][] submit report (by: 20 September 2025 11PM)
3. [E][] team meeting (from: 19 September 2025 2PM to: 19 September 2025 3PM)
```
![Screenshot of Hawker Uncle](/data/images/ListDemonstration.png)

### Marking Tasks as Done
#### Feature
Mark a task as completed.
#### Mechanism
Use the `mark` command followed by a task numeber.
#### Demonstration
Example command:
```
mark 2
```
Expected output:
```
Nice! I've marked this task as done:
   [D][X] submit report (by: 20 September 2025 11PM)
```
![Screenshot of Hawker Uncle](/data/images/MarkDemonstration.png)

### Unmarking Tasks
#### Feature
Mark a task as uncompleted.
#### Mechanism
Use the `unmark` command followed by a task numeber.
#### Demonstration
Example command:
```
unmark 2
```
Expected output:
```
OK, I've marked this task as not done yet:
   [D][] submit report (by: 20 September 2025 11PM)
```
![Screenshot of Hawker Uncle](/data/images/UnmarkDemonstration.png)

### Delete Tasks
#### Feature
Remove a task from the task list.
#### Mechanism
Use the `delete` command followed by a task numeber.
#### Demonstration
Example command:
```
delete 1
```
Expected output:
```
Noted. I've removed this task:
   [T][] buy groceries
Now you have 2 tasks in the list.
```
![Screenshot of Hawker Uncle](/data/images/DeleteDemonstration.png)

### Find Tasks
#### Feature
Search tasks containing a keyword
#### Mechanism
Use the `find` command followed by the search keyword.
#### Demonstration
Example command:
```
find report
```
Expected output:
```
Here are the matching tasks in your list:
1. [D][] submit report (by: 20 September 2025 11PM)
```
![Screenshot of Hawker Uncle](/data/images/FindDemonstration.png)

### Getting Reminders
#### Feature
Get reminders for upcoming deadlines and events.
#### Mechanism
Use the `remind` command to show all future tasks that are not done.
#### Demonstration
Example command:
```
remind
```
Expected output:
```
Here are your upcoming tasks:
1. [D][] submit report (by: 20 September 2025 11PM)
2. [E][] team meeting (from: 19 September 2025 2PM to: 19 September 2025 3PM)
```
![Screenshot of Hawker Uncle](/data/images/ReminderDemonstration.png)

## Summary
HawkerUncle helps you:
- Add, view, and manage ToDos, Deadlines, and Events.
- Search and filter tasks.
- Mark tasks as done and delete them.
- Get reminders about upcoming tasks.

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
