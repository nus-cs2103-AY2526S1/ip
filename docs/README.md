# Larry

> “Your mind is for having ideas, not holding them.” — David Allen

**Larry** is a simple, text-based task bot with a small JavaFX GUI. It lets you track **todos**, **deadlines**, and **events**, and saves them to disk so your list survives restarts.

* Java 17
* Gradle build
* CLI **and** GUI
* Saves to `data/larry.txt`

---

## Screenshot

![Larry GUI](./Ui.png)

---

## Quick Start

### GUI (recommended)

```bash
# from the repo root
./gradlew run
# Windows:
.\gradlew run
```

### JAR

1. Download the latest release JAR from the **Releases** page.
2. Double-click it **or** run:

```bash
java -jar larry.jar
```

---

## What Larry Can Do

* Add **todos**, **deadlines**, **events**
* List tasks
* Mark / unmark as done
* Delete tasks
* Find tasks by keyword
* Show help
* Exit safely (auto-saves)

> **Tip:** Type `help` any time to see a quick in-app summary.

---

## Date & Time Format (Important)

When entering dates/times for **deadlines** and **events**, Larry accepts:

* **Date only:** `yyyy-MM-dd`
  *Example:* `2025-10-20`
* **Date & time:**
  `yyyy-MM-dd HH:mm`  (*Example:* `2025-10-20 18:30`)
  `yyyy-MM-dd HHmm`   (*Example:* `2025-10-20 1830`)

If parsing fails, Larry keeps your original text so you can fix it later.

---

## Commands

Type a command and press **Enter**.

| Action          | Command & Format                                                                    | Example                                                     |
| --------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------- |
| Show all tasks  | `list`                                                                              | `list`                                                      |
| Add a todo      | `todo <description>`                                                                | `todo read CS2103T notes`                                   |
| Add a deadline  | `deadline <description> /by <yyyy-MM-dd[ HH:mm/HHmm]>`                              | `deadline iP submission /by 2025-10-20 18:00`               |
| Add an event    | `event <description> /from <yyyy-MM-dd[ HH:mm/HHmm]> /to <yyyy-MM-dd[ HH:mm/HHmm]>` | `event hackathon /from 2025-10-21 0900 /to 2025-10-21 1800` |
| Mark as done    | `mark <index>`                                                                      | `mark 2`                                                    |
| Unmark          | `unmark <index>`                                                                    | `unmark 2`                                                  |
| Delete          | `delete <index>`                                                                    | `delete 3`                                                  |
| Find by keyword | `find <keyword>`                                                                    | `find cs2103t`                                              |
| Help            | `help`                                                                              | `help`                                                      |
| Exit            | `bye`                                                                               | `bye`                                                       |

**Notes**

* Indexes are **1-based** (see `list` output).
* Descriptions can contain spaces.
* For **events**, `/from` should be earlier than or equal to `/to`.

---

## Examples

```text
todo buy milk
deadline project report /by 2025-10-20
deadline submission /by 2025-10-20 2359
event team meeting /from 2025-10-21 0900 /to 2025-10-21 1030
list
find report
mark 1
unmark 1
delete 2
bye
```

---

## Storage

* File: `data/larry.txt` (auto-created if missing)
* Changes save automatically when you modify tasks or exit.

---

## Troubleshooting

* **“Unknown command”** → Run `help` to see valid commands and formats.
* **Date rejected / looks wrong** → Re-enter using the formats above (e.g., `2025-10-20 1830`).
* **Nothing happens on Enter** → Click the input box and try again.
* **App doesn’t start** → Ensure **Java 17** is installed (`java -version`).

---

## Shortcuts & Tips

* Use `list` to confirm indexes before `mark`, `unmark`, or `delete`.
* `find <keyword>` narrows results so you don’t mark/delete the wrong item.

