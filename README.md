# Chatbot9000

> A lightweight JavaFX task chatbot. Add todos, deadlines, and events; list, find, mark/unmark, delete, save — all from a simple chat UI.
 
![Ui.png](Ui.png)

## Quick Start

Requirements

- Java 17+ (or the version your project uses)
- Gradle (wrapper included)

**Run**

```angular2html
./gradlew run
```
The app window title should show Chatbot9000.

## Command Summary

| Command      | Format                                  | What it does                                  |
| ------------ | --------------------------------------- | --------------------------------------------- |
| **todo**     | `todo DESCRIPTION`                      | Adds a todo task.                             |
| **deadline** | `deadline DESCRIPTION /by DATETIME`     | Adds a deadline.                              |
| **event**    | `event DESCRIPTION /from START /to END` | Adds an event (free-text times).              |
| **list**     | `list`                                  | Lists all tasks with indexes.                 |
| **mark**     | `mark INDEX`                            | Marks a task as done.                         |
| **unmark**   | `unmark INDEX`                          | Marks a task as not done.                     |
| **delete**   | `delete INDEX`                          | Deletes a task.                               |
| **find**     | `find KEYWORD`                          | Filters tasks containing the keyword.         |
| **save**     | `save`                                  | Saves tasks to local storage.                 |
| **reset**    | `reset`                                 | Clears the in-memory task list (fresh start). |
| **help**     | `help`                                  | Shows help summary.                           |
| **bye**      | `bye`                                   | Exits the app.                                |

> Indexes are 1-based as shown by list.

## Adding Todos

**Usage**

```todo finish homework```

**Example output**

```
Added: [T][ ] Finish homework
You now have 1 task in the list.
```

## Adding Deadlines

**Usage**

```deadline Submit report /by 2019-12-02 1800```

Accepted date/time formats

- yyyy-mm-dd 
- yyyy-mm-dd HHmm 
- dd-mm-yyyy 
- dd-mm-yyyy HHmm

For example: 2019-12-02 1800 or 02-12-2019 1800.

**Example output**

```
Added: [D][ ] Submit report by: 2nd Dec 2019 1800
You now have 2 tasks in the list.
```

If the date/time cannot be parsed, you’ll see:

```angular2html
Invalid date/time. Use one of: yyyy-mm-dd, yyyy-mm-dd HHmm, dd-mm-yyyy, dd-mm-yyyy HHmm (e.g., 2019-12-02 1800 or 02-12-2019 1800).
```

## Adding Events

Events use plain strings for times (no date parsing).

**Usage**

```event Chill session /from now /to later```

**Example output**

```
Added: [E][ ] Chill session from: now to: later
You now have 3 tasks in the list.
```

## Listing Tasks

**Usage**

```list```

**Example output**

```
1. [T][ ] Finish homework
2. [D][ ] Submit report by: 2019-12-02 1800
3. [E][ ] Chill session from: now to: later
```
## Marking/Unmarking

**Usage**

```
mark 1
unmark 1
```

**Example output**

```
Okay, I've marked this task as completed:
[T][X] Finish homework
Okay, I've marked this task as not completed:
[T][ ] Finish homework
```
## Deleting

**Usage**

```delete 1```

**Example output**

```
Deleting this task:
[T][X] Finish homework
```

## Finding

**Usage**

```Find homework```

**Example output**

```
Here are the matching tasks in your list:
1. [T][X] Finish homework
```

## Saving

**Usage**

```save```

**Example output**

```
Tasks have been successfully saved!
```

## Resetting

**Usage**

```reset```

**Example output**

```
reset completed
```

## Help

**Usage**

```help```

**Example output**

```
Here are the commands you can use BEEP B00P:
...
...
...
```

## Exit

**Usage**

```bye```

**Example output**

```
bye! BEEP B00P BEEP B00P    
```
