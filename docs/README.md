# King User Guide

> I long to accomplish a great and noble task, but it is my chief duty to accomplish small tasks as if they were great
> and noble. ~ Helen
> Keller ([source](https://www.goodreads.com/quotes/299032-i-long-to-accomplish-a-great-and-noble-task-but))

![Image of GUI of King Chatbot](Ui.png)

The King is there to assist you with your *small-task management 24/7* ⏱️. With the following features:

- Manage todo tasks, deadlines and events
- Search for tasks
- Track completion
- Sort by priority

## Adding tasks

The King allows you to add three types of tasks: todos, deadlines and events.

Sample: `event SoC Career Fair /priority VH /from 2025-10-23 /to 2026-01-10`

```
It is decreed! I have added this task to thy realm: 
[E][ ][Very High] SoC Career Fair (from: 23 Oct 2025 to: 10 Jan 2026) 
Thou now hold 5 tasks in thy service.
```

## Searching tasks

You can also search for tasks with multiple string texts.

Sample: `find SoC CS2103`

```
Lo! These tasks dost match thy search:
1. [D][ ][Low] Submit CS2103 iP (by: 31 Dec 2025) 
4. [E][X][Very High] Submit CS2103 tP (by: 31 Dec 2025)
5. [E][ ][High] SoC Career Fair (from: 23 Nov 2025 to: 31 Dec 2025)
Thou now hold 5 tasks in thy service.
```

## Find by deadline

You can search for tasks by deadline too!

Sample: `due 2025-10-23`

```
Behold! These are the tasks that dwell within thy service: 
1. [D][ ][Low] Submit CS2103 iP (by: 31 Dec 2025) 
```

## Mark / Unmark completion

You can mark the completion of any type of task.

Sample: `mark 3`

```
Tasks decreed for 31 Dec 2025 are as follows:
3. [D][X][Low] Submit CS2103 iP (by: 31 Dec 2025)
```


