# AI Usage Log

This file documents the use of AI tools during the project, including what they were used for, observations about their effectiveness, and reflections on time saved.

---

## 2025-09-18 — Branch: A-MoreTesting
- **AI Tool Used**: Claude Sonnet 4
- **Purpose**: Generate additional test cases for the `Parser` class.
- **Rationale**:
  - After writing 2–3 test cases manually, I noticed I was duplicating code patterns.
  - Using AI saved time by generating a bulk of test cases, though manual corrections were still required.
- **Observations**:
    - AI successfully generated more test cases, which helped to quickly cover all possible cases while Parser handles input.
    - However, it produced some erroneous inputs when calling task creation methods in tests (incorrect format for different task types).
    - I had to manually fix those inputs so the tests would pass.
- **Time Savings**: Moderate — reduced repetitive effort but required review and fixes.

**Reflection**: AI tools speed up my work as they can handle tasks I already know how to do, but faster. Nonetheless, my intervention is still necessary since the AI doesn’t understand my codebase the way I do.
