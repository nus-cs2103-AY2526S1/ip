# Mambo User Guide

Mambo is a desktop chatbot application that helps you manage your tasks efficiently. It features a graphical user 
interface (GUI) built with JavaFX while retaining the functionality of a command-line interface.

![Mambo ChatBot Interface](https://kentalim2.github.io/ip/Ui.png)

---

## Quick Start
1. Ensure you have **Java 17 or above** installed on your computer.
2. Download the latest **mambo.jar** file from the releases page.
3. Open a terminal, navigate to the folder containing the JAR file, and run:

   ```bash
   java -jar mambo.jar
   ```
4. The application window will appear with Mambo ready to assist you.  
5. Type your commands in the input field and press **Enter** to execute them.

---

## Features

### 1. Adding a Todo Task: `todo`
Adds a simple todo task to your task list.

**Format:** ``` todo TASK_DESCRIPTION ```

**Examples:**

- ```todo Read book```

- ```todo Complete homework``` 

### 2. Adding a Deadline Task: `deadline`
Adds a task with a specific deadline.

**Format:**```deadline TASK_DESCRIPTION /by DEADLINE```

**Examples:**
- ```deadline Submit report /by 2023-10-31```
- ```deadline Pay bills /by Friday 5pm```

### 3. Adding an Event Task: `event`
Adds an event with a start and end time/date. 

**Format:**```event EVENT_DESCRIPTION /from START_TIME /to END_TIME```

**Examples:**
- ```event Team meeting /from Mon 2pm /to Mon 4pm```
- ```event Conference /from 2023-11-15 6pm /to 2023-11-17 3pm```


### 4. Listing All Tasks: `list`
Displays all tasks in your current task list.

**Format:**```list```


### 5. Marking a Task as Done: `mark`
Marks a specific task as completed.

**Format:**```mark INDEX```

**Examples:**
- ```mark 1``` - Marks the first task in the list as done
- ```mark 3``` - Marks the third task as done

### 6. Unmarking a Task: `unmark`
Changes a completed task back to not done.

**Format:** ```unmark INDEX```

**Example:**
- ```unmark 2``` - Unmarks the second task

### 7. Finding Tasks: `find`
Searches for tasks containing specific keywords.

**Format:**```find KEYWORD```

**Examples:**
- ```find book # Finds all tasks containing "book"```
- ```find meeting # Finds all meeting-related tasks```

### 8. Deleting a Task: `delete`
Removes a task from your list.

**Format:**```delete INDEX```

**Examples:**
- ```delete 1``` - Deletes the first task
- ```delete 4``` - Deletes the fourth task


### 9. Getting Help: `help`
Displays a list of all available commands and their formats.

**Format:**```help```


### 10. Exiting the Program: `bye`
Saves your task list and exits the application.

**Format:**```bye```

---

## Task Storage
Mambo automatically saves your tasks in a file called `tasks.txt` in the same directory as the application. Your 
data will be loaded automatically when you restart the application.

---

## Command Summary

| Command  | Format                              | Example                           |
|----------|-------------------------------------|-----------------------------------|
| Todo     | `todo DESCRIPTION`                  | `todo Buy groceries`              |
| Deadline | `deadline DESCRIPTION /by DEADLINE` | `deadline Assignment /by Friday`  |
| Event    | `event DESCRIPTION /from START /to END` | `event Party /from 7pm /to 11pm` |
| List     | `list`                              | `list`                            |
| Mark     | `mark INDEX`                        | `mark 2`                          |
| Unmark   | `unmark INDEX`                      | `unmark 3`                        |
| Find     | `find KEYWORD`                      | `find book`                       |
| Delete   | `delete INDEX`                      | `delete 1`                        |
| Help     | `help`                              | `help`                            |
| Exit     | `bye`                               | `bye`                             |

---

## FAQ

**Q: How do I transfer my data to another computer?**  
A: Copy the `tasks.txt` file along with the JAR file to the new computer.

**Q: What happens if I accidentally close the application without typing `bye`?**  
A: Your recent changes might not be saved. Always use the `bye` command to ensure your data is saved properly.

**Q: Can I edit tasks directly in the text file?**  
A: It's not recommended as it might corrupt the data format. Use the application commands instead.

---

## Support
If you encounter any issues or have suggestions for improvement, please create an issue on our GitHub repository.

---

✨ **Happy task managing with Mambo!** 🎯