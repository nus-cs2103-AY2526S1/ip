# Alpaca User Guide 🦙

![Alpaca Screenshot](Ui.png)
<!-- Add your actual screenshot here -->

**Alpaca** is your personal task management assistant that helps you organize and track your todos, deadlines, events, and tasks within specific periods. With a simple and intuitive interface, Alpaca makes it easy to stay on top of your responsibilities.

## Quick Start

1. Launch Alpaca
2. Type your commands in the input field
3. Press Enter or click Send
4. Alpaca will respond with confirmation and updates

## Features

### Adding Todo Tasks

Add simple tasks that need to be completed.

**Command:** `todo <description>`

**Example:** `todo read book`

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

### Adding Deadlines

Add tasks with specific due dates.

**Command:** `deadline <description> /by <yyyy-MM-dd>`

**Example:** `deadline submit assignment /by 2025-10-15`

```
Got it. I've added this task:
  [D][ ] submit assignment (by: Oct 15 2025)
Now you have 2 tasks in the list.
```

### Adding Events

Add events with start and end times or dates.

**Command:** `event <description> /from <start> /to <end>`

**Example:** `event team meeting /from 2025-09-30 14:00 /to 2025-09-30 16:00`

```
Got it. I've added this task:
  [E][ ] team meeting (from: Sep 30 2025 2:00 PM to: Sep 30 2025 4:00 PM)
Now you have 3 tasks in the list.
```

### Adding Tasks Within a Period

Add tasks that need to be completed within a specific time period.

**Command:** `within <description> /from <start-date> /to <end-date>`

**Example:** `within finish project /from 2025-09-20 /to 2025-09-30`

```
Got it. I've added this task:
  [W][ ] finish project (within: Sep 20 2025 to Sep 30 2025)
Now you have 4 tasks in the list.
```

### Viewing All Tasks

Display all your current tasks.

**Command:** `list`

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit assignment (by: Oct 15 2025)
3. [E][ ] team meeting (from: Sep 30 2025 2:00 PM to: Sep 30 2025 4:00 PM)
4. [W][ ] finish project (within: Sep 20 2025 to Sep 30 2025)
```

### Marking Tasks as Done

Mark completed tasks with a checkmark.

**Command:** `mark <task number>`

**Example:** `mark 1`

```
Nice! I've marked this task as done:
  [T][X] read book
```

### Unmarking Tasks

Remove the completion mark from tasks.

**Command:** `unmark <task number>`

**Example:** `unmark 1`

```
OK, I've marked this task as not done yet:
  [T][ ] read book
```

### Deleting Tasks

Remove tasks from your list permanently.

**Command:** `delete <task number>`

**Example:** `delete 2`

```
Noted. I've removed this task:
  [D][ ] submit assignment (by: Oct 15 2025)
Now you have 3 tasks in the list.
```

### Finding Tasks

Search for tasks containing specific keywords.

**Command:** `find <keyword>`

**Example:** `find meeting`

```
Here are the matching tasks in your list:
1. [E][ ] team meeting (from: Sep 30 2025 2:00 PM to: Sep 30 2025 4:00 PM)
```

### Getting Help

Display available commands and their formats.

**Command:** `help`

```
Available commands:
1. todo <description> - Add a todo task
2. deadline <description> /by <yyyy-MM-dd> - Add a deadline task
3. event <description> /from <start> /to <end> - Add an event
4. within <description> /from <start> /to <end> - Add a task within a period
5. list - List all tasks
6. mark <task number> - Mark a task as done
7. unmark <task number> - Mark a task as not done
8. delete <task number> - Delete a task
9. find <keyword> - Find tasks containing keyword
10. help - Show this help message
11. bye - Exit the app
```

### Exiting the Application

Close Alpaca safely.

**Command:** `bye`

```
Bye. Hope to see you again soon!
```

## Task Status Indicators

- `[T]` - Todo task
- `[D]` - Deadline task
- `[E]` - Event task
- `[W]` - Within period task
- `[ ]` - Not completed
- `[X]` - Completed

## Date Format

All dates should be entered in `yyyy-MM-dd` format (e.g., `2025-12-31`).

For events with specific times, you can include time in `HH:mm` format (e.g., `2025-12-31 14:30`).

## Tips

- Task numbers in commands refer to the position shown in the `list` command
- Use descriptive task names for better organization
- The `find` command searches through task descriptions
- All your tasks are automatically saved and will persist between sessions

## Troubleshooting

### Common Error Messages

**"Invalid input: Todo description cannot be empty."**
- Make sure to provide a description for your todo task
- Example: `todo read book` ✅, not just `todo` ❌

**"Invalid input: Deadline format: deadline <description> /by <yyyy-MM-dd>"**
- Check that you're using the correct format with `/by`
- Example: `deadline assignment /by 2025-10-15` ✅

**"Invalid input: Event format: event <description> /from <start> /to <end>"**
- Ensure you include both `/from` and `/to` parts
- Example: `event meeting /from 2025-09-30 /to 2025-09-30` ✅

**"An unexpected error occurred while adding the task."**
- Check your date format (should be yyyy-MM-dd)
- Ensure all required parts are included in your command