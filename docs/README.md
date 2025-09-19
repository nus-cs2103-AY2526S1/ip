# Jerome
Jerome is a chatbot that helps you manage your tasks.

## Adding todo task
Adds a todo task.  
Format: todo description   
Example: `todo read book`

## Adding deadline task
Adds a deadline task.  
Format: deadline description /by yyyy-mm-dd  
Example: `deadline return book /by 2025-12-02`

## Adding event task
Adds an event task.  
Format: event description /from yyyy-mm-dd /to yyyy-mm-dd  
Example: `event winter holiday /from 2025-12-12 /to 2025-12-29`

## List all tasks
Lists all tasks.  
Format: list

## Mark task
Marks task as done.  
Format: mark taskIndex  
Example: `mark 1`

## Unmark task
Marks task as not done.  
Format: unmark taskIndex  
Example: `unmark 1`

## Delete task
Deletes task from list.  
Format: delete taskIndex  
Example: `delete 1`

## Find tasks
Finds all tasks that contain given keyword.  
Format: find keyword  
Example: `find book`

## Reschedule task
Reschedules a deadline or event task

- Deadline task
  - Format for deadline: reschedule taskIndex(of deadline task) yyyy-mm-dd
  - Example: `reschedule 2 2025-12-15`  

- Event task 
  - Format for event: reschedule taskIndex(of event task) yyyy-mm-dd yyyy-mm-dd
  - Example: `reschedule 3 2025-11-12 2025-11-29`
