# Buddy User Guide

![Buddy GUI (full window)](Ui.png)

Buddy is your very own personal task assistant. 

It supports quick commands and short aliases (e.g., t for todo). 
This guide shows how to use each feature with examples and expected outputs.

## Quick Start

* Launch the app and type commands into the input box at the bottom.
* Use `bye`  or its alias `b` to exit.

## Adding todos

Create a simple task with just a description.

**Format**

```
todo <description>
```

Alias: `t`

**Example**

```
t borrow book
```

**Expected outcome**

```
Got it. I've added this task:
  [T][ ] borrow book
Now you have 5 tasks in the list.
```

---


## Adding deadlines

Add a task that has a due date/time.

**Format**

```
deadline <description> /by <date or time>
```

Alias: `dl`

**Examples**

```
deadline submit CS2103 ip /by 19/09/2025
```

**Expected outcome**

```
Got it. I've added this task:
  [D][ ] submit CS2103 ip (by: 19/09/2025)
Now you have 5 tasks in the list.
```
---

## Adding events

Add a task that spans a time range.

**Format**

```
event <description> /from <start> /to <end>
```

Alias: `e`

**Example**

```
event project meeting /from Mon 10am /to Mon 11am
```

**Expected outcome**

```
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 10am to: Mon 11am)
Now you have 7 tasks in the list.
```
---

## Listing tasks

Show all tasks with their indexes.

**Format**

```
list
```

Alias: `ls`

**Expected outcome**

```
1.[T][ ] borrow book
2.[D][ ] submit CS2103 ip (by: 19/09/2025)
3.[E][ ] project meeting (from: Mon 10am to: Mon 11am)
```

---

## Marking / Unmarking tasks

Mark a task as done or not done (use the **task number** from `list`).

**Formats**

```
mark <taskNumber>      
unmark <taskNumber>    
```
Alias: `m` for mark and `um` for unmark

**Examples**

```
m 2
um 2
```

**Expected outcomes**

```
Nice! I've marked this task as done:
  [D][X] submit CS2103 ip (by: 19/09/2025)
```

```
OK, I've marked this task as not done yet:
  [D][ ] submit CS2103 ip (by: 19/09/2025)
```
---

## Deleting tasks

Remove a task by its number.

**Format**

```
delete <taskNumber>
```

Alias: `rm`

**Example**

```
rm 3
```

**Expected outcome**

```
Noted. I've removed this task:
  [E][ ] project meeting (from: Mon 10am to: Mon 11am)
Now you have 6 tasks in the list.
```

---

## Finding tasks

Search tasks by a keyword (case-insensitive substring).

**Format**

```
find <keyword>
```

Alias: `f`

**Example**

```
f CS2103
```

**Expected outcome**

```
Here are the matching tasks in your list:
1. [D][ ] submit CS2103 ip (by: 19/09/2025)
```

---

## Exiting

Close the application.

**Format**

```
bye
```

Alias: `b`

**Expected outcome**

```
Bye. Hope to see you again soon!
```
* Tasks are saved so that it can be loaded the next time you open Buddy



---