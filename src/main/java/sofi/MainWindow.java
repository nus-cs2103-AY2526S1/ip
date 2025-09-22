package sofi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private SOFI sofi;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image sofiImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSofi(SOFI sofi) {
        this.sofi = sofi;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing SOFI's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSofiDialog(response, sofiImage)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        }
        
        // Process the command through SOFI
        String command = Parser.parseCommand(input);
            
            if (command.equals("list")) {
                return formatTaskList(sofi.getTasks().getTasks());
            } else if (command.equals("todo")) {
                String taskDescription = Parser.parseTodoDescription(input);
                if (taskDescription.isEmpty()) {
                    return "A todo needs a description. Try: todo read book";
                }
                sofi.getTasks().addTask(new Todo(taskDescription));
                sofi.saveTasks();
                return "Got it. I've added this task:\n   " 
                        + sofi.getTasks().getTask(sofi.getTasks().size() - 1).toString() 
                        + "\nNow you have " + sofi.getTasks().size() + " tasks in the list.";
            } else if (command.equals("deadline")) {
                if (!input.contains(" /by ")) {
                    return "Deadlines must include /by. Example: deadline return book /by Sunday";
                }
                String[] parts = Parser.parseDeadline(input);
                String taskDescription = parts[0];
                String by = parts[1];
                if (taskDescription.isEmpty()) {
                    return "The deadline description cannot be empty.";
                }
                if (by.isEmpty()) {
                    return "The /by time cannot be empty.";
                }
                sofi.getTasks().addTask(new Deadline(taskDescription, by));
                sofi.saveTasks();
                return "Got it. I've added this task:\n   " 
                        + sofi.getTasks().getTask(sofi.getTasks().size() - 1).toString() 
                        + "\nNow you have " + sofi.getTasks().size() + " tasks in the list.";
            } else if (command.equals("event")) {
                if (!input.contains(" /from ") || !input.contains(" /to ")) {
                    return "Events need /from and /to. Example: event team meeting /from Mon 2pm /to Mon 3pm";
                }
                String[] parts = Parser.parseEvent(input);
                String taskDescription = parts[0];
                String from = parts[1];
                String to = parts[2];
                if (taskDescription.isEmpty()) {
                    return "The event description cannot be empty.";
                }
                if (from.isEmpty() || to.isEmpty()) {
                    return "Both /from and /to times must be provided.";
                }
                sofi.getTasks().addTask(new Event(taskDescription, from, to));
                sofi.saveTasks();
                return "Got it. I've added this task:\n   " 
                        + sofi.getTasks().getTask(sofi.getTasks().size() - 1).toString() 
                        + "\nNow you have " + sofi.getTasks().size() + " tasks in the list.";
            } else if (command.equals("mark")) {
                String[] tokens = input.split(" ", 2);
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    return "Please provide a task number to mark. Example: mark 2";
                }
                int taskNumber;
                try {
                    taskNumber = Parser.parseTaskNumber(input);
                } catch (NumberFormatException e) {
                    return "That doesn't look like a number. Try: mark 2";
                }
                if (!sofi.getTasks().isValidIndex(taskNumber)) {
                    return "Task number out of range. You have " + sofi.getTasks().size() + " task(s).";
                }
                sofi.getTasks().markTask(taskNumber, true);
                sofi.saveTasks();
                return "Nice! I've marked this task as done:\n   " + sofi.getTasks().getTask(taskNumber).toString();
            } else if (command.equals("unmark")) {
                String[] tokens = input.split(" ", 2);
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    return "Please provide a task number to unmark. Example: unmark 2";
                }
                int taskNumber;
                try {
                    taskNumber = Parser.parseTaskNumber(input);
                } catch (NumberFormatException e) {
                    return "That doesn't look like a number. Try: unmark 2";
                }
                if (!sofi.getTasks().isValidIndex(taskNumber)) {
                    return "Task number out of range. You have " + sofi.getTasks().size() + " task(s).";
                }
                sofi.getTasks().markTask(taskNumber, false);
                sofi.saveTasks();
                return "OK, I've marked this task as not done yet:\n   " + sofi.getTasks().getTask(taskNumber).toString();
            } else if (command.equals("delete")) {
                String[] tokens = input.split(" ", 2);
                if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                    return "Please provide the task number to delete. Example: delete 3";
                }
                int taskNumber;
                try {
                    taskNumber = Parser.parseTaskNumber(input);
                } catch (NumberFormatException e) {
                    return "That doesn't look like a number. Try: delete 3";
                }
                if (!sofi.getTasks().isValidIndex(taskNumber)) {
                    return "Task number out of range. You have " + sofi.getTasks().size() + " task(s).";
                }
                Task removed = sofi.getTasks().removeTask(taskNumber);
                sofi.saveTasks();
                return "Noted. I've removed this task:\n   " + removed.toString() 
                        + "\nNow you have " + sofi.getTasks().size() + " tasks in the list.";
            } else if (command.equals("find")) {
                String searchTerm = Parser.parseFindKeyword(input);
                if (searchTerm.isEmpty()) {
                    return "Please provide a search term. Example: find book";
                }
                ArrayList<Task> matchingTasks = sofi.getTasks().findTasks(searchTerm);
                return formatFoundTasks(matchingTasks);
            } else {
                return "I don't recognize that command. Try: list, todo, deadline, event, mark, unmark, delete, find, bye";
            }
    }
    
    private String formatTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "You have no tasks in your list.";
        }
        
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return result.toString().trim();
    }
    
    private String formatFoundTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        }
        
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return result.toString().trim();
    }
}
