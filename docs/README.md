# Geni User Guide
Welcome to **Geni**, your personal chatbot for managing tasks.  
This guide will help you learn the available commands and their formats.




## Adding deadline

It adds a task deadline to the list of tasks.

Format: deadline description /by yyyy-mm-dd HHmm



Example: `deadline assignment /by 2025-09-19 2359`


```
expected output
Got it. I've added this task:
  [D][ ] assignment (by: Sep 19 2025, 11:59pm)
Now you have 1 tasks in the list.

```

## Adding Event

It adds a task Event to the list of tasks.

Format: event description /from yyyy-mm-dd HHmm /to yyyy-mm-dd HHmm

Example: `event meeting /from 2025-01-01 2359 /to 2025-01-02 2359`

```
expected output
Got it. I've added this task:
[E][ ] meeting (from: Jan 1 2025, 11:59pm to: Jan 2 2025, 11:59pm)
Now you have 2 tasks in the list.
```
## List tasks
It shows all the tasks currently in the list.
Format: list

Example: `list`

```
expected output
Here are the tasks in your list:
1. [D][ ] assignment (by: Sep 19 2025, 11:59pm)
2. [E][ ] meeting (from: Jan 1 2025, 11:59pm to: Jan 2 2025, 11:59pm)

```

## Mark tasks as done

It marks a task as done in the list.

Format: mark index

Example: `mark 1`

```
expected output
Nice! I've marked this task as done:
[X] assignment

```


## Unmark tasks as not done

It marks a task as not done in the list.

Format: unmark index

Example: `unmark 1`

```
expected output
OK, I've marked this task as not done yet:
[ ] assignment
```

## Adding a Todo task
It adds a task to be done in the list.

Format: todo description

Example: `todo study`


```
expected output
Got it. I've added this task:
  [T][ ] study
Now you have 3 tasks in the list.

```

## Delete a task

It deletes a task from the list

Format: delete index 

Example :`delete 1`

```
expected output
Noted. I've removed this task:
  [D][ ] assignment (by: Sep 19 2025, 11:59pm)
Now you have 2 tasks in the list.
```

## Find a task
It finds a task in the list that matches the description

Format: find description

Example: `find meeting `

```
expected output
Here are the matching tasks in your list:
1. [E][ ] meeting (from: Jan 1 2025, 11:59pm to: Jan 2 2025, 11:59pm)
```

## Finding the nearest free SLot
It finds the nearest free time slot for the time period required

Format: free time in hours

Example: `free 4`

```
expected output
Nearest free 4h slot: 2025-09-19 13:26 to 2025-09-19 17:26
```



## Quitting Geni

Just type bye

Closes the chatbot!