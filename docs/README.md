# Steven User Guide

![Screenshot of UI](./Ui.png)

**Nothing beats a task manager chatbot with easy to use commands.
And now, you can save 50 pounds per person,
that's 200 pounds for a family of four!!!**

## Adding tasks
The commands below will add a certain task to the Task List

### ToDo tasks
- ToDo tasks are the simplest form of tasks, only having a description
- Command: `todo <description>`
- Example:`todo shower`
- Output: 
```
OK, I've added this task: [T][] shower
Now there are 1 tasks in your list
```

### Deadline tasks
- Tasks with a description and a deadline
- Command: `deadline <description> /by <deadline>`
- The `<deadline>` must be in the format: `dd-mm-yyyy tttt`
  - `dd` represents the day
  - `mm` represents the month
  - `yyyy` represents the year 
  - `tttt` represents the time in 24-hour format 

- Example: `deadline submit assignment /by 23-09-2025 2359`
- Output:
```
OK, I've added this task: [D][] submit assignment (by: 23 Sept 2025 23:59)
Now there are 2 tasks in your list
```

### Event tasks
- Tasks that start and end at a specific date and time
- Command: `event <description> /from <start> /to <end>`
- The `<start>` and `<end>` must be in the format:`dd-mm-yyyy tttt`, similar to `Deadline` tasks
- Example: `event SOC Career Fair /from 16-09-2025 1600 /to 16-09-2025 2000`
- Output: 
```
OK, I've added this task: [E][ ] SOC Career Fair (from: 16 Sept 2025 16:00 to: 16 Sept 2025 20:00)
Now there are 3 tasks in your list
```

### Fixed duration tasks
- Tasks with a fixed duration (in hours) 
- Command: `fixed-duration <description> /duration <duration>`
- Example: `fixed-duration sleep /duration 3`
- Output: 
```
OK, I've added this task: [FD][ ] sleep (needs 3 hours)
Now there are 4 tasks in your list
```

## Listing tasks
- Tell Steven to show your task list 
- Command: `list`
- Output:
```
Here are the tasks in your list: 
    1. [T][ ] shower
    2. [D][ ] submit assignment (by: 23 Sept 2025 23:59)
    3. [E][ ] SOC Career Fair (from: 16 Sept 2025 16:00 to: 16 Sept 2025 20:00)
    4. [FD][ ] sleep (needs 3 hours)
```

## Delete a task
- Delete a task by specifying its task number in the list 
- Command: `delete <number>`
- Example: `delete 1`
- Output:
``` 
OK, DELETE THIS ONE ALR:
[T][ ] shower
Now ur list got 3 task
```

## Find a task
- Search for a task based on how its listed in the list
- Command: `find <keyword>`
- Example: `find submit`
- Output:
```
Here are the matching tasks in your list:
1. [D][ ] submit assignment (by: 23 Sept 2025 23:59)
```

## Marking tasks
- You can mark your tasks as done or not done
- Tasks marked as done will have an `X` in its second box

### Mark task as done
- Mark a task as done by specifying its number in the list
- Command: `mark <number>`
- Example: `mark 2`
- Output:
```
OK, I will mark this task as done 
[E][X] SOC Career Fair (from: 16 Sept 2025 16:00 to: 16 Sept 2025 20:00)
```

### Unmark task
- Mark a task as not done by specifying its number in the list
- Command: `unmark <number>`
- Example: `unmark 2`
- Output:
```
OK, I will mark this task as not done 
[E][ ] SOC Career Fair (from: 16 Sept 2025 16:00 to: 16 Sept 2025 20:00)
```

## Exit the chatbot
- simply type the command `bye` and Steven will exit its program

## Errors
- As human beings, we can always make errors when typing in a command or specifying a parameter
- Steven has error detection and handling, and it will tell you what error you have made when typing a command
- You can then easily find where you have made an error and correct it immediately 

## Unknown command
- Unlike certain chatbots, if you type gibberish by mistake, Steven will not exit and terminate the program because 
it has been specially designed to handle unknown commands
- Steven will politely respond with `????` when the command you have sent is unknown to Steven

## Real-time local save
- Steven will save your task list to your local computer every time it changes
- Even if you don't exit the chatbot in the way it was designed, i.e. by using the `bye` command, Steven will still 
save your latest changes in your task list and you will be able to retrieve it the next time you run Steven

## Amazing GUI
- This one doesn't need any explanation, just run it and be wowed