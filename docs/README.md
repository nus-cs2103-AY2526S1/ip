# 🌙 LunarBot User Guide

LunarBot is a simple yet powerful command-line chatbot for managing tasks.  
It supports todos, deadlines, and events, with persistent storage in CSV format so your tasks are saved across runs.

---

## 🚀 Getting Started

1. **Run the bot**
   ```
   java -jar LunarBot.jar
   ```

2. **You'll see a greeting message. Start typing commands to interact with the bot!**

3. **Type `Help` anytime to see available commands!**


## Command overview

| Command               | Usage                                       | Example                                                       |
| --------------------- | ------------------------------------------- | ------------------------------------------------------------- |
| **Add task**          | `<task_name>`                               | `Buy groceries`                                               |
| **Add todo**          | `todo <task_name>`                          | `todo Finish CS2106 lab`                                      |
| **Add deadline**      | `deadline <task_name> /by <date/time>`      | `deadline Submit report /by 2025-09-30 23:59`                 |
| **Add event**         | `event <task_name> /from <start> /to <end>` | `event Team sync /from 2025-09-21 10:00 /to 2025-09-21 11:00` |
| **List tasks**        | `list`                                      | `list`                                                        |
| **Mark task as done** | `mark <task_id>`                            | `mark 2`                                                      |
| **Unmark task**       | `unmark <task_id>`                          | `unmark 2`                                                    |
| **Delete task**       | `delete <task_id>`                          | `delete 3`                                                    |
| **Help**              | `help`                                      | `help`                                                        |
| **Exit**              | `bye`                                       | `bye`                                                         |


## Storage
- Tasks are saved in a CSV file automatically after graceful termination with the "bye" command
- The format includes
    - Task Type
    - Completion status
    - Task name
    - Date field (if applicable)

# Example:
`T,false,Buy Groceries`
`D,false,Submit report,2025-09-30 23:59`