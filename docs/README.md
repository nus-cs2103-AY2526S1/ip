# Paul

Better call Paul! Your trusty task assistant.🤓

![Ui.png](Ui.png)

Paul is your friendly **personal assistant** for managing tasks.

Here's how you can start!

1. Download it from [here](https://github.com/TeoShiYuan/ip/releases/tag/A-Release).
2. Open a command window in the folder with the `jar` file.
3. Run it with `java -jar paul.jar`.
4. Add your tasks and deadlines.
5. Let Paul manage your life for you. 🎉

## Features

Here are some features of Paul:

* [x]  **Add tasks**: ToDos, Deadlines and Events.
* [X]  **List** all your tasks that you have added.
* [x]  **Mark / Unmark tasks** to track progress _easily_.
* [x]  **Delete tasks** after completing them.
* [x]  **Find tasks** _quickly_ by keyword.
* [x]  **Storing and loading** of tasks to _save_ your progress.

## Adding tasks

There are 3 types of tasks you can add.

### ToDo tasks `todo`

ToDos are tasks that do not have a specified date.

Format: `todo <description>`

* Adds a todo task with the given description.
* `description` cannot be empty.

Example: `todo homework`

```
Let's go! Task added to the system:
[T][ ] homework
Now tracking 1 tasks in total.
```

### Deadline tasks `deadline`

Deadlines are tasks that has a deadline.

Format: `deadline <description> /by <deadline>`

* Adds a deadline task with the given description and deadline.
* `description` cannot be empty.
* `/by` must be present in the command.
* `<deadline>` must be a date in the format `yyyy-mm-dd`. e.g. `2019-10-15`

Example: `deadline homework /by 2025-09-19`

```
Let's go! Task added to the system:
[D][ ] homework (by: Sep 19 2025)
Now tracking 2 tasks in total.
```

### Event tasks `event`

Events are tasks that has a start and end date.

Format: `event <description> /from <start> /to <end>`

* Adds an event task with the given description and start/end date.
* `description` cannot be empty.
* `/from` and `/to` must be present in the command.
* `<start>` and `<end>` must be a date in the format `yyyy-mm-dd`. e.g. `2019-10-15`

Example: `event recess /from 2025-09-22 /to 2025-09-26`

```
Let's go! Task added to the system:
[E][ ] recess (from: Sep 22 2025, to: Sep 26 2025)
Now tracking 3 tasks in total.
```

## List all tasks `list`

Shows a list of all tasks in the task list.

Format: `list`

Example: `list`

```
Here are the tasks in your list:
1. [T][ ] homework
2. [D][ ] homework (by: Sep 19 2025)
3. [E][ ] recess (from: Sep 22 2025, to: Sep 26 2025)
```

## Mark/Unmark tasks `mark` / `unmark`

Marks/unmarks a task in the task list, indicating the task is completed/incomplete.

Format: `mark <task number>` / `unmark <task number>`

* Marks/unmarks the task at the specified `<task number>`.
* `<task number>` must not be empty.
* `<task number>` must be a **positive integer** that is **within the range** of the list.

Example: `mark 1`

```
Good job! I've marked this job as done:
[T][X] homework
```

Example: `unmark 1`

```
Okay, I've reactivated this task for you:
[T][ ] homework
```

## Delete tasks `delete`

Deletes a task from the task list.

Format: `delete <task number>`

* Deletes the task at the specified `<task number>`.
* `<task number>` must not be empty.
* `<task number>` must be a **positive integer** that is **within the range** of the list.

Example: `delete 3`

```
OK! Deleting task from the system:
[E][ ] recess (from: Sep 22 2025, to: Sep 26 2025)
Now tracking 2 tasks in total.
```

## Find tasks `find`

Finds all tasks with description containing the keyword.

Format: `find <keyword>`

* Find is case-insensitive. e.g. `paul` will match `PAUL`.
* `<keyword>` must not be empty.
* Only the description is searched.

Example: `find work`

```
Yay! I've found these matches:
1. [T][ ] homework
2. [D][ ] homework (by: Sep 19 2025)
```

## Exiting the program `bye`

Exits the program.

Format: `bye`

```
Goodbye! Paul will miss you :(
```