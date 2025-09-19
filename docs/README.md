# Piper User Guide

![Ui.png](Ui.png)

Piper is your friendly resident bird chatbot.
It lets you conveniently manage todos, deadlines, and events.
All your tasks are saved automatically, so you can close Piper and return later without losing progress.

## Adding todos
A todo is the simplest task type. It only has a description and no date.

**How to use:**<br>
Type the `todo` keyword, followed by the task description.

Example:
```
todo read book
```
Expected outcome:
```
TWEET! I've tucked this task into the nest:
[T][] read book
Now you have 4 tasks in the list.
```

## Adding deadlines

A deadline is a task with a specific due date, and optionally, a time.
When you add a deadline, Piper records the description and due date, and will show it when you list your tasks.

**How to use:**<br>
Type the `deadline` keyword, followed by the task description, then `/by` and the due date.

Examples:
```
deadline submit assignment /by 2025-10-12
deadline pay bills /by end of this week
```
Expected outcome:
```
TWEET! I've tucked this task into the nest:
[D][] submit assignment (by: Oct 12 2025)

TWEET! I've tucked this task into the nest:
[D][] pay bills (byP: end of this week)
```

## Adding events

An event is a task that spans a start and end date.
When using ISO format (`yyyy-MM-dd`), Piper checks that `/to` comes after `/from`.

**How to use:**<br>
Type the `event` keyword, followed by the event description, then `/from` and the start date, then `/to` and the
end date.

Example:
```
event project demo /from 2025-09-20 /to 2025-09-21
```
Expected outcome:
```
TWEET! I've tucked this task into the nest:
[E][] project demo (from: Sep 20 2025 to: Sep 21 2025)
```

## Listing tasks

Shows all tasks currently stored.

**How to use:**<br>
Type `list`

Expected outcome: list of all tasks, e.g.
```
1. [T][] do laundry
2. [D][] buy groceries (by: Sep 26 2025)
3. [E][] hackathon (from: 10 October to: 12 October)
```

## Marking and unmarking tasks

Mark a task as done, or undo it.

**How to use:**<br>
Type `mark` or `unmark`, followed by the task index.

Example:
```
mark 2
unmark 2
```
Expected output:
```
SWEET! I've marked this task as done:
[T][X] buy milk

ALRIGHTY, I've marked this task as not done yet:
[T][] buy milk
```

## Finding tasks

Search for tasks containing a keyword.

**How to use:**<br>
Type `find`, followed by the keyword.

Example:
```
find assignment
```
Expected outcome:
```
Any of these the task you're looking for?
1. [D][] submit assignment (by: Oct 2 2025)
2. [T][] assignment of peer mentors
```

## Snoozing tasks

Push back a deadline or event by a given number of days.

**How to use:**<br>
Type `snooze`, followed by the task index, then the number of days to snooze.

Example:
```
snooze 2 3
```
Expected output:
```
ZZZ...
[D][] clean my room (by: Nov 4 2025)
```

## Exiting Piper

Close the program gracefully.

**How to use:**<br>
Type `bye`.
