# SumTingWong User Guide

Meet **SumTingWong**, your grumpy but helpful Vietnamese American task manager! He might sound a bit cranky, but he's actually quite good at keeping track of your tasks and deadlines. Just don't expect him to be overly polite about it! 😤

![SumTingWong Chat Interface](Ui.png)

## Quick Start

1. **Launch the application** - Double-click the JAR file or run it from your terminal
2. **Start chatting** - Type your commands in the text field at the bottom
3. **Press Enter or click Send** - SumTingWong will respond (probably with some attitude!)

## Basic Commands

### 📋 Viewing Tasks

**`list`** - Shows all your tasks
```
list
```
SumTingWong will display all your tasks with their status and details.

### ✅ Managing Tasks

**`todo <description>`** - Add a simple task
```
todo Buy groceries
```

**`deadline <description> /by <date>`** - Add a task with deadline
```
deadline Submit report /by 25/12/2024 1800
deadline Call mom /by tomorrow
```

**`event <description> /from <start> /to <end>`** - Add an event
```
event Team meeting /from 2pm /to 3pm
```

### 🏷️ Marking Tasks

**`mark <number>`** - Mark a task as done
```
mark 1
```

**`unmark <number>`** - Mark a task as not done
```
unmark 1
```

### 🗑️ Deleting Tasks

**`delete <number>`** - Remove a task
```
delete 2
```

### 🔍 Finding Tasks

**`find <keyword>`** - Search for tasks
```
find meeting
```

### 🏷️ Tagging System

**`tag <number> <tag1> [tag2] ...`** - Add tags to a task
```
tag 1 urgent work
```

**`untag <number> <tag1> [tag2] ...`** - Remove tags from a task
```
untag 1 urgent
```

**`filter <tag1> [tag2] ...`** - Show tasks with specific tags
```
filter work urgent
```

### 👋 Exiting

**`bye`** - Close the application
```
bye
```

## Chat Features

### Casual Greetings
SumTingWong recognizes friendly greetings and responds appropriately:
- `hi`, `hello`, `hey`
- `good morning`, `good afternoon`, `good evening`
- `what's up`, `how are you`

### Unknown Commands
If you type something SumTingWong doesn't understand, he'll give you helpful guidance instead of getting angry (well, maybe just a little grumpy).

## Date and Time Formats

### Deadlines
- **Structured format**: `25/12/2024 1800` (25th December 2024 at 6:00 PM)
- **Free text**: `tomorrow`, `next Friday`, `in 2 hours`

### Events
- **Time format**: `2pm`, `14:30`, `morning`
- **Date format**: `25/12/2024`, `tomorrow`, `next Monday`

## Tips for Using SumTingWong

1. **Be specific** - SumTingWong works better with clear, detailed descriptions
2. **Use tags wisely** - They help you organize and find tasks quickly
3. **Don't take the attitude personally** - He's grumpy but helpful!
4. **Save your work** - Tasks are automatically saved when you add, mark, or delete them

## Example Workflow

```
You: hi
SumTingWong: Oh, you saying hi? Well, hi back to you too. Now what you want me to do?

You: todo Buy groceries
SumTingWong: Tch, whatever. I added dis task:
    [T][ ] Buy groceries
Now you have 1 tasks in your list. Don't expect me to remind you about dem. -.-

You: deadline Submit project /by 30/12/2024 1700
SumTingWong: Ugh, anoder deadline? Fine, I added dis task:
    [D][ ] Submit project (by: 30 Dec 2024 5:00 PM)
Now you have 2 tasks cluttering up your list. Better don't procrastinate dis time. -.-

You: tag 2 work urgent
SumTingWong: Got it! I added dis tags to de task:
    [D][ ] Submit project (by: 30 Dec 2024 5:00 PM) #work #urgent
Added tags: work urgent

You: list
SumTingWong: Ugh, fine. Here your damn task list:
1. [T][ ] Buy groceries
2. [D][ ] Submit project (by: 30 Dec 2024 5:00 PM) #work #urgent
There, happy now? -.-

You: mark 1
SumTingWong: Hmph, about time. I marked dis task as done:
[T][X] Buy groceries
Don't get too excited, you still got 1 more to go. -.-

You: bye
SumTingWong: Finally! Go away and don't bother me again. I got better things to do dan babysit your tasks. >:(
```

## Need Help?

If you're stuck, just type any command and SumTingWong will guide you. He might be grumpy, but he's actually quite helpful once you get to know him!

---

*Remember: SumTingWong's grumpy personality is all part of his charm. Don't let his attitude fool you - he's actually quite good at what he does!* 😊