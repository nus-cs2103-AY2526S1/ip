# AI.md — Use of AI in This Project

This document discloses where and how AI (ChatGPT) was used while building this project, and outlines review/verification practices.

---

## Tooling

* **AI assistant:** ChatGPT
* **Primary purpose:** Drafting code, refactoring, documentation, test scaffolding, and developer ergonomics suggestions.
* **Human oversight:** All AI outputs were reviewed, adapted, and tested by the project maintainer before inclusion.

---

## Summary of AI-Assisted Contributions

### Command framework

* **Designed & generated:**

    * `Command` interface (contract, exit flag pattern, error handling via `ParseException`)
    * `CommandResult` record and helpers `ok(...)`, `exit(...)`
* **Concrete commands:**

    * `AddTodoCommand`, `AddDeadlineCommand`, `AddEventCommand`
    * `DeleteCommand`, `MarkCommand`, `UnmarkCommand`, `ListCommand`, `ByeCommand`
    * `FindCommand` (substring search)
    * `PostponeCommand` (strict grammar: `/by` for deadlines; `/from ... /to ...` for events)

### Parsing

* **`CommandParser`**

    * Verb dispatch + argument parsing
    * Robust usage messages
    * Date/time validation hooks
    * `parsePostpone(...)` reduced to only two forms (as specified)

### Date & time

* **`DateTimeParser`**

    * Strict parsing with `ResolverStyle.STRICT`
    * Public `HUMAN_PATTERN` for user-facing messages

### Model layer

* **`Task` base class** (serialization helpers, formatting, completion state)
* **Concrete tasks:** `Todo`, `Deadline`, `Event` (+ getters as needed)
* **`TaskList`** (basic list operations; suggestion to add `set(int, Task)`)

### Storage

* **`FileStorage`**

    * Read/write of pipe-delimited format
    * Safer path resolution (next to JAR; home-dir fallback)
    * Guidance on atomic saves, UTF-8, creating parent directories
    * Javadoc and error messaging improvements

### UI & App wiring

* **CLI:** `ConsoleUi`, `John` CLI loop refinements and docstrings
* **JavaFX:** `Main`, `MainWindow`, `DialogBox`

    * Controller injection, scrolling bind, null/empty input guards (suggested)
    * Close-on-`bye` behavior via `John.Reply` (message + exit flag)

### Documentation

* **Readme drafting & GFMD requirements**

    * Plaintext + Markdown versions with headings, lists, code blocks, task lists, emoji, blockquotes, hyperlinks, inline code, and formatting
* **Javadoc**

    * Added/expanded Javadoc across commands, parser, model, storage, and UI classes
