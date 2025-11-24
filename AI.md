# AI declaration for IP

Tool Used: ChatGPT 
---

## Timeline of AI Use

### 1. Level 1: Basic Project Setup

* **Tasks Assisted:**

    * Guidance on setting up branches for increments.
    * Advice on project structure and organizing files.
* **Observations:**

    * Helped avoid common mistakes in branching and Git workflow.

### 2. Assertions Increment

* **Tasks Assisted:**

    * Suggested assert statements for `Task`, `TaskList`, `Parser`, `Storage`, and `Ui`.
    * Provided examples for `addTask()`, `markTask()`, `unmarkTask()`, `parse()`, and `load/save()`.
    * Reviewed your initial assertions and advised on improvements.
* **Observations:**

    * AI assistance helped identify missing invariants and edge cases.
    * Time saved: 30–45 minutes.

### 3. Commit Messages & Git Conventions

* **Tasks Assisted:**

    * Suggested improments for more structured commit messages.
* **Observations:**

    * Improved commit clarity and adherence to conventions.
    * Time saved: 5 minutes per commit.

### 4. Code Quality Increment

* **Tasks Assisted:**

    * Refactoring `Command` classes (`MarkCommand`, `UnmarkCommand`, etc.).
    * Adding JavaDoc comments for all methods and classes.
    * Ensuring proper exception handling (e.g., `IOException` in `Storage.save()`).
    * Suggested changes for method contracts and clarity.
* **Observations:**

    * Made code more maintainable and readable.
    * Time saved: 2 hour.

### 5. Debugging & Testing

* **Tasks Assisted:**

    * Diagnosed failing tests in `TaskListTest.testRemoveTask()`.
    * Advised on proper assertions and index handling.
    * Helped troubleshoot branching mistakes (commits in wrong branch).
* **Observations:**

    * Time saved: 30–45 minutes per debugging session.

### 6. Extension Increment: View Schedule

* **Tasks Assisted:**

    * Planned and implemented a `ScheduleCommand` for viewing tasks by date.
    * Advised on filtering tasks in `TaskList` and formatting output in `Ui`.
* **Observations:**

    * AI helped design the extension logic and avoided reinventing common patterns.
    * Time saved: 1–2 hours.

---

## Overall Observations

* **Effective AI usage:**

    * Writing assertions and test-ready code.
    * Code refactoring, documentation, and exception handling.
    * Designing extensions and filtering logic.

* **Limitations:**

    * Some AI suggestions required manual adaptation to project-specific classes.
    * Mistakes in branch workflow still needed human oversight.

* **Total Productivity Gain:**

    * Estimated 7–8 hours saved across all increments.

