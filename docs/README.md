# Ackermann User Guide
![Ackermann UI](./Ui.png)

Ackermann frees your mind of having to remember things you need to do.
It's
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use

All you need to do is:
1. Download the Jar file from [here]()
2. Run, when you are in the directory containing the Jar file, using
```declarative
java -jar "Ackermann.jar"
```
3. Let it manage your tasks for you! 

## Features
- Add deadlines
- Add toDos
- Add events
- List tasks
- Find task
- Delete task
- Mark task as done/undone
- Tag task

### Adding deadlines
Adds deadline task to your to do list.
```declarative
deadline (String) /by (date in YYYY-MM-DD)
```
Example: `deadline homework /by 2025-12-12`

Expected output:
```
Can bro. I've added this Deadline:
[D][ ] homework (by Dec 12 2025) [ ]
Now you have X tasks in the list.
```

### Add toDos
Adds a toDo task to your to do list. 
```declarative
todo (String)
```
Example: `todo something`

Expected output:
```
Got it bro. I've added this ToDo:
[T][ ] homework [ ]
Now you have X tasks in the list.
```

### Add events
Adds an event task to your to do list.

*Take note that the "from" date must be before "to" date, and day and month must be 2 digits
```declarative
event (String) /from (date in YYYY-MM-DD) /to (date in YYYY-MM-DD)
```
Example: `event something /from 2025-12-02 /to 2025-12-10`

Expected output:
```
Roger bro. I've added this Event:
[E][ ] something (from: Dec 2 2025 to: Dec 10 2025) [ ]
Now you have X tasks in the list.
```
### List Tasks
Lists all tasks in to-do list. 

Search input string is case-insensitive.
```declarative
list
```
Example: `list`

Expected output:
```
Here are the tasks in your list bro:
1. [T][X] test [ ]
2. [E][ ] test2 (from: Dec 12 2025 to: Dec 15 2025) [ ]
```

### Find Task
Finds a task based on the name of it. It matches as long as 
the search input string is a substring of the name of the task. 

Search input string is case-insensitive.
```declarative
find (substring)
```
Example: `find test`

Expected output:
```
Ok bro, I've helped you to search and here are the matching tasks in your list:
[E][ ] test (from: Dec 12 2025 to: Dec 14 2025) [ ]
[T][ ] test2 [ ]
```

### Delete Task
Deletes a task from your list.

Recommended to list first to make sure you delete the correct task.
```declarative
delete (int)
```
Example: `delete 1` -> deletes first item in the list

Expected output:
```
Ok bro, I've removed this task:
[T][ ] something [ ]
Now you have X tasks in the list
```

### Mark task
Marks a task as completed or uncompleted.
```declarative
mark (int)
unmark (int)
```
Example: `mark 1` `unmark 1` 

Expected output for **mark**:
```
Nice one bro! I've marked this task as done:
[T][X] test [ ]
```

Expected output for **unmark**:
```
Ok bro, I've marked this task as not done yet:
[T][ ] test [ ]
```

### Tag task
Tags a task in your list. You can use this to mark important tasks,
or for any other suitable use cases.
```declarative
tag (int) (String)
```
Example: `tag 1 important` -> tags first item in the list as "important"

Expected output:
```
Noted bro, I have successfully tagged:
[T][ ] something [important]
```
