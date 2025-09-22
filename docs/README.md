# Wowo User Guide

![Wowo GUI overview](Ui.png)

Wowo is a lightweight task manager with a chat-style UI. You can add Todos, Deadlines, and
Events; mark or unmark them; search, sort, and delete. Data is saved to `data/wowo.txt`
so your list persists between runs.

---

## Adding deadlines

**What it does**

Adds a task that has a due date. Wowo accepts dates in any of these formats:

- `yyyy-MM-dd` (e.g., `2025-10-05`)
- `d/M/yyyy`   (e.g., `5/10/2025`)
- `dd/M/yyyy`  (e.g., `05/10/2025`)

**Syntax**

deadline  /by 

**Examples**

deadline CS2103 iP /by 2025-10-05
deadline Pay rent /by 5/10/2025

**Expected output**

Noted. I’ve added:
[D][ ] CS2103 iP (by: 2025-10-05)
You have 1 tasks. Must do them all

---

## Other common actions

### Add a todo

**Syntax**

todo

**Example**

todo Buy milk

**Expected output**

Noted. I’ve added:
[T][ ] Buy milk
You have 2 tasks. Must do them all

---

### Add an event

**What it does**

Adds a task with a start and end date.

**Syntax**

event  /from  /to 

**Example**

event Recess trip /from 12/10/2025 /to 15/10/2025

**Expected output**

Noted. I’ve added:
[E][ ] Recess trip (from: 2025-10-12 to: 2025-10-15)
You have 3 tasks. Must do them all

---

### List tasks

**Syntax**

list

**Expected output**

Here are your tasks:
1.	[T][ ] Buy milk
2.	[D][ ] CS2103 iP (by: 2025-10-05)
3.	[E][ ] Recess trip (from: 2025-10-12 to: 2025-10-15)

---

### Mark / Unmark

**Syntax**

mark <1-based index>
unmark <1-based index>

**Example**

mark 2

**Expected output**

Good! Now go back to work, I’ve marked:
[D][X] CS2103 iP (by: 2025-10-05)

---

### Delete

**Syntax**

delete <1-based index>

**Example**

delete 1

**Expected output**

Noted. I’ve removed this task:
[T][ ] Buy milk
Now you have 2 tasks in the list.

---

### Find

**What it does**

Shows tasks whose description contains the keyword (case-insensitive).

**Syntax**

find 

**Example**

find trip

**Expected output**

Here are the matching tasks in your list:
1.	[E][ ] Recess trip (from: 2025-10-12 to: 2025-10-15)

---

### Sort (optional feature)

**What it does**

Sorts tasks by name (lexicographically) and date.

**Syntax**

sort

**Expected output**

Sorted your tasks by name and date.

---

## Saving the data

Your tasks are stored in `data/wowo.txt`. The file is created automatically if it does not
exist. You can back it up or copy it across machines to migrate your task list.

---

## Exiting

bye

Wowo will save your data and close.