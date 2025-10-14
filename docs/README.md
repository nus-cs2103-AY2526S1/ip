# Lux User Guide

Lux is a simple command-line personal assistant chatbot for managing tasks. It
supports Gradle builds and a small JavaFX GUI. The project is organized into
Java packages (`lux.commands`, `lux.data`, `lux.parser`, `lux.storage`,
`lux.ui`, `lux.exception`) to separate responsibilities.

## Features

- Add todo, deadline, and event tasks
- List all tasks
- Mark/unmark tasks as done (zero-based indices)
- Delete tasks
- Save and load tasks automatically (serialized objects in `data/`)
- Robust command parsing (commands support `/by`, `/from`, `/to` keywords)
- Date/time parsing for deadlines and events using the pattern `HHmm dd-MM-yyyy`
- Persistent storage files: `data/tasks.ser` and `data/aliases.ser`
- Modular codebase with packages: `lux.commands`, `lux.data`, `lux.parser`, `lux.storage`, `lux.ui`, `lux.exception`

## Usage

### Compiling, Running, and Testing

#### Using Make (convenience wrappers)

The repository includes a `makefile` with shortcuts that call Gradle
internally. These are optional; you can use Gradle directly if you prefer.

To compile the project:

```sh
make compile
```

To run the chatbot (console mode):

```sh
make run
```

To run tests:

```sh
make test
```

#### Using Gradle Directly

To build the project with Gradle:

```sh
./gradlew build
```

To run the console chatbot (main class `lux.Lux`):

```sh
./gradlew run
```

To run tests:

```sh
./gradlew test
```

## Commands

### Adding a Todo

Add a simple todo task. The argument is the description text.

```sh
todo read book
```

### Adding a Deadline

Add a task with a deadline. The application expects date/time in the
pattern used by the UI formatter: `HHmm dd-MM-yyyy` (for example: `2359 30-08-2025`).

```sh
deadline submit report /by 2359 30-08-2025
```

### Adding an Event

Add an event with a start and end time. Dates use the pattern `HHmm dd-MM-yyyy`.

```sh
event meeting /from 1000 30-08-2025 /to 1100 30-08-2025
```

### Listing Tasks

Show all current tasks (tasks are displayed with their zero-based index):

```sh
list
```

### Marking/Unmarking Tasks

Mark a task as done (note: indices are zero-based):

```sh
mark 2
```

Unmark a task:

```sh
unmark 2
```

### Deleting a Task

Delete a task by its zero-based index:

```sh
delete 2
```

### Exiting

Exit the chatbot (also persists tasks and aliases to `data/`):

```sh
bye
```

### Alias command

Create a short alias for an existing command. Aliases persist across runs in `data/aliases.ser`.

Usage:

```sh
alias <aliasName> <command>
```

Examples:

- Create a short alias for `list`:

```sh
alias ls list
```

- Alias `td` to `todo` so you can add todos quickly:

```sh
alias td todo
```

Notes:

- Aliases are expanded by the `Parser` before command parsing. If an alias collides with an existing command name, the alias will override the literal token during parsing.
- Alias changes are saved automatically and will be available the next time you start Lux.

## Notes

Notes and tips

- Tasks and aliases are persisted into the `data/` directory as serialized
  Java objects (`tasks.ser` and `aliases.ser`). Do not edit these files by hand.
- Indices used by `mark`, `unmark`, `delete` are zero-based. Be careful when
  using numbers shown in `list()` output.
- Date/time format used by the CLI and GUI is `HHmm dd-MM-yyyy` (hours/minutes
  followed by day-month-year).
- Common error messages you may see:
  - "Invalid task index: must be an integer." — you passed a non-integer to an index command.
  - "Task index out of range." — the index is less than 0 or greater than the highest index.
  - "Invalid date/time format ..." — date/time doesn't match the expected pattern.

## Advanced

- The GUI entry point is `lux.Main` (JavaFX). The console entry point is
  `lux.Lux` which runs an interactive loop and can also produce single-turn
  responses via `getResponse(String)` used by the GUI controller.
- Modify or extend alias mappings in `lux.data.AliasList` or via the `alias`
  command at runtime (e.g. `alias ls list`).

For anything else, examine the source packages mentioned above to see how
commands are parsed (`lux.parser.Parser`) and executed (`lux.commands`).
