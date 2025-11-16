# CuteOwl User Guide

Say hello to CuteOwl, your intelligent digital assistant designed to transform the way you manage tasks. Whether you're juggling deadlines, organizing projects, or simply trying to stay focused, CuteOwl brings clarity, calm, and a touch of charm to your workflow


![Ui.png](Ui.png)

### Command quick reference

todo <description> - add a plain todo task

deadline <description> /by <datetime> - add a task with a deadline

event <description> /from <datetime> /to <datetime> - add an event spanning a time range

list - show all tasks

mark <index> - mark a task done (index is 1-based)

unmark <index> - mark a task as not done

delete <index> - delete a task

find <keyword> - find tasks whose description contains a keyword

note <content> - add a note

notes - list all saved notes

bye - exits CLI application 

## Adding todo tasks

Create a simple task to do (no time attached)

**Syntax:**
```
todo <description>
```

**Example:**
```
todo Buy pencil
```

Example: `keyword (optional arguments)`

CuteOwl adds a new Todo to your task list and shows a confirmation that includes the item and the new total task count.

**Expected output:**

```
Got it. I've added this task:
  [T][ ] Buy pencil
Now you have 1 tasks in the list.
```
---

## Adding deadline tasks
Create task with a deadline 

**Syntax:**
```
deadline <description> /by <datetime>
```

**Example:**
```
deadline Submit report /by 11/09/2025 2359
```

CuteOwl saves a `Deadline` task and displays a confirmation with the deadline time and the updated task count.

**Expected output:** 

```
Got it. I've added this task:
  [D][ ] Submit report (by: Sept 11 2025 23:59)
Now you have 2 tasks in the list.
```
---



## Adding event tasks
Create an event that has a start and end time

**Syntax:**
```
event <description> /from <datetime> /to <datetime>
```

**Example:**
```
event DAO2703 meeting /from 11/9/2025 1000 /to 11/9/2025 1100
```
CuteOwl creates an `Event` task and confirms the addition, showing the times and new task count.

**Expected output:**
```
Got it. I've added this task:
  [E][ ] DAO2703 meeting (from: Sept 11 2025 1000 to: Sept 11 2025 1100)
Now you have 3 tasks in the list.
```
---



## List tasks
Show all tasks currently in CuteOwl

**Syntax:**
```
list
```
CuteOwl prints each task with an index (1-based), type marker, done marker, description, and any time info.

**Expected output:**
```
1. [T][ ] Buy pencil
2. [D][ ] Submit report (by: Sept 11 2025 23:59)
3. [E][ ] DAO2703 meeting (from: Sept 11 2025 1000 to: Sept 11 2025 1100)
```
---



## Mark a task
Marks a task as done

**Syntax:**
```
mark <index>
```

**Example:**
```
mark 2
```

CuteOwl marks the task as done and displays the marked task. 

**Expected output:**
```
Nice! I've marked this task as done:
  [D][X] Submit report (by: Sept 11 2025 23:59)
```
---


## Unmark a task
Mark a task as not done. 

**Syntax:**
```
unmark <index>
```

**Example:**
```
unmark 2
```

CuteOwl marks the task as not done and displays the undone task.

**Expected output:**
```
OK, I've marked this task as not done yet:
  [D][ ] Submit report (by: Sept 11 2025 23:59)
```
---


## Delete a task
Remove a task from the list. 

**Syntax:**
```
delete <index>
```

**Example:**
```
delete 3
```

CuteOwl removes the task, and displays the removed task to user. 

**Expected output:**
```
Noted. I've removed this task:
  [E][ ] DAO2703 meeting (from: Sept 11 2025 1000 to: Sept 11 2025 1100)
Now you have 2 tasks in the list.
```
--- 


## Finding a task
search tasks by substring match on the description

**Syntax:**
```
find <keyword>
```

**Example:**
```
find report
```

CuteOwl finds keyword that matches with any of the task description and list them.  

**Expected output:**
```
Here are the matching tasks in your list:
1. [D][X] Submit report (by: Sept 11 2025 23:59)
2. [T][ ] Report ideas
```
---


## Adding a note 
Save a short note separate from tasks

**Syntax:**
```
note <content>
```

**Example:**
```
note ideas for project presentation
```

CuteOwl save the note into the note list and confirms what user has noted down. 

**Expected output:**
```
You've noted down idea for project presentation
```
---



## Listing notes 
Display all notes saved in CuteOwl

**Syntax:**
```
notes
```

CuteOwl displays all notes saved in the note list. 

**Expected output:**
```
Here are your notes~
-- idea for project presentation
-- buy eyedrops later
```
---

## Exit 
Exit CLI application 

**Syntax:**
```
bye 
```

Application will print out an exit message. 

**Expected output:**
```
Bye. Hope to see you again soon!
```
