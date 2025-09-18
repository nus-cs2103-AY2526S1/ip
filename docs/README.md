# BobbyWasabi Task & Client Manager

![BobbyWasabi Logo](./Ui.png)

**BobbyWasabi** is a lightweight JavaFX desktop application that helps you manage tasks, deadlines, events, and client information efficiently. The application supports intuitive command-based inputs and provides structured responses for task and client management.

---
# Managing Tasks

---

## Listing All Tasks

Shows all tasks in your task list.

**Example:** `list`

Displays a numbered list of all tasks, including todos, deadlines, and events.

---

## Adding Todos

Adds a task to your todo list.

**Example:** `todo, Read book`

Adds a new task "Read book" to your task list.

Output: [] Read book

---

## Adding Deadlines

Adds a task with a deadline.
Deadline format: `dd/MM/yyyy HHmm` (e.g., `20/12/2025 2359`) 

**Example:** `deadline, Submit report, 20/12/2025 2359`

Adds a new task "Submit report" with a deadline of Dec 20 2025 2359.

---

## Adding Events

Adds a task with a start and end time.

Start and End Date and time format: `dd/MM/yyyy HHmm` (e.g., `22/12/2025 1000`)

**Example:** `event, Project meeting, 22/12/2025 1000, 22/12/2025 1200`

Adds a new event "Project meeting" from Dec 22 2025 1000 to Dec 22 2025 1200.

---

## Marking a Task as Done

Marks a task as completed.

**Example:** `mark, 1`

Marks the first task in the list as done.

---

## Unmarking a Task

Marks a previously done task as not done.

**Example:** `unmark, 1`

Marks the first task in the list as not done.

---

## Finding Tasks

Searches for tasks containing a keyword.

**Example:** `find, book`

Displays all tasks containing "book".

---

## Deleting Tasks

Removes a task from your task list.

**Example:** `delete, 1`

Deletes the first task in your task list.

---

# Managing Clients

---
## Listing All Clients

Displays all clients in the system with their full details.

**Example:**  
`clients`

Shows all clients

---

## Adding a Client

Adds a new client with the provided name, contact number, age, occupation, and current policies.

Phone Number: Strictly a 8-digit number with no spacing or special characters in between

Age: Must be an integer  

**Example:**  
`addclient, John Doe, 12345678, 30, Engineer, PolicyA`

Adds "John Doe" as a new client in the system.

---

## Editing a Client

Updates the specified field (name, contact number, age, occupation, or current policies) of the client at the given index. Indexing starts from 1.

Field names: `name`, `contactnumber`, `age`, `occupation`, `currentpolicies`

**Example:**  
`editclient, 1, name, Jane Doe`

Changes the name of client #1 to "Jane Doe".

---

## Deleting a Client

Removes the client at the specified index from the client list.

**Example:**  
`deleteclient, 1`

Deletes client #1 from the system.

---

## Saying Goodbye

Sends a farewell message.

**Example:** `bye`
---

## Handling Unrecognized Commands

Any unrecognized command will return an error message.

**Example:** `foobar`

