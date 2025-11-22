# Watermelon Chatbot

Watermelon is a simple chatbot for managing your tasks, deadlines, and events. It helps you organize your work efficiently using structured commands.

## Features

- Add, list, mark, and delete tasks (todos, deadlines, events)
- Search for tasks by keyword
- View tasks scheduled for a specific date

## Getting Started

1. Ensure you have **Java 17 or above** installed on your computer.  
   *Mac users: Ensure you have the precise JDK version prescribed [here](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html).*

2. Download the latest `.jar` file from [here](https://github.com/watermelonisred/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your Watermelon tasks.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the following command to run the application:
   ```
   java -jar watermelon.jar
   ```

## Usage

Interact with Watermelon using the following commands:

| Command Format                                                  | Description |
|-----------------------------------------------------------------| ----------- |
| `todo <description>`                                            | Adds a todo task |
| `deadline <description> /by <ddMMyyyy HHmm>`                    | Adds a deadline task |
| `event <description> /from <ddMMyyyy HHmm> /to <ddMMyyyy HHmm>` | Adds an event task |
| `list`                                                          | Lists all tasks |
| `mark <task number>`                                            | Marks a task as done |
| `unmark <task number>`                                          | Marks a task as not done |
| `delete <task number>`                                          | Deletes a task |
| `find <keyword>`                                                | Finds tasks containing the keyword |
| `schedule <ddMMyyyy>`                                           | Shows tasks scheduled for a specific date (leave blank for today) |
| `bye`                                                           | Exits the application |

**Remarks:**
- For `deadline` and `event` commands, please enter dates and times in the format: `ddMMyyyy HHmm` (eg. `30062024 2359` for 30 June 2024, 11:59 PM).
- For `schedule` command, please enter dates in the format: `ddMMyyyy` (eg. `30062024` for 30 June 2024).
- All commands are **case sensitive**. Please enter them exactly as shown.
- All data is saved automatically.

For detailed instructions and examples, please refer to the [User Guide](docs/USER_GUIDE.md).

--- 
Have fun using Watermelon to manage your tasks!
