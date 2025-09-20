# Ming User Guide

Ming is a desktop app for managing your tasks, optimized for fast command-line input while providing a modern Graphical User Interface (GUI). If you prefer typing commands, Ming helps you manage todos, deadlines, and events efficiently.

## Quick Start

1. Ensure you have **Java 17** or above installed on your computer.
2. Download the latest `.jar` file from the [releases page](#).
3. Place the `.jar` file in your desired folder.
4. Open a terminal, navigate (`cd`) to the folder, and run:

   ```
   java -jar ming.jar
   ```

5. The Ming GUI similar to the below should appear in a few seconds. ![Screeshot of Ming GUI](Ui.png)
6. Refer to the [Features](#features) below for the details of each command.

## Features

### Adding a todo: `todo`

Adds a todo task.

**Format:** `todo DESCRIPTION`

**Example:**  
`todo borrow book`

### Adding a deadline: `deadline`

Adds a task with a deadline.

**Format:** `deadline DESCRIPTION /by DATE_TIME`

**Example:**  
`deadline return book /by 2019-12-09 1800`

### Adding an event: `event`

Adds an event with a start and end time.

**Format:** `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`

**Example:**  
`event project meeting /from 2019-12-09 2000 /to 2019-12-09 2200`

### Listing all tasks: `list`

Shows a list of all tasks.

**Format:** `list`

### Marking a task as done: `mark`

Marks the specified task as done.

**Format:** `mark INDEX`

**Example:**  
`mark 2`

### Unmarking a task: `unmark`

Marks the specified task as not done.

**Format:** `unmark INDEX`

**Example:**  
`unmark 2`

### Deleting a task: `delete`

Deletes the specified task.

**Format:** `delete INDEX`

**Example:**  
`delete 3`

### Finding tasks: `find`

Finds tasks by keyword or tag.

**Format:**

- By name: `find KEYWORD`
- By tag: `find KEYWORD --tags`

**Example:**  
`find book`  
`find urgent --tags`

### Exiting the program: `bye`

Exits the application.

**Format:** `bye`

## Saving the Data

Ming saves your tasks automatically to `data/Ming.txt` after any command that changes your tasks. No manual saving is required.

## Editing the Data File

Advanced users can edit the `data/Ming.txt` file directly.  
**Caution:** Editing the file incorrectly may cause Ming to fail to load your tasks.

## FAQ

**Q:** How do I transfer my tasks to another computer?  
**A:** Copy the `data/Ming.txt` file to the same location on your new computer.

## Command Summary

| Action       | Format / Example                                                                                             |
| ------------ | ------------------------------------------------------------------------------------------------------------ |
| Add Todo     | `todo DESCRIPTION`<br>`todo borrow book`                                                                     |
| Add Deadline | `deadline DESCRIPTION /by DATE_TIME`<br>`deadline return book /by 2019-12-09 1800`                           |
| Add Event    | `event DESCRIPTION /from START /to END`<br>`event project meeting /from 2019-12-09 2000 /to 2019-12-09 2200` |
| List         | `list`                                                                                                       |
| Mark         | `mark INDEX`<br>`mark 2`                                                                                     |
| Unmark       | `unmark INDEX`<br>`unmark 2`                                                                                 |
| Delete       | `delete INDEX`<br>`delete 3`                                                                                 |
| Find         | `find KEYWORD`<br>`find book`<br>`find urgent --tags`                                                        |
| Exit         | `bye`                                                                                                        |

---

For more details, refer to the Features section above.
