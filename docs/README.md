# CC User Guide

Imagine having a cute _wombat_ helping you track your tasks? CC bot got it.


CC bot supports these functions:

## todo t/TASK

Adds a _"todo"_ task to your tasklist. 

1. Todo task has no deadline, no start, free and easy.
2. Once added successfully, CC tells you how many tasks you have right now, also the entry of the task, with its type and completion status. 

Example: 

```
todo run

```


## deadline t/TASK, by d/DATE

Adds a _"deadline"_ task to your tasklist. 

1. Deadline task has a deadline, no start. 
2. Various date formats are supported,such as DD-MM-YYYY, DD/MM/YYYY, but not alphabetical input, such as "12 Dec 2025". 
3. Inputs for date and month, even if they are represented by 1 digit, should be 2 digits long, such as "02-20-2025".
4. Once added successfully, CC tells you how many tasks you have right now, also the entry of the task, with its type and completion status. 
5. If invalid date format is provided, CC asks you "Where got time?"

Example: 

```
deadline more run, by 19/9/2025

```


## event t/TASK, from d/DATE, to d/DATE

Adds a _"event"_ task to your tasklist. 

1. Event task has a deadline and a start.
2. Various date formats are supported,  such as DD-MM-YYYY, DD/MM/YYYY, but not alphabetical input, such as "12 Dec 2025".
3. Once added successfully, CC tells you how many tasks you have right now, also the entry of the task, with its type and completion status. 
4. If invalid date format is provided, CC asks you "Where got time?"

Example: 

```
evnet more more run, from 2-12-2025, to 3/12/2025

```

## list

"list" returns all the task in your tasklist at the moment. 

1. Each task's type, and completion status is reflected. 
2. If you have added a priority to the task, it is also reflected.

Example: 

```
:list
1. [T][X][P=1] run
2. [E][ ] more run(from Feb 02 2024 to Mar 03 2025)

```

## delete n/NUMBER

Deletes the task at serial number NUMBER in the tasklist.

Example: 

```
:list
1. [T][X][P=1] run
2. [E][ ] more run(from Feb 02 2024 to Mar 03 2025)

:delete 2
magic magic it's gone:
[E][ ] more run(from Feb 02 2024 to Mar 03 2025)
1 task left

```

## mark n/NUMBER & unmark n/NUMBER

Mark and unmark toggle the completion status of each task. 

1. Tasks are identified by the serial number they are given in the tasklist.

Example: 

```
:mark 1
Nice! You got it boss:
[T][X] run

:unmark 1
Fine get it done soon:
[T][] run

```

## find w/WORD

Lists all tasks including this word. 

1. Serial numbers are according to their numbers in the list, so you can use the serial number in unmark, mark directly.

Example: 
```
:list 
1. [T][X] run
2. [T][X] eat
3. [T][X] run

:find run
1. [T][X] run
3. [T][X] run	//index follows that in the list

```

## priority n/SERIAL_NUMBER p/PRIORITY

Adds a priority to your task. 

Example: 
```
:list 
1. [T][X] run

:priority 1 1
[T][][P=1] run

```

## Known issues
1. When the command is not handled but also does not create an exception, CC would return an empty line. If this happens, just input your next command.


