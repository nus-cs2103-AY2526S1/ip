# Heimerdinger — your yordle task chatbot 🧪⚙️

Heimerdinger is a chat-style desktop app that manages your tasks (ToDo / Deadline / Event) through natural, typed commands.
It replies in a Piltover scientist voice and persists your tasks to disk. For science and progress!

---

## ✨ What you can do

- Add **ToDo**, **Deadline**, and **Event** tasks
- **List**, **find**, **mark**, **unmark**, and **delete** tasks
- See an **agenda** for a given date with `schedule`
- Enjoy a clean chat UI (user vs bot bubbles, error highlighting)
- Automatic **saving** to a text file

---

## 🧰 Usage

### Add tasks

**ToDo**
```commandline
todo <description>
```

**Deadline**
```commandline
deadline <description> /by <yyyy-MM-dd OR yyyy-MM-dd HH:mm>
```

**Event**
```commandline
event <description> /from <yyyy-MM-dd OR yyyy-MM-dd HH:mm> /to <yyyy-MM-dd OR yyyy-MM-dd HH:mm>
```

### Manage tasks

**List all tasks**
```commandline
list
```

**Mark as done**
```commandline
mark <task-number>
```

**Mark as not done**
```commandline
unmark <task-number>
```

**Delete**
```commandline
delete <task-number>
```

### Search & schedule

**Find by keyword (single word)**
```commandline
find <keyword>
```

**Show agenda for a date**
```commandline
schedule <yyyy-MM-dd OR yyyy-MM-dd HH:mm>
```

### Exit

**Quit the app**
```commandline
bye
```

