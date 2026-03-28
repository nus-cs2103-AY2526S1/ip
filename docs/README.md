# Tweety User Guide
**Welcome to Tweety**, your adorable task management assistant!
This guide will walk you through how to interact with Tweety using commands and what responses you can expect.

## Product screenshot
![User Interface](Ui.png)

*Full GUI window of Tweety.*

---

## Overview
Tweety is your adorable personal task manager! This friendly chatbot helps you organise your life with todos, deadlines and events - all while speaking in Tweety's signature cute dialect !

---

## Features

### Adding a "Todo" Task
**Command:**

`todo <description>`

**Example:**

`todo submit CS2100 Assignment`

**Expected Output:**
```` 
Got it! I’ve added dis task faw ya: 
[T][ ] submit CS2100 Assignment 
Now ya have 1 tasks in your list, aww!

```` 
---

### Adding a "Deadline" Task
**Command:**  

`deadline <description> /by <dd/mm/yyyy>`

**Example:**  

`deadline finish iP /by 2025-09-19`

**Expected Output:**
```` 
Got it! I’ve added dis task faw ya: 
[D][ ] finish iP (by Sep 19 2025)  
Now ya have 2 tasks in your list, aww!
 
````
---

### Adding a "Event" Task
**Command:**  

`event <description> /from <startTime> /to <endTime>`

**Example:**  

`event tP meeting /from 3pm Tues /to 5pm Tues`

**Expected Output:**
```` 

Got it! I’ve added dis task faw ya: 
[E][ ] tP meeting (from: 3pm Tues to: 5pm Tues)  
Now ya have 3 tasks in your list, aww!
 
````
---

### Marking a task as done
**Command:**  

`mark <taskNumber>`

**Example:**  

`mark 1`

**Expected Output:**
```` 

Nice! I’ve marked dis task as done, yay!
[T][X] submit CS2100 Assignment
 
````
---

### Unmarking a Task
**Command:**  

`unmark <taskNumber>`

**Example:**  

`unmark 1`

**Expected Output:**
```` 

OK! I’ve marked dis task as not done yet, hon:
[T][] submit CS2100 Assignment
 
````
---

### Deleting a Task

**Command:**  

`delete <taskNumber>`

**Example:**  

`delete 1`

**Expected Output:**
```` 

Noted! I’ve removed dis task faw ya:
[T][] submit CS2100 Assignment
Now ya have 2 tasks in your list, aww!
 
````
___

### Listing all Tasks

**Command:**  

`list`

**Example:** 

`list`

**Expected Output:**
```` 

Here awe da tasks in your list, hon:
[D][ ] finish iP (by Sep 19 2025)  
[E][ ] tP meeting (from: 3pm Tues to: 5pm Tues)  

````
___

### Finding Tasks with keyword

**Command:**  

`find <keyword>`

**Example:**  

`find iP`

**Expected Output:**
```` 

Here awe da matching tasks in your list, hon:
[D][ ] finish iP (by Sep 19 2025)  

````
___

### Editing a Task

**Command:**  

`edit <taskNumber> <field> <newValue>`

**Example:**  

`edit 1 description finish tP`

**Expected Output:**
```` 

Nice! I’ve edited dis task for ya:
[D][ ] finish tP (by Sep 19 2025)  

````
___

### Exiting the Chatbot

**Command:**  

`bye`

**Example:**  

`bye`

**Expected Output:**
```` 
Aww, you gotta go? Bye bye! Hope to see ya soon!

````

