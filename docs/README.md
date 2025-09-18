# Jimbot User Guide
![Ui.png](Ui.png)

Jimbot is your personal jimbo for managing your tasks  ∠( ᐛ 」∠)＿
You can add, delete, list, search and mark all your tasks with Jimbot!
Lighten your mind and go play with Jimbot ♪♪

## Say HI
Greet your jimbo!

Example: `hi`

```
expected output
Hello♪ I'm Jimbot!
What can I do for you?
(^з^)-☆
```
## Adding deadlines

Deadline tasks are tasks that need to be done before a specific date/time.

*You can include either date or time or BOTH (in dd/MM/yyyy HH:mm).

Format: `deadline <task description> /by <date/time>`

Example: `deadline Finish IP /by 19/09/2025 2359`


```
expected output
Cool beans~ I've added this task:
 [D][ ] Finish IP 
        (BY: Sept 19 2025, 23:59)
You now have 1 tasks in the list!
(￣^￣)ゞ
```

## Adding events

Event tasks are tasks that start at a specific date/time and end at a specific date/time.

*You can include either date or time or BOTH (in dd/MM/yyyy HH:mm).

Format: `event <task description> /from <date/time> /to <date/time>`

Example: `event Recess week /from 22/09/2025 /to 28/09/2025`
```
expected output
Cool beans~ I've added this task:
 [E][ ] Recess week 
        (FROM: Sept 22 2025 TO: Sept 28 2025)
You now have 2 tasks in the list!
(￣^￣)ゞ
```
## Adding todos

ToDo tasks are tasks without any date/time attached to them.

Format: `todo <task description>`

Example: `todo Make dinner`
```
expected output
Cool beans~ I've added this task:
 [T][ ] Make dinner
You now have 3 tasks in the list!
(￣^￣)ゞ
```

## Listing
Ask Jimbot to show you all the tasks in your list.
*It'll be sorted in chronological order with ToDos at the bottom.

Example: `list`

```
expected output
Here are the tasks in your list:
 [D][ ] Finish IP 
        (BY: Sept 19 2025, 23:59)
 [E][ ] Recess week 
        (FROM: Sept 22 2025 TO: Sept 28 2025)
 [T][ ] Make dinner    ノ( ゜-゜ノ)
```

## Marking and unmarking
Once you're done with your task, you can ask Jimbot to mark them as such.
If you marked the wrong task, you can simply ask him to unmark it.

Format: `mark` or `unmark <task index in list>`

Example: `mark 1` or `unmark 1`
```
expected output
Nice♪ I've marked this task as done:
   [D][X] Finish IP 
        (BY: Sept 19 2025, 23:59)
 ♪ ｖ(＾＿＾ｖ)♪ ~~ ♪(ｖ＾＿＾)ｖ
 
Ogayy, I've marked this task as not done yet:
   [D][ ] Finish IP 
        (BY: Sept 19 2025, 23:59)
(｀_´)ゞ
```

## Deleting 
Remove tasks from your list!

Format: `delete <task index in list>`

Example: `delete 3`

```
expected output
Aite! I've removed this task:
    [T][ ] Make dinner
You now have 2 tasks in the list!
(─.─)ゞ
```

## Searching
Search for tasks in your list based on their description, or dates!

Formats: 

`find <keyword>` to find tasks that share the same/similar description

`dd/MM/yyyy` or `today` to find tasks on that date/today

Examples: `find ip`,
`23/09/2025`, `today`

```
expected output
Here are the tasks in your list:
 [D][ ] Finish IP 
        (BY: Sept 19 2025, 23:59)  ノ( ゜-゜ノ)
        
Your tasks for that date:
 [E][ ] Recess week 
        (FROM: Sept 22 2025 TO: Sept 28 2025)  (・ω・)ノ

(ㄏ-_-)ㄏ You've nothing to do today...
```

## Clearing
Clear your list of all tasks!

Example: `clear`

```
expected output
(＾∀＾)ゞ I've cleared your tasks! Go play~
```

## Getting help
Request for the list of commands Jimbot accepts.

Example: `help`

```
expected output
Here are the commands you can use:
  (input date in dd/MM/yyyy)
  bai/bye/goodbye
  clear
  deadline
  delete
  event
  find
  hi
  list
  mark
  today
  todo
  unmark         _(•̀ω•́ 」∠)_
```

## Byeinggg
Put your jimbo to sleep...

Example: `bye`, `bai`, `goodbye`

```
expected output
\(^O^) Baiii~ Hope to see you again soon!
```