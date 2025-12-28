# Mr. Meeseeks Task Manager - User Guide

Welcome to **Mr. Meeseeks Task Manager**! I'm here to help you manage your tasks efficiently. Look at me! 🎯

## 🚀 Getting Started

### Launching the Application
- Run the application using the GUI launcher
- The application will start with a welcome message and show you all available commands
- You can type commands in the input field and press **Enter** or click the **Send** button

### Interface Features
- **Modern Chat Interface**: Asymmetric conversation design optimized for user experience
- **Error Highlighting**: Error messages are displayed in red to catch your attention
- **Responsive Design**: Window can be resized and content scales appropriately
- **Keyboard Shortcuts**: Press **Enter** to send messages quickly

---

## 📋 Task Types

Your Meeseeks can manage **4 different types of tasks**:

### 1. **Todo Tasks** 📝
Simple tasks without any time constraints.

**Format:** `todo <description>`

**Example:**
```
todo Buy groceries
```

### 2. **Deadline Tasks** ⏰
Tasks with a specific due date and time.

**Format:** `deadline <description> /by <date> <time>`

**Date Format:** `d/M/yyyy HHmm` (e.g., `25/12/2023 1400`)

**Example:**
```
deadline Submit project report /by 15/1/2024 1700
```

### 3. **Event Tasks** 📅
Tasks that occur during a specific time period.

**Format:** `event <description> /from <start> /to <end>`

**Example:**
```
event Team meeting /from 2pm /to 3pm
```

### 4. **Recurring Tasks** 🔄
Tasks that repeat at regular intervals (daily, weekly, monthly, yearly).

**Format:** `recurring <description> /every <frequency> /due <date> <time>`

**Frequencies:** `daily`, `weekly`, `monthly`, `yearly`

**Example:**
```
recurring Weekly team standup /every weekly /due 15/1/2024 0900
recurring Daily exercise /every daily /due 16/1/2024 0700
```

---

## 🎯 Available Commands

### **Adding Tasks**

| Command | Format | Example |
|---------|--------|---------|
| **Todo** | `todo <description>` | `todo Read a book` |
| **Deadline** | `deadline <description> /by <date> <time>` | `deadline Finish homework /by 20/1/2024 2359` |
| **Event** | `event <description> /from <start> /to <end>` | `event Doctor appointment /from 10am /to 11am` |
| **Recurring** | `recurring <description> /every <frequency> /due <date> <time>` | `recurring Monthly review /every monthly /due 1/2/2024 1400` |

### **Managing Tasks**

| Command | Format | Description | Example |
|---------|--------|-------------|---------|
| **List** | `list` | Show all tasks | `list` |
| **Mark Done** | `mark <number>` | Mark task as completed | `mark 1` |
| **Unmark** | `unmark <number>` | Mark task as not done | `unmark 1` |
| **Delete** | `delete <number>` | Remove task from list | `delete 2` |
| **Find** | `find <keyword>` | Search for tasks | `find meeting` |

### **Special Features**

| Command | Format | Description | Example |
|---------|--------|-------------|---------|
| **Overdue** | `overdue` | Show overdue recurring tasks | `overdue` |
| **Stats** | `stats` | Display task statistics | `stats` |
| **Exit** | `bye` | Close the application | `bye` |

---

## 📊 Task Statistics

Use the `stats` command to get insights about your task management:

**Example Output:**
```
=== Task Statistics ===
Total tasks: 10
Completed: 6
Pending: 4
Completion rate: 60.0%
```

---

## 🔍 Finding and Managing Tasks

### **Searching Tasks**
- Use `find <keyword>` to search for tasks containing specific words
- Search is case-insensitive
- Shows all matching tasks with their numbers

**Example:**
```
find project
```
**Output:**
```
Here are the matching tasks in your list:
1.[D][ ] Submit project report (by: 2024-01-15T17:00)
2.[T][X] Review project documentation
```

### **Task Status Management**
- **Mark as Done**: `mark <number>` - Marks a task as completed ✅
- **Mark as Not Done**: `unmark <number>` - Marks a completed task as pending ⏳
- **Delete Task**: `delete <number>` - Permanently removes a task 🗑️

### **Recurring Task Management**
- **Check Overdue**: `overdue` - Shows all overdue recurring tasks
- **Automatic Updates**: When you mark a recurring task as done, the next due date is automatically calculated based on the frequency

