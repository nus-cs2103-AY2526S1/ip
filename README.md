# Shrek: Your Friendly Ogre Task Manager

> "It's all about o-gre now." – Shrek, probably.

Shrek is here to free your mind from the burdens of remembering tasks, so you can focus on more important things, like enjoying layers (of an onion) or rescuing princesses. It's a **fast**, **text-based** task manager that lives in your command line, powered by Java.

## 🧅 Why Use Shrek?

Shrek is:
*   **Text-based** and easy to use.
*   **Super FAST** at managing your tasks.
*   **Easy to learn** with a simple command set.
*   **Absolutely FREE!**

## 🚀 Quick Start

Getting started is a breeze:

1.  Download the latest `shrek.jar` from the [releases page](https://github.com/jj55j7/ip/releases/tag/A-Release).
2.  Double-click the JAR file to launch the application.
3.  Start adding your tasks!
4.  Let Shrek manage your tasks for you 😉

## ✨ Features

- [x] **Add, List, Delete Tasks**
- [x] **Three Task Types:**
    - `Todo`: For simple tasks.
    - `Deadline`: For tasks with a by-date.
    - `Event`: For tasks with a start and end time.
- [x] **Mark Tasks as Done/Not Done**
- [x] **Data Persistence**: Your tasks are automatically saved to `./data/shrek.txt`.
- [x] **Search Functionality** 
- [x] **GUI** 

## 💻 For the Java Enthusiasts

Shrek is built with Java and follows OOP principles. Here's a peek at the chatbot entry point:

```java
public class Shrek {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Shrek(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        // Process user input and return response
        try {
            Instruction instruction = Parser.parse(input);
            return instruction.execute(tasks, ui, storage);
        } catch (ShrekException e) {
            return ui.showError(e.getMessage());
        }
    }
}