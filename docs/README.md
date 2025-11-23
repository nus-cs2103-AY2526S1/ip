# Diablo User Guide

![Screenshot of an example conversation in the Diablo Chatbot.](Ui.png)

Diablo is a feature rich chatbot which allows users to store and access scheduled tasks.

## Listing tasks

The `list` command lists all the current tasks in the task list.

Example: `list`

Assuming you have these tasks in the task list,
`[D][] finish homework (by: Oct 10 2025)`,
`[E][] hackathon (from Oct 04 2025 to: Oct 06 2025)`,
this is the expected output:

```
1: [D][] finish homework (by: Oct 10 2025)
2: [E][] hackathon (from Oct 04 2025 to: Oct 06 2025)
```

If there are no tasks in the list yet, this is the expected output:

```
There are currently no items in the list!
```

## Finding tasks

The `find` command finds all tasks in the task list containing a specified keyword. 

Example: `find homework` 

Assuming you have these tasks in the task list,
`[D][] finish homework (by: Oct 10 2025)`, 
`[E][] hackathon (from Oct 04 2025 to: Oct 06 2025)`,
this is the expected output:

```
1: [D][] finish homework (by: Oct 10 2025)
```

If there are no tasks in the list yet, or no tasks contain the keyword, this is the expected output:

```
The items you are looking for are not in the list!
```

## Marking tasks

The `mark` command marks the task specified by its position in the task list.

Example: `mark 1`

Assuming you have these tasks in the task list,
`[D][] finish homework (by: Oct 10 2025)`,
`[E][] hackathon (from Oct 04 2025 to: Oct 06 2025)`,
this is the expected output:

```
Nice! I've marked this task as done:
    [D][X] finish homework (by: Oct 10 2025)
```

## Deleting tasks

The `delete` command deletes the task specified by its position in the task list.

Example: `delete 1`

Assuming you have these tasks in the task list,
`[D][] finish homework (by: Oct 10 2025)`,
`[E][] hackathon (from Oct 04 2025 to: Oct 06 2025)`,
this is the expected output:

```
Alright, I've removed this task: 
    [D][] finish homework (by: Oct 10 2025)
```

## Adding deadlines

The `deadline` command adds a deadline task to the task list. 

Example: `deadline finish homework /by 2025-10-10`

This will be the expected output:

```
Got it. I've added this task:
    [D][] finish homework (by: Oct 10 2025)
```

## Adding todos

The `todo` command adds a todo task to the task list.

Example: `todo finish homework`

This will be the expected output:

```
Got it. I've added this task:
    [T][] finish homework
```

## Adding events

The `event` command adds an event task to the task list.

Example: `event hackathon /from 2025-10-04 /to 2025-10-06`

This will be the expected output:

```
Got it. I've added this task:
    [E][] hackathon (from Oct 04 2025 to: Oct 06 2025)
```

## Exiting the chatbot

The `bye` command exits the chatbot.

Example: `bye`

This simply exits the chatbot and closes the GUI window.

## Searching for commands

If you don't know what a command does, you can use the `help` command followed by the command to find out!

Example: `help list`

This will be the expected output:

```
list - lists all current tasks in the task list
```

If you happen to be new to the chatbot, use the `help` command by itself to show all commands and use cases.
