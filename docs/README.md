# Vicky User Guide

![img.png](Ui.png)

> "Your mind is for having ideas, not holding them. – David Allen ([source](https://dansilvestre.com/productivity-quotes))

Vicky helps free your mind of having to remember things you need to do. It's:
- text-based
- easy to learn
- ~~fast~~ SUPER FAST to use

To set up Vicky, you just need to:
1. download it from ([here](https://github.com/cleo7799/ip.git)).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 😸

And it's **FREE**! 💥
~~because unpaid labour~~

# Quick View
* Adding tasks:
  * Adding Todo task: `todo`
  * Adding Deadline task: `deadline`
  * Adding Event task: `event`
* Listing tasks: `list`
* Marking a task: `mark`
* Unmarking a task: `unmark`
* Deleting a task: `delete`
* Changing a task:
  * Changing a task's name: `change name`
  * Changing a task's end time: `change end`
  * Changing a task's start time: `change start`
  * Changing a task's start and end times: `change event`
* Finding a task by name: `find`
* Clearing all tasks: `clear`
* Getting Vicky to say "love": `love`
* Exiting: `bye`

# Features

## Listing tasks: `list`
Displays all tasks in the current task list, showing their completion status and details.

### Example Usage
```
list
```

### Expected Outputs
```
Here's your todo list:
[T][ ] Buy groceries
[D][X] Finish assignment (by: 20-9-2025 23:59)
[E][ ] CS2103T meeting (from 20-9-2025 15:00 to 20-9-2025 16:00)
```
---
## Adding todo task: `todo`
Creates a todo task and adds it to the current task list.

### Format
`todo NAME`

### Example Usage
```
todo fart at my dog
```

### Expected Outputs
```
Ok! I've added this task:
    [T] [] fart at my dog
Now you have 4 tasks in your list :)
```
---
## Adding deadline task: `deadline`
Creates a deadline task and adds it to the current task list.

### Format
`deadline NAME [/by DEADLINE]`
* `DEADLINE` must be in the format: `ddMMyyyy HHmm`.

### Example Usage
```
deadline sleep /by 22092025 2359
```

### Expected Outputs
```
Ok! I've added this task:
    [D] [] sleep (by: 22-09-2025 23:59)
Now you have 5 tasks in your list :)
```
---
## Adding event task: `event`
Creates an event task and adds it to the current task list.

### Format
`event NAME [/from START TIME] [/to END TIME]`
* `START TIME` and `END TIME` must be in the format: `ddMMyyyy HHmm`.

### Example Usage
```
event IS1108 Meeting /from 23092025 1400 /to 23092025 1600
```

### Expected Outputs
```
Ok! I've added this task:
    [E] [] IS1108 Meeting (from 23-09-2025 14:00 to 16:00)
Now you have 6 tasks in your list :)
```
---

## Marking a task: `mark`
Marks an existing task in the task list as completed.

### Format
`mark INDEX`

### Example Usage
```
mark 4
```

### Expected Output
```
YAY! I've marked this task as done:
    [T] [X] fart at my dog
```
---

## Unmarking a task: `unmark`
Marks an existing task in the task list as uncompleted.

### Format
`unmark INDEX`

### Example Usage
```
unmark 4
```

### Expected Outputs
```
OK, I've marked this task as not done yet:
    [T] [] fart at my dog
```
---

## Deleting a task: `delete`
Deletes an existing task in the task list.

### Format
`delete INDEX`

### Example Usage
```
delete 4
```

### Expected Outputs
```
Noted with many thanks! I've removed this task:
    [T] [] fart at my dog
You now have 5 tasks left!
```
---
## Changing a task's name: `change name`
Changes the name of an existing task in the task list.

### Format
`change name INDEX [/ NAME]`

### Example Usage
```
change name 6 / finish CS2100 Homework
```

### Expected Outputs
```
Ok I've changed your task:
    [T] [ ] finish CS2100 Homework
```
---
## Changing a task's end time: `change end`
Changes the end time of an existing task in the task list.

### Format
`change end INDEX [/ END TIME]`
* `END TIME` must be in the format: `ddMMyyyy HHmm`.
* If the task is an event, the new end time must be after the start time of the event task.

### Example Usage
```
change end 2 / 22092025 2359
```

### Expected Outputs
```
Ok I've changed your task:
    [D] [ ] finish ip (by: 22-09-2025 23:59)
```
---
## Changing a task's start time: `change start`
Changes the start time of an existing event task in the task list.

### Format
`change start INDEX [/ START TIME]`
* `START TIME` must be in the format: `ddMMyyyy HHmm`.
* The new start time must be before the event task's end time.
* The task at `INDEX` must be an event task.

### Example Usage
```
change start 5 / 23092025 1300
```

### Expected Outputs
```
Ok I've changed your task:
    [E] [ ] IS1108 Meeting (from 23-09-2025 13:00 to 16:00)
```
---
## Changing an event's start and end time: `change event`
Changes the start and end time of an existing event task in the task list.

### Format
`change event INDEX [/ START TIME] [/ END TIME]`
* `START TIME` and `END TIME` must be in the format: `ddMMyyyy HHmm`.
* The start time must be before the end time.
* The task at `INDEX` must be an event task.

### Example Usage
```
change event 7 / 23092025 0900 / 23092025 1000
```

### Expected Outputs
```
Ok I've changed your task:
   [E] [ ] shower (from 23-09-2025 09:00 to 10:00)
```
---

## Finding a task: `find`
Finds tasks whose names contain any of the given keywords.

### Format
`find KEYWORD(S)`

### Example Usage
```
find finish cs2100
```

### Expected Outputs
```
Found it! Here are the matching tasks in your list:
    1. [T] [ ] finish CS2100 homework
```
---

## Clearing all tasks: `clear`
Clears all tasks from the task list.

### Format
`clear`

---

## Making Vicky love something: `love`
Makes Vicky say she loves the given keyword.

### Format
`love KEYWORD(S)`

### Example Usage
```
love aaron tan
```

### Expected Outputs
```
Hehe I love aaron tan too pookie!!
```
---

## Exiting the program `bye`
Exits the program.

### Format
`bye`

---