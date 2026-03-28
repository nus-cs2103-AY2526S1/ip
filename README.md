# Dukey Introduction
```
 ____        _        
|  _ \ _   _| | _______  __
| | | | | | | |/ / _ \ \/ /
| |_| | |_| |   <  __/\  /
|____/ \__,_|_|\_\___|/_/
```

My CLI chatbot is named Dukey. Dukey is a quick and efficient way to help you to keep track of tasks
and deadlines. Dukey will store your tasks in a .txt file and update it automatically. A new file, 
Dukey.txt will be automatically created for you upon starting the program for the first time.
Given below are instructions on how to use it.


## Setup Guide

1. Open ip folder in an IDE. 
2. Use gradle to and perform the task : run.
3. Enter commands into the textbox.
4. For subsequent use, enter the filepath of the newly created Dukey.txt file into Main.java. 


# Commands

- **todo TASK NAME** Adds a task to list
- **deadline TASK NAME /by DATE_TIME** Adds a task with deadline. 
- **event TASK NAME /from DATE_TIME /to DATE_TIME** Adds a task with a start and end time.
- **mark TASK_NUMBER**, Marks 1st task as completed.
- **unmark TASK_NUMBER** Unmarks 1st task as completed.
- **find WORD** Returns list of tasks containing the word "book".
- **bye** Exits program.

*DATE_TIME is in the format dd/mm/yyyy hhmm, where hhmm follows 24 hour convention.