---

## 💾 Data Persistence

- All your tasks are automatically saved to `data/meeseeks.txt`
- Tasks persist between application sessions
- No need to manually save - everything is automatic!

---

## 🎨 User Interface Features

### **Visual Design**
- **Asymmetric Layout**: User messages appear on the right (green), Meeseeks responses on the left (blue)
- **Error Highlighting**: Error messages are displayed in red with bold text
- **Profile Pictures**: User and Meeseeks avatars for visual appeal
- **Responsive Design**: Optimized for different window sizes

### **User Experience**
- **Auto-scroll**: Automatically scrolls to show new messages
- **Keyboard Support**: Press Enter to send messages
- **Clear Visual Feedback**: Different styling for different message types
- **Space Optimization**: Efficient use of screen space

---

## 📝 Example Usage Session

```
You: todo Buy groceries
Meeseeks: Got it. I've added this task:
        [T][ ] Buy groceries
       Now you have 1 tasks in the list.

You: deadline Submit report /by 20/1/2024 1700
Meeseeks: Got it. I've added this task:
        [D][ ] Submit report (by: 2024-01-20T17:00)
       Now you have 2 tasks in the list.

You: recurring Weekly standup /every weekly /due 15/1/2024 0900
Meeseeks: Got it. I've added this recurring task:
        [R][ ] Weekly standup (due: 2024-01-15T09:00, frequency: weekly)
       Now you have 3 tasks in the list.

You: list
Meeseeks: Here are the tasks in your list:
        1.[T][ ] Buy groceries
        2.[D][ ] Submit report (by: 2024-01-20T17:00)
        3.[R][ ] Weekly standup (due: 2024-01-15T09:00, frequency: weekly)

You: mark 1
Meeseeks: Nice! I've marked this task as done:
        [T][X] Buy groceries

You: stats
Meeseeks: === Task Statistics ===
         Total tasks: 3
         Completed: 1
         Pending: 2
         Completion rate: 33.3%

You: find standup
Meeseeks: Here are the matching tasks in your list:
        1.[R][ ] Weekly standup (due: 2024-01-15T09:00, frequency: weekly)

You: bye
Meeseeks: Bye. Hope to see you again soon!
```

---

## ⚠️ Important Notes

### **Date and Time Format**
- **Deadlines and Recurring Tasks**: Use format `d/M/yyyy HHmm`
- **Examples**: `25/12/2023 1400`, `1/1/2024 0900`, `15/3/2024 2359`

### **Task Numbers**
- Task numbers start from 1 (not 0)
- Use `list` command to see current task numbers
- Numbers may change when tasks are deleted

### **Error Handling**
- Invalid commands show helpful error messages in red
- Date format errors provide specific guidance
- Task number errors indicate valid ranges

### **Recurring Tasks**
- When marked as done, the next due date is automatically calculated
- Use `overdue` to check for missed recurring tasks
- Frequencies: `daily`, `weekly`, `monthly`, `yearly`

---

## 🆘 Troubleshooting

### **Common Issues**

1. **"Task index out of range!"**
   - Use `list` to see current task numbers
   - Make sure the task number exists

2. **"Invalid date format"**
   - Use format: `d/M/yyyy HHmm`
   - Example: `25/12/2023 1400`

3. **"Unknown recurrence frequency"**
   - Use: `daily`, `weekly`, `monthly`, or `yearly`

4. **"Invalid command format"**
   - Check the command syntax in this guide
   - Make sure all required parts are included

### **Getting Help**
- Type any command to see if it's recognized
- Check this user guide for proper syntax
- Error messages provide specific guidance

---

## 🎉 Tips for Effective Use

1. **Use Descriptive Names**: Give your tasks clear, descriptive names
2. **Set Realistic Deadlines**: Use the deadline feature for time-sensitive tasks
3. **Leverage Recurring Tasks**: Set up regular tasks like meetings, exercise, or reviews
4. **Check Overdue Tasks**: Regularly use `overdue` to catch up on missed recurring tasks
5. **Monitor Progress**: Use `stats` to track your completion rate
6. **Search Efficiently**: Use `find` to quickly locate specific tasks

---

**Happy task managing! Remember, I'm Mr. Meeseeks, and I'm here to help! Look at me!** 🎯✨
