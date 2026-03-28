# Hachi
>inspired by Hachiko, an akita dog that was remembered for his remarkable loyalty

## Just like its namesake it will:
+ be friendly (to use)
* guard (save) your task list
- perform your task quickly

## All you need to do is:
1. download it from [here](https://github.com/ren0716/ip)
2. double-click it
3. add your tasks
4. let it manage it for you

And it is **FREE!!!!!**

## Current features:
###  Add a task: *todo deadline event*
creates and adds a task to your task list

Format:
>todo (task description)

>deadline (task description) /by [deadline timing]

>event (task description) /from [event start] /to [event end]

+ all [ ] fields to be filled in dd/mm/yyyy ha format

example:
>deadline do homework /by 5/5/2025 2359

This creates a deadline type task that has a deadline timing of 5/5/2025 2359

## List all task: *list*
displays all task

Format:
>list

## Delete a task: *delete*
removes a specified task

Format:
>delete (task index)

example:
>delete 1

This removes task no.1 on the list

## Do/Undo a task: *mark unmark*
change the completion status of a task

Format:
>mark (task index)

>unmark (task index)

+ marked task will be displayed with an [X] with the list command
- unmarked task will be displayed with an [ ] with the list command

## Save and Exit: *bye*
saves your current task list and exits the app

Format:
>bye

## Locating a task by description: *Find*
displays a list of task that matches given keyword

Format:
>find (keyword)

+ search is case insensitive. e.g hello will match Hello
* order of keywords matters. e.g hello there will not match there hello
- partial words will be matched. e.g he will match hello

## Add notes to task: *note*
adds additional information to a task

Format:
>note (task number) (description)

example:
>note 1 wear dress

This adds a note to task 1 that captures:
+ time of creation according to local device time
+ the description *wear dress*

## Display notes of a task: *display*
show the notes of a task

Format:
>display (task number)

example:
>display 1

This will display the note attached to task 1