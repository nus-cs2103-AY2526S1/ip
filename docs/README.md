# Pengu User Guide

![UI](Ui.png)

A penguin chatbot to help you remember your tasks, so you don't have to.

# Features
**Notes about the command format:**

- Words in angled brackets are fields to be supplied by the user. \
e.g. in `todo <description>`, description is a field which can be supplied as `todo Buy a pencil`.
- Datetime fields are shown in their required format. \
e.g. `<yyyy-MM-dd HH:mm>` would require something like `2025-05-05 10:30`. 

## Adding todos: `todo`
Adds a task that you want to do.

Format: `todo <description>`

Example: `todo Buy a pencil`

Example output: 
```
Got it. I've added this task:
  [T][ ] Buy a pencil
Now you have 1 tasks in the list.
```

## Adding deadlines: `deadline`

Adds a task with a deadline.

Format: `deadline <description> /by <yyyy-MM-dd HH:mm>`

Example: `deadline Submit PA1 /by 2025-09-20 23:59`

Example output: 
```
Got it. I've added this task:
  [D][ ] Submit PA1 (by: Sep 20 2025 23:59)
Now you have 2 tasks in the list.
```

## Adding events: `event`

Adds a event with a start and end date/time.

Format: `event <description> /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>`

Example: `event Attend CS2103T tutorial /from 2025-09-18 10:00 /to 2025-09-18 11:00`

Example output: 
```
Got it. I've added this task:
  [E][ ] Attend CS2103T tutorial (from: Sep 18 2025 10:00 to: Sep 18 2025 11:00)
Now you have 3 tasks in the list.
```

## Marking tasks as done: `mark`

Marks the task at the given index as done.

Format: `mark <index> `

- `<index>` has to be a positive integer from `1` to `N`, where `N` is the current length of the task list.

Example: `mark 2`

Example output: 
```
Nice! I've marked this task as done:
  [D][X] Submit PA1 (by: Sep 20 2025 23:59)
```

## Marking tasks as not done: `unmark`

Marks the task at the given index as not done.

Format: `unmark <index> `

- `<index>` has to be a positive integer from `1` to `N`, where `N` is the current length of the task list.

Example: `unmark 2`

Example output: 
```
OK, I've marked this task as not done yet:
  [D][ ] Submit PA1 (by: Sep 20 2025 23:59)
```

## Listing all tasks: `list`

Lists all tasks currently in the list.

Format: `list`

Example Output:
```
1. [T][ ] Buy a pencil
2. [D][ ] Submit PA1 (by: Sep 20 2025 23:59)
3. [E][ ] Attend CS2103T tutorial (from: Sep 18 2025 10:00 to: Sep 18 2025 11:00)
```

## Find tasks by substring: `find`

Finds all tasks whose description contains the given substring.

Format: `find <string_to_search>`

Examples: 
- `find t`
- `find pencil`

Example Outputs:
```
Here are the matching tasks in your list:
1. [D][ ] Submit PA1 (by: Sep 20 2025 23:59)
2. [E][ ] Attend CS2103T tutorial (from: Sep 18 2025 10:00 to: Sep 18 2025 11:00)
```
```
Here are the matching tasks in your list:
1. [T][ ] Buy a pencil
```

## Update a single field in a task: `update`

Updates the specified field in the task at the given index.

Format: `update <index> </field> <new_value>`
- `</field>` is the field to update, has to be `/desc` to update the description or one of the fields associated with the task type.

Examples: 
- `update 1 /desc Buy a pen`
- `update 2 /by 2025-09-21 23:59`

Example Outputs:
```
Great! I've updated the details of this task:
  [T][ ] Buy a pen
```
```
Great! I've updated the details of this task:
  [D][ ] Submit PA1 (by: Sep 21 2025 23:59)
```

## Quit the app: `bye`

Quits the app.

Format: `bye`
