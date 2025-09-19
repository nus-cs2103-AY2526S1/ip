# Abang User Guide

![Abang UI](Ui.png)

_A simple task management chatbot for the command line._

Abang helps you keep track of your **todos**, **deadlines**, and **events**.  
You can add tasks, mark them as done, delete them, search for them, and tag them for better organization.

---

## Quick Start

1. Ensure you have Java 11 or above installed.
2. Download the latest release of Abang from this repository.
3. Run:

   java -jar abang.jar

4. You should see a welcome message. Type commands to interact with Abang.

---

## Features

### Adding Deadlines
Adds a task with a description and a deadline.

**Format**

    deadline DESCRIPTION /by TIME

**Accepted TIME formats (parsed by Abang)**  
Abang will try to parse `/by` using these patterns (in this order). If both fail, it keeps your text as-is:
- Date only: `d/M/yyyy`  (e.g., `21/9/2025`)
- Date + time: `d/M/yyyy HHmm`  (e.g., `21/9/2025 1800`)

When parsing succeeds, Abang **displays** the time as:
- `MMM dd yyyy` (e.g., `Sep 21 2025`) for date-only, or
- `MMM dd yyyy HH:mm` (e.g., `Sep 21 2025 18:00`) for date+time. :contentReference[oaicite:0]{index=0}

**Examples**

    deadline file taxes /by 21/9/2025
    deadline submit report /by 21/9/2025 1800
    deadline book flights /by Fri 6pm   ← kept as free text if not matching patterns

**Expected output**

    Got it. I've added this task:
      [D][ ] submit report (by: Sep 21 2025 18:00)
    Now you have 1 task in the list.

---

### Adding Todos
Adds a simple task without date or time.

**Format**

    todo DESCRIPTION

**Example**

    todo read book

**Expected output**

    Got it. I've added this task:
      [T][ ] read book
    Now you have 2 tasks in the list.

---

### Adding Events
Adds a task with a start and end time.

**Format**

    event DESCRIPTION /from START /to END

**Accepted START/END formats (parsed by Abang)**  
Abang tries each side (`START`, `END`) with the same rules as deadlines; otherwise it stores your text as-is:
- Date only: `d/M/yyyy`  (e.g., `21/9/2025`)
- Date + time: `d/M/yyyy HHmm`  (e.g., `21/9/2025 1400`)

When parsing succeeds, Abang **displays** times as:
- `MMM dd yyyy` for date-only, or
- `MMM dd yyyy HH:mm` for date+time. :contentReference[oaicite:1]{index=1}

**Examples**

    event team meeting /from 21/9/2025 1400 /to 21/9/2025 1530
    event sprint /from 21/9/2025 /to 25/9/2025
    event hackathon /from Sat 21 Sep 9am /to Sun 22 Sep 6pm   ← kept as free text if not matching patterns

**Expected output**

    Got it. I've added this task:
      [E][ ] team meeting (from: Sep 21 2025 14:00 to: Sep 21 2025 15:30)
    Now you have 3 tasks in the list.

---

### Listing Tasks
Shows all tasks in the list.

**Command**

    list

**Expected output**

    Here are the tasks in your list:
    1.[T][ ] read book
    2.[D][ ] submit report (by: Sep 21 2025 18:00)
    3.[E][ ] team meeting (from: Sep 21 2025 14:00 to: Sep 21 2025 15:30)

---

### Marking and Unmarking Tasks
Marks or unmarks a task as done.

**Format**

    mark INDEX
    unmark INDEX

**Example**

    mark 1

**Expected output**

    Nice! I've marked this task as done:
      [T][X] read book

---

### Deleting Tasks
Deletes a task from the list.

**Format**

    delete INDEX

**Example**

    delete 2

**Expected output**

    Noted. I've removed this task:
      [D][ ] submit report (by: Sep 21 2025 18:00)
    Now you have 2 tasks in the list.

---

### Finding Tasks
Finds tasks containing a keyword.

**Format**

    find KEYWORD

**Example**

    find meeting

**Expected output**

    Here are the matching tasks in your list:
    1.[E][ ] team meeting (from: Sep 21 2025 14:00 to: Sep 21 2025 15:30)

---

### Tagging Tasks
Tags a task with a label.

**Format**

    tag INDEX TAGNAME

**Example**

    tag 3 urgent

**Expected output**

    Got it. I've tagged this task:
      [E][ ] [#urgent] team meeting (from: Sep 21 2025 14:00 to: Sep 21 2025 15:30)

---

### Clearing the List
Removes all tasks from the list.

**Command**

    clear

**Expected output**

    All tasks have been cleared!

---

### Exiting the Program
Ends the session.

**Command**

    bye

**Expected output**

    Bye. Hope to see you again soon!

---


