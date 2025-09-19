# Zell User Guide


![Screenshot of Zell](./Ui.png)


### Zell is a chatbot designed to improve the life of its users. It is a task manager based chatbot which help users keep track of their task. The best part of it is that it is **FREE**.

# Setup
Prerequisite: Ensure you have Java 17 installed on your Computer

1. Download the latest `.jar` file from [here](https://github.com/zisaac99/ip/releases).

2. Open a terminal and `cd` into the folder the jar file is in.

3. Run the application using `java -jar zell.jar`

4. Refer below for all the commands you can use. You just need to type the valid commands in the box and click send or press enter to execute it.

# Features
## List all tasks: `list` or `l`
Shows a list of all of your tasks in the task list

Usage: `list` or `l`

Example:
```
list
____________________________________________________________
Currently you have added this tasks to your list:
1. [E][] go to bookstore (from: Aug 27 2025 02:00 pm to: Aug 29 2025 03:00 pm)
2. [E][] play games (from: Sep 8 2025 to: Sep 11 2025 05:00 pm)
3. [E][] run (from: Sep 11 2025 to: Sep 12 2025)
4. [T][] go jog
____________________________________________________________
```

```
l
____________________________________________________________
Currently you have added this tasks to your list:
1. [E][] go to bookstore (from: Aug 27 2025 02:00 pm to: Aug 29 2025 03:00 pm)
2. [E][] play games (from: Sep 8 2025 to: Sep 11 2025 05:00 pm)
3. [E][] run (from: Sep 11 2025 to: Sep 12 2025)
4. [T][] go jog
____________________________________________________________
```

## Adding a todo task: `todo` or `t` 
Adds a new todo task to the task list

Usage: `todo {task name}` or `t {task name}`

Examples:
```
todo do homework
____________________________________________________________
Noted. The following task has been added: 
 [T][] do homework
There are currently 1 task in the list.
____________________________________________________________
```

```
t do homework
____________________________________________________________
Noted. The following task has been added: 
 [T][] do homework
There are currently 1 task in the list.
____________________________________________________________
```

## Adding a deadline task: `deadline` or `d`
Adds a new todo task to the task list

Usage: `deadline {task name} /by {datetime of due}` or `d {task name} /by {datetime of due}`


> Datetime should be in the following format: yyyy-MM-dd or yyyy-MM-dd HH:mm

Examples:
```
deadline buy book /by 2025-09-01 18:30
____________________________________________________________
Noted. The following task has been added: 
 [D][] buy book (by: Sep 1 2025 06:30 pm) 
There are currently 2 task in the list.
____________________________________________________________
```

```
d buy book /by 2025-09-01 18:30
____________________________________________________________
Noted. The following task has been added: 
 [D][] buy book (by: Sep 1 2025 06:30 pm) 
There are currently 2 task in the list.
____________________________________________________________
```

## Adding a event task: `event` or `e`
Adds a new todo task to the task list

Usage: `event {task name} /from {datetime of start} /to {datetime of end}` <br> or `event {task name} /from {datetime of start} /to {datetime of end}`


> Datetime should be in the following format: yyyy-MM-dd or yyyy-MM-dd HH:mm

Examples:
```
event play games /from 2025-09-17 14:30 /to 2025-09-17 18:30
____________________________________________________________
Noted. The following task has been added: 
 [E][] play games (from: Sep 17 2025 02:30 pm to: Sep 17 2025 06:30 pm)
There are currently 3 task in the list.
____________________________________________________________
```

```
e play games /from 2025-09-17 14:30 /to 2025-09-17 18:30
____________________________________________________________
Noted. The following task has been added: 
 [E][] play games (from: Sep 17 2025 02:30 pm to: Sep 17 2025 06:30 pm)
There are currently 3 task in the list.
____________________________________________________________
```

## Mark a task: `mark` or `m`
Marks a task as done

Usage: `mark {valid task number}` or `m {valid task number}`

Examples:
```
mark 2
____________________________________________________________
Nice! I've marked this task as done: 
 [E][X] play games (from: Sep 8 2025 to: Sep 11 2025 05:00 pm)
____________________________________________________________
```

```
m 2
____________________________________________________________
Nice! I've marked this task as done: 
 [E][X] play games (from: Sep 8 2025 to: Sep 11 2025 05:00 pm)
____________________________________________________________
```

## Unmark a task: `unmark` or `un`
Unmarks a task as not done

Usage: `unmark {valid task number}` or `un {valid task number}`

Examples:
```
unmark 2
____________________________________________________________
OK, I've marked this task as not done yet: 
 [E][] play games (from: Sep 8 2025 to: Sep 11 2025 05:00 pm)
____________________________________________________________
```

```
un 2
____________________________________________________________
OK, I've marked this task as not done yet: 
 [E][] play games (from: Sep 8 2025 to: Sep 11 2025 05:00 pm)
____________________________________________________________
```

## Delete a task: `delete` or `del`
Deletes a task

Usage: `delete {valid task number}` or `del {valid task number}`

Examples:
```
delete 2
____________________________________________________________
Noted. The following task has been removed: 
 [T][] do homework
There are currently 3 task in the list.
____________________________________________________________
```

```
del 2
____________________________________________________________
Noted. The following task has been removed: 
 [T][] do homework
There are currently 3 task in the list.
____________________________________________________________
```

## Find a task: `find` or `f`
Finds task that matches the keywords

Usage: `find {keywords}` or `f {keywords}`

Examples:
```
find book
____________________________________________________________
Here are the matching tasks in your list: 
 1. [E][] go to bookstore (from: Aug 27 2025 02:00 pm to: Aug 29 2025 03:00 pm)
 2. [D][] buy book (by: Dec 1 2019 06:30 pm) 
 3. [D][] sellbook (by: Sep 1 2025 06:30 pm)
____________________________________________________________
```

```
f book
____________________________________________________________
Here are the matching tasks in your list: 
 1. [E][] go to bookstore (from: Aug 27 2025 02:00 pm to: Aug 29 2025 03:00 pm)
 2. [D][] buy book (by: Dec 1 2019 06:30 pm) 
 3. [D][] sellbook (by: Sep 1 2025 06:30 pm)
____________________________________________________________
```

## Exit chatbot application: `bye` or `b`
Exits the application

Usage: `bye` or `b`

Examples:
```
bye
____________________________________________________________
Application should auto close
____________________________________________________________
```

```
b
____________________________________________________________
Application should auto close
____________________________________________________________
```
