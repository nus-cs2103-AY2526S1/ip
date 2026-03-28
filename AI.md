AI Assistance and Responsible Use

Overview
- This project used the Cursor AI tool (GPT-based) as a pair-programmer to accelerate documentation and code-quality work. All AI-assisted changes were reviewed, edited, and validated by a human maintainer before being committed.

How AI Was Used
- Documentation: drafting and tightening code comments and JavaDoc documentation.
- Code quality: small refactors that do not change behavior (e.g., explicit imports, final classes for commands, private constructor for utility classes, improved encapsulation, clearer messages).
- Feature support: improving the search experience (partial/phrase/typo-tolerant matching) and adding focused unit tests that describe and verify the expected behavior.
- Build/dev ergonomics: suggestion to scope JVM assertions to packages for safer testing without affecting unrelated modules.

How AI Was Used Responsibly
- Human-in-the-loop: every suggestion was inspected and, when appropriate, edited by a human before merging.
- Verification: code compiled and tests were run locally using the Gradle wrapper (./gradlew test). New tests were added to cover the BetterSearch behavior.
- Minimal surface area: AI was provided only with repository context necessary for the task. No credentials or secrets were shared with the tool, and no generated secrets were committed.
- Licensing and originality: the assistant did not paste external, license-incompatible code. All changes are either original or transformations of existing project code.

Scope of AI Contributions (Examples)
- Search: enhancement of TaskList.find to support partial matches, quoted phrases, and limited typo tolerance (edit distance ≤ 1 with sensible length guards).
- Tests: TaskListFindTest covering partial, phrase, typo tolerance, and multi-term AND behavior.
- Code quality: final command classes, @FunctionalInterface on Command, private ctor + final for FileParser, explicit imports in Parser, small clarity and robustness improvements in exceptions and task model classes.

Validation & Reproducibility
- Build and test with the wrapper:
  - ./gradlew clean test
  - ./gradlew run
- Java/JavaFX: the repo uses the Gradle wrapper and JavaFX 17; the wrapper ensures a reproducible build environment.

Limitations & Notes
- AI suggestions can be imperfect. Tests cover primary and new behaviors, but additional edge cases may surface; please file issues if you find any.
- AI-generated text may have been further edited for clarity and accuracy; final responsibility for the code and documentation rests with project maintainers.

AI Assistance Disclosure

This project used the Cursor AI tool to assist with documentation and for betterhelop during development.


