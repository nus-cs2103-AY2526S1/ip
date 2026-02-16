# Salah User Guide

Salah is your personal chatbot assistant, inspired by the Egyptian King! It helps you keep track of your tasks, deadlines, and events — all through simple text commands. Just type your commands in the input box and Salah will respond instantly.

---

## Installation & Running

1. Ensure you have **Java 17** (or higher) and **Gradle** installed.
2. Clone this repository:
   ```
   git clone https://github.com/<your-username>/<your-repo>.git
   cd <your-repo>
   ```
3. Run the application
    ```
    ./gradlew run
    ```
Salah will launch in a JavaFX window with an input bar at the bottom and a scrollable task display

## Adding tasks

There are three kinds of tasks that can be added:
1. todo task
2. deadline task
3. event task

Use the **todo** command to add a simple task without a time or date
```
todo buy groceries
```
Expected outcome:
```
Got it. I've added this task:
[T][] buy groceries
```

Use the **deadline** command to add a task with a specific due date or time
```
deadline Submit report /by 2025-09-21 2359
```
Expected outcome:
```
Got it. I've added this task:
[D][] Submit report (by: Sep 21 2025, 11.59 pm)
```
- <ins>DATE</ins> must be in yyyy-MM-dd or d/M/yyyy format (e.g., 2025-09-21 or 21/9/2025)
- <ins>TIME</ins> is optional, in 24-hour HHmm or HH:mm format (e.g., 2359 or 23:59)

Use the **event** command to add tasks that span a start and end time
```
event Team meeting /from 2025-09-20 1400 /to 2025-09-20 1600
```
Expected outcome:
```
Got it. I've added this task:
[E][] Team meeting (from: Sep 20 2025, 2:00 pm to: Sep 20 2025, 4:00 pm)
```
- <ins>DATE</ins> must be in yyyy-MM-dd or d/M/yyyy format
- <ins>TIME</ins> is optional but recommended; use 24-hour HHmm or HH:mm
- <ins>/from</ins> must come before <ins>/to</ins>

## Listing tasks

See all your tasks at once with the **list** command
```
list
```
Expected outcome:
```
The Egyptian King has these tasks for you to complete:
1. [T][ ] Buy groceries
2. [D][ ] Submit report (by: 21 Sep 2025 23:59)
3. [E][ ] Team meeting (from: 20 Sep 2025 14:00, to: 20 Sep 2025 16:00)
```

## Marking tasks
Use the **mark** command followed by the **task number** to mark it complete
```
mark 2
```
The task number must be a valid index from the current task list

Expected outcome:
```
This task is marked at complete. Well done!
[D][X] Submit report (by: 21 Sep 2025 23:59)
```

## Unmarking tasks
Use the **unmark** command followed by the **task number** to mark it complete
```
unmark 1
```
The task number must be a valid index from the current task list.

Expected outcome:
```
Task has been marked as incomplete.
[T][ ] Buy groceries
```

## Deleting tasks
Remove a task when you no longer need it using the **delete** command followed by the **task number**
```
delete 3
```
The task number must be a valid index from the current task list.

Expected outcome:
```
The Egyptian King has removed this task:
[E][ ] Team meeting (from: 20 Sep 2025 14:00, to: 20 Sep 2025 16:00)
```

## Exiting the application
Exit the chatbot by using the **bye** command to close Salah
```
bye
```
Expected outcome:
```
Hope you have a great day! Remember, you'll never walk alone :))
```