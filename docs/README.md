# Eve — User Guide

Hiiiiiiii, I'm **Eve** ✨  
Eve is your cute and energetic chatbot assistant.  
Adapted from its ancestor Duke, Eve grows with more features, better UI design, and so on, waiting for you to explore.
As your assistant, she helps you keep track of tasks: ToDos, Deadlines, and so much more.

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
1. Ensure you have environment Java-17 set up correctly
2. Download the eve.jar from the latest release in [Release](https://github.com/Lzkkuan/ip/releases)
3. Run the file in terminal with command

```bash
   java -jar eve.jar
````

4. Input messages to get started! Or simply type in "help" if donno where to get started

---

## Features

All what you might try out with eve:

### Add a Todo

```
todo <desc>
```

* Example: `todo sleep`

### Add a Deadline

```
deadline <desc> /by <time>
```

* Example: `deadline complete CS2103T iP /by 2025/09/21`
* The time here can also be in other descriptions, just use whatever fits you the best!

### Add an Event

```
event <descn> /from <start> /to <end>
```

* Example: `event play CS /from 4pm /to 6pm`

### Add a Period
```
period <desc> /from <start> /to <end>

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

* Example: `find sleep`

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
### Error Handling

Eve is cheerful but firm when something goes wrong, she will help you find out what is going wrong and hopefully we can solve it together:

- Unknown command → e.g., “Ehh? I don’t quite get that… (・・;) Maybe try 'help'?”

- Missing flags → e.g., missing /by, /from, or /to.

- Invalid dates → impossible dates (e.g., 2025-02-30) are rejected.

- Event/Period ranges → start must be before end.

- Duplicates → adding an identical task warns without crashing.

- Data file issues → Eve auto-creates the folder/file, skips corrupted lines, and continues running.

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
