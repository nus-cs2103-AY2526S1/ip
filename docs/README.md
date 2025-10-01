# Panda User Guide

![Screenshot of Panda GUI](./Ui.png)

Panda is a chatbot that helps you to manage all your tasks. You just 
have to type the relevant commands and send them to Panda, and then you can view them easily.

## Starting Off
1. Make sure that your computer supports java `17` or above.\
For **Mac Users**, you need to have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
2. Download the latest `.jar` file from [here](https://github.com/udonzzz/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Panda.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar panda.jar` command to run the application.
5. Upon starting, type the command in the reply box and press **Enter** on your keyboard or **Send** button in the GUI.\
If you are uncertain on what commands to use, type **help** to get the list of actions available.

## Features

### Viewing help: `help`
Shows the list of actions that are available for you to use.\
![Screenshot of help action](./help.png)\
Format: `help`

### Add todo task: `todo`
Adds a todo task to your list.\
![Screenshot of todo action](./todo.png)\
Format: `todo {task description}`\
Example: `todo read book`

### Add deadline task `deadline`
Adds a deadline task to your list.\
![Screenshot of deadline action](./deadline.png)\
Format: `deadline {task description} /by {deadline dateTime}`\
Example: `deadline return book /by 2025-09-20 12:00`

### Add event task `event`
Adds an event task to your list.\
![Screenshot of event action](./event.png)\
Format: `event {task description} /from {start dateTime} /to {end dateTime}`\
Example: `event project meeting /from 2025-09-20 14:00 /to 2025-09-20 15:00`


### List tasks `list`
List all the tasks that are in your list.\
![Screenshot of list action](./list.png)\
Format: `list`

### Mark task `mark`
Mark the specified task as completed.\
![Screenshot of mark action](./mark.png)\
Format: `mark {index of task}`\
Example: `mark 1`

### Unmark task 'unmark'
Mark the specified task as incomplete.\
![Screenshot of unmark action](./unmark.png)\
Format: `unmark {index of task}`\
Example: `unmark 1`

### Find task `find`
Find tasks that contain the keyword in their description.\
![Screenshot of find action](./find.png)\
Format: `find {keyword}`\
Example: `find book`

### Delete task `delete`
Delete a specified task from the list.\
![Screenshot of delete action](./delete.png)\
Format: `delete {index of task}`\
Example: `delete 2

### Exit program `bye`
Exits the program.\
Format: `bye`
