# Lyra User Guide

> **Lyra** - Your Personal Task Manager

![Lyra GUI Screenshot](https://via.placeholder.com/800x500/0078d4/ffffff?text=Lyra+GUI+Interface)

**Lyra** is a powerful, user-friendly task management application that helps you organize your life with simple, intuitive commands. Whether you prefer the sleek graphical interface or the efficient command-line experience, Lyra adapts to your workflow.

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
- [Command Reference](#command-reference)
- [GUI Interface](#gui-interface)
- [Usage Examples](#usage-examples)
- [Troubleshooting](#troubleshooting)
- [Support](#support)

## Quick Start

### Installation

1. **Download** the latest release from the [releases page](https://github.com/yourusername/lyra/releases)
2. **Extract** the files to your desired location
3. **Run** the application:
   - **GUI Version**: Double-click `lyra.jar` or run `java -jar lyra.jar`
   - **Command Line**: Run `java -jar lyra.jar` in your terminal

### First Run

When you first launch Lyra, you'll see:

```
Hello! I'm Lyra
What can I do for you?
```

You're ready to start managing your tasks! 🎉

## Features

### ✨ Core Functionality
- **📝 Todo Tasks** - Simple tasks without deadlines
- **⏰ Deadline Tasks** - Tasks with specific due dates
- **📅 Event Tasks** - Tasks with start and end times
- **✅ Mark/Unmark** - Track task completion status
- **🔍 Search** - Find tasks quickly
- **🗑️ Delete** - Remove completed or unwanted tasks
- **💾 Auto-Save** - Your tasks are automatically saved

### 🎨 Interface Options
- **Modern GUI** - Beautiful, responsive graphical interface
- **Command Line** - Fast, keyboard-driven interface
- **Cross-Platform** - Works on Windows, macOS, and Linux

### 🚀 Advanced Features
- **Smart Parsing** - Understands natural language commands
- **Data Persistence** - Tasks saved between sessions
- **Error Handling** - Clear, helpful error messages
- **Responsive Design** - Adapts to different screen sizes

## Command Reference

### Adding Tasks

#### Todo Tasks
Add simple tasks without deadlines.

```
todo <description>
```

**Examples:**
```
todo buy groceries
todo read a book
todo call mom
```

#### Deadline Tasks
Add tasks with specific due dates.

```
deadline <description> /by <date>
```

**Examples:**
```
deadline submit report /by 2024-12-31
deadline pay bills /by 2024-01-15
deadline finish project /by 2024-02-28
```

**Date Formats:**
- `2024-12-31` (YYYY-MM-DD)
- `Dec 31 2024`
- `31/12/2024`

#### Event Tasks
Add tasks with start and end times.

```
event <description> /from <start> /to <end>
```

**Examples:**
```
event team meeting /from 2024-01-15 2pm /to 2024-01-15 3pm
event conference /from 2024-02-01 /to 2024-02-03
event vacation /from 2024-06-01 /to 2024-06-15
```

### Managing Tasks

#### List All Tasks
View all your tasks.

```
list
```

**Output:**
```
Here are the tasks in your list:
1.[T][ ] buy groceries
2.[D][ ] submit report (by: Dec 31 2024)
3.[E][ ] team meeting (from: Jan 15 2024 2:00 PM to: Jan 15 2024 3:00 PM)
```

#### Mark Tasks as Done
Mark a task as completed.

```
mark <task_number>
```

**Examples:**
```
mark 1
mark 3
```

#### Mark Tasks as Not Done
Mark a completed task as not done.

```
unmark <task_number>
```

**Examples:**
```
unmark 1
unmark 3
```

#### Delete Tasks
Remove a task from your list.

```
delete <task_number>
```

**Examples:**
```
delete 1
delete 3
```

#### Search Tasks
Find tasks containing specific keywords.

```
find <keyword>
```

**Examples:**
```
find meeting
find report
find groceries
```

### System Commands

#### Exit Application
Close the application.

```
bye
```

## GUI Interface

### Getting Started with the GUI

1. **Launch** the GUI version by running `./gradlew run` or double-clicking `lyra.jar`
2. **Type** your commands in the text field at the bottom
3. **Send** by clicking the "Send" button or pressing Enter
4. **View** responses in the chat-like interface

### GUI Features

#### 🎨 Modern Design
- **Asymmetric Layout** - User messages on the right (blue), Lyra responses on the left (white)
- **Error Highlighting** - Errors appear in red with warning icons
- **Success Feedback** - Successful actions show in green
- **Responsive Design** - Adapts to different window sizes

#### 💬 Chat Interface
- **Conversation Style** - Messages appear in a chat-like format
- **Auto-scroll** - Automatically scrolls to show new messages
- **Message History** - All interactions are preserved during the session

#### ⌨️ Keyboard Shortcuts
- **Enter** - Send message
- **Escape** - Clear input field
- **Ctrl+A** - Select all text

### Window Management
- **Resizable** - Drag corners to resize the window
- **Minimum Size** - 500x600 pixels
- **Default Size** - 600x700 pixels
- **Responsive Layout** - Content adapts to window size

## Usage Examples

### Daily Task Management

**Morning Routine:**
```
todo check emails
todo review calendar
deadline prepare presentation /by 2024-01-15
```

**Afternoon Tasks:**
```
event team standup /from 2024-01-15 2pm /to 2024-01-15 2:30pm
todo update project documentation
deadline submit weekly report /by 2024-01-19
```

**Evening Review:**
```
list
mark 1
mark 2
find presentation
```

### Project Planning

**Project Setup:**
```
todo research requirements
todo create project timeline
deadline complete wireframes /by 2024-02-01
event client meeting /from 2024-01-20 10am /to 2024-01-20 11am
```

**Progress Tracking:**
```
list
mark 1
unmark 2
find client
```

### Event Planning

**Wedding Planning:**
```
todo book venue
todo send invitations
deadline finalize guest list /by 2024-03-01
event venue visit /from 2024-02-15 2pm /to 2024-02-15 4pm
event dress fitting /from 2024-02-20 3pm /to 2024-02-20 4pm
```

## Troubleshooting

### Common Issues

#### Application Won't Start
- **Check Java Version**: Ensure you have Java 17 or later installed
- **Verify Installation**: Make sure all files are properly extracted
- **Run from Terminal**: Try running `java -jar lyra.jar` from command line

#### Commands Not Working
- **Check Syntax**: Ensure commands follow the exact format shown in examples
- **Date Format**: Use YYYY-MM-DD format for dates
- **Task Numbers**: Use numbers from the `list` command

#### GUI Issues
- **Window Size**: Try resizing the window if content appears cut off
- **Text Input**: Click in the text field before typing
- **Scrolling**: Use mouse wheel or scroll bar to navigate long conversations

### Error Messages

#### "Invalid task number"
- **Cause**: Task number doesn't exist
- **Solution**: Use `list` to see available task numbers

#### "Sorry, a todo needs a description"
- **Cause**: Empty todo description
- **Solution**: Add a description after `todo`

#### "Sorry, a deadline needs a description and '/by'"
- **Cause**: Missing description or `/by` keyword
- **Solution**: Use format `deadline <description> /by <date>`

#### "Sorry, an event needs a description and times"
- **Cause**: Missing description or time keywords
- **Solution**: Use format `event <description> /from <start> /to <end>`

### Getting Help

If you encounter issues not covered here:

1. **Check the Console**: Look for error messages in the terminal/console
2. **Restart the Application**: Close and reopen Lyra
3. **Check File Permissions**: Ensure Lyra can write to the data directory
4. **Report Issues**: Create an issue on the GitHub repository

## Support

### Resources
- **GitHub Repository**: [Link to your repository]
- **Issue Tracker**: [Link to issues page]
- **Documentation**: [Link to full documentation]

### Contact
- **Email**: [Your email]
- **Discord**: [Your Discord server]
- **Twitter**: [Your Twitter handle]

---

**Happy task managing with Lyra!** 🚀

*Last updated: January 2024*