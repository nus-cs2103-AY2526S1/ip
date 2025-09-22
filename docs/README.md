# Botccoli 🥦 User Guide

> "Best Veggie is Broccoli, Best Chatbox is Botccoli" - Jiayi Zhang [Who is her?](https://broccoli0616.github.io/Zhang-Jiayi-page/)

Botccoli frees your mind of having to remember things you need to do. It's a:
- **text-based** task management application
- **easy to learn** and use
- **FAST** *SUPER FAST* to operate

![Userinterface](./Ui.png)

### For Regular Users
1. Download the latest release JAR file[here](https://github.com/broccoli0616/ip/releases/tag/A-CheckStyle)
2. Double-click the downloaded file to launch the application or Copy the jar file into an empty folder, Open a command window in that folder, enter : 
```
java -jar "botccoli.jar"
```
3. Start adding your tasks!

### For Java Programmers
If you want to practice Java or contribute to development:

```java
public void start(Stage stage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Botccoli");
            fxmlLoader.<MainWindow>getController().setBroccoli(broccoli);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
```

### Getting Started
When you first launch Botccoli, you'll be greeted with:
```
Hello! Hello! Hello! I'm Broccoli. Your Green Task Buddy!
Tell me what you gonna do and I will help you track them!
Enter (help), I will let you know how to use the app
```

Type `help` to see all available commands.

### Feature 1: Adding Tasks

#### 1. Todo Tasks
For simple tasks without specific dates:
```
todo finish lecture quiz
todo buy groceries
todo call mom
```

#### 2. Deadline Tasks
For tasks with specific due dates:
```
deadline submit assignment /by 25/12/2024 2359
deadline pay bills /by 01/01/2025 1200
```
**Format:** `deadline [task name] /by DD/MM/YYYY HHMM`

#### 3. Event Tasks
For tasks with start and end times:
```
event team meeting /from 2pm /to 4pm
event birthday party /from 6pm /to 11pm
```
**Format:** `event [task name] /from [start time] /to [end time]`

### Feature 2: Managing Your Tasks

#### View All Tasks
```
list
```
This displays all your tasks with their current status.

#### Mark Tasks as Complete
```
mark 1
mark 3
```
Marks the specified task number as completed (shows x).

#### Unmark Tasks
```
unmark 2
```
Removes the completion mark from the specified task.

#### Delete Tasks
```
delete 1
delete 5
```
Permanently removes the specified task from your list.

#### Find Tasks
```
find meeting
find assignment
find grocery
```
Searches for tasks containing the specified keywords.

### Other Commands

#### Get Help
```
help
```
Displays the complete list of commands and their usage.

#### Exit Application
```
bye
```
Saves your tasks and closes the application with a friendly goodbye message.

## Data Storage

- Your tasks are automatically saved to `./data/broccoli.txt`
- Tasks are loaded automatically when you restart the application
- No manual saving required - everything is handled automatically!


## Error Handling

Botccoli will guide you if you make mistakes:
- Empty commands will prompt you to enter a valid command
- Invalid task numbers will show an error message
- Missing arguments will tell you exactly what's needed

## Technical Requirements

- Java 11 or higher
- JavaFX (included in distribution)
- Minimum 220px height × 417px width display

## Contributing

This is a Java-based application built with:
- JavaFX for GUI
- FXML for interface design
- File-based storage system

Feel free to contribute improvements or report issues!

