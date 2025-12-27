# User Guide to Tension

Tension is an app that allows you to store tasks categorised into todos, deadlines and events. You can perform actions such as add, mark, unmark, tag, delete and list to manage your tasks.

# To start

1. Ensure you have Java 17 or above installed in your Computer.
   Mac users: Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
2. Download the latest .jar file from [here](https://github.com/porcupinezzz/ip/blob/master/tension.jar).
3. Copy the file to the folder you want to use as the home folder for your AddressBook.
4. Within the same folder, create a file named **data.txt**
5. Open a command terminal of your choice and run `java -jar tension.jar`
6. A GUI should pop up and you can start typing in commands of your choice.

# Features

**All commands should be typed in lower-case**

## Listing all tasks `list`

Simply type `list` to retrieve all the tasks in your memory

## Adding a task

### Adding a todo `todo`

Adds a todo task to memory

Format: `todo DESCRIPTION`

### Adding a deadline `deadline`

Adds a deadline task to memory

Format: `deadline DESCRIPTION/by DATE(in YYYY-DD-MM)`

### Adding an event `event`

Adds an event task to memory

Format: `event DESCRIPTION/from TIME_START/to TIME_END`

## Deleting a task `delete`

Deletes a task from memory

Format: `delete LINENUMBER`

## Marking/Unmarking a task `mark` `unmark`

Marks a task as complete/unmarks a task as complete

Format: `mark LINENUMBER` or `unmark LINENUMBER`

## Finding a task `find`

Finds a task that contains the word **PARAM**

Format: `find PARAM`

## Tag a task `tag`

Tags a task with tag **PARAM**

Format: `tag PARAM`

## Say bye `bye`

Greets bye to the bot, simply type `bye`