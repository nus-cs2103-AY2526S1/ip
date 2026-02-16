# AI.md

## Overview
This document records the use of AI tools during the iP (Individual Project).

---

## Week 5

### Tool Used
- GitHub Copilot

### Usage
Used Copilot to generate the Javadoc documentation for the `find` command in the project.
This format is also implemented in other Javadoc of `execute` method.
Specifically, it suggested the following block:

```java
/**
 * Executes the {@code find} command.
 * Extracts the keyword from the input string, iterates through the {@link TaskList},
 * and displays all tasks that contain the keyword in their string representation.
 * If no tasks match, a message is displayed instead.
 *
 * @param tasks   the current list of tasks
 * @param ui      the user interface used to display messages
 * @param storage the storage handler (not used in this command)
 * @return {@code false} as the program should not terminate after execution
 * @throws TaroException if the keyword is missing or empty
 */
