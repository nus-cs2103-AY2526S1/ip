# GoksChat - User Guide

Welcome to GoksChat, your personalized desktop task list designed for fast and efficient task management. Optimized for students and professionals who prefer a command-line interface (CLI), GoksChat helps you manage your tasks without ever taking your hands off the keyboard.

This guide will walk you through the setup and features of GoksChat.

## Table of Contents
1. Getting Started
2. Features
   * Add Todo Task
   * Add Deadline Task
   * Add Event Task
   * Delete Task
   * Display Task List
   * Find Task
   * Mark Task as Complete
   * Unmark Tak as Incomplete
   * Sort Tasks by Deadline
   * Bye
3. Acknowledgement

## Getting Started
Prerequisites: Ensure you are using JDK 17.

1. Download: Get the latest `goksChat.jar` from the [release page](https://github.com/ravichandran-gokool/ip/releases).
2. Run the application:
   * Place the `.jar` file in an empty folder.
   * Open a command terminal in that folder.
   * Run the command `java -jar goksChat.jar`
   * The application's Graphical User Interface (GUI) should now appear.

## Features
GoksChat follows a consistent format: `command [parameters...]`. The parameter is usually the task description itself, the task number, or for some commands, there are no parameters.

1. Add Todo Task: `todo`
Adds a new todo task to the task list.

**Format:**
`todo [description]`
* `todo` followed by description of task

**Example:**
`todo Read a book`

2. Add Deadline Task: `deadline`
Adds a new task with a deadline to the task list.

**Format:**
`deadline [description] /by [uuuu-MM-dd]`
* Description is the same as for a Todo task but date must follow the format specified above

**Example:**
`deadline Finish CS2100 Assignment /by 2025-09-15`

3. Add Event Task: `event`
Adds an event task to the task list.

**Format:**
`event [description] /from [description] /to [description]`
* From and To descriptions can be anything according to user's preference, just like the Event description

**Example:**
`event Birthday Party /from 1pm today /to 4am tomorrow`

4. Delete Task: `delete`
Removes a task from the task list.

**Format:**
`delete [serial number]`
* `delete` followed by serial number of the task user would like to delete

**Example:**
`delete 1`

5. Display Task List: `display`.
Displays the current list of tasks. 

**Format:**
`display`

6. Find Task: `find`
Search for a task based off a keyword.

**Format:**
`find [keyword]`
* `find` followed by any keyword

**Example:**
`find Watch`

7. Mark Task as Complete: `mark`
Marks a task as completed.

**Format:**
`mark [serial number]`
* `mark` followed by serial number of the task user would like to mark as complete

**Example:**
`mark 1`

8. Unmark Task as Incomplete: `unmark`
Unmarks a task as incomplete.

**Format:**
`unmark [serial number]`
* `unmark` followed by serial number of the task user would like to unmark as incomplete

**Example:**
`unmark 1`

9. Sort Tasks by Deadline: `sort`.
Deadline Tasks are flushed up above others and sorted by deadline, with earliest coming first.

**Format:**
`sort`

10. Bye: `bye`.
CLI command to close the application

**Format:**
`bye`

## Acknowledgement
UI in this application was built using the [SE-EDU Java-FX Tutorial](https://se-education.org/guides/tutorials/javaFxPart1.html).
