# ALUNE

your personal task manager chatbot~

## available commands

**commands:**
+ list | lists the current tasks
+ todo (task name) | adds a todo task to the list
+ deadline (task name) /by (deadline^) | adds a deadline task to the list
+ event (task name) /from (start^) /to (end^) | adds an event task to the list
+ mark (number) | marks the numbered task as done
+ unmark (number) | marks the numbered task as undone
+ delete (number) | deletes the numbered task from the list
+ clear | deletes all tasks from the list
+ find | lists tasks that include the name provided
+ undo | reverses the most recent change
+ update | clears all done tasks from the lsit
+ bye | closes the program
^must be in dd/mm/yyyy hhmm format where hhmm is 24h time

**example uses:**
+ todo write report
+ deadline return book /by 20/08/2025 1200
+ event birthday party /from 21/08/2025 1700 /to 21/08/2025 2100
+ mark 1
+ find math
