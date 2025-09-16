# MinhGPT User Guide

![alt text](Ui.png)

### MinhGPT is a chatbot to help you manage your tasks

## Getting started

1. Go to this [link](https://github.com/MinhMXC/ip/releases/tag/A-BetterGui).
2. Under **Assets**, click on `minhgpt.jar` to download the executable.
3. Double-click on the downloaded file on your computer to run.
4. Alternatively, run `java -jar minhgpt.jar` on a terminal (on same directory as `minhgpt.jar`) to run.

## Features

### Task Types

**Todo**: tasks without any date/time attached to it. *e.g., visit new theme park*

**Deadline**: tasks that need to be done before a specific date/time. *e.g., submit report by 11/10/2019 5pm*

**Event**: tasks that start at a specific date/time and ends at a specific date/time.
*e.g., (a) team project meeting 2/10/2019 2-4pm or (b) orientation week 4/10/2019 to 11/10/2019*

### Possible actions

* **Add** tasks.
* **Mark** tasks (as done).
* **Unmark** tasks (mark it as undone).
* **Delete** tasks.
* **List** all tasks.
* **Sort** all tasks.

### Task display format

`[TYPE][STATUS] NAME (EXTRA)`

`TYPE` is the type of the task: `T` for Todo tasks, `D` for deadline tasks and `E` for Event tasks

`STATUS` is the status of the task: `X` for completed tasks, ` `&nbsp;(empty space) for uncompleted tasks

`NAME` is the name of the task

`EXTRA` is addtional information about the task: none for Todo task, **deadline** for Deadline tasks, and **time period** for Event tasks

### Date input format

All date input must conform to `YYYY-MM-DD`. For example, `1970-01-01`

### Saving & Loading

The application automatically save and load your tasks.

**Important**: To save your tasks, you must chat `bye` in the chatbox before you leave. Pressing X on the application window will not do any saving.

## Usage

### Add Todo task

**Format**: `todo NAME`, where `NAME` is the name of the task.

Example: ```todo task1```

Expected output: 
```
F-fine! I added it. It's not like I wanted to or anything...
[T][] task1
```

### Add Deadline task

**Format**: `deadline NAME /by TIME`, where `NAME` is the name of the task and `TIME` is the deadline for the task

Example: ```deadline task1 /by Sunday```

Expected output: 
```
F-fine! I added it. It's not like I wanted to or anything...
[D][] task2 (by: Jan 01 1970)
```

### Add Event task

**Format**: `event NAME /from FROM /to TO`, where `NAME` is the name of the task, `FROM` is the **start** time for the event and `TO` is the **end** time for the event

Example: ```event task1 /from Monday 12:00AM /to Friday 23:59PM```

Expected output: 
```
F-fine! I added it. It's not like I wanted to or anything...
[E][] task3 (from: Jan 02 1970 to: Jan 04 1970)
```

### Mark task

**Format**: `mark INDEX`, where `INDEX` is the index of the task in the list.

Example: `mark 1`

Expected output: 
```
I-It's not like I care, but... g-good job, I guess.
[T][X] task1
```

### Unmark task

**Format**: `unmark INDEX`, where `INDEX` is the index of the task in the list.

Example: `unmark 1`

Expected output: 
```
H-huh?! Why did you unmark it?! Are you trying to make me do more work?!
[T][] task1
```

### Delete task

**Format**: `delete INDEX`, where `INDEX` is the index of the task in the list.

Example: `delete 1`

Expected output: 
```
Good riddance. I-I mean, it's removed now. Hmph.
[T][] task1
```

### List all tasks

**Format**: `list`

Example: `list`

Expected output: 
```
Don't get the wrong idea! I'm just listing them out because... because I have to, okay?! You have 3 tasks in total.
1.[T][] task1
2.[D][] task2 (by: Jan 01 1970)
3.[E][] task3 (from: Jan 02 1970 to: Jan 04 1970)
```

### Sort all tasks

**Format**: `sort`

Example: `sort`

Expected output: 
```
Don't get the wrong idea! I'm just listing them out because... because I have to, okay?! You have 3 tasks in total.
1.[D][] task2 (by: Jan 01 1970)
2.[E][] task3 (from: Jan 02 1970 to: Jan 04 1970)
3.[T][] task1
```

### Exit from the application

**Format**: `bye`

Example: `bye`

Expected behaviour: The application will exit.
