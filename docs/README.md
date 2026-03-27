<<<<<<< HEAD
# Duke User Guide

// Update the title above to match the actual product name

// Product screenshot goes here

// Product intro goes here

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details
=======
# Borat User Guide

![Borat Chatbot](https://img.shields.io/badge/Version-1.0-blue.svg) ![Java](https://img.shields.io/badge/Java-17-orange.svg) ![Platform](https://img.shields.io/badge/Platform-Cross--Platform-green.svg)

Borat is a friendly task management chatbot that helps you organize your daily tasks, deadlines, and events. Whether you're a student juggling assignments or a professional managing projects, Borat is here to keep you on track!

## Quick Start

1. **Download** the `borat.jar` file
2. **Open** a terminal/command prompt
3. **Navigate** to the folder containing `borat.jar`
4. **Run** the command: `java -jar borat.jar`
5. **Start chatting** with Borat!

> **Note:** Make sure you have Java 17 or later installed on your computer.

## Features

### 📝 Adding Tasks

#### Todo Tasks
Add simple tasks without any time constraints.

**Command:** `todo <description>`

**Example:**
```
todo read book
```

**Expected Output:**
```
added: read book
Now you have 1 tasks in the list.
```

#### Deadline Tasks
Add tasks with specific due dates and times.

**Command:** `deadline <description> /by <date time>`

**Example:**
```
deadline submit assignment /by 2/12/2024 1800
```

**Expected Output:**
```
added: submit assignment
Now you have 2 tasks in the list.
```

#### Event Tasks
Add tasks that span a specific time period.

**Command:** `event <description> /from <start time> /to <end time>`

**Example:**
```
event team meeting /from 3/12/2024 1400 /to 3/12/2024 1500
```

**Expected Output:**
```
added: team meeting
Now you have 3 tasks in the list.
```

### 📋 Viewing Tasks

#### List All Tasks
See all your tasks in one organized list.

**Command:** `list`

**Expected Output:**
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit assignment (by: Dec 02 2024 18:00)
3.[E][ ] team meeting (from: Dec 03 2024 14:00 to: Dec 03 2024 15:00)
```

### ✅ Managing Tasks

#### Mark Tasks as Done
Mark completed tasks to track your progress.

**Command:** `mark <task number>`

**Example:**
```
mark 1
```

**Expected Output:**
```
Nice! I've marked this task as done:
 [T][X] read book
```

#### Unmark Tasks
Change your mind? Unmark completed tasks.

**Command:** `unmark <task number>`

**Example:**
```
unmark 1
```

**Expected Output:**
```
Ok, I've marked this task as not done yet:
 [T][ ] read book
```

#### Delete Tasks
Remove tasks you no longer need.

**Command:** `delete <task number>`

**Example:**
```
delete 2
```

**Expected Output:**
```
Noted. I've removed this task:
 [D][ ] submit assignment (by: Dec 02 2024 18:00)
Now you have 2 tasks in the list.
```

### 🔍 Finding Tasks

Search for specific tasks using keywords.

**Command:** `find <keyword>`

**Example:**
```
find book
```

**Expected Output:**
```
Here are the matching tasks in your list:
1.[T][ ] read book
```

### ⚠️ Schedule Clash Detection

Borat automatically detects scheduling conflicts to help you avoid double-booking!

**When adding events or deadlines, Borat will warn you if they clash with existing tasks:**

```
added: important meeting
Take note: schedule clash detected: "team meeting"
```

### 🚪 Exiting the Program

**Command:** `bye`

**Expected Output:**
```
Bye. Hope to see you again soon!
```

## Date and Time Format

When adding deadlines and events, use this format:
- **Format:** `d/M/yyyy HHmm`
- **Examples:**
  - `2/12/2024 1800` (2nd December 2024, 6:00 PM)
  - `15/1/2025 0900` (15th January 2025, 9:00 AM)

## Tips for Best Results

1. **Be specific** with task descriptions for easier searching
2. **Use the find feature** to quickly locate tasks
3. **Mark tasks as done** to track your progress
4. **Check for schedule clashes** when adding time-sensitive tasks
5. **Regularly review** your task list to stay organized

## Troubleshooting

### Common Issues

**Problem:** "I don't know what that means."
- **Solution:** Check your command spelling and format. Use the commands listed above.

**Problem:** "Invalid task number!"
- **Solution:** Use the `list` command to see valid task numbers.

**Problem:** "Please provide a valid number!"
- **Solution:** Make sure you're using a number (not text) for task operations.

**Problem:** GUI doesn't open
- **Solution:** Ensure you have Java 17+ installed and run from terminal: `java -jar borat.jar`

### Getting Help

If you encounter any issues:
1. Check that you're using the correct command format
2. Ensure Java 17+ is installed
3. Verify the JAR file is not corrupted
4. Try running from a different folder

## Data Storage

Your tasks are automatically saved to `data/tasks.txt` in the same folder as the JAR file. This file is created automatically and persists your tasks between sessions.

---

**Happy task managing with Borat! 🎉**
>>>>>>> 92aed1889172aa707c804a68c94cc466d8b28fd4
