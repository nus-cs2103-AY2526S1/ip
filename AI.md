# AI.md

This file documents my use of AI tools in developing the floydai.FloydAI project.

## Tools Used

- **ChatGPT (OpenAI GPT-5)** – used to generate Java code snippets, explain design choices, and assist with debugging.
- Occasionally used ChatGPT to suggest testing sequences and `input.txt` examples.

---

## Record of AI Usage

### Level 1: Basic Echo & Exit

- Generated initial chatbot skeleton to greet the user, echo commands, and exit on `bye`.
- AI helped format the greeting box and CLI input loop.

### Level 2: Add & List Tasks

- Used AI to extend functionality for storing tasks (`ArrayList<task.Task>`).
- AI suggested both arrays and `ArrayList`; I chose `ArrayList` for dynamic sizing.
- Learned how to append tasks and display a numbered list.

### Level 3: Mark/Unmark Tasks

- Asked AI to design a `task.Task` class with `markAsDone()` and `markAsNotDone()`.
- Generated code for `[X]` / `[ ]` display.
- Learned about exception handling when marking/unmarking invalid indexes.

### Level 4: Multiple task.Task Types (task.Todo, task.Deadline, task.Event)

- Used AI to implement **inheritance**: `task.Todo`, `task.Deadline`, `task.Event` classes extending `task.Task`.
- Generated parsing logic for commands like `deadline return book /by Sunday` and `event meeting /from Mon 2pm /to 4pm`.
- Learned how to use polymorphism to store all tasks in a single `ArrayList<task.Task>`.

### Level 5: Optional (Enums)

- AI suggested using a **Java enum** for task types (`TODO`, `DEADLINE`, `EVENT`).
- Refactored `task.Task` and subclasses to use `task.TaskType` enum.
- Benefits: removed redundant `getTypeIcon()`, improved type safety, and simplified `toString()` logic.

### Level 6: Delete Tasks

- Implemented `delete <index>` command using `ArrayList.remove(index)`.
- AI provided the skeleton for delete handling, confirming removal and updating task count.
- Learned to integrate deletion with list numbering and CLI output.

### UI and GFMD
- Used ChatGPT to customize UI class with the theme of paying tribute to George Floyd and systemic racial injustice.

---

## Observations

**What worked well:**

- AI was excellent for generating repetitive boilerplate code (parsing, CLI formatting, inheritance).
- Saved time implementing features like task addition, marking/unmarking, and deletion.
- Helped visualize a full test script (`input.txt`) and expected CLI outputs.

**What required manual work:**

- Debugging index errors when marking/deleting tasks.
- Matching exact output format for tests (e.g., pluralization `"task(s)"`, brackets `[T]`, `[D]`, `[E]`).
- Understanding AI suggestions before integrating them into my project.
- Some tonality and output from ChatGPT is particularly insensitive to the suffering of George Floyd e.g. over mentioning the lack of ability to breathe. I had to manually vet through and remove overly charged sections to ensure it remains politically neutral and accepted by everyone.

**Overall impact:**

- Using AI significantly reduced coding time and let me focus on debugging, feature design, and test validation.
- I became more comfortable with **polymorphism, inheritance, enums, and Java collections** through AI-assisted coding.