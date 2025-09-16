# Luke User Guide

Luke is a task manager that helps you keep track of your tasks.

## Adding tasks

There are 3 types of tasks: todo, deadline, and event.

To create a new todo task, type 'todo [description]'. 

E.g: `todo buy bread`

To create a new deadline task, type 'deadline [description] /by [YYYY-MM-DD]. 

E.g: `deadline return book /by 2020-01-03`

To create a new event task, type 'event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]'. 
    
E.g: `event midterms /from 2020-02-01 /to 2020-02-10`

**Expected output:**
```
Got it. I've added this task:
[T][] buy bread
Now you have 1 task in the list.

Got it. I've added this task:
[D][] return book (by: Jan 3 2020)
Now you have 2 tasks in the list.

Got it. I've added this task:
[E][] midterms (from: Feb 1 2020 to: Feb 10 2020)
Now you have 3 tasks in the list.
```

## Mark task

Type 'mark [task number]' to mark that task as completed.

E.g: `mark 2`

**Expected output:**
```
Nice! I've marked this task as done:
[D][X] return book (by Jan 3 2020)
```

## Delete task

Type 'delete [task number]' to delete that task.

E.g: `delete 3`

**Expected output:**
```
Noted. I've removed this task:
[E][] midterms (from: Feb 1 2020 to: Feb 10 2020)
Now you have 18 tasks in the list
```

## Find task

Type 'find [task]' to find all tasks that contain that word/phrase.

E.g: `find book`

**Expected output:**
```
Here are the matching tasks in your list:
1. [D][X] return book (by: Jan 3 2020)
```

## List tasks

Type 'list' to view you current tasks.

E.g: `list`

**Expected output:**
```
Here are the tasks in your list:
1. [T][] buy bread
2. [D][X] return book (by: Jan 3 2020)
```
