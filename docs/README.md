# Ineffa User Guide

![Product screenshot](Ui.png)

Ineffa is a bot that help you to track tasks that you need to do.

> ⚠️ **Important:** To ensure task list is stored locally, you **must** use the `bye` command upon exiting program.

## Adding Deadlines

`todo [Task description of any length and spacing]`

Create a todo task with a description of task

Example: `todo borrow book`

Outcome:

- A todo task will be created
  - Task: borrow book

```
Got it. I've added this task:
[T][ ] borrow book
Now you have XXX tasks in the list.
```

## Adding Deadlines

`deadline [task description] /by YYYY-MM-DD [time(optional)]`

Create a deadline task with a specific due date. Optionally, a time can be included to specify when the task is due on that date.

Example: `deadline return book /by 01-11-2022 1pm`

Outcome: 

- A task will be created with a deadline
  - Task: return book
  - Due Date: November 1, 2022
  - Time (optional): 1 PM

```
Got it. I've added this task:
[D][ ] return book (by: 2022-11-01 1pm)
Now you have XXX tasks in the list.
```

## Adding Events

`event [task description] /from [Day and/or time] /to [Day and/or time]`

Create an event task with a start and end date. Optionally, a time can be included.

Example: `event project meeting /from Mon 2pm /to 4pm`

Outcome:

- A task will be created with as an event
  - Task: project meeting
  - From Date: Mon 2pm
  - To Date: Mon 4pm

```
Got it. I've added this task:
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have XXX tasks in the list.
```

## List Tasks

`list`

List all Tasks that is in your task list

Example outcome:

```
Here are the tasks in your list:

1. [T][ ] borrow book
2. [E][ ] project meeting (from: Mon 2pm to: 4pm)
3. [D][ ] return book (by: 2022-11-01 1pm)
```

## Mark Task

`mark [Task position in list]`

Mark task position in list as **done**

Example: `mark 1`

Outcome: 
- Mark task 1 in list as done

```
[T][X] borrow book
```

## Unmark Task

`unmark [Task position in list]`

Mark task position in list as **not done**

Example: `unmark 1`

Outcome: 
- Unmark task 1 in list

```
[T][ ] borrow book
```

## Find Task

`find [optional: description]`

Filter task in task list with a description

Example: `find borrow`

Outcome: 
- Find any tasks having "borrow" description in task list and list them out.

```
Here are the tasks in your list:

1. [T][ ] borrow book
```

## Delete Task

`delete [Task position in list]`

Delete a task from task list

Example: `delete 1`

Outcome: 
- Removes Task 1 from list. [You can check value number using `list`]

```
Noted I've removed this task:
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have XXX tasks in the list.
```

## Exit program

`help`

To get help on commands while using the bot

## Exit program

To exit program: `bye`