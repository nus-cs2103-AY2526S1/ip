## Penguin – Task Chatbot User Guide

Penguin is a friendly task-list chatbot you can run in the terminal or as a JavaFX app. It supports adding todos, deadlines and events, listing, marking, deleting, saving to disk, and a powerful BetterSearch to find items quickly.

### Quick start
- Prerequisites: JDK 17+
- Terminal (text UI):
  - Build and run tests: `./gradlew clean test`
  - Run: `./gradlew run`
- GUI (JavaFX): launches automatically with `./gradlew run`.

Data file location: `data/penguin.txt` (created on first run).

### Core features

1) Add tasks
- Todo
  - Command: `todo <description>`
  - Example: `todo read book`
- Deadline
  - Command: `deadline <description> /by <yyyy-MM-dd>`
  - Example: `deadline submit iP /by 2025-12-03`
  - Stored as: `D | <0/1> | <description> | <yyyy-MM-dd>`
- Event
  - Command: `event <description> /from <start> /to <end>`
  - Format: ISO local date-time `yyyy-MM-dd'T'HH:mm`
  - Example: `event project meeting /from 2025-12-30T16:30 /to 2025-12-30T18:30`
  - Stored as: `E | <0/1> | <description> | <start> | <end>`

2) List tasks
- Command: `list`
- Shows all tasks with 1-based numbering.

3) Mark and unmark
- Mark done: `mark <index>`
- Mark undone: `unmark <index>`
- Example: `mark 2`

4) Delete tasks
- Command: `delete <index>`
- Example: `delete 3`

5) BetterSearch (find)
- Command: `find <query>`
- Matching rules (case-insensitive):
  - All terms must match somewhere in the task text
  - Partial word matches for terms ≥ 3 chars (e.g., `book` matches `booking`)
  - Quoted phrase matches exact text (e.g., `"team meeting"`)
  - Small-typo tolerance for terms ≥ 4 chars (edit distance ≤ 1)
- Examples:
  - `find book`
  - `find project Aug`
  - `find "team meeting"`
  - `find bokk` (matches `book`)

6) Save and exit
- Exit: `bye` (GUI: close the window)
- Tasks are saved to `data/penguin.txt` automatically when exiting.

### Tips
- Indexes shown by `list` are 1-based; use those numbers with `mark`, `unmark`, and `delete`.
- Deadline uses date format `yyyy-MM-dd`.
- Event uses date-time format `yyyy-MM-dd'T'HH:mm`.

### Troubleshooting
- If the app doesn’t start, ensure Java 17 is selected (Gradle wrapper manages the rest).
- If the data file path is missing, it will be created on first save.

### Development
- Build and run tests: `./gradlew clean test`
- Run the app: `./gradlew run`
