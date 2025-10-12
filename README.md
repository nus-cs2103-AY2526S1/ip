# SmartOtter Project Template

This project is adapted from the Duke project and given a unique personality: a *Smart Otter*. Instructions on how to
use it are provided below.

## Setting Up in IntelliJ

**Prerequisites:** JDK 17 and the latest version of IntelliJ.

1. Open IntelliJ.

    * If you are not on the welcome screen, click **File → Close Project** to close any existing project first.
2. Open the project:

    1. Click **Open**.
    2. Select the project directory and click **OK**.
    3. Accept all default prompts.
3. Configure the project to use **JDK 17** (not other versions) as
   explained [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).

    * In the same dialog, set the **Project language level** field to the **SDK default** option.
4. Locate the file:

   ```
   src/main/java/com/oadultradeepfield/smartotter/SmartOtter.java
   ```

   Right-click it and choose **Run 'SmartOtter.main()'**.

    * If the editor shows compile errors, try restarting IntelliJ.
    * If everything is set up correctly, you should see output like this:

        ```
            ____________________________________________________________
            Hello! I'm SmartOtter 🦦📚
            I can help you with anything,
            as long as you give me fish 😋
            ____________________________________________________________
        ```

## Testing the Program

After further development, make sure to update the testing scripts in `text-ui-test`. For Unix operating systems, ensure
that `dos2unix` is installed so that the script can be executed correctly.

**Warning:** Keep the `src/main/java` folder as the root for Java files (do not rename these folders or move Java files
outside this path), as some tools (e.g., Gradle) expect this standard structure.

## Changelog

### [2025-09-06] - GUI Update

- Migrated the project to **Gradle** for simplified build and dependency management.
- Integrated **JavaFX** for a graphical user interface (GUI).
- Added basic **GUI support**, including text input, message display, and send button.

![SmartOtter GUI](gui.png)

### [2025-09-07] - Support Minor Typos in Commands

* Added fuzzy matching for command keywords in `CommandParser` to tolerate minor typos (up to 2 edits).
* Introduced `FuzzyMatcher` utility implementing Levenshtein distance for approximate string matching.
* Added `CommandType.allKeywords()` to provide all command keywords for fuzzy matching.
* Commands now correctly recognize input with small typos, improving usability (e.g., `"fnd"` → `"find"`, `"lits"` → `"list"`).
* Maintains existing argument validation and no-argument command handling.
