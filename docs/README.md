# Charli User Guide

![Product Screenshot](Ui.png)

Charli is a lightweight desktop chatbot that helps you track todos, deadlines, and events. It supports tagging, search, and autosave to a local file.

---
### Quick start

When Charli starts, type commands into the input box and press **Enter**.

Example session:

```
rotation
bop 360 
drop Palette /by 18/12/2025 2359
show The Chase /from Fri 7pm /to Fri 10pm
find Palette
tag 1 #indiepop
untag 1 #indiepop
played 1
unplayed 1
delete 1
bye
```
---

## Feature: Adding todos (bops)

Describe the action: Adds a simple todo without a date.

**Example:** `bop <description>`

**Example usage:**

```
bop Woozy
```

**Expected outcome:**

```
HOT! Added this bop to your rotation:
[T][ ] Woozy

Now you have 2 tracks in your rotation!
```

---

## Feature: Adding deadlines (drops)

Describe the action: Adds a deadline task that appears in the list and is saved to the data file. Dates must be in `dd/mm/yyyy HHmm`.

**Example:** `drop <description> /by <dd/mm/yyyy HHmm>`

**Example usage:**

```
drop Palette /by 19/09/2025 2359
```

**Expected outcome:**

```
Sweettt! Added this upcoming drop:
[D][ ] Palette (by: 19 Sep 2025, 11:59PM)

Now you have 1 tracks in your rotation!
```

---

## Feature: Adding shows (events)

Describe the action: Adds an event with free‑form `/from` and `/to` strings.

**Example:** `show <title> /from <string> /to <string>`

**Example usage:**

```
show The Chase /from Fri 7pm /to Fri 10pm
```

**Expected outcome:**

```
Icon, I added this show to your schedule:
[E][ ] The Chase (from: Fri 7pm, to: Fri 10pm)

Now you have 3 tracks in your rotation!
```

---

## Feature: Tagging tasks

Describe the action: Add or remove tags from a task. Tags are normalized to start with `#`; duplicates are ignored.

**Add tags** — `tag <index> <tag1>`

```
tag 2 #urgent
```

**Expected outcome:**

```
Tagged track 2 with #urgent
[T][ ] 360 [#urgent]
```

**Remove tags** — `untag <index> <tag1>`

```
untag 2 #urgent
```

**Expected outcome:**

```
Untagged track 2 with #urgent
[T][ ] 360
```

---

## Feature: List and Find

Describe the action: Show all tasks, or filter by keyword in description.

**List** — `rotation`

```
INCREDIBLE MIX INCOMING!!! 
1.[T][ ] buy projector tickets [#school]
2.[D][ ] Palette (by: 19 Sep 2025, 11:59PM)
3.[E][ ] Hackathon (from: Sat 9am, to: Sun 6pm)
```

**Find** — `find <keyword>`

```
find Woozy
```

**Expected outcome:**

```
Here are the matching tracks in your rotation:
1.[T][ ] Woozy [#karaoke]
```

---

## Feature: Mark played / unplayed

Describe the action: Mark a task done (played) or not done (unplayed).

**Mark played** — `played <index>`

```
played 1
```

**Expected outcome:**

```
AMAZING! I've marked this bop as played:
[T][X] 360
```

**Mark unplayed** — `unplayed <index>`

```
unplayed 1
```

**Expected outcome:**

```
AW! I've marked this track as unplayed:
[T][ ] 360
```

---

## Feature: Delete

Describe the action: Delete a task by its 1‑based index.

**Delete** — `delete <index>`

```
delete 1
```

**Expected outcome:**

```
Got it! I've removed this track:
[T][ ] 360
```
