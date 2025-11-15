# Nami - User Guide

Nami is a task management application designed to help you organize your tasks easily using either a Command Line Interface (CLI) or Graphical User Interface (GUI). If you’re familiar with typing commands, Nami will be faster than traditional GUI applications!

## Quick Start

Ensure you have Java 17 or above installed.

### Download the Latest `.jar` File:
[Download the latest JAR from here](https://github.com/BiggillyBoyMan/ip/releases)

Copy the JAR file to the folder where you want to run Nami.

### Run the Application:

Open a terminal in the folder where the JAR file is located.

Run the following command:

```bash
java -jar Nami.jar
```

This should open a GUI window if you are using the GUI version. If you’re using CLI, the terminal will show the text-based interface.

## Features
### 1. Adding a Task: add
Adds a task to the task list. There are 3 types of tasks: todo, events, and deadlines.

Format:

```bash

add t/TASK_TYPE DESCRIPTION
```

For Todo:

```bash
add t/todo DESCRIPTION
```

For Deadline:

```bash
add t/deadline DESCRIPTION /by DATE (DD/MM/YYYY HH:MM)
```

For Event:

```bash
add t/event DESCRIPTION /from DATE (DD/MM/YYYY HH:MM) /to DATE (DD/MM/YYYY HH:MM)
```

### 2. Listing All Tasks: list
Shows a list of all tasks in the task list.

Format:

```bash
list
```

### 3. Locating Tasks by Description: find
Finds tasks whose descriptions contain the given keywords.

Format:

```bash
find KEYWORD [MORE_KEYWORDS]
```

Examples:


```bash
find assignment
find project deadline
```

### 4. Deleting a Task: delete
Deletes the specified task from the task list.

Format:

```bash
delete INDEX
```
Examples:

```bash
delete 2
```

### 5. Marking / Unmarking Tasks as Done: mark / unmark
Mark or unmark a task as done.

Format:

```bash
mark INDEX
unmark INDEX
```

Examples:

```bash
mark 2
unmark 2
```

### 6. Exiting the Program: bye
Exits the application.

Format:

```bash
bye
```

Saving the Data
Tasks are automatically saved after any change (add, edit, delete) to the task list. You do not need to manually save the data.

Editing the Data File
The tasks data is stored in a file: data/nami.txt.

Task Types (t/TASK_TYPE)
The task can be one of the following types:

todo: General tasks

deadline: Tasks with deadlines

event: Tasks involving events with start and end dates

Keywords for find
The search is case-insensitive.

The order of the keywords doesn’t matter.