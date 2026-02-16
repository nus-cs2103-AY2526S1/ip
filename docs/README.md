# Hope Chatbot User Guide
Hope is a to-do list chatbot designed to help you manage tasks efficiently. This guide provides an overview of the 
available commands, their usage, and examples to get you started.
## Overview
Hope allows you to create, manage, and track tasks through a simple text-based interface. It supports various task types, including to-dos, deadlines, events, and fixed-duration tasks. Tasks are persisted using a storage system, and you can list, mark, unmark, delete, or search for tasks with ease.
Commands

## Quick Start

1. Ensure you have Java 17 or above installed in your Computer.
Mac users: Ensure you have the precise JDK version prescribed [here](https://se-education.
   org/guides/tutorials/javaInstallationMac.html).


2. Download the latest .jar file.


3. Copy the file to the folder you want to use as the home folder for Hope.


4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar hope.jar command 
to run the application.
A GUI similar to the below should appear in a few seconds.


![img.png](sampleimg.png)


5. Type the command in the command box and press Enter to execute it. Refer below for the list of commands


## Commands
Below is a list of all supported commands, their descriptions, syntax, and examples.
### 1. bye

Description: Exits the chatbot.
Syntax: bye
Example:bye

Output: The chatbot terminates with a farewell message.

### 2. list

Description: Displays all tasks in the to-do list.
Syntax: list
Example:list

Output: A numbered list of all tasks, showing their status and details.

### 3. mark

Description: Marks a task as completed.
Syntax: mark <task_number>
Example:mark 1

Output: Marks the task at index 1 as completed and updates the storage.

### 4. unmark

Description: Marks a task as not completed.
Syntax: unmark <task_number>
Example:unmark 1

Output: Marks the task at index 1 as not completed and updates the storage.

### 5. todo

Description: Adds a simple to-do task to the list.
Syntax: todo <task_description>
Example:todo Buy groceries

Output: Adds a to-do task "Buy groceries" to the list and saves it.

### 6. deadline

Description: Adds a task with a deadline.
Syntax: deadline <task_description> /by <deadline>
Example:deadline Submit report /by 2025-10-01

Output: Adds a deadline task "Submit report" with the specified deadline and saves it.

### 7. event

Description: Adds an event with a start and end time.
Syntax: event <event_description> /from <start_time> /to <end_time>
Example:event Team meeting /from 2025-10-02 14


## FAQ

*Q*: How do I transfer my data to another Computer?
*A*: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous AddressBook home folder.
## Hope Chatbot Command Summary

This is a concise summary of all commands supported by the **Hope** chatbot for managing tasks.

| Command    | Syntax                                                        | Description                                          |
|------------|---------------------------------------------------------------|------------------------------------------------------|
| `bye`      | `bye`                                                         | Exits the chatbot.                                   |
| `list`     | `list`                                                        | Lists all tasks in the to-do list.                   |
| `mark`     | `mark <task_number>`                                          | Marks a task as completed.                           |
| `unmark`   | `unmark <task_number>`                                        | Marks a task as not completed.                       |
| `todo`     | `todo <task_description>`                                     | Adds a to-do task.                                   |
| `deadline` | `deadline <task_description> /by <deadline>`                  | Adds a task with a deadline (MM-DD-YYYY).            |
| `event`    | `event <event_description> /from <start_time> /to <end_time>` | Adds an event with start and end times (MM-DD-YYYY). |
| `delete`   | `delete <task_number>`                                        | Deletes a task from the list.                        |
| `find`     | `find <keyword>`                                              | Searches for tasks containing the keyword.           |
| `fdt`      | `fdt <task_description> /duration <duration>`                 | Adds a fixed-duration task.                          |

**Notes**:
- Task numbers (for `mark`, `unmark`, `delete`) start from 1, as shown in the `list` output.
- Commands are case-sensitive.
- Invalid commands trigger an error message.


## Credits
- Credits for most of the chatbots responses: https://anythingtranslate.com/translators/don-quixote-limbus-company-translator/
- Credits to grok for generation of javadoc comments and helping with junit
- Credits for Don Quixote image goes to bean_dobe: https://x.com/bean_dobe?lang=en
- Credits for Dante image goes to Sansti24: https://www.reddit.com/r/limbuscompany/comments/16zy6uf/dante_chibi/
- Credits for background photo goes to Project Moon
- Credits to https://se-education.org/guides/tutorials/javaFx.html for gui implementation