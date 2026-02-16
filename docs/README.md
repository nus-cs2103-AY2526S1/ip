# TronaldDump User Guide

![TronaldDump GUI](src/main/resources/Ui.png)

Welcome to **TronaldDump**, your unique task management chatbot with personality! TronaldDump is a Java-based application that helps you manage your tasks through an intuitive graphical user interface. Whether you need to track todos, schedule events, or meet deadlines, TronaldDump has got you covered with its distinctive communication style.

## Adding deadlines

Add tasks with specific due dates and times. This command creates deadline tasks that help you track time-sensitive assignments and projects.

Example: `deadline <description> /by <due_date>`

The deadline task will be added to your task list with the specified due date. You can use date-only format (yyyy-MM-dd) or include specific times (yyyy-MM-dd HHmm).

```
deadline Submit project report /by 2024-12-31 2359
```

```
Expected output:
________________________________
OKAY. I HAVE ADDED THIS TASK:
 [D] [ ] Submit project report (by: Dec 31 2024, 11:59PM)
NOW YOU HAVE 1 TASK IN THE LIST.

________________________________
```

## Adding todos

Add simple tasks without time constraints. This command creates basic todo items for your daily tasks and reminders.

Example: `todo <description>`

The todo task will be added to your task list and can be marked as complete when finished.

```
todo Buy groceries
```

```
Expected output:
________________________________
OKAY. I HAVE ADDED THIS TASK:
 [T] [ ] Buy groceries
NOW YOU HAVE 2 TASKS IN THE LIST.

________________________________
```

## Adding events

Add tasks with specific start and end times. This command creates event tasks for meetings, appointments, and scheduled activities.

Example: `event <description> /from <start_time> /to <end_time>`

The event task will be added to your task list with the specified time range. You can use date-only format or include specific times.

```
event Team meeting /from 2024-12-25 1400 /to 2024-12-25 1500
```

```
Expected output:
________________________________
OKAY. I HAVE ADDED THIS TASK:
 [E] [ ] Team meeting (from: Dec 25 2024, 2:00PM to: Dec 25 2024, 3:00PM)
NOW YOU HAVE 3 TASKS IN THE LIST.

________________________________
```

## Sorting tasks

Automatically organize all tasks by priority and date. This command sorts your tasks in a logical order to help you prioritize your work effectively.

Example: `sort`

Your tasks will be reordered and displayed in the sorted format. The sorting follows a specific hierarchy: deadlines first (by due date), then events (by start time), then todos (alphabetically by description).

```
sort
```

```
Expected output:
________________________________
HERE ARE YOUR TASKS SORTED:
1. [D] [ ] Submit project report (by: Dec 31 2024, 11:59PM)
2. [E] [ ] Team meeting (from: Dec 25 2024, 2:00PM to: Dec 25 2024, 3:00PM)
3. [T] [ ] Buy groceries

________________________________
```

## Listing tasks

View all your current tasks with their completion status. This command displays your entire task list in an organized format.

Example: `list`

All tasks will be displayed with their type, completion status, and details.

```
list
```

```
Expected output:
________________________________
HERE ARE THE TASKS IN YOUR LIST:
1. [T] [ ] Buy groceries
2. [E] [ ] Team meeting (from: Dec 25 2024, 2:00PM to: Dec 25 2024, 3:00PM)
3. [D] [ ] Submit project report (by: Dec 31 2024, 11:59PM)

________________________________
```

## Marking tasks

Mark a task as completed by its number. This command helps you track your progress and completed tasks.

Example: `mark <task_number>`

The specified task will be marked as done and displayed with a checkmark.

```
mark 1
```

```
Expected output:
________________________________
GOOD JOB! TASK 1 IS MARKED AS DONE.

[T] [X] Buy groceries

________________________________
```

## Unmarking tasks

Mark a completed task as incomplete. This command allows you to undo a completion status if needed.

Example: `unmark <task_number>`

The specified task will be marked as not done and displayed without a checkmark.

```
unmark 1
```

```
Expected output:
________________________________
TASK 1 IS UNMARKED.

[T] [ ] Buy groceries

________________________________
```

## Deleting tasks

Remove a task from your list permanently. This command helps you clean up your task list by removing unwanted or completed items.

Example: `delete <task_number>`

The specified task will be removed from your task list and you'll see a confirmation message.

```
delete 2
```

```
Expected output:
________________________________
TASK 2 HAS BEEN REMOVED:
[E] [ ] Team meeting (from: Dec 25 2024, 2:00PM to: Dec 25 2024, 3:00PM)
NOW YOU HAVE 2 TASKS IN THE LIST.

________________________________
```

## Finding tasks

Search for tasks containing specific keywords. This command helps you quickly locate tasks by searching through their descriptions.

Example: `find <keyword>`

All tasks containing the keyword will be displayed, making it easy to find related tasks.

```
find meeting
```

```
Expected output:
________________________________
HERE ARE THE MATCHING TASKS IN YOUR LIST:
1. [E] [ ] Team meeting (from: Dec 25 2024, 2:00PM to: Dec 25 2024, 3:00PM)

________________________________
```

## Exiting the application

Close the application gracefully and save your tasks. This command ends your session while ensuring your data is preserved.

Example: `bye`

The application will display a farewell message and close, with all your tasks automatically saved.

```
bye
```

```
Expected output:
________________________________
THANK YOU FOR YOUR ATTENTION TO THIS MATTER!

________________________________
```
