# Phuc Task Manager

A sleek and efficient task management application that helps you stay organized with todos, deadlines, and events.

---

## 📸 Screenshot
![Phuc Task Manager Interface](Ui.png)  
*Main application interface showing task management features*

---

## 🚀 Quick Start

### Prerequisites
1. Java 17 or later
2. JavaFX 17 SDK (included in download)

### Installation
1. Download the latest version from the **[Releases Page](#)**.
2. Run the application:

```
# Method 1: Double-click the JAR file
# Method 2: Command line
java -jar Phuc.jar
```

### Using Phuc

```
# Command Line Interface
java -cp Phuc.jar phuc.Launcher

# Graphical Interface (default)
java -jar Phuc.jar
```

## 📋 Features

### Adding Tasks

#### 📝 Todo Tasks

Add simple tasks without date constraints.

**Format:**

```
todo <DESCRIPTION>
```

**Example**

```
todo Buy groceries
```

**Expected Output:**

```
________________________________________
Got it. I've added this task:
[T][ ] Buy groceries
Now you have 1 tasks in the list.
________________________________________
```

#### ⏰ Deadline Tasks

Add tasks with specific due dates.

**Format:**

```
deadline <DESCRIPTION> /by <DATE_TIME>
```

**Example**

```
deadline Submit report /by 25/12/2024 2359
```

**Expected Output:**

```
________________________________________
Got it. I've added this task:
[D][ ] Submit report (by: Dec 25 2024, 11:59PM)
Now you have 2 tasks in the list.
________________________________________
```

#### 📅 Event Tasks

Add events with start and end times.

**Format:**

```
event <DESCRIPTION> /from <START_DATE> /to <END_DATE>
```

**Example**

```
event Team meeting /from 25/12/2024 1000 /to 25/12/2024 1200
```

**Expected Output:**

```
________________________________________
Got it. I've added this task:
[E][ ] Team meeting (from: Dec 25 2024, 10:00AM to: Dec 25 2024, 12:00PM)
Now you have 3 tasks in the list.
________________________________________
```

### Managing Tasks

#### ✅ Mark Tasks as Done

Mark tasks as completed.

**Format:**

```
mark <TASK_NUMBER>
```

**Example**

```
mark 1
```

**Expected Output:**

```
________________________________________
Nice! I've marked this task as done:
[T][X] Buy groceries
________________________________________
```

#### 🔄 Unmark Tasks

Mark tasks as not completed.

**Format:**

```
unmark <TASK_NUMBER>
```

**Example**

```
mark 1
```

**Expected Output:**

```
________________________________________
OK, I've marked this task as not done yet:
[T][ ] Buy groceries
________________________________________
```

#### 🗑️ Delete Tasks

Remove tasks from the list.

**Format:**

```
delete <TASK_NUMBER>
```

**Example**

```
delete 1
```

**Expected Output:**

```
________________________________________
Noted. I've removed this task:
[T][ ] Buy groceries
Now you have 2 tasks in the list.
________________________________________
```

### Viewing Tasks

#### 📋 List All Tasks

Display all tasks in your list.

**Format:**

```
list
```

**Expected Output:**

```
________________________________________
Here are the tasks in your list:
1. [T][   ] Buy groceries
2. [D][   ] Submit report (by: Dec 25 2024, 11:59PM)
3. [E][   ] Team meeting (from: Dec 25 2024, 10:00AM to: Dec 25 2024, 12:00PM)
________________________________________
```

#### 🔍 Find Tasks

Remove tasks from the list.

**Format:**

```
find <KEYWORD>
```

**Example**

```
find meeting
```

**Expected Output:**

```
________________________________________
Here are the matching tasks in your list:
1. [E][   ] Team meeting (from: Dec 25 2024, 10:00AM to: Dec 25 2024, 12:00PM)
________________________________________
```

#### 🔄 Sort Tasks

Sort tasks by date in ascending or descending order.

**Format:**

```
sort <DIRECTION>
```

**Example**

```
sort ascending
```

**Expected Output:**

```
________________________________________
Here are the sorted tasks in your list:
1. [E][   ] Team meeting (from: Dec 25 2024, 10:00AM to: Dec 25 2024, 12:00PM)
2. [D][   ] Submit report (by: Dec 25 2024, 11:59PM)
3. [T][   ] Buy groceries
________________________________________
```

### Application Commands

#### ❓ Help

Display all available commands.

**Format:**

```
help
```

**Expected Output:**

```
________________________________________
PHUC COMMAND HELP
=================

LIST MANAGEMENT:
• list           - Show all tasks
• sort ascending - Sort tasks by date (oldest first)
• sort descending- Sort tasks by date (newest first)
• find <keyword> - Search for tasks

TASK OPERATIONS:
• mark <number>  - Mark task as done
• unmark <number>- Mark task as not done
• delete <number>- Remove a task

ADD TASKS:
• todo <description>
      - Add a simple todo task
      Example: todo Buy milk

• deadline <description> /by <date>
      - Add task with deadline
      Example: deadline Submit report /by 25/12/2024 2359

• event <description> /from <date> /to <date>
      - Add event with time range
      Example: event Meeting /from 25/12/2024 1000 /to 25/12/2024 1200

DATE FORMATS:
25/12/2024 2359, 25-12-2024 2359, 2024-12-25 2359, 25/12/2024

APPLICATION:
• help - Show this help message
• bye  - Exit the application
________________________________________
```

#### 👋 Exit

Exit the application.

**Format:**

```
bye
```

**Expected Output:**

```
________________________________________
 Bye. Hope to see you again soon!
________________________________________
```

## 📅 Date Formats

Phuc supports multiple date formats:

- **25/12/2024 2359**

- **25-12-2024 2359**

- **2024-12-25 2359**

- **25 12 2024 2359**

- **25/12/2024 (time defaults to 0000)**

<div align="center">
Enjoy using Phuc! 🎯

</div>