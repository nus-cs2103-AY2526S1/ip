# <span style="color:pink"> Meo User Guide </span>

![alt text](Ui.png)

<span style="color:pink"> Meo </span>is a chatbot (or a catbot!) that will help you keep track of your tasks. 


## Adding deadlines

**Function:** Add an event to your task list, including the starting time and ending time. 

**Command:** `deadline [deadline description] /by [deadline time]`

**Example:** 
```ruby
deadline scratch sofa /by 5pm

Output:
Added: [D][] scratch sofa | by: 5pm 
```


## Adding to do

**Function:** Add a to do task to your task list. 

**Command:** `todo [to do task description]`

**Example:** 
```ruby
event eat grass

Output:
Added: [T][] eat grass
```


## Adding events

**Function:** Add an event to your task list, including the starting time and ending time. 

**Command:** `event [event description] /from [start time] /to [end time]`

**Example:** 
```ruby
event breakfast /from 11am /to 12pm

Output:
Added: [E][] lunch | from: 11am to: 12pm 
```


## Show all tasks

**Function:** Print the entire task list. 

**Command:** `list`

**Example:** 
```ruby
list

Output:
1. [D][] scratch sofa | by: 5pm 
2. [T][] eat grass
3. [E][] lunch | from: 11am to: 12pm 
```


## Mark task as done

**Function:** Mark a task as done. 

**Command:** `mark [index number of task]`

**Example:** 
```ruby
mark 1

Output:
Good job~ Your task is done!
[D][X] scratch sofa | by: 5pm 
```


## Unmark task

**Function:** Unmark a finished task.

**Command:** `unmark [index number of task]`

**Example:** 
```ruby
umark 1

Output:
This task is marked as not done yet
[D][] scratch sofa | by: 5pm 
```


## Sort tasks alphabetically by name 

**Function:** Sort the task list in ascending order or descending order by their names.

**Command:** 
`sort asc` 
`sort desc`

**Example:** 
```ruby
sort asc

Output:
1. [T][] eat grass
2. [E][] lunch | from: 11am to: 12pm
3. [D][] scratch sofa | by: 5pm 
```

```ruby
sort desc

Output:
1. [D][] scratch sofa | by: 5pm 
2. [E][] lunch | from: 11am to: 12pm
3. [T][] eat grass
```


## Delete task

**Function:** Delete a task.

**Command:** `delete [index number of task]`

**Example:** 
```ruby
delete 1

Output:
I have eaten your task.
```


## Find tasks

**Function:** Find a task by name.

**Command:** `find [keyword]`

**Example:** 
```ruby
find eat

Output:
1. [T][] eat grass
```


