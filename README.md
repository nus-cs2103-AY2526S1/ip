# ✨ Mithrandir User Guide ✨

Mithrandir is your wise companion for managing tasks.
With his guidance, you shall command your to-dos, deadlines, and events.

> “All we have to decide is what to do with the time that is given us.”
> — *Gandalf the Grey*

---

## 📖 Table of Contents

1. [Adding Todos](#-adding-todos)
2. [Adding Deadlines](#-adding-deadlines)
3. [Adding Events](#-adding-events)
4. [Listing Tasks](#-listing-tasks)
5. [Marking Tasks as Done](#-marking-tasks-as-done)
6. [Unmarking Tasks](#-unmarking-tasks)
7. [Deleting Tasks](#-deleting-tasks)
8. [Archiving Tasks](#-archiving-tasks)
9. [Finding Tasks](#-finding-tasks)
10. [Exiting Mithrandir](#-exiting-mithrandir)

---

## 📝 Adding Todos

* **Action**: Create a task with only a description.
* **Usage**:

```
todo <description>
```

* **Example**:

```
todo read book
```

* **Expected Outcome**:

```
Added Todo: [T][ ] read book
A simple task, yet no less vital - it has been woven into your journey.
```

---

## ⏳ Adding Deadlines

* **Action**: Create a task with a description and a deadline.
* **Usage**:

```
deadline <description> /by <deadline>
```

* **Example**:

```
deadline return book /by 18/09/2025 12:00
```

* **Expected Outcome**:

```
Added Deadline: [D][ ] return book (by: 18/09/2025 12:00)
The hour is set, and the task must be done ere its appointed time.
```

---

## 📅 Adding Events

* **Action**: Create a task with a description and a start–end time.
* **Usage**:

```
event <description> /from <start time> /to <end time>
```

* **Example**:

```
event project meeting /from 18/09/2025 14:00 /to 18/09/2025 16:00
```

* **Expected Outcome**:

```
Added Event: [E][ ] project meeting (from: 18/09/2025 14:00 to: 18/09/2025 16:00)
Aye, lad - the event is written, and when its time comes, you shall be ready.
```

---

## 📋 Listing Tasks

* **Action**: Display all tasks currently in the list.
* **Usage**:

```
list
```

* **Expected Outcome**:

```
1. [T][ ] read book
2. [D][ ] return book (by: 18/09/2025 12:00)
3. [E][ ] project meeting (from: 18/09/2025 14:00 to: 18/09/2025 16:00)
Behold! Here are the labors you have set upon, laid plain before your eyes as stones upon the path.
```

---

## ✅ Marking Tasks as Done

* **Action**: Mark a task at the given index as completed.
* **Usage**:

```
mark <index>
```

* **Example**:

```
mark 1
```

* **Expected Outcome**:

```
Well done! The following task is deemed complete:
[T][x] read book
```

---

## ❌ Unmarking Tasks

* **Action**: Mark a task at the given index as not done.
* **Usage**:

```
unmark <index>
```

* **Example**:

```
unmark 1
```

* **Expected Outcome**:

```
Alas! It's the job that's never started as takes longest to finish. This task is marked undone:
[T][ ] read book
```

---

## 🗑️ Deleting Tasks

* **Action**: Remove a task from the list at the given index.
* **Usage**:

```
delete <index>
```

* **Example**:

```
delete 2
```

* **Expected Outcome**:

```
Removed task: [D][ ] return book (by: 18/09/2025 12:00)
The deed is struck from the scrolls of your labors, passing now into shadow and memory
```

---

## 📦 Archiving Tasks

* **Action**: Remove a task from the list and store it in the archive.
* **Usage**:

```
archive <index>
```

* **Example**:

```
archive 1
```

* **Expected Outcome**:

```
Archived task: [T][ ] read book
the task is now sealed away in the vaults of memory, never again to trouble your road ahead.
```

---

## 🔍 Finding Tasks

* **Action**: Search for tasks by keyword.
* **Usage**:

```
find <keyword>
```

* **Example**:

```
find meeting
```

* **Expected Outcome**:

```
Ah... so you seek among your tasks, do you?
Very well. I shall unveil what lies hidden in your list...
1. [E][ ] project meeting (from: 18/09/2025 14:00 to: 18/09/2025 16:00)
```

---

## 🧙 Exiting Mithrandir

* **Action**: Exit the application.
* **Usage**:

```
bye
```

* **Expected Outcome**:

```
Farewell. My work is now finished.
```
And the application exit itself after 3 seconds.

---

⚡ **Note**:

* Active tasks are stored persistently in `Save.txt`.
* Archived tasks are stored in `Archive.txt`.
* Indices are **1-based** when marking, deleting, or archiving.
* All commands are **case-insensitive**.

---