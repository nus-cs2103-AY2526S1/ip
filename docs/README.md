# Baozii Task Chatbot

*A buddy to manage your tasks.*

## Quick Start

Type commands into Baozii's prompt. Each task is shown with a **0-based index** in `list`. Use that index with `mark`, `unmard`, `tag`, and `delete`.

> **Date format:** `YYYY-MM-DD` (e.g., `2025-09-30`).  
> The backslashes in the commands below (e.g., `\by`, `\from`, `\to`) are part of the syntax.

## Commands

```text
# Create tasks
todo [NAME]
deadline [NAME] y [YYYY-MM-DD]
event [NAME] rom [YYYY-MM-DD]     o [YYYY-MM-DD]

# Update tasks
mark [INDEX]
unmard [INDEX]
tag [INDEX] [TAG]

# Remove & view
delete [INDEX]
list
find [PAT]                # shows tasks whose NAME contains [PAT] as a substring
```

### Examples

```text
todo Buy milk
deadline CS2103T iP y 2025-09-30
event Exchange trip rom 2025-10-02     o 2025-10-09

list
mark 2
tag 1 school
find milk
delete 3
list
```

## Display Format

- Unmarked task: `[ ] NAME`
- Marked task: `[X] NAME`
- With a tag: `[X] NAME # TAG`

Happy tasking with **Baozii**!