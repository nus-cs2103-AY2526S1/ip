# Echo User Guide

![UI Screenshot](./Ui.png)

Meet Echo, a playful chatbot inspired by the sound-warping alien from *Ben-10*. In this universe, tasks are aliens, causing chaos everywhere, and 
Echo is here to help you capture, organise and defeat them.

## Features

### Add Tasks

#### Add Todo Task
Adds a todo task with a description.

**Format**:`todo <description>`

**Example**: `todo run`

**Expected Output**: 
```
New alien added! New alien added!
  [T][] sleep
Now you have 1 alien to contain.
```

#### Add Deadline Task
Adds a deadline task with a description and a deadline.

**Format**:`deadline <description> /by <DD/MM/YYYY HHmm>`

**Example**: `deadline run /by 18/9/2025 1000`

**Expected Output**:
```
New alien added! New alien added!
  [D][] run (by:Sep 18 2025, 10:00am)
Now you have 1 alien to contain.
```
#### Add Event Task
Adds an event task with a description, a from and to date.

**Format**: `event <description> /from <DD/MM/YYYY HHmm> /to <DD/MM/YYYY HHmm>`

**Example**: `event run /from 18/9/2025 1000 /to 19/9/2025 1000`

**Expected Output**:
```
New alien added! New alien added!
  [E][] play (by:Sep 18 2025, 10:00am to:Sep 19 2025, 11:00am)
Now you have 1 alien to contain
```
### View Task

#### List Tasks

Lists all tasks currently in list

**Format**: `list`

**Example**: `list`

**Expected Output**:
```
Mission briefing! Here are your aliens to capture Ben:
1.[T][] sleep
2.[D][] run (by:Sep 18 2025, 10:00am)
3.[E][] play (by:Sep 18 2025, 10:00am to:Sep 19 2025, 11:00am)
```
#### Find Keywords
Lists all tasks currently in list that contains the keyword

**Format**: `find <keyword>`

**Example**: `find run`

**Expected Output**: 
```
Incoming! Matching aliens detected on your list:
1.[T][] run
```

### Manage Tasks

#### Mark Tasks
Mark task at input index as completed

**Format**: `mark <index>`

**Example**: `mark 1`

**Expected Output**:
```
Boom! Mission Accomplished! Alien contained!:
  [T][X] run
```

#### Unmark Tasks
Unmark task at input index as uncompleted

**Format**: `unmark <index>`

**Example**: `unmark 1`

**Expected Output**:
```
Uh-oh! Uh-oh! Mission reset! Alien has escaped!:
  [T][] run
```

#### Delete Tasks
Delete task at input index

**Format**: `delete <index>`

**Example**: `delete 1`

**Expected Output**:
```
Destroying Alien task.....AHHHHHH!
  [T][] run
Now you have 3 aliens to contain.
```
#### Sort Tasks
Sort tasks based on task type: Todo > Deadline > Event and lists them

**Format**: `sort`

**Example** `sort`

**Expected Output**:
```
Mission briefing! Here are your aliens to capture Ben:
1.[T][] sleep
2.[D][] run (by:Sep 18 2025, 10:00am)
3.[E][] play (by:Sep 18 2025, 10:00am to:Sep 19 2025, 11:00am)
```

### Others

#### Exit Application
Saves tasks locally and exits application

**Format**: `bye`

**Example**: `bye`

**Expected Output**:
```
Echo Echo...going back into the Omnitrix! See you soon!
```