# Squiddy User Guide

![Screenshot of the product](/docs/Ui.png)

Have you ever needed a chatbot to write down your tasks? **Squiddy** is here for you!

### Squiddy is able to:

- Add tasks (deadlines, events, todos)
- Delete tasks
- Mark tasks
- List tasks
- Find tasks
- Snooze tasks

Use **Squiddy** today! :squid:

## Adding tasks

Add a task to your task list! Task as categorised into todo, deadline and event.

Adding a todo task: 

```
todo (task description)
```

Example: `todo homework` will add the todo task `homework` to the list.

Adding a deadline task:

```
deadline (task description) /by (due date in yyyy-mm-dd)
```

Example: `deadline homework /by 2020-10-10` will add the deadline task homework
with due date 10 Oct 2020 to the list.

Adding an event task:

```
event (task description) /from (start date in yyyy-mm-dd) /to (end date in yyyy-mm-dd)
```

Example: `event homework /from 2020-10-10 /to 2020-11-10` will add an event task
with start date 10 Oct 20 and end date 10 Nov 20 to the list.



## Deleting tasks

Delete a task from the task list removes it from the task list permanently.

To delete a task simply type:

```
delete (task index)
```

Example: `delete 2` will delete the second task in my task list.

## Marking tasks

Mark a task as done or not done to indicate if it has been completed.

To mark a task, type:

```
mark (task index)
```

And to unmark a task, type:

```
unmark (task index)
```

Example: `mark 3` will mark the third task in my task list as done while 
`unmark 5` will mark the fifth task in my task list as not done.


## Viewing task list

View your current task list and see each task's index, type, description, completion
and dates if applicable.

To do this, just type:

```
list
```

Example output:

```
Let's see what you've got:
    1. [T][X] Water plants
    2. [D][ ] Clean the house (by: 10 Oct 20)
    3. [E][ ] Attend team meeting (from: 11 Oct 20, to: 12 Oct 20)
```

## Finding tasks

Search for a task based on its name.

To find a task, type:

```
find (keyword)
```

Example: `find plants` to find all tasks with `plants` in the description

## Snoozing tasks

Snooze a task to change its due date.

To snooze a deadline, type:

```
snooze (task index) /by (new due date in yyyy-mm-dd)
```

To snooze an event:

```
snooze (task index) /from (new start date in yyyy-mm-dd) /to (new end date in yyyy-mm-dd)
```

Example `snooze 8 /by 2022-15-10` will snooze task 8 if it is a deadline task 

and `snooze 9 /from 2020-12-10 /to 2022-11-11` will snooze task 9 if it is an event task.

## Closing Squiddy

Squiddy can be closed by typing the command: `bye`