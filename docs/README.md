# Eve — User Guide

Welcome to **Eve** ✨  
Eve is your cute and energetic chatbot assistant.  
She helps you keep track of tasks: ToDos, Deadlines, Events, and more.

![UI](Ui.png)

---

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Add a Todo](#add-a-todo)
  - [Add a Deadline](#add-a-deadline)
  - [Add an Event](#add-an-event)
  - [Add a Period](#add-a-period)
  - [List Tasks](#list-tasks)
  - [Find Tasks](#find-tasks)
  - [Mark / Unmark / Delete](#mark--unmark--delete)
  - [Help](#help)
- [Error Handling](#error-handling)
- [FAQ](#faq)
- [Credits](#credits)

---

## Quick Start
1. Ensure you have Java 17+ installed.
2. Build the fat JAR:
```bash
   ./gradlew clean shadowJar
````

3. Run:

   ```bash
   java -jar build/libs/Quokka.jar
   ```
4. Type a command in the input bar (bottom right). Press **Enter** or click **Send**.

---

## Features

### Add a Todo

```
todo <description>
```

* Example: `todo read book`

### Add a Deadline

```
deadline <description> /by <date>
```

* Example: `deadline submit report /by 2025-09-10`
* Dates are parsed strictly (e.g., **Feb 30** is rejected).

### Add an Event

```
event <description> /from <start-date> /to <end-date>
```

* Example: `event camp /from 2025-09-10 /to 2025-09-12`
* Start must be **strictly before** end.

### Add a Period
```
period <description> /from <start> /to <end>

```

* Example: `period Revise midterms /from 2025-09-20 /to 2025-09-27`

* Start must be **strictly before** end as well.


### List Tasks

```
list
```

### Find Tasks

```
find <keyword>
```

* Example: `find book`

### Mark / Unmark / Delete

```
mark <index>
unmark <index>
delete <index>
```

### Help

```
help
```

---

## Error Handling

Quokka aims to be forgiving and clear:

* **Unknown command** → e.g., “Alas… I do not know this incantation: …”
* **Empty parts / missing flags** → e.g., missing `/by`, or `/from`…`/to`.
* **Invalid dates** → impossible calendar dates (e.g., `2025-02-30`) are rejected.
* **Event range** → start must be **before** end.
* **Duplicates** → adding an identical task warns without crashing.
* **Data file issues** → Quokka auto-creates the folder/file, **skips corrupted lines**, and continues running.

---

## FAQ

**Q: What Java version do I need?**
A: Java 17+ is recommended

**Q: Does Eve support both CLI and GUI?**
A: Yes. CLI prints directly; GUI uses bubbles. We keep messages consistent by routing show*() through render*().

**Q: Can I change the tone of Eve’s replies?**
A: Yup! Edit strings in Ui.render*()—that’s the single source of truth for messages.

**Q: How do I add bold text in replies?**
A: Use a TextFlow with Text nodes for mixed formatting, or keep Label for simple text.

---
## Credits

* **JavaFX UI** — built with FXML layouts (`MainWindow.fxml`, `DialogBox.fxml`) and styled using CSS-inspired properties for chat bubbles and avatars.  
* **Task model** — inspired by the CS2103T "Duke" project, extended with the custom *Do-Within-Period* task type.  
* **Date/time parsing** — uses `java.time` API (`LocalDateTime`) with `ResolverStyle.STRICT` for robust validation of deadlines and events.  
* **Testing** — implemented with JUnit 5, following the JUnit 5 user guide.  
* **Gradle build system** — manages compilation, testing, and packaging into a fat JAR (`shadowJar`).  
* **GitHub Pages** — used to host this User Guide in Markdown format.  
* **Inspiration** — project structure and initial guidelines based on the CS2103T iP (Individual Project) framework, with creative additions (cute Eve persona, bubble UI, personalized responses).

*All other code authored and maintained by the Eve project team.*
