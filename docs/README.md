# Bestie User Guide

Bestie is your peppy personal task buddy. Chat with her chat with her in the JavaFX app to track everything from simple 
todos to scheduled events, all while she keeps your spirits up.

---

## Quick start

1. **Install Java 17 or newer.** JavaFX is bundled through Gradle, so no extra setup is required.
2. **Download the project.** Download the newest release of Bestie
3. **Launch the chatbot.**
    * **GUI experience (recommended):**
      * Download the JAR file and double click it, or navigate to where you downloaded the JAR file and run it with 
      `java -jar Bestie.jar`
    * **Command-line experience:**
      ```bash
      ./gradlew build
      java -cp build/classes/java/main bestie.Bestie
      ```
      You will see Bestie's welcome banner and can start chatting in your terminal.
4. **Where your tasks live:** Bestie autosaves to `data/bestie.txt` in the working directory after every change. Keep 
this file with the program to preserve your list.

---

## Reading this guide

* **Command format** uses `UPPER_CASE` for parameters you supply.
* **Date and time inputs** accept friendly formats such as `2024-03-15`, `15/03/2024`, or `2024-03-15 1800`.
* **Task numbers** are always 1-based and correspond to the indices shown by the `list` command.

---

## Command summary

| Action | Format | Example | What Bestie says                                         |
| --- | --- | --- |----------------------------------------------------------|
| Show all tasks | `list` | `list` | "Here is your task list bestie!" followed by every task. |
| Add a todo | `todo DESCRIPTION` | `todo plan spring break` | Confirms the task and reports the new total.             |
| Add a deadline | `deadline DESCRIPTION /by DATE_TIME` | `deadline submit report /by 2024-04-01 2359` | Displays the deadline with a pretty due date.            |
| Add an event | `event DESCRIPTION /from START /to END` | `event finals /from 2024-04-10 0900 /to 2024-04-10 1100` | Shows the event span with parsed times.                  |
| Mark as done | `mark TASK_NO` | `mark 2` | Celebrates that the task is complete.                    |
| Mark as not done | `unmark TASK_NO` | `unmark 2` | Lets you know the task is undone.                        |
| Delete a task | `delete TASK_NO` | `delete 3` | Removes the task and shares the remaining count.         |
| Tag a task | `tag TASK_NO TAG…` | `tag 1 school urgent` | Adds one or more tags, ignoring duplicates.              |
| Find by keyword or tag | `find KEYWORD` | `find school` | Lists all matching tasks, including tag hits.            |
| Exit | `bye` | `bye` | Sends you off with encouragement.                        |

---

## Working with your tasks

### Listing your tasks
```
> list
____________________________________________________________
 Here is your task list bestie!
 1.[T][ ] plan spring break
 2.[D][ ] submit report (by: Apr 1 2024, 11:59 PM)
____________________________________________________________
```
Use this whenever you need the latest overview. Newly added tasks appear at the end of the list.

### Adding todos
```
> todo plan spring break
____________________________________________________________
 aightt ive added this task:
  [T][ ] plan spring break
 now you have 1 tasks in your list!!
____________________________________________________________

```
Use todos for simple reminders with no dates. Descriptions must not be empty.

### Adding deadlines
```
> deadline submit report /by 2024-04-01 2359
____________________________________________________________
 aightt ive added this task:
  [D][ ] submit report (by: Apr 1 2024, 11:59 PM)
 now you have 2 tasks in your list!!
____________________________________________________________
```
* Add the `/by` clause to specify when the work is due.
* Bestie parses common date and time formats and normalizes them for display and storage.

### Adding events
```
> event finals /from 2024-04-10 0900 /to 2024-04-10 1100
____________________________________________________________
 aightt ive added this task:
  [E][ ] finals (from: Apr 10 2024, 9:00 AM to Apr 10 2024, 11:00 AM)
 now you have 3 tasks in your list!!
____________________________________________________________
```
Provide both `/from` and `/to` clauses so Bestie knows the full time span.

### Marking and unmarking
```
> mark 2
____________________________________________________________
 YAYYY ive marked:
  [D][X] submit report (by: Apr 1 2024, 11:59 PM)
____________________________________________________________
```
`mark` celebrates a completed task, while `unmark` switches it back to pending. Use the task number from `list`.

### Tagging tasks
```
> tag 1 school urgent
____________________________________________________________
 yasss ive tagged it with:
  #school #urgent
  [T][ ] plan spring break #school #urgent
____________________________________________________________
```
* Add as many tags as you like in one command.
* Tags are case-insensitive, deduplicated, and always displayed with a `#` prefix.

### Finding matches
```
> find school
____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][ ] plan spring break #school #urgent
____________________________________________________________
```
Searches look for the keyword in both descriptions and tags, making it easy to pull up related work.

### Deleting tasks
```
> delete 3
____________________________________________________________
 Noted. I've removed this task:
  [E][ ] finals (from: Apr 10 2024, 9:00 AM to Apr 10 2024, 11:00 AM)
 Now you have 2 tasks in the list.
____________________________________________________________
```
Use this to clear tasks you no longer need. Deletions are immediate, so double-check the task number first.

### Saying goodbye
```
> bye
____________________________________________________________
 Bye bestie~ Keep slayin and prayin!
____________________________________________________________
```
This command ends the session. Your tasks remain saved to disk.

---
## Tips & troubleshooting

* **"Please tell me what to find bestie!"** – Most commands need extra details. Recheck the format in the table above.
* **"that doesn't look like a valid date"** – Ensure your `/by`, `/from`, or `/to` values follow one of the supported date formats (e.g., `2024-03-15` or `15/03/2024 1800`).
* **Recovering your list on a new machine** – Copy `data/bestie.txt` alongside Bestie before launching.
* **Editing the save file manually** – Only do this while Bestie is closed. Keep the `|` separators intact to avoid skipped entries.

---

## What's next?

Bestie already handles the essentials: autosaving, tagging, and flexible date parsing. Keep an eye on the project page for future enhancements like reminders and recurring tasks. Until then, happy chatting with your new bestie!