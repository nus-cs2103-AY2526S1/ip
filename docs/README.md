# Siri   User Guide



![Siri Product Screenshot](Ui.png)

Siri is a simple chatbot that helps you **manage tasks** via text commands.
## Adding deadlines

Use the `deadline` command to add a task with a due date.

Example:  
deadline return book /by 2025-10-10

---

Expected outcome:

```
Got it. I've added this task:
[D][ ] return book (by: 2025-10-10)
Now you have 1 task in the list.

```

## Adding todos

Todos can be added with the `todo` command.

Example:  
todo read book

---

Expected outcome:

```
Got it. I've added this task:
[T][ ] read book
Now you have 2 task in the list.

```
---
## Adding events

Events can be added with the `event` command, which takes a description, start time, and end time.

Example:  
event project meeting /from 2025-10-10 14:00 /to 2025-10-10 16:00

---

Expected outcome:

```
Got it. I've added this task:
[E][ ] project meeting (from: 2025-10-10 14:00 to: 2025-10-10 16:00)
Now you have 3 tasks in the list.

```
---
## Listing tasks

Use the `list` command to display all tasks currently stored.


Example:  
list

---

Expected outcome:

```
Here are the tasks in your list:

1. [T][ ] read book

2. [D][ ] return book (by: 2025-10-10)

3. [E][ ] project meeting (from: 2025-10-10 14:00 to: 2025-10-10 16:00)

```

## Marking tasks

Use the `mark` command to mark a task as done.

Example:  
mark 1

---
Expected outcome: 
``` 
Nice! I've marked this task as done:
[T][X] read book

```


---
## Unmarking tasks

Use the `unmark` command to set a completed task back to not done.

Example:  
unmark 1

---
Expected outcome:  
```
OK, I've marked this task as not done yet:
[T][ ] read book

```
---
## Deleting tasks

Use the `delete` command to remove a task from the list.

Example:  
delete 2
---
Expected outcome:  
```
Noted. I've removed this task:
[D][ ] return book (by: 2025-10-10)
Now you have 2 tasks in the list.
```



---
## Finding tasks

Use the `find` command to search for tasks containing a keyword.

Example:  
find book
---

Expected outcome:  
```
Here are the matching tasks in your list:

[T][ ] read book
```


---

## Undoing actions

Use the `undo` command to revert the most recent action. You can also undo a specific task modification by index.

Examples:
undo 1

---
Expected outcome:  
```
Reverted last action.
```



---

## Exiting the application

Use the `bye` command to exit the chatbot.

Example:  
bye

---

Expected outcome:  
```
Bye. Hope to see you again soon!
```
