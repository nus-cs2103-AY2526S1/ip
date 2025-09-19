# APleaseBot User Guide

![Screenshot of APleaseBot in action](Ui.png)

APleaseBot is a chatbot designed to assist users with task-keeping. It features many functions:
* Adding tasks with deadlines
* Marking and unmarking tasks as done
* Deleting tasks
* Finding tasks by keywords
* Listing all tasks
* Adding events with start and end times

## Adding deadlines

APleaseBot allows users to add tasks with deadlines using the `deadline` command.

### Example usage

Example: `deadline {task name} \by {deadline in YYYY-MM-DD HHMM}`

```
________________________________________
Got it. I've added this task:
  [D][ ] task name (by: {deadline in DD MMM YYYY HHMM})
Now you have X tasks in the list.
________________________________________
```

## Marking & Unmarking Tasks

APleaseBot allows users to manage tasks using the `mark` and `unmark` commands.

### Example usage

Example: `mark {task index in list}` & `unmark {task index in list}`

```
// Mark as done
________________________________________
Nice! I've marked this task as done:
  [T][X] task name
________________________________________

// Unmark
________________________________________
OK, I've marked this task as not done yet:
  [T][ ] task name
________________________________________
```


## Deleting tasks

APleaseBot allows users to remove tasks using the `delete` command.

### Example usage

Example: `delete {task index}`

```
________________________________________
Noted. I've removed this task:
  [T][ ] task name
Now you have X tasks in the list.
________________________________________
```

## Finding tasks

APleaseBot allows users to search for tasks using the `find` command.

### Example usage

Example: `find {keyword}`

```
________________________________________
Here are the matching tasks in your list:
1.[T][ ] task name
2.[D][X] another task name (by: {deadline in DD MMM YYYY HHMM})
________________________________________

// Empty result
________________________________________
Something went wrong!
APleaseBot error: Issue with data file: Data file empty
________________________________________
```

## Listing all tasks

APleaseBot allows users to view all tasks using the `list` command.

### Example usage

Example: `list`

```
________________________________________
Here are the tasks in your list:
1.[T][ ] task name
2.[D][X] another task name (by: {deadline in DD MMM YYYY HHMM})
3.[E][ ] event name (at: {start time in DD MMM YYYY HHMM} to {end time in DD MMM YYYY HHMM})
________________________________________
```

## Adding events

APleaseBot allows users to add events with start and end times using the `event` command.

### Example usage

Example: `event {event name} \at {start time in YYYY-MM-DD HHMM} to {end time in YYYY-MM-DD HHMM}`

```
________________________________________
Got it. I've added this task:
  [E][ ] event name (at: {start time in DD MMM YYYY HHMM} to {end time in DD MMM YYYY HHMM})
Now you have X tasks in the list.
________________________________________
```