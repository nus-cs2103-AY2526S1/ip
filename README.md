<!-- Written using ChatGPT -->
# Guibot User Guide

Welcome to **Guibot**, a simple task manager! Use this guide to learn how to manage and track your tasks easily. Guibot is a Java-based application available as a `.jar` file.

---

## Getting Started

### Installation

1. Download the Guibot `.jar` file from the official source.
2. Run it with the following command:

   ```bash
   java -jar Guibot.jar
   ```
## Core Features

### Add a task
Create a basic task
   ```bash
   todo <description>
   ```
Example
   ```bash
   todo Do laundry
   ```


### Set deadlines
Create a task with a deadline
   ```bash
   deadline <description> /by <date time>
   ```
Example
   ```bash
   deadline Submit report /by 2025-09-30 2359
   ```


### Add events
Create an event with start and end times
   ```bash
   event <description> /from <start> /to <end>
   ```
Example
   ```bash
   event Team meeting /from 2025-09-22 1400 /to 2025-09-22 1500
   ```


### Mark and Unmark tasks
Mark a task as done
   ```bash
   mark <task_number>
   ```
Unmark task
   ```bash
   unmark <task_number>
   ```


### List tasks
View all tasks
   ```bash
   list
   ```


### Find tasks
Search for tasks by keyword
   ```bash
   find <search_string>
   ```


### Archive tasks
Save tasks to a file
   ```bash
   archive <file_name>
   ```


### Load tasks
Load tasks from an archived file
   ```bash
   load <file_name>
   ```


### Bye
Close the Guibot
   ```bash
   bye
   ```


This guide provides the basics for getting started with Guibot. Enjoy managing your tasks!
