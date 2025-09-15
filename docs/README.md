# 👾 ByteBot

![ByteBot](Ui.png)
> *Organize yourself, one task at a time*

**ByteBot** is a task management application to help you stay productive


## 📋 Commands

1. **Add Tasks**:
   - `todo <description>` - Add a simple todo task
   - `deadline <description> /by <date>` - Add a task with deadline
   - `event <description> /from <start> /to <end>` - Add an event task

2. **Manage Tasks**:
   - `list` - Display all tasks
   - `mark <task_number>` - Mark a task as complete
   - `unmark <task_number>` - Mark a task as incomplete
   - `delete <task_number>` - Remove a task

3. **Navigation**:
   - `find <keyword>` - Search for description
   - `bye` - Exit the application

## 💻 Driver Code

```java
public class ByteBot {
    private final Ui ui;
    private final Storage storage;
    
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage);
                isExit = c.isExit();
            } catch (ByteException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.closeScanner();
    }
}
```

## Try ByteBot NOW!

```bash
> todo Buy groceries
> deadline Submit report /by 01/01/2024 1000
> list
> mark 1
> find report
> bye
```

## ✅ Functionality

- [x] **`Core Task Management`** - Add, delete, mark tasks
- [x] **`Handle multiple tasks`** - Todo, Deadline, Event
- [x] **`Search Functionality`** - Find tasks by keyword
- [x] **`Data Persistence`** - Save/load tasks from file
- [x] **`Error Handling`** - Exception handling


## 🤝 Releases

Here is how you access the releases:

1. Find the release and download [here](https://github.com/lhurr/ip/releases/tag/v0.1)
2. Add your tasks freely!
3. Manage your tasks seamlessly with Bytebot!


## 📄 Source code link

[Link to ByteBot](https://github.com/lhurr/ip/tree/master)


## Bug Reports

Found a bug? Please report it:
- A clear description of the problem
- Steps to reproduce the issue
- Expected vs actual behavior
- Other information

---

*~~procrastination~~ productivity starts with good organization*
