# Fish User Guide

Fish is your task management tool that could 

help record your events, deadlines, todos and their status

### Running from the command line
- Start the application with the Gradle wrapper:
  ```bash
  ./gradlew run
  ```
- To produce a runnable JAR, use:
  ```bash
  ./gradlew shadowJar
  java -jar build/libs/fish.jar
  ```
The first launch creates `data/fish.txt`, which is where your tasks are saved.

## Using Fish
Below are some essential commands to try once Fish greets you:

| Command                                                             | What it does                                              |
|---------------------------------------------------------------------|-----------------------------------------------------------|
| `list`                                                              | Shows every task with its index and status.               |
| `todo <description>`                                                | Adds a simple todo task.                                  |
| `deadline <description> /by <yyyy-mm-dd>`                           | Adds a deadline due on a specific date.                   |
| `event <description> /from <yyyy-mm-dd HHmm> /to <yyyy-mm-dd HHmm>` | Adds an event with a start and end timeframe.             |
| `mark <index>` / `unmark <index>`                                   | Toggles whether a task is complete.                       |
| `delete <index>`                                                    | Removes the task at the given position.                   |
| `find <keyword> `                                                   | Returns the task whose description contains that keyword. |
| `bye`                                                               | Saves your list and closes the app.                       |

## Exiting Fish
When you are done, enter `bye`. Fish will thank you (`Merci Au Revoir~`) and close the window after saving your task list.
