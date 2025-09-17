# Boop

**Boop** is a command-line task management assistant designed to help you manage your tasks with ease and flair. Guided by Duke, the **Great Sage**, Boop provides an authoritative, omniscient, and slightly witty experience while keeping your tasks organized.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Commands](#commands)
- [Undo Functionality](#undo-functionality)
- [Error Handling](#error-handling)
- [Development](#development)
- [AI Assistance](#ai-assistance)

## Features

- Add, delete, mark, unmark tasks.
- Supports **Todo**, **Deadline**, and **Event** tasks.
- Task filtering and searching.
- Persistent storage with save/load support.
- Undo the last action with full restoration.
- Engaging personality: interact with Duke, the Great Sage.
- Comprehensive error messages guiding proper usage.

## Installation

1. Ensure you have **Java 17** installed.
2. Clone this repository:

```bash
git clone https://github.com/EugeneOYZ1203n/ip.git
```

3.  Build using Gradle:

```bash
./gradlew build
```

## Usage

Interact with Duke (the Great Sage) using text commands, such as:

- `todo Read a book`
- `deadline Submit report /by 2025-09-30`
- `event Party /from 18:00 /to 23:00`
- `list`
- `find book`
- `mark 1`
- `unmark 1`
- `delete 2`
- `undo`
- `bye`

---

## Commands

`todo <task>`
Add a todo task.

`deadline <task> /by <date>`
Add a task with a deadline.

`event <task> /from <start> /to <end>`
Add an event task.

`list`
Display all tasks.

`find <keyword>`
Filter tasks containing a keyword.

`mark <index>`
Mark task at given index as completed.

`unmark <index>`
Unmark task at given index.

`delete <index>`
Delete task at given index.

`undo`
Undo the last task-changing action.

`bye`
Exit Boop.

## Undo Functionality

Boop supports undo for the most recent task-changing command (add, delete, mark, unmark). If no previous action exists, attempting `undo` returns an error message.

## Error Handling

Boop provides clear, authoritative error messages for:

- Invalid commands
- Missing or incorrect task parameters
- Save/load errors
- Invalid indices or dates

## Development

- Written in **Java** using **Gradle** for build management.
- Task and command logic are modular for easy extension.
- Persistent storage via a local save file.
- Unit tests included with **JUnit**.

## AI Assistance

AI tools like ChatGPT were used for:

- Drafting initial code structure for commands and task handling.
- Writing utility classes and repetitive boilerplate code.
- Drafting commit messages and README content.
- Iterative refinements of error messages and personality phrasing.
