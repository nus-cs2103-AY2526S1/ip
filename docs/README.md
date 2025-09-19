# Stella User Guide

// Update the title above to match the actual product name

// Product screenshot goes here
![Screenshot of Stella] (./Ui.png)

// Product intro goes here
## Product Introduction
Stella is a desktop application that allows you to keep track of the various tasks you need to complete. Your task list will be automatically save when you close the app, allowing you to continue right from where you left off!

### Type of tasks
There are three types of tasks you can keep track of: 
1. todo (task with only the description)
2. deadline (task with description and a deadline)
3. event (task with description, when it starts and when it ends).
 
### Specifying priority level
There is also an option to include the priority level of the task (which include HIGH, MIDDLE, LOW, UNDECIDED). If the priority level is not stated, the priority level will take on the UNDECIDED value by default.

### Specifying time
There are also three ways to specify time, which will come in useful when adding event or deadline. The three ways are:
1. dd-mm-yyyy (e.g. 12-06-2025, which is displayed as 06 June 2025 in the application)
2. dd-mm-yyyy-hhmm (e.g. 12-06-2025-2359, which is displayed as 23:59, 06 June 2025 in the application)
3. Or any other format you prefer (e.g. Today, next Monday, this afternoon)

# Adding tasks
## Adding todos
### Command Option 1: `todo <description>`
The task with the following description will be added to the task list. By default, the priority level of the task will be UNDECIDED.

Example of usage: `todo read book`
Expected outcome:
```
added: [T][] read book (Priotity: UNDECIDED)
Now you have 1 task(s) in the list
```
### Command Option 2: `todo <description>/<priority level>`
The task with the following description, and a specified priority level, will be added to the task list. As a reminder, there are only 4 valid priority value: HIGH, MIDDLE, LOW, UNDECIDED.

Example of usage: `todo skating/LOW`
Expected outcome:
```
added: [T][] skating (Priotity: LOW)
Now you have 2 task(s) in the list
```
## Adding deadlines
### Command Option 1: `deadline <description>/<deadline>`
The task with the following description and deadline will be added to the task list. By default, the priority level of the task will be UNDECIDED. There are 3 ways to specify the deadline, as explained in the **Specifying time** subsection above. 

Example of usage: `deadline math homework/this wednesday`
Expected outcome:
```
added: [D][] math homework (by: this wednesday)
(Priority: UNDECIDED)
Now you have 3 task(s) in the list
```

### Command Option 2: `deadline <description>/<deadline>/<priority level>`
The task with the following description, deadline and the specified priority level, will be added to the task list. 

Example of usage: `deadline return book/01-06-2025/HIGH`
Expected outcome:
```
added: [D][] return book (by: 01 June 2025) (Priority:
HIGH)
Now you have 4 task(s) in the list
```
## Adding events
### Command Option 1: `event <description>/<start>/<end>`
The task with the following description, start and end time will be added to the task list. By default, the priority level of the task will be UNDECIDED. There are 3 ways to specify the start and end time, as explained in the **Specifying time** subsection above. 

Example of usage: `event lunch with friends/02-06-2025-1200/02-06-2025-1400`
Expected outcome:
```
added: [E][] lunch with friends (from: 12:00, 02 June 
2025 | to: 14:00, 02 June 2025) (Priority: UNDECIDED)
Now you have 5 task(s) in the list
```
### Command Option 2: `event <description>/<start>/<end>/<priority_level>`
The task with the following description, start, end and the specified priority level, will be added to the task list. 

Example of usage: `event CCA interview/03-06-2025-1200/03-06-2025-1400/HIGH`
Expected outcome:
```
added: [E][] CCA interview (from: 12:00, 03 June 
2025 | to: 14:00, 03 June 2025) (Priority: HIGH)
Now you have 6 task(s) in the list
```

## Other Features
### View the whole task list
Command: `list`
Allow you to view the current task list.

### Find tasks that contain the keyword
Command: `find <keyword>'
Example of usage: `find book`
Expected outcome: The list of tasks that contain the `book` keyword

### Mark a task that is completed
Command: `mark <index>`
Example of usage: `mark 1`
Expected outcome: The first task in the list will be marked as completed.

_Tips: You can first use the_ `list` _command before the_ `find` _command
if you are unsure what index a particular task have_

### Unmark a task 
Command: `unmark <index>`
Example of usage: `unmark 1`
Expected outcome: The first task in the list will be marked as uncompleted.

_Tips: the_ `unmark` _command can come in useful when you have marked the wrong task, and you want to unmark that particular task_

### Delete a task
Command: `delete <index>`
Example of usage: `delete 1`
Expected outcome: The first task in the list will be deleted.

_Tips: List is getting too long? Perhaps, you can consider using the_ `delete` _command._

### Saying goodbye to Stella
Command: `bye`
After saying goodbye to Stella, it is time to start working on your tasks!