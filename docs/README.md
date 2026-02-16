# Mon User Guide

Mon is your personal task manager with a modern, chat-like interface. Manage your todos, deadlines, and events effortlessly through natural conversation with Mon, your AI assistant.

## Quick Start

1. Launch Mon application
2. Type your commands in the chat interface
3. Mon will respond with confirmations and help you stay organized
4. Use the welcome message as a reference for available commands

---

## Core Features

### Adding Todo Tasks

Add simple tasks to your list without any specific timing.

**Command:** `todo <description>`

**Example:** `todo buy groceries`

**Expected Output:**
```
Got it! I've added this task:
  [T][ ] buy groceries
Now you have 1 task in the list.
```

### Adding Deadlines

Create tasks with specific due dates to keep track of important deadlines.

**Command:** `deadline <description> /by <date>`

**Date Format:** `yyyy-MM-dd` (e.g., 2025-09-20)

**Example:** `deadline submit assignment /by 2025-09-20`

**Expected Output:**
```
Got it! I've added this task:
  [D][ ] submit assignment (by: Sep 20 2025)
Now you have 2 tasks in the list.
```

### Adding Events

Schedule events with start and end dates for better time management.

**Command:** `event <description> /from <start-date> /to <end-date>`

**Example:** `event team meeting /from 2025-09-20 /to 2025-09-20`

**Expected Output:**
```
Got it! I've added this task:
  [E][ ] team meeting (from: Sep 20 2025 to: Sep 20 2025)
Now you have 3 tasks in the list.
```

### Viewing All Tasks

Display your complete task list to see what needs to be done.

**Command:** `list`

**Expected Output:**
```
Here are the tasks in your list:
1. [T][ ] buy groceries
2. [D][ ] submit assignment (by: Sep 20 2025)
3. [E][ ] team meeting (from: Sep 20 2025 to: Sep 20 2025)
```

### Marking Tasks as Done

Mark completed tasks to track your progress.

**Command:** `mark <task-number>`

**Example:** `mark 1`

**Expected Output:**
```
Nice! I've marked this task as done:
  [T][X] buy groceries
```

### Unmarking Tasks

Unmark tasks if you need to work on them again.

**Command:** `unmark <task-number>`

**Example:** `unmark 1`

**Expected Output:**
```
OK, I've marked this task as not done yet:
  [T][ ] buy groceries
```

### Deleting Tasks

Remove tasks that are no longer needed.

**Command:** `delete <task-number>`

**Example:** `delete 2`

**Expected Output:**
```
Noted. I've removed this task:
  [D][ ] submit assignment (by: Sep 20 2025)
Now you have 2 tasks in the list.
```

### Finding Tasks

Search for specific tasks using keywords.

**Command:** `find <keyword>`

**Example:** `find meeting`

**Expected Output:**
```
Here are the matching tasks in your list:
1. [E][ ] team meeting (from: Sep 20 2025 to: Sep 20 2025)
```

### Exiting the Application

Close Mon when you're done managing your tasks.

**Command:** `bye`

**Expected Output:**
```
Bye! Hope to see you again soon!
```

---

## User Interface Features

### Modern Chat Design
- **Chat Bubbles**: Your messages appear in blue bubbles on the right, Mon's responses in white bubbles on the left
- **Professional Header**: Clean interface with app branding
- **Smooth Scrolling**: Easy navigation through your conversation history
- **Auto-focus**: Input field is ready for typing immediately

### User Experience Enhancements
- **Welcome Message**: Mon greets you with helpful command examples
- **Input Validation**: Empty messages are prevented from being sent
- **Responsive Design**: Interface adapts to your window size
- **Visual Feedback**: Hover effects and smooth animations

---

## Task Status Indicators

| Symbol | Task Type | Status |
|--------|-----------|--------|
| `[T][ ]` | Todo | Not done |
| `[T][X]` | Todo | Completed |
| `[D][ ]` | Deadline | Not done |
| `[D][X]` | Deadline | Completed |
| `[E][ ]` | Event | Not done |
| `[E][X]` | Event | Completed |

---

## Tips for Effective Use

1. **Use Descriptive Names**: Make task descriptions clear and specific
2. **Set Realistic Deadlines**: Use dates you can actually meet
3. **Regular Reviews**: Use `list` command frequently to stay on track
4. **Search Functionality**: Use `find` to quickly locate specific tasks
5. **Clean Up**: Delete completed tasks periodically to keep your list manageable

---

## Data Storage

Mon automatically saves your tasks to `data/mon.txt`. Your data persists between sessions, so you never lose your important tasks and deadlines.

---

## Troubleshooting

### Common Issues

**"Unknown command" error**
- Check your command spelling
- Refer to the command examples above
- Use exact format including `/by`, `/from`, `/to` keywords

**Date format errors**
- Use `yyyy-MM-dd` format (e.g., 2025-12-31)
- Ensure dates are valid (no Feb 30th, etc.)

**Task number errors**
- Use `list` command to see current task numbers
- Task numbers change when tasks are deleted

### Getting Help

If you need assistance, type any message and Mon will provide helpful guidance. The welcome message also contains quick reference information for all available commands.