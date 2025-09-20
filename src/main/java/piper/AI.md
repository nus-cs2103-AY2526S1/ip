# AI Usage Log (AI.md)

This document records how AI tools (e.g., ChatGPT) were used during the development of this project.  
The purpose is to provide transparency about external assistance and track lessons learned.

---

## Increments where AI was used
- **Error handling improvements**  
  Used ChatGPT to propose additional error handling for `Parser.java`, `TaskList.java`, `Deadline.java`, `Event.java`, and `Storage.java`.
    - AI suggested how to normalize whitespace, catch duplicate flags (`/by`, `/from`, `/to`).
    - Also provided safe file I/O patterns (atomic writes, permission checks).

- **Event date validation**  
  Asked how to enforce `/to` > `/from` only if both values are ISO `yyyy-MM-dd`.
    - AI suggested using `LocalDate.parse(...)` and performing the comparison only if both parse successfully.
    - This aligned with the existing design where `fromDate` and `toDate` can be `null` for free-form strings.

## Observations
- **What worked well:**
    - AI gave concrete code snippets that fit into existing methods without renaming variables.
    - Helpful at pointing out where to insert checks rather than forcing big refactors.
- **What didn’t work as well:**
    - Some suggestions were more elaborate than needed (new exception hierarchies, etc.), which I had to simplify to stay consistent with the current codebase.
    - Some suggestions were repetitive, including code that I had already implemented in other code files.

## Time saved
- Estimated ~3–4 hours of research/implementation time saved.
- Especially helpful for tricky cases like conditional date validation and consistent error messaging.

---
