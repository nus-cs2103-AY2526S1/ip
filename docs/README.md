# JinBot User Guide

![JinBot Screenshot](Ui.png)
*A simple yet powerful chatbot with a clean JavaFX interface.*

___

## Introduction
JinBot is a task-management chatbot that helps you keep track of todos, deadlines, and events.

___

## Adding Deadlines
Add a task with a due date using the 'deadline' command followed by 'by' to indicate due date.

**Format:**
deadline description /by yyyy-mm-dd (or dd/mm/yyyy)

**Example:**
deadline submit assignment /by 2025-09-30

**Expected Outcome:**
Got it. I've added this task:
 [D][] submit (by: Sep 30 2025)
[MEDIUM]
Now you have 1 task in the list.

The outcome shows that the task is added with showing how many tasks in the list.

___

## Adding Todos
Add
**Format:**
todo description

**Example:**
todo read book

**Expected Outcome:**
Got it. I've added this task:
 [T][] read book [MEDIUM]
Now you have 2 tasks in the list.

___

## Adding Events
Add an event task with a period of date using the 'event' command followed by '/from' to indicate start date and '/to' to indicate end date. .

**Format:**
event description /from <yyyy-mm-dd> /to <yyyy-mm-dd> (dd/mm/yyyy also allowed)

**Example:**
event showcase /from 2025-09-25 /to 2025-09-30

**Expected Outcome:**
Got it. I've added this task:
[E][] showcase (from: Sep 25 2025 to: Sep 30 2025)
[MEDIUM]
Now you have 3 task in the list.

___

## Listing Tasks
View all saved tasks with the 'list' command.

**Example:**
list

**Expected Outcome:**
Here are the tasks in your list: 
 1. [T][] read book [MEDIUM]
 2. [D][] submit assignment (by: Sep 30 2025) [MEDIUM]
 3. [E][] showcase (from: Sep 25 2025 to: Sep 30 2025) [MEDIUM]

___

## Marking and Unmarking Tasks
**Format:**
mark task_number
unmark task_number

**Example:**
mark 1

**Expected Outcome:**
Nice! I've marked this task as done:
[T][X] read book [MEDIUM]

___

## Deleting Tasks
Remove tasks with the 'delete' command.

**Format:**
delete task_number

**Example:**
delete 2

**Expected Outcome:**
Noted. I've removed this task:
[D][ ] submit assignment (by: Sep 30 2025) [MEDIUM]
Now you have 2 tasks in the list.

___

## Finding Tasks
Search tasks by keyword wih the 'find' command.

**Example:**
find book

**Expected Outcome:**
Here are the matching tasks in your list:
 1. [T][X] read book [MEDIUM]

___

## Setting Priority
Updates priority level of tasks with 'priority' command followed by task_number and one of LOW MEDIUM HIGH

**Example:**
priority 2 HIGH

**Expected Outcome:**
Set priority of task:
[D][] submit (by: Sep 30 2025) [HIGH]

___

## Error Handling
- Invalid commands are highlighted in **red dialog bubbles**.
- This makes it easy to spot and fix mistakes quickly.