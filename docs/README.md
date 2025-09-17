# Project Haru 🐴🌸

> *I won't let losing get me down! Urara, la la la~!*

**Haru** is a cheerful chatbot that helps you manage your tasks, inspired by the popular
racehorse [Haru Urara](https://umamusume.fandom.com/wiki/Haru_Urara).  
No matter how long your to-do list is, she's always here to encourage you!

## Commands

### `todo TASK_DESCRIPTION`

Adds a to-do task.

```text
todo read book
```

### `deadline TASK_DESCRIPTION /by DATE_TIME`

Adds a deadline task with a due date.

Accepted date formats:

- `dd/MM/yyyy HH:mm`
- `yyyy-MM-dd HH:mm`

```text
deadline submit assignment /by 05/09/2025 23:59
deadline submit assignment /by 2025-09-05 23:59
```

### `event TASK_DESCRIPTION /from DATE_TIME /to DATE_TIME`

Adds an event task with a start and end time.

Accepted date formats:

- `dd/MM/yyyy HH:mm`
- `yyyy-MM-dd HH:mm`

```text
event project meeting /from 06/09/2025 14:00 /to 06/09/2025 16:00
event project meeting /from 2025-09-06 14:00 /to 2025-09-06 16:00
```

### `list`

Shows all tasks in the list.

```text
list
```

### `mark TASK_NUMBER`

Marks a task as done.

```text
mark 2
```

### `unmark TASK_NUMBER`

Marks a task as not done.

```text
unmark 2
```

### `delete TASK_NUMBER`

Deletes a task by its number.

```text
delete 3
```

### `find KEYWORD`

Finds tasks matching a keyword.

```text
find assignment
```

### `tag TASK_NUMBER /name TAG_NAME`

Adds a tag to a task.

```text
tag 1 /name urgent
```

### `filter TAG_NAME`

Shows all tasks with a specific tag.

```text
filter urgent
```

### `bye`

Closes the application.

```text
bye
```

✨ Haru will always save your tasks between sessions, and cheer you on while you get things done!
