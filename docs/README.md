# FRIDAY
![Screenshot of Friday Chatbot GUI](/docs/Ui.png)

Friday is your personal chatbot assistant designed to make task 
management simple and intuitive. Instead of clicking through menus or
juggling multiple apps, you can chat naturally with Friday to keep 
track of everything you need to do.

## Features

### Adding Todo Tasks

Add a todo task by typing `todo` followed by the task description.

Example: `todo (description of task) (Optional:#tag)`

After keying in the command line Friday will prompt you with a confirmation message.

```
Got it. I've added this task:
  [T][ ] (description of task) (Optional:#tag)
Now you have X tasks in the list.
```

### Adding Deadline Tasks

Add a deadline task by typing `deadline` followed by the task description and due date.

Example: `deadline (description of task) /by (due date:DD/MM/YYYY HHMM) (Optional:#tag)`

After keying in the command line Friday will prompt you with a confirmation message.

```
Got it. I've added this task:
  [D][ ] (description of task) /by (by: due date) (Optional: #tag)
Now you have X tasks in the list.
```


### Adding Event Tasks

Add an event task by typing `event` followed by the task description
and event start date and end date.

Example: `event (description of task) /from (start date:DD/MM/YYYY HHMM) /to (end date:DD/MM/YYYY HHMM) (Optional:#tag)`

After keying in the command line Friday will prompt you with a confirmation message.

```
Got it. I've added this task: 
  [E][ ] (description of task) /from (from: start date) /to (to: end date) (Optional:#tag)
Now you have X tasks in the list.
```

### Deleting Tasks

Delete a task by typing `delete` followed by the task number.

Example: `delete (task number)`

After keying in the command line Friday will prompt you with a confirmation message.

```
Noted. I've removed this task:
  [task details]
Now you have X tasks in the list.
```

### Marking Tasks as Done

Mark a task as done by typing `mark` followed by the task number.

Example: `mark (task number)`

After keying in the command line Friday will prompt you with a confirmation message.

```
Nice! I've marked this task as Done:
  [task details]
```

### Unmarking Tasks as Not Done

Unmark a task as not done by typing `unmark` followed by the task number.

Example: `unmark (task number)`

After keying in the command line Friday will prompt you with a confirmation message.

```
OK, I've unmarked this task as Done:
  [task details]
```

### Listing All Tasks

List all tasks by typing `list`.

Example: `list`

After keying in the command line Friday will display all your tasks.

```
Here are the tasks in your list:
1.[task details]
2.[task details]
...
X.[task details]
```

### Finding Tasks

Find tasks by typing `find` followed by a keyword.

Example: `find (keyword)`

After keying in the command line Friday will display all matching tasks.

```
Here are the matching tasks in your list:
1.[task details]
2.[task details]
...
X.[task details]
```

### Tagging Tasks

Tag a task by adding `#tag` after the task description or deadline when adding a task.

Example: `todo (description of task) #tag`

After keying in the command line Friday will prompt you with a confirmation message.

```
Got it. I've added this task:
  [T][ ] (description of task) #tag
Now you have X tasks in the list.
``` 