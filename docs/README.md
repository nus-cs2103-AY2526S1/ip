# TaskBot


---

## Quick Start

1. Download the latest JAR file from the [Releases](https://github.com/h-virdi/ip/releases) section.
2. Open a terminal and navigate to the folder containing the JAR file.
3. Run:

   ```bash
   java -jar taskbot.jar
   ```
4. Start adding and managing your tasks! 

---

## Features

### 1. Add a Task

Adds a simple task to your list.

**Format:**

```
add DESCRIPTION
```

**Example:**

```
add read book
```

---

### 2. Add a Deadline

Adds a task with a deadline.

**Format:**

```
deadline DESCRIPTION /by DATE
```

**Example:**

```
deadline return book /by 2025-09-30
```

---

### 3. Add an Event

Adds an event with a start and end time.

**Format:**

```
event DESCRIPTION /from START_DATE /to END_DATE
```

**Example:**

```
event project meeting /from 2025-09-25 /to 2025-09-26
```

---

### 4. Mark/Unmark a Task

Marks a task as done or undone.

**Format:**

```
mark INDEX
unmark INDEX
```

**Example:**

```
mark 1
unmark 2
```

---

### 5. Delete a Task

Deletes a task from the list.

**Format:**

```
delete INDEX
```

**Example:**

```
delete 3
```

---

### 6. Update a Task

Updates the details of an existing task without deleting it.

**Format:**

```
update INDEX desc/NEW_DESCRIPTION
update INDEX by/NEW_DATE
update INDEX start/NEW_START_DATE end/NEW_END_DATE
```

**Examples:**

```
update 2 desc/return borrowed book
update 2 by/2025-10-01
update 3 start/2025-09-28 end/2025-09-29
```

---

### 7. Find a Task

Finds tasks containing the given keyword.

**Format:**

```
find KEYWORD
```

**Example:**

```
find book
```

---

### 8. List All Tasks

Displays all tasks in the list.

**Format:**

```
list
```

---

### 9. Exit

Exits TaskBot.

**Format:**

```
bye
```


---

##  Contributions

This project was enhanced with help from AI tools (ChatGPT, Windsurf) under the [A-AiAssisted](https://se-education.org/guides/tutorials/ai-assistance.html) increment.

---
