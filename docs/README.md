# Mani User Guide

![UI Screenshot](Ui.png)

Mani is a chatbot that will help you to take note of tasks, deadlines, and priorities in a simple and intuitive way.

## Adding tasks

There are 3 types of tasks - Todo, Deadline, Event

### Todo

Todo helps you take note of tasks in general.

Format: `todo TASK`

Example: `todo read book`

The chatbot will store this Todo for you to refer.

```
Got it. I've added this task:
[T][] read book
Now you have 1 tasks in the list.
```

### Deadline

Deadline helps you take note of tasks and their deadlines.

Format: `deadline TASK /by DATETIME`

Example: `deadline return book /by 2/12/2019 1800`

The chatbot will store this Deadline for you to refer to be finished by the specified date and time.

```
Got it. I've added this task:
[D][] return book (by: Dec 2 2019 6PM)
Now you have 2 tasks in the list.
```

### Event

Event helps you take note of events and from when to when they occur.

Format: `event TASK /from DATETIME /to DATETIME`

Example: `event visit library /from 2/12/2019 1800 /to 2/12/2019 1900`

The chatbot will store this Todo for you to refer.

```
Got it. I've added this task:
[E][] visit library (from: Dec 2 2019 6PM to: Dec 2 2019 7PM)
Now you have 3 tasks in the list.
```

## Delete tasks

You can delete tasks from the lists of your tasks.

Format: `delete INDEX`

Example: `delete 1`

The chatbot will delete the 'n'th task (e.g. number after delete - first task).

```
Noted. I've removed this task:
[T][] borrow book"
Now you have 2 tasks in the list.
```

## Mark tasks

You can mark tasks as complete, visually using 'X' sign.

Format: `mark INDEX`

Example: `mark 1`

The chatbot will mark the 'n'th task as completed (e.g. number after mark - first task).

```
Nice! I've marked this task as done:
[D][X] return book (by: Dec 2 2019 6PM)
```

## Unmark tasks

You can unmark tasks as not complete, removing the 'X' sign.

Format: `unmark INDEX`

Example: `unmark 1`

The chatbot will mark the 'n'th task as completed (e.g. number after mark - first task).

```
OK, I've marked this task as not done yet:
[D][] return book (by: Dec 2 2019 6PM)
```

## List tasks

You can list all the tasks you have provided Mani to take note.

Example: `list`

The chatbot will list all the tasks, chronological order, with details like task, type of task and whether its completed or not.

```
Here are the tasks in your list:
1.[D][] return book (by: Dec 2 2019 6PM)
2.[E][] visit library (from: Dec 2 2019 6PM to: Dec 2 2019 7PM)
```

## Find tasks

Using a keyword, you can find the tasks that contains it.

Format: `find KEYWORD`

Example: `find book`

The chatbot will return the tasks containing the keyword.

```
Here are the matching tasks in your list:
1.[D][] return book (by: Dec 2 2019 6PM)
```

## Leaving Application

You can exit Mani Chatbot

Example: `bye`

The chatbot will close.

## Handling Duplicates

Mani will handle duplicates if given task is equivalent to the existing task.

Example: `todo read book`

The chatbot will check its existing task, and if there's a duplicate, it will add the count of duplicate to the existing task string.

```
Got it. I've added this task:
(Note this is a duplicate)
[T][] readbook1
Now you have 3 tasks in the list.
```
