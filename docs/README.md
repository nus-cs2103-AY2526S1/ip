# Johnny User Guide

Have you ever had issues remembering things you need to do? Always forget deadlines and special events? Fear not, Johnny bot is here to help you!

To make it user-friendly, Johnny has been designed to be:

- Lightweight
- Easy to download and run
- Command-based (with intuitive commands)

How to download?

1. Ensure you have Java 17 installed. If you don't, you can download it [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2. Click on this [link](https://github.com/ndhhh/ip/releases)
3. Download the latest jar file, and put in a folder
4. Launch the file by right clicking. If you prefer the terminal, `cd` into the folder containing the jar file, and run `java -jar johnny.jar`.
5. Now you're good to go!

Some features you can expect are:

- [x] Adding tasks with deadlines or specific events
- [x] Deleting tasks
- [x] Finding tasks
- [ ] And more!

And here's a list of commands that you can use to get started!

```
todo <task name>
deadline <task name> /by <dd/MM/yyyy>
event <task name> /from <dd/MM/yyyy HH:mm> /to <HH:mm>
period <task name> /between <dd/MM/yyyy> /and <dd/MM/yyyy>
list
delete
mark
unmark
find
```

You can refer below for some sample usage!

## Viewing help: `help`

Shows a message explaining all the different commands.

Format: `help`

## Saving changes made to task list: `bye`

Saves all the changes you made to your task list, allowing you to close the program safely.

Format: `bye`

**<ins>Note:</ins>** If you do not run this command and exit the program, your changes will not be saved.

## Listing tasks: `list`

This displays all the current saved tasks in a list.

Format: `list`

## Adding Todo tasks: `todo`

This represents a task that you need to do, with no specific deadlines or timings.

Format:

```
todo <task name>
```

After adding this new todo task, you should expect to see the following in the dialog box.

```
Task added:
[T][ ]<Task name>
Now you have <number> tasks in your list.
```

## Adding deadlines: `deadline`

This represents a task that you need to do by a specified deadline in dd/MM/yyyy.

Format:

```
deadline <task name> /by <dd/MM/yyyy>
```

After adding this new deadline task, you should expect to see the following in the dialog box.

```
Task added:
[D][ ]<Task name> by: <deadline>
Now you have <number> tasks in your list.
```

## Adding event tasks: `event`

This represents an event that you need to do on a specific date, between 2 timings.

Format:

```
event <task name> /from <dd/MM/yyyy HH:mm> /to <HH:mm>
```

After adding this event task, you should expect to see the following in the dialog box.

```
Task added:
[E][ ]<task name> from: <dd/MM/yyyy HH:mm> to: <dd/MM/yyyy>
Now you have <number> tasks in your list.
```

## Adding do within period tasks: `period`

This represents a task that you need to do within a specified period in dd/MM/yyyy.

Format:

```
period <task name> /between <dd/MM/yyyy> /and <dd/MM/yyyy>
```

After adding this new period task, you should expect to see the following in the dialog box.

```
Task added:
[P][ ]<Task name> between: <start date> and: <end date>
Now you have <number> tasks in your list.
```

## Mark / unmarking a task as done: `mark` or `unmark`

This marks/unmarks a task as done.

Format:

```
mark <number>
unmark <number>
```

After marking a task as done, you should expect to see the following in the dialog box.

```
Marked task as done:
[<task type>][X]<task name and details>
```

And the following for unmarking a task.

```
Marked task as undone:
[<task type>][X]<task name and details>
```

**Note:** This command only accepts a number between **1** and the **number of tasks in your list**.

For example: If you have **5** tasks in your list, the command only accepts a number between **1 and 5**.

## Deleting a task: `delete`

This deletes a task from the list.

Format:

```
delete <number>
```

After deleting a task, you should expect to see the following in the dialog box.

```
Task deleted successfully.
<task details>
Now you have <number> task in your list.
```

**Note:** This command only accepts a number between **1** and the **number of tasks in your list**.

For example: If you have **5** tasks in your list, the command only accepts a number between **1 and 5**.

## Finding a task: `find`

This finds all relevant tasks which have names that contain the text you are searching for.

Format:

```
find <text>
```

You should see a list of tasks with names that contain the text you searched for.
If no such tasks exist, a message `No tasks in list` will be displayed.
