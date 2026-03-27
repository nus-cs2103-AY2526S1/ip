# Joe User Guide

Joe is a simple **JavaFX chatbot** that helps you manage your tasks in a conversational way.  
You can add todos, deadlines, and events, mark them as done, delete them, and list them — all using natural commands.

---

## Adding todos

Adds a basic task without any date or time attached.


**Format:**  

    todo Make notes

**Output:**  

Got it. I've added this task: 

[T][0] Make notes

Now you have 4 tasks in the list.

## Adding deadlines

Adds a task that has a dealine attached to it.

**Format:**  

    deadline Finish homework /by 10pm

**Output:**  

Got it. I've added this task:

[D][0] Finish homework (by: 10pm)

Now you have 4 tasks in the list.

## Adding events

Adds a task that has a start and end date. The start and end date is expected to be in the YYYY-MM-DD format.

**Format:**  

    event Japan trip /from 2025-06-01 /to 2025-06-14

**Output:**  

Got it. I've added this task:

[E][0] Japan trip (from: Jun 01 2025 to: Jun 14 2025)

Now you have 1 tasks in the list.


## Mark tasks 

Marks task as done and undone depending on its completion status. This completion status is represented by the 0 and 1 next to the task type. 0 means that the task has yet to be completed, while 1 means the task is done.

**Format:**  

    mark 1
    unmark 1

**Output:**

Ok, I've marked the task as done:

[E][1] Japan trip (from: Jun 01 2025 to: Jun 14 2025)

and

Ok, I've marked the task as not done:

[E][0] Japan trip (from: Jun 01 2025 to: Jun 14 2025)

## Listing tasks

Lists all the tasks that are currently in the list.

**Format:**  

    list

**Output:**

Your To-Do List:
1. [E][0] Japan trip (from: Jun 01 2025 to: Jun 14 2025)

## Deleting tasks

Deletes the selected task from the list.

**Format:**  

    delete 1

**Output:**

Got it. I've removed this task from your list:

[E][0] Japan trip (from: Jun 01 2025 to: Jun 14 2025)

Now you have 0 tasks in the list.

## Finding tasks

Finds all tasks that contains the same word as the word queried.

**Format:**  

    find Japan

**Output:**

Here are the matching tasks in your list:
1. [E][0] Japan trip (from: Jun 01 2025 to: Jun 14 2025)

## Bye (Exiting the chat)

Closes the program and ends the chat.

**Format:**  

    bye

**Output:**

Goodbye!
