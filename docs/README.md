# Briar User Guide
![Screenshot of Briar](/docs/Ui.png)

Briar is a desktop chatbot app for managing your tasks, optimized for use by Command Line Interface (CLI). Briar also has personality which will make tracking tasks fun.

## Quick start
1. [Download](https://github.com/siderealmaple/ip/releases) the latest version of Briar.
2. Move the file to the folder you want to use as the home folder for Briar.
3. Open a command terminal, `cd` into the folder with the `Briar.jar` file and run `java -jar Briar.jar`
4. Use the `help` command to see the list of commands.

## Features

### Tracking tasks
Briar allows you to add & delete tasks while also mark & unmarking them as done as you go.

### Saving data
Briar task data is saved locally automatically after any command that updates the task list.

### Editing data
Briar data is saved automatically as a TXT file `[JAR file location]/data/Briar.txt`. Advanced users are welcome to update data directly by editing the file.\
:exclamation: Note that if your changes make the format invalid, Briar may behave in unexpected ways.

## Command Reference

### Listing Commands
Display a list of all commands.\
Format: `help`\
Example: \
`help`\
Expected Output:
```
Here's the list of all commands:
todo <description> | Add a todo task to the list
deadline <description> /by <YYYY-MM-DD> | Add a deadline task to the list
event <description> /from <time> /to <time> | Add a event task to the list
delete <index> | Delete the task at the specified index
bye | Exit the program
find <keyword> | Find tasks that contain the specified keyword
help | Displays a list of all commands
list | Displays a list of all tasks
mark <index> | Mark the task at the specified index
unmark <index> | Unmark the task at the specified index
```

### Adding a Todo Task
Add a task with a description.\
Format: `todo <description>`\
Example: \
`todo Math Tutorial 1`\
Expected Output:
```
Okie! I've added this task:
[T][ ] Math Tutorial 1
You now have 1 tasks in the list!
```

### Adding a Deadline Task
Add a task with a deadline.\
Format: `deadline <description> /by <YYYY-MM-DD>`\
Example: \
`deadline Science homework /by 2025-10-20`\
Expected Output:
```
Okie! I've added this task:
[D][ ] Science homework (by:Oct 20 2025)
You now have 2 tasks in the list!
```

### Adding an Event Task
Add a task with a start and end time.\
Format: `event <description> /from <time> /to <time>`\
Example: \
`event Team project meeting /from 2pm /to 4pm`\
Expected Output:
```
Okie! I've added this task:
[E][ ] Team project meeting (from:2pm to:4pm)
You now have 3 tasks in the list!
```

### Listing the Task List
Display the whole Task List.\
Format: `list`\
Example: \
`list`\
Expected Output:
```
Here's your task list:
1. [T][ ] Math Tutorial 1
2. [D][ ] Science homework (by:Oct 20 2025)
3. [E][ ] Team project meeting (from:2pm to:4pm)
```

### Deleting a Task
Delete a task from the list.\
Format: `delete <index>`\
Example: \
`delete 1`\
Expected Output:
```
Okie! I've removed this task:
[T][ ] Math Tutorial 1
You now have 2 tasks in the list!
```

### Marking a Task
Mark a task in the list as done.\
Format: `mark <index>`\
Example: \
`mark 2`\
Expected Output:
```
Nice! I've marked this task as done:
[D][X] Science homework (by:Oct 20 2025)
```

### Unmarking a Task
Mark a task in the list as not done.\
Format: `unmark <index>`\
Example: \
`unmark 2`\
Expected Output:
```
OK, I've marked this task as not done yet:
[D][ ] Science homework (by:Oct 20 2025)
```

### Finding Tasks
Find tasks containing the specified keyword.\
Format: `find <keyword>`\
Example: \
`find Math`\
Expected Output:
```
Okie! Here are the tasks that match in your list:
1. [T][ ] Math Tutorial 1
```

### Exiting Briar
Exits and closes the program.\
Format: `bye`\
Example: \
`bye`\
Expected Output:
```
Bye. Hope to see you again soon!
```