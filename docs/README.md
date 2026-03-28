# Luna User Guide

![Luna Display](Ui.png)

## Features:
1. [Adding Todos](#Adding-todos)
2. [Adding Deadlines](#Adding-deadlines)
3. [Adding Events](#Adding-events)
4. [Listing tasks](#Listing-tasks)
5. [Mark / Unmark tasks](#Marking-and-unmarking-tasks)
6. [Delete tasks](#Deleting-tasks)
7. [Tagging tasks](#Tagging-tasks)
8. [Find tasks](#Finding-tasks)



### Adding Todos

Allows user to add tasks without specified time.

**Example:** `todo homework`

A task with the name "homework" will be added to the list. 

```
Okay! I've added this task:
  [T][] homework 
Looks like you have 1 tasks in the list...
```

### Adding Deadlines

Allows user to add tasks with specified deadline.

**Example:** `adeadline cs2100 assignment 1 /by 2025-09-15 13:00`

A task with the name "cs2100 assignment 1" due on 15 Sep 2025 at 1pm will be added to the list.

```
Okay! I've added this task: 
  [D][] cs2100 assignment 1 (by: Sep 15 2025 13:00) 
Looks like you have 2 tasks in the list...
```

### Adding Events

Allows user to add tasks with specified start and end time.

**Example:** `event birthday party /from 2025-09-15 13:00 /to 2025-09-15 19:00`

A task with the name "birthday party" from 9 Dec 2025 1pm to 7pm will be added to the list.

```
Okay! I've added this task: 
  [E][] birthday party (from: Dec 09 2025 13:00 to: Dec 09 2025 19:00) 
Looks like you have 3 tasks in the list...
```

### Listing Tasks

Allows user to view the current list of tasks.

**Example:** `list`

The list of current tasks will be shown with their corresponding index.

```
Here are the tasks in your list!!!
 1. [T][] homework 
 2. [D][] cs2100 assignment 1 (by: Sep 15 2025 13:00) 
 3. [E][] birthday party (from: Dec 09 2025 13:00 to: Dec 09 2025 19:00) 
```

### Marking and Unmarking Tasks

Allows user to mark or unmark a task as completed or uncompleted respectively. 

**Example:** `mark 2`

The task at index 2 will be marked as completed.

```
Yay!!I've marked this task as done: 
 [D][X] cs2100 assignment 1 (by: Sep 15 2025 13:00) 
```

**Example:** `unmark 2'

The task at index 2 will be unmarked. 

```
Oh no:( I've marked this task as not done yet: 
 [D][] cs2100 assignment 1 (by: Sep 15 2025 13:00) 
```

### Deleting Tasks

Allows user to delete the task at a specified index.

**Example:** `delete 1`

The task at index 1 will be removed.

```
Okay! I've removed this task: 
  [T][] homework 
Looks like you have 2 tasks in the list...
```

### Tagging Tasks

Allows user to assign a tag to a specified task

**Example:** `tag 1 urgent`

The task at index 1 will be marked as urgent.

```
I've added the tags to the task: 
 [D][] cs2100 assignment 1 (by: Sep 15 2025 13:00) | #urgent
```

### Finding Tasks

Allows user to find tasks with a specified name or tag. 

**Example:** `find urgent`

Task tagged with "urgent" will be shown in a list.

```
Here are the matching tasks in your list: 
 1. [D][] cs2100 assignment 1 (by: Sep 15 2025 13:00) | #urgent
```
**Example:** `find birthday`

Task tagged with "urgent" will be shown in a list.

```
Here are the matching tasks in your list: 
 1. [E][] birthday party (from: Dec 09 2025 13:00 to: Dec 09 2025 19:00) 
```

