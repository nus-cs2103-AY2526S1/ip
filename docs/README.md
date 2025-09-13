# BobbyWasabi Task & Client Manager

![BobbyWasabi Logo](./Ui.png)

**BobbyWasabi** is a lightweight JavaFX desktop application that helps you manage tasks, deadlines, events, and client information efficiently. The application supports intuitive command-based inputs and provides structured responses for task and client management.

---

## Listing All Tasks

Shows all tasks in your task list.

Example: `list`

Displays a numbered list of all tasks, including todos, deadlines, and events.


## Adding Todos

Adds a task to your todo list.

Example: `todo, Read book`

Adds a new task "Read book" to your task list.

Output: [] Read book


## Adding Deadlines

Adds a task with a deadline.

Example: `deadline, Submit report, 20/12/2025 2359`

Adds a new task "Submit report" with a deadline of 20th Dec 2025, 23:59.


## Adding Events

Adds a task with a start and end time.

Example: `event, Project meeting, 22/12/2025 1000, 22/12/2025 1200`

Adds a new event "Project meeting" from 10:00 to 12:00 on 22nd Dec 2025.



---

## Marking a Task as Done

Marks a task as completed.

Example: `mark, 1`

Marks the first task in the list as done.



---

## Unmarking a Task

Marks a previously done task as not done.

Example: `unmark, 1`

Marks the first task in the list as not done.




---

## Finding Tasks

Searches for tasks containing a keyword.

Example: `find, book`

Displays all tasks containing "book".


## Deleting Tasks

Removes a task from your task list.

Example: `delete, 1`

Deletes the first task in your task list.


---

## Managing Clients

### Adding a Client

Example: `addclient, John Doe, 12345678, 30, Engineer, PolicyA`

Adds a new client with the provided details.


---

## Managing Clients

### Adding a Client

Example: `addclient, John Doe, 12345678, 30, Engineer, PolicyA`

Adds a new client with the provided details.


### Editing a Client

Example: `editclient, 1, name, Jane Doe`

Updates the first client's name to "Jane Doe".



### Deleting a Client

Example: `deleteclient, 1`

Removes the first client from the client list.


### Listing All Clients

Example: `clients`

Displays all clients with their details.



---

## Exiting the Application

Example: `bye`

Closes the BobbyWasabi application with a farewell message.



---

## Handling Unrecognized Commands

Any unrecognized command will return an error message.

Example: `foobar`

