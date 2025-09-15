# Butler — User Guide

> “Your mind is for having ideas, not holding them.” – David Allen


## Overview

Butler is a tiny, chat-based task manager. You can add todos, deadlines, and events; list, find, mark, unmark, and delete tasks — all via simple commands. Your tasks are saved to disk and reloaded on startup.


## Quick Start

1. Launch the app.

2. Type a command into the input field.

3. Butler replies with the result.


### Date & Time Formats

- **Date:** `yyyy-MM-dd` (e.g., `2019-10-15`)

- **DateTime (any of these):**

    - `yyyy-MM-ddTHH:mm` (e.g., `2019-10-15T18:00`)

    - `yyyy-MM-dd HH:mm` (e.g., `2019-10-15 18:00`)

    - `yyyy-MM-dd HHmm` (e.g., `2019-10-15 1800`)


## Command Reference

### Add todo

    todo <description>


### Add deadline

    deadline <description> /by <yyyy-MM-dd>


### Add event

    event <description> /from <start> /to <end>

Start/end accept the supported DateTime formats above.


### List tasks

    list


### Find tasks (case-sensitive substring)

    find <keyword>


### Mark / Unmark

    mark <index>
    unmark <index>


### Delete

    delete <index>


### Exit

    bye


## Examples

### Adding a todo

**Command**

    todo read book

**Expected output**

    Got it. I've added this task:
       [T][ ] read book
    Now you have 1 tasks in the list.


### Adding a deadline

**Command**

    deadline return book /by 2019-10-15

**Expected output**

    Got it. I've added this task:
       [D][ ] return book (by: Oct 15 2019)
    Now you have 2 tasks in the list.


### Adding an event

**Command**

    event project meeting /from 2019-12-02 14:00 /to 2019-12-02 16:00

**Expected output**

    Got it. I've added this task:
       [E][ ] project meeting (from: Dec 2 2019 14:00 to: Dec 2 2019 16:00)
    Now you have 3 tasks in the list.


### Listing tasks

**Command**

    list

**Expected output**

    Here are the tasks in your list:
     1.[T][ ] read book
     2.[D][ ] return book (by: Oct 15 2019)
     3.[E][ ] project meeting (from: Dec 2 2019 14:00 to: Dec 2 2019 16:00)


### Finding by keyword

**Command**

    find book

**Expected output**

    Here are the matching tasks in your list:
     1.[T][ ] read book


### Marking & Unmarking

**Commands**

    mark 1
    unmark 1

**Expected outputs**

    Nice! I've marked this task as done:
       [T][X] read book

<!---->

    OK, I've marked this task as not done yet:
       [T][ ] read book


### Deleting

**Command**

    delete 2

**Expected output**

    Noted. I've removed this task:
       [D][ ] return book (by: Oct 15 2019)
    Now you have 2 tasks in the list.


### Exiting

**Command**

    bye

**Expected output**

    Bye. Hope to see you again soon!


## Common Error Messages

- ⚠ Sorry, I don't recognize that command.

- ⚠ Please tell me what the todo is about.

- ⚠ A deadline needs a '/by \<date>' part (yyyy-MM-dd).

- ⚠ Event description cannot be empty.

- ⚠ Please include the end time using '/to \<end>'.

- ⚠ Please use date format yyyy-MM-dd (e.g., 2019-10-15).

- ⚠ Please use datetime format 'yyyy-MM-dd HHmm' or ISO 'yyyy-MM-ddTHH:mm'.


## Data Persistence

Tasks are saved to a text file and reloaded on startup. Closing the app is safe — your list will be restored next run.


## FAQ

**Are commands case-sensitive?** Yes. Use lowercase command words as shown (e.g., `todo`, `deadline`, `event`).\
**Do find results change the list?** No. It only shows matches; it does not filter or delete tasks.
