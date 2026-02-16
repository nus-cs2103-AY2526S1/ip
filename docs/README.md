# TalkGPT

> TalkGPT is a simple chatbot application that helps you manage tasks with commands to add, mark, unmark, delete, and list tasks.

---

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Commands](#commands)

---

## Features

- Add new tasks (todo, deadline, event) to your task list
- Mark tasks as completed
- Unmark tasks to mark them as not completed
- Delete tasks from your list
- List all current tasks
- Find tasks based on key words

---

## Installation

1. Clone the repository: 

git clone https://github.com/Lucasn24/ip.git
cd ip


2. Move to ip/build/libs

3. Run java -jar .\TalkGPT.jar

---

## Usage

Run the chatbot and interact with it by typing commands. Commands are case-sensitive.

### Available Commands:

| Command               | Description                              | Example Usage                                                               |
|-----------------------|------------------------------------------|-----------------------------------------------------------------------------|
| `add ToDo Task`       | Adds a new Todo task                     | `todo read book /tag reading`                                               |
| `add Deadline Task`   | Adds a new Deadline task                 | `deadline code submission /by 20/9/2025 2359 /tag school `                  |
| `add Event Task`      | Adds a new Event task                    | `event project meeting /from 20/9/2025 1800 /to 20/9/2025 2000 /tag school` |
| `mark`                | Marks a task as completed                | `mark 1`                                                                    |
| `unmark`              | Marks a task as not completed            | `unmark 1`                                                                  |
| `delete`              | Deletes a task                           | `delete 2`                                                                  |
| `list`                | Lists all tasks                          | `list`                                                                      |
| `find`                | Finds all tasks containing the keyword   | `find book`                                                                 |
| `tag`                 | Finds all task of the same tag           | `tag school`                                                                |

---



