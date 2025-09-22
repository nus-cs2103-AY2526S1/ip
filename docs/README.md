# Klalopz User Guide

**Have you ever wanted a talking turtle that could help you list and note down everything you needed to do?**

Look no further than Klalopz, the digital turtle to help note and easily find your needs.

![Example of Klalopz usage](../docs/Ui.png)

## Adding Tasks

Add your tasks that have a certain deadline

Your lab assignment that has a deadline on 19th September 2025

Example: `deadline lab /19-09-2025`

Klalopz will automatically add it to the list

```
Klalopz has added this task:
[D][] lab (by: 19 Sep 2025)
Now you have X tasks in the list.
```

## Finding Tasks

Now that you have added your tasks, you can also easily find them.

Let's try and find the lab assignment you just added above

Example: `find lab`

Klalopz will find every single instance that has lab inside
```
Klalopz has found the Tasks containing lab:
4. [D][] lab (by: 19 Sep 2025)
```


## Adding tags

You can also add tags to every single one of your tags for further grouping.

We can add the tag `SCHOOL` to our task above

Example: `add_tag {index} 5`

5 is our SCHOOL tag, below you can find the numbers for our other tags  
FAMILY(0)  
FRIENDS(1)  
WORK(2)  
URGENT(3)  
HOME(4)  
SCHOOL(5)  

So our output should be something like below
```
Klalopz has added this tag to the following task:
[D][] lab (by: 19 Sep 2025) #SCHOOL
```

## Marking Tasks

When you have finished a task you can mark them as finished

Let's mark the lab task

Example: `mark {index}`

Now our task will be marked as done as noted by the [X]

```
Well done! Klalopz has marked this task:
[X] lab (by: 19 Sep 2025) #SCHOOL
```