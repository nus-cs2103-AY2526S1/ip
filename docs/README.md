# BananaBot User Guide

Welcome to BananaBot, a fun and intuitive task management application! BananaBot helps you organize your tasks, 
deadlines, and events through a simple chat-like interface. With its colorful design, BananaBot makes task tracking 
enjoyable. Use the text input at the bottom to enter commands, and watch your tasks appear in the scrollable dialog 
area. Let’s explore how to use BananaBot effectively!

![Product screenshot.](https://github.com/bananapielearnsjava/ip/blob/master/docs/Ui.png)

## Adding todo

The todo command allows you to add a normal todo task.
Once added, the task will appear in your task list.

**Examples of Usage:**\
Format: `todo` + `description`\
Example: `todo laundry`

**Expected Outcome:**\
The task will be added to your list. The dialog box will display a confirmation message.
```
Got it. I've added this task:
[T][] laundry 
Now you have 1 task in the list.
```

## Adding deadline

The deadline command allows you to add a task with a specific due date. This is perfect for scheduling homework or 
chores with a deadline. 
Once added, the task will appear in your task list with the due date.

**Examples of Usage:**\
Format: `deadline` + `description` + `/by yyyy-mm-dd hhMM`\
Example: `deadline 2103T homework /by 2025-09-20 2359`

**Expected Outcome:**\
The task will be added to your list with the specified due date. The dialog box will display a confirmation message, 
and the task will be sorted by date if you use the sort command later.

```
Got it. I've added this task:
[T][] laundry 
[D][] 2103T homework (by: Sept 23 2025, 11:59pm)
Now you have 2 task in the list.
```

## Adding event

The event command adds a task with a start and end time or date range, perfect for scheduling activities like 
parties or study sessions. Use /from and /to to specify the range.

**Examples of Usage:**\
Format: `event` + `description` + `/from yyyy-mm-dd hhMM` +  `/to yyyy-mm-dd hhMM`\
Example: `event birthday party /from 2025-09-20 1800 /to 2025-09-20 2200`

**Expected Outcome:**\
The event will be added to your list with the specified time range. The dialog box will display a confirmation, 
and the event will be sortable by start date.
```
Got it. I've added this task:
[T][] laundry 
[D][] 2103T homework (by: Sept 23 2025, 11:59pm)
[E][] birthday party (from: Sep 20 2025, 6:00pm to: Sep 20 2025, 10:00pm)
Now you have 3 task in the list.
```

## Sorting Tasks

The sort command organizes your tasks by date, making it easy to see what’s due soonest. This feature simplifies 
planning, especially to manage multiple tasks. No arguments are needed—just type sort to trigger date-based sorting.

**Examples of Usage:**\
Format: `sort`

**Expected Outcome:**\
All tasks in the list will be rearranged in chronological order based on their due dates. The dialog box will update 
to show the sorted list, with the earliest deadline at the top.


## Marking Task as Done

The mark command lets you mark a task as completed. This is great for tracking progress and feeling accomplished! 
Specify the task number from the list to mark it as done.

**Examples of Usage:**\
Format: `mark` + `task index`\
Example: `mark 1`

**Expected Outcome:**\
The specified task will be marked as done, and the dialog box will update to reflect its completed status 
(e.g., with a checkmark 'X').

```
Nice! I've marked this task as done:
[D][X] 2103T homework (by: Sept 23 2025, 11:59pm)
```
## Unmarking Task

The unmark command reverses a task’s completed status, turning it back to incomplete. Use the task number to unmark it, 
giving you a chance to revisit unfinished work.

**Examples of Usage**\
Format: `unmark` + `task index`\
Example: `unmark 1`

**Expected Outcome:**\
The specified task will be unmarked, and the dialog box will update to show its incomplete status.

```
Ok! I've unmarked this task as not done:
[D][] 2103T homework (by: Sept 23 2025, 11:59pm)
```

## Viewing the Task List

The list command displays all your current tasks, including deadlines and their statuses. This helps you keep an 
overview of what needs to be done.

**Examples of Usage:**\
Format: `list`

**Expected Outcome:**\
The dialog box will show a numbered list of all tasks, with details like due dates and completion status.

```
Here are the matching tasks:
1. [T][] laundry 
2. [D][] 2103T homework (by: Sept 23 2025, 11:59pm)
3. [E][] birthday party (from: Sep 20 2025, 6:00pm to: Sep 20 2025, 10:00pm)
```

## Finding Tasks by Keyword

The find keyword command searches your task list for tasks containing a specific keyword. This helps you quickly 
locate tasks related to a topic.

**Examples of Usage:**\
Format: `find` + `keyword`\
Example: `find party`

**Expected Outcome:**\
The dialog box will display a list of tasks that include the keyword.

```
Here are the matching tasks:
1. [E][] birthday party (from: Sep 20 2025, 6:00pm to: Sep 20 2025, 10:00pm)
```

## Finding Tasks by Date

The on command filters tasks due on a specific date. This is useful for planning your day or week based on deadlines.

**Examples of Usage:**\
Format: `on` +  `yyyy-mm-dd`\
Example: `on 2025-09-23`

**Expected Outcome:**\
The dialog box will show a list of tasks due on the specified date, including deadlines and events.

```
Tasks on 2025-09-23:
1. [D][] 2103T homework (by: Sept 23 2025, 11:59pm)
```
## Deleting Task

The delete command removes a task from your list. Use the task number to delete it, helping you clean up completed or 
irrelevant tasks.

**Examples of Usage:**\
Format: `delete` + `task index`\
Example: `delete 3`

**Expected Outcome:**\
The specified task will be removed, and the dialog box will confirm the deletion with an updated task count.

```
Noted. I've removed this task:
[E][] birthday party (from: Sep 20 2025, 6:00pm to: Sep 20 2025, 10:00pm)
Now you have 2 tasks in the list.
```