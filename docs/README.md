# Evansbot User Guide

![Sample image of Chatbot](Ui.png)

EvansBot is your friendly personal chatbot for managing tasks, events, and deadlines. Chat with EvansBot to add, view, mark, delete, and find tasks in a simple chat-style interface.

## Adding deadlines

Deadlines are tasks with a specific due date.

The Command should be written in a format: `deadline <task description> /by <date>`

Example: `deadline Finish assignment /by 2025-09-30`

The expected output on a clear list would look something like this: 

```
Got it. I have added this task:
[D][ ] Finish assignment (by: Sep 30 2025)
Now you have 1 tasks in the list.
```

## Adding todos

Todos are tasks without a specific date or time.

The Command should be written in a format: `todo <task description>`

Example: `todo Buy groceries`

The expected output on a clear list would look like this:
```
Got it. I have added this task:
[T][ ] Buy groceries
Now you have 2 tasks in the list.
```

## Adding events

Events are tasks that occur at a specific time.

The Command should be written in a format: `event <task description> /from <date/time> /to <date/time`

Example: `event Team meeting /from 2025-09-20 14:00 /to 2025-09-20 16:00`

The expected output on a clear list would look like this: 
```
Got it. I have added this event:
[E][ ] Team meeting (from: Sep 20 2025 14:00, to: Sep 20 2025 16:00)
Now you have 3 tasks in the list.
```

## Listing all tasks

The Command should be written in a format: `list`

Example: `list`

The expected output would show all tasks in the list: 
```
Here are the tasks in your list:
1.[T][ ] Buy groceries
2.[D][ ] Finish assignment (by: Sep 30 2025)
3.[E][ ] Team meeting (at: Sep 20 2025 14:00)
```


## Marking a task as done

The Command should be written in a format: `mark <task number>`

Example: `mark 2`

The expected output would look like this: 
```
Good Job! I have marked this task as done:
[D][X] Finish assignment (by: Sep 30 2025)
```

## Unmarking a task

The Command should be written in a format: `unmark <task number>`

Example: `unmark 2`

The expected output would look like this: 
```angular2html
Oh no! I have unmarked this task for now:
[D][ ] Finish assignment (by: Sep 30 2025)
```

## Deleting a task

The Command should be written in a format: `delete <task number>`

Example: `delete 3`

The expected output would look like this: 
```angular2html
Okay! I will remove this task:
[E][ ] Team meeting (at: Sep 20 2025 14:00)
Now you have 2 tasks in the list.
```

## Finding tasks by keyword

The Command should be written in a format: `find <keyword>`

Example: `find assignment`

The expected output would look like this: 
```
Here are the matching tasks in your list:
1.[D][ ] Finish assignment (by: Sep 30 2025)
```
## Viewing tasks by date

The Command should be written in a format: `view <date>`

Example: `view 2025-09-20`

The expected output would look like this: 
```angular2html
Here are your tasks on Sep 20 2025:
1.[E][ ] Team meeting (from: Sep 20 2025 14:00, to: Sep 20 2025 16:00)
```
## Greeting

The Command should be written in a format: `greet`

Example: `greet`

The expected output would look like this: 
```angular2html
Hello! I’m EvansBot. 
How can I help you today?
```
## Exiting the program

The Command should be written in a format: `bye`

Example: `bye`

The expected output would look like this:
```angular2html
Bye. Hope to see you again soon!
```
