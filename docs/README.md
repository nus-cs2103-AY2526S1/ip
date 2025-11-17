# PagroBot — User Guide

> The passive‑aggressive task CLI that keeps you on track (and on your toes).

---

## Quick Start

### Requirements

* Java 17 or newer (JDK)
* A terminal (macOS/Linux/Windows)
* (Optional) Gradle 7+

### Install & Run

```bash
# in the project root
gradlew clean build    # or: ./gradlew … on macOS/Linux
java -jar build/libs/pagrobot.jar
```

You should see PagroBot’s greeting, then a blinking cursor waiting for commands.

```
____________________________________________________________
Hello. I'm PagroBot. Let's get this over with.
____________________________________________________________
```

Type `help` to see all commands. Type `bye` to quit.

---

## How It Works (in one minute)

* You type a **command**.
* PagroBot replies (short, passive‑aggressive). It **prints** to console and also **returns** the same text internally so tests can assert it.
* Tasks live in memory unless you wired in storage (see **Persistence**).

---

## Command Summary

| Command      | Format                       | Example                                       | What PagroBot says                          |
| ------------ | ---------------------------- | --------------------------------------------- | ------------------------------------------- |
| Help         | `help`                       | `help`                                        | "You blur? Fine, here: …" (shows full list) |
| List         | `list`                       | `list`                                        | "Here. Your tasks:" + list                  |
| Add To‑Do    | `todo <desc>`                | `todo buy milk`                               | "Added. You're welcome." + task + total     |
| Add Deadline | `deadline <desc> /by <when>` | `deadline CS2101 slides /by 05/10/2025 23:59` | as above                                    |
| Add Event    | `event <desc> /from <when> /to <when>`    | `event meeting /from 20/09/2025 1400 /to 21/09/2025 1500`          | as above                                    |
| Mark         | `mark <index>`               | `mark 1`                                      | "Fine. Marked." + task                      |
| Unmark       | `unmark <index>`             | `unmark 1`                                    | "Undo lor. Unmarked." + task                |
| Delete       | `delete <index>`             | `delete 1`                                    | "Deleted. Happy?" + task + left             |
| Find         | `find <keyword>`             | `find milk`                                   | "Found lor:" + matching tasks               |
| Sort         | `sort`                       | `sort`                                        | "Sorted. Finally." + list                   |
| Quit         | `bye`                        | `bye`                                         | "Bye. Try not to come back."                |

> **Indices** are 1‑based in commands (i.e., `1` is the first task).

> **Dates/Times**: Use the formats your parser accepts (e.g., `DD/MM/YYYY HHmm`). If the format is wrong, you’ll get an "Invalid command. Don't anyhow." message.

---

## Examples (copy‑paste ready)

### Add + List

```
> todo your auntie
Added. You're welcome.
[T][ ] your auntie
Total: 1
____________________________________________________________
> list
Here. Your tasks:
1. [T][ ] your auntie
____________________________________________________________
```

### Mark (bad index) + Delete

```
> mark 2
Task 2 not found.
____________________________________________________________
> delete 1
Deleted. Happy?
[T][ ] your auntie
Left: 0
____________________________________________________________
```

### Help

```
> help
You blur? Fine, here:
  list        - show all tasks
  todo XXX    - add a todo
  deadline XXX /by DATE - add a deadline
  event XXX /from DATE /to DATE    - add an event
  mark N      - mark task N done
  unmark N    - unmark task N
  delete N    - delete task N
  find WORD   - find tasks with WORD
  sort        - sort tasks
  help        - show this help
  bye         - exit
____________________________________________________________
```

---

## Error Messages (what they mean)

* **`Invalid command. Don't anyhow.`** → Unknown command or wrong format (e.g., missing `/by` or index not an integer).
* **`Task N not found.`** → Index out of range for the current list.
* **`Error lor: …`** → An internal exception bubbled up (parser/storage); the message shows the cause.

---

## Uninstall / Quit

Just close the terminal window.

---

## FAQ

* **Why is the bot sarcastic?** Dont ask stupid questions.
* **Why 1‑based indices?** Friendlier for humans, less off‑by‑one rage.
* **Can I change the tone?** Yes—edit `pagrobot.ui.Ui` responses.

---

## About

* Product name: **PagroBot**
* Interface: CLI
