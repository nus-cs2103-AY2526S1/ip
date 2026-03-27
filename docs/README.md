# Shrek 
## User Guide 🧅

![Shrek GUI](https://jj55j7.github.io/ip/Ui.png)

> "It's all about o-gre now." – Shrek, probably.

Welcome to Shrek's Swamp! Shrek is your friendly, ogre-powered task manager that helps you keep track of all your onions (tasks) in one place. With a beautiful GUI and intuitive commands, managing tasks has never been more fun!

## 🚀 Quick Start

1. **Ensure you have Java 11 or above** installed on your computer.

2. **Download the latest version** of Shrek from the [releases page](https://github.com/jj55j7/ip/releases/tag/A-Release).

3. **Run the application** by double-clicking the `shrek.jar` file.

4. **Start chatting with Shrek** by typing commands in the text field at the bottom.

5. **Type `help`** at any time to see available commands.

## 🎯 Features

### Adding Tasks

Shrek supports three types of tasks:

#### 1. Todo Tasks
Simple tasks without any date/time.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo Buy onions
```

**Expected Output:**
```
Okies, onion (task) added:
[T][ ] Buy onions
Now you have 1 tasks in the list.
```

#### 2. Deadline Tasks
Tasks with a specific due date and time.

**Format:** `deadline DESCRIPTION /by yyyy-MM-dd HH:mm`

**Example:**
```
deadline Return book /by 2025-12-31 23:59
```

**Expected Output:**
```
Okies, onion (task) added:
[D][ ] Return book (by: Dec 31 2025, 11:59PM)
Now you have 2 tasks in the list.
```

#### 3. Event Tasks
Tasks with a start and end time.

**Format:** `event DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm`

**Example:**
```
event Team meeting /from 2025-12-05 14:00 /to 2025-12-05 16:00
```

**Expected Output:**
```
Okies, onion (task) added:
[E][ ] Team meeting (from: Dec 5 2025, 2:00PM to: Dec 5 2025, 4:00PM)
Now you have 3 tasks in the list.
```

### Viewing Tasks

#### Listing All Tasks
Shows all tasks in your list.

**Format:** `list`

**Expected Output:**
```
Here are your onions (tasks):
1: [T][ ] Buy onions
2: [D][ ] Return book (by: Dec 31 2025, 11:59PM)
3: [E][ ] Team meeting (from: Dec 5 2025, 2:00PM to: Dec 5 2025, 4:00PM)
```

### Managing Tasks

#### Marking Tasks as Done
Mark a task as completed.

**Format:** `mark INDEX`

**Example:**
```
mark 1
```

**Expected Output:**
```
Awesome! One layer of onion(task) has been removed
(marked done)
[T][X] Buy onions
```

#### Unmarking Tasks
Mark a completed task as not done.

**Format:** `unmark INDEX`

**Example:**
```
unmark 1
```

**Expected Output:**
```
One layer of onion(task) has been added back
(marked as not done yet)
[T][ ] Buy onions
```

#### Deleting Tasks
Remove a task from your list.

**Format:** `delete INDEX`

**Example:**
```
delete 2
```

**Expected Output:**
```
One onion has been YEETED! (task removed)
[D][ ] Return book (by: Dec 31 2025, 11:59PM)
Now you have 2 tasks in the list.
```

**Error Cases:**
- If task number is too big or small: `"BIG onion! Task numbers start from 1, not X!"` or `"That onion doesn't exist in Shrek's swamp!"`
- If list is empty: `"Shrek's swamp is already empty! No onions to YEET!"`

### Smart Features

#### Finding Tasks
Search for tasks containing specific keywords.

**Format:** `find KEYWORD`

**Example:**
```
find book
```

**Expected Output:**
```
Matching onions (tasks) with: book
2. [D][ ] Return book (by: Dec 31 2025, 11:59PM)
```

#### Sorting Tasks
Sort tasks by different criteria.

**Format:** `sort CRITERIA` (description/date/type)

**Examples:**
```
sort description
sort date
sort type
```

**Expected Output:**
```
Sorted onions by description:
1: [T][ ] Buy onions
2: [E][ ] Team meeting (from: Dec 5 2025, 2:00PM to: Dec 5 2025, 4:00PM)
3: [D][ ] Return book (by: Dec 31 2025, 11:59PM)
```

#### Viewing Tasks on Specific Dates
Find tasks occurring on a particular date.

**Format:** `ondate yyyy-MM-dd`

**Example:**
```
ondate 2025-12-05
```

**Expected Output:**
```
Tasks on 2025-12-05:
[E][ ] Team meeting (from: Dec 5 2025, 2:00PM to: Dec 5 2025, 4:00PM)
```

### Exiting the Application

**Format:** `bye`

**Expected Output:**
```
Bye! I'm going to find Princess Fiona :)
See ya later
```

(The application will close automatically after 2 seconds)

## 💡 Tips & Tricks

- **Duplicate Prevention**: Shrek automatically prevents adding identical tasks
- **Auto-save**: Your tasks are saved automatically after every command
- **Case Insensitive**: Commands work regardless of uppercase/lowercase
- **Flexible Event Times**: You can specify event times in either order (`/from` then `/to` or `/to` then `/from`)

## ❓ Frequently Asked Questions

**Q: How do I transfer my tasks to another computer?**
A: Copy the `data/shrek.txt` file from your current computer to the same location on the new computer.

**Q: What happens if I accidentally close the application?**
A: Your tasks are automatically saved, so you won't lose any data when you reopen the application.

**Q: Can I edit tasks directly in the data file?**
A: Yes, but be careful! The file format is specific. Make a backup first if you need to edit it manually.

**Q: Why can't I add the same task twice?**
A: Shrek prevents duplicate tasks to keep your list clean and organized.

## 🐛 Known Issues

- Sorting by date puts todos at the end since they don't have dates
- `sort` does not overwrite the ordering of tasks (it is simply for display)

## 🆘 Command Summary

| Action | Format | Example |
|--------|--------|---------|
| Add Todo | `todo DESCRIPTION` | `todo Buy milk` |
| Add Deadline | `deadline DESCRIPTION /by yyyy-MM-dd HH:mm` | `deadline Homework /by 2025-12-31 23:59` |
| Add Event | `event DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm` | `event Meeting /from 2025-12-05 14:00 /to 2025-12-05 16:00` |
| List Tasks | `list` | `list` |
| Mark Done | `mark INDEX` | `mark 1` |
| Unmark | `unmark INDEX` | `unmark 1` |
| Delete | `delete INDEX` | `delete 2` |
| Find | `find KEYWORD` | `find book` |
| Sort | `sort CRITERIA` | `sort date` |
| On Date | `ondate yyyy-MM-dd` | `ondate 2025-12-05` |
| Help | `help` | `help` |
| Exit | `bye` | `bye` |

## 🖼️ Media Credits

- **Shrek Character**: Official DreamWorks Animation character
- **Shrek's Swamp Background**: [Wallpapers.com](https://wallpapers.com/wallpapers/shrek-s-swamp-house-912ivi1v9qemna0n.html)
- **User Profile Picture**: [Pinterest](https://www.pinterest.com/pin/shrek--189432728069529529/)
- **Onion Icon**: [Flaticon](https://www.flaticon.com/free-icons/onion) (by Freepik)

## 🤖 AI Assistance

This project utilized AI tools for:
- Test generation and specification drafting
- Code refactoring suggestions
- Documentation improvement
- GUI enhancement recommendations

All AI-generated content was thoroughly reviewed and adapted to ensure quality.

---

*"That'll do, donkey. That'll do." - Shrek, when your tasks are all organized* 🧅