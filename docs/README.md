# YapBot User Guide

A CLI application to track and manage your tasks.\
Here is a list of valid commands ('-' is reserved for flags):

## Adding a task
Adds a ToDo task to your list: `[T][ ] read book`
```
Noted. I've added this task to the list:
[T][ ] read book
You have 1 tasks in the list
____________________________________________________________
```

Adds a Deadline task to your list `[D][ ] return book -by 01/10/2025`\
flags: `-by DD/MM/YYYY`
```
Noted. I've added this task to the list:
[D][ ] return book -by 1 OCTOBER 2025
You have 2 tasks in the list
____________________________________________________________
```

Adds an Event task to your list `[E][ ] family outing -from 01/10/2025 -to 05/10/2025`\
flags: `-from DD/MM/YYYY -to DD/MM/YYYY`
```
Noted. I've added this task to the list:
[E][ ] family outing -from 1 OCTOBER 2025 -to 5 OCTOBER 2025
You have 3 tasks in the list
____________________________________________________________
```

Adds a marked {ToDo} task to your list: `[T][X] homework`
```
Noted. I've added this task to the list:
[T][X] homework
You have 4 tasks in the list
____________________________________________________________
```

## Marking & Unmarking tasks
Marks the {1st, 2nd} tasks as done: `[mark] 1 2`
```
Nice! I've marked these tasks as done:
[T][X] read book
[D][X] return book -by 1 OCTOBER 2025
____________________________________________________________
```

Unmarks the {1st, 2nd} tasks as not done: `[unmark] 1 2`
```
OK, I've marked these tasks as not done
[T][ ] read book
[D][ ] return book -by 1 OCTOBER 2025
____________________________________________________________
```

## Deleting tasks
Deletes the {1st, 2nd} tasks in the list: `[delete] 1 2`
```
Noted, I've removed these tasks:
[T][ ] read book
[D][ ] return book -by 1 OCTOBER 2025
You have 2 tasks in the list
____________________________________________________________
```

## Looking up a keyword
finds all tasks that matches with the specified keyword: `[find] book`
```
Here are the tasks that matches with 'book':
[T][ ] read book
[D][ ] return book -by 1 OCTOBER 2025
____________________________________________________________
```

## Update a task
Updates the {1st} task with the new description: `[update] 1 -to write book`\
flags: `-to new description`
```
Noted I've updated the task:
[T][ ] write book
____________________________________________________________
```

## Listing your tasks
Shows a list of available tasks: `[list]`
```
Here are the tasks in your list:
[T][ ] read book
[D][ ] return book -by 1 OCTOBER 2025
[E][ ] family outing -from 1 OCTOBER 2025 -to 5 OCTOBER 2025
[T][X] homework
You have 4 tasks in the list
____________________________________________________________
```

## Get a Reminder
Shows a list of available tasks sorted in chronological order: `[reminder]`
```
Here are the tasks sorted by time:
[E][ ] family outing -from 1 OCTOBER 2025 -to 5 OCTOBER 2025
[T][ ] read book
[D][ ] return book -by 1 OCTOBER 2025
[T][X] homework
You have 4 tasks in the list
____________________________________________________________
```

## Exiting the Chatbot
Exits the chatbot: `[bye]`
```
Bye. Hope to see you again soon!
____________________________________________________________
```

## Jumping to previous commands
press the "UP" / "DOWN" arrow key to iterate through the command history