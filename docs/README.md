# Leo User Guide

<img src="Ui.png" alt="UI image">

# Introduction
Leo is a simple command-line chatbot for managing tasks. It supports three types:
To-dos: tasks without dates.
Deadlines: tasks to finish by a certain date.
Events: activities with a start and end date.

Tasks are saved automatically to a local file and reloaded each time you run Leo.

# Adding Tasks
Deadline: deadline <description> /by <yyyy-mm-dd>
Example: 
```
deadline finish assignment /by 2025-09-30
[D][ ] finish assignment (by: 2025-09-30)
```

Event: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>
Example:
```
event project meeting /from 2025-09-20 /to 2025-09-20
[E][ ] project meeting (from: 2025-09-20 to: 2025-09-20)
```

To-do: todo <description>
Example: 
```
todo read a book
[T][ ] read a book
```

# Managing Tasks
List tasks: list
Example :
```
list
1. [D][ ] finish assignment (by: 2025-09-30)
2. [E][ ] project meeting (from: 2025-09-20 to: 2025-09-20)
3. [T][ ] read a book
```

Mark as done: 
```
mark <task number>
```

Delete task:
```
delete <task number>
```

Use bye to close Leo. 
Example: 
```
Bye. Hope to see you again soon!
```


## Acknowledgments
The `Storage` class and TaskListTest were initially generated with assistance from ChatGPT (GPT-5 Thinking, Sep 18, 2025).
I adapted and reviewed the code for this project.
