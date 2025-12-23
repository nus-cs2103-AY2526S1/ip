# George the Monkey User Guide 🐒

George is a friendly, banana-loving monkey chatbot that helps you manage your tasks! With an intuitive GUI interface, George makes task management fun and easy.

## Adding Deadlines ⏰

Add tasks with specific due dates using the deadline command.

**Format:** `deadline DESCRIPTION /by DATETIME`

**Example:**

deadline Submit report /by 2024-12-25 23:59


**Expected Output:**
```
You get a task. I get a banana!
[D][] Submit report (by: Dec 25 2024)
You now have 1 things to do!!!
Remember to do them NOW!!!
```

## Adding Todos 📝

Add simple todo tasks with just a description.

**Format:** `todo DESCRIPTION`

**Example:**

todo Buy bananas


**Expected Output:**
```
You get a task. I get a banana!
[T][] Buy bananas
You now have 1 things to do!!!
Remember to do them NOW!!!
```

## Adding Events 🎉

Add events with specific start and end times.

**Format:** `event DESCRIPTION /from START_TIME /to END_TIME`

**Example:**
event Team meeting /from 2024-12-25 14:00 /to 2024-12-25 15:00


**Expected Output:**
```
You get a task. I get a banana!
[E][] Team meeting (from: Dec 25 2024 14:00 to: Dec 25 2024 15:00)
You now have 2 things to do!!!
Remember to do them NOW!!!
```

## Listing Tasks 👀

View all your current tasks in a numbered list.

**Format:** `list`

**Example:** list


**Expected Output:**
```
OOO OOO AAA AAA here is all your tasks
1.[T][] Buy bananas
2.[E][] Team meeting (from: Dec 25 2024 14:00 to: Dec 25 2024 15:00)
3.[D][] Submit report (by: Dec 25 2024)
EEE EEE AAA AAA remember to do them all
```

## Marking Tasks ✅

Mark tasks as done or not done.

**Mark as done:** `mark NUMBER`
**Mark as not done:** `unmark NUMBER`

**Example:** mark 1


**Expected Output:**
```
Good job! Here is a banana for you!
[X] Buy bananas
```

## Finding Tasks 🔍

Search for tasks containing specific keywords.

**Format:** `find KEYWORD`

**Example:** find meeting

**Expected Output:**
```
OOO OOO AAA AAA found 1 matching tasks:
1.[E][] Team meeting (from: Dec 25 2024 14:00 to: Dec 25 2024 15:00)
```

## Getting Help 📚

View available commands and their formats.

**Format:** `help`

**Example:**
help

**Expected Output:**
```
1. Here are the commands I understand:
2. todo <description> - Add a todo task
3. deadline <description> /by <datetime> - Add a deadline task
4. event <description> /from <start> /to <end> - Add an event task
5. list - Show all tasks
6. mark <number> - Mark task as done
7. unmark <number> - Mark task as not done
8. delete <number> - Remove a task
9. find <keyword> - Search for tasks
10. format - Show date/time formats
11. help - Show this message
12. bye - Exit the application
```


## Date Formats 📅

View supported date and time formats.

**Format:** `format`

**Example:** format

**Expected Output:**

```
📅 Supported Date/Time Formats:

Date Formats:
• yyyy-MM-dd (e.g., 2024-12-25)
• yyyy/MM/dd (e.g., 2024/12/25)

DateTime Formats:
• yyyy-MM-dd HHmm (e.g., 2024-12-25 1430)
• yyyy-MM-dd HH:mm (e.g., 2024-12-25 14:30)
• yyyy/MM/dd HHmm (e.g., 2024/12/25 1430)
• yyyy/MM/dd HH:mm (e.g., 2024/12/25 14:30)

Note: Dates without time will default to start of day (00:00)
```

## Exiting the Application 🚪

Close George gracefully.

**Format:** `bye`

**Example:** bye

**Expected Outcome:** Application closes after a brief delay