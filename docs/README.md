# Dan User Guide

<img width="392" height="616" alt="Ui" src="https://github.com/user-attachments/assets/39f0e531-28bb-4255-bbbb-02a1defe2d23" />

Dan is a text-based chatbot that frees your mind of having to remember things you need to do.

## Features

### Listing added tasks

Command: list 

Outcome: displays all the tasks in tasklist

Example: `list`

### Adding deadlines

Command template: deadline (description of event) /by (deadline date)

Outcome: adds a deadline task to your task list

Example: `deadline assignment /by 01/01/2025`

### Adding events

Command template: event (description of event) /from (start date) /to (end date)

Outcome: adds a event task to your task list

Example: `event fair /from 01/01/2025 /to 02/01/2025`

### Adding todo tasks
Command template: todo (description of event) 

Outcome: adds a todo task to your task list

Example: `todo hw`

### Marking tasks as done

Command template: mark (index of task in list starting from 1)

Outcome: marks task as done in your task list

Example: `mark 1`

### Deleting tasks 

Command template: delete (index of task in list starting from 1)

Outcome: deletes task from your task list

Example: `delete 1`

### Finding tasks 

Command template: find (search string)

Outcome: returns tasks with descriptions matching the search string

Example: `find hw`

### Get reminders of upcoming tasks
Command template: remind /within (number of days from now)

Outcome: returns tasks with dates within number of specified days from current day

Example: `remind /within 2` 



