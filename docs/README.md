# Dawae User Guide 🤡🤡🤡

![Dawae GUI](Ui.png)

## dyu kno da wae ? :]
Dawae is a task management application that helps you organize and keep track of your tasks efficiently. 
It supports three types of tasks: Todo, Deadline, and Event. 
Lost your way? Dawae has an archive function that helps you clear your clutter and begin with a fresh start.

## How to add da tasks?
### Todo
`Todo Decorate Room`

- `Todo` is the command word
- `Decorate Room` is the task description

outputs:
```
Got it. I've added this task:
 [T][ ] wakeup
Now you have 1 tasks in the list.
```

### Deadline
`Deadline essay /by 2025-08-31T06:30:00`

- `Deadline` is the command word
- `essay` is the task description
- `/by` is a delimiter to indicate the start of the date and time
- `2025-08-31T06:30:00` is the date and time in the format `yyyy-MM-ddTHH:mm:ss`

outputs:
```
Got it. I've added this task:
 [D][ ] essay (by: 31 Aug 2025, 6:30:00 am)
Now you have 2 tasks in the list.
```

### Event
`Event career fair /from 2025-08-20T06:30:00 /to 2025-08-20T08:30:00`

- `Event` is the command word
- `career fair` is the task description
- `/from` is a delimiter to indicate the start of the start date and time
- `2025-08-20T06:30:00` is the start date and time in the format `yyyy-MM-ddTHH:mm:ss`
- `/to` is a delimiter to indicate the start of the end date and time
- `2025-08-20T08:30:00` is the end date and time in the format `yyyy-MM-ddTHH:mm:ss`

outputs:
```
Got it. I've added this task:
 [E][ ] career fair (from: 20 Aug 2025, 6:30:00 am to: 20 Aug 2025, 8:30:00 am)
Now you have 3 tasks in the list.
```

## Basic Actions on Tasks

### list
`list` - Displays all tasks in the current task list. 

### mark
`mark 2` - Marks the task at index 2 as completed.

### unmark 
`unmark 2` - Marks the task at index 2 as not completed.

### delete
`delete 2` - Deletes the task at index 2 from the current task list.

## Feature Find
`find essay` - Searches for tasks containing the keyword "essay" in their description and displays them.

`find sle` - Searches for tasks containing the keyword "sle" in their description and displays them.

## Feature Archive
`archive` - Moves all completed tasks from the current task list to the archive. Also clears current task list.

## exit
You may just type `bye` to exit dawae! your list is auto saved so the next time you start dawae, 
your tasks will be right where you left them!

# Enjoy using Dawae! 🤡🤡🤡