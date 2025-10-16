# Junny User Guide

Welcome to Junny, your personal chatbot assistant!

---

## Quick Start
1. Ensure you have **Java 17** or above installed.
2. Download the latest release of `Junny.jar`.
3. Run the program using: java -jar Junny.jar
4. Start typing commands (following the format) into the chat window.

---

## Features
 **Add tasks**
- `todo read book`
- `deadline submit report /by 2025-10-01`
- `event project meeting /from 2025-10-01 /to 2025-10-02`

 **List tasks**
- `list`

 **Mark / Unmark tasks**
- `mark 2`
- `unmark 2`

 **Delete tasks**
- `delete 3`

 **Find tasks by keyword**
- `find book`

 **List tasks on a specific date**
- `list /on 2025-10-01`

 **Sort tasks**
- `sort`

---

## Command Summary
| Command   | Format | Example |
|-----------|--------|---------|
| **todo**  | `todo <description>` | `todo read book` |
| **deadline** | `deadline <description> /by yyyy-mm-dd` | `deadline submit report /by 2025-10-01` |
| **event** | `event <description> /from yyyy-mm-dd /to yyyy-mm-dd` | `event project meeting /from 2025-10-01 /to 2025-10-02` |
| **mark**  | `mark <task number>` | `mark 2` |
| **unmark**| `unmark <task number>` | `unmark 2` |
| **delete**| `delete <task number>` | `delete 3` |
| **list**  | `list` | `list` |
| **find**  | `find <keyword>` | `find book` |
| **list by date** | `list /on yyyy-mm-dd` | `list /on 2025-10-01` |
| **sort**  | `sort` | `sort` |
