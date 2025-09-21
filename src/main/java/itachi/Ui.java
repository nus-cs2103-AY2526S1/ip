package itachi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import itachi.task.TaskList;

/**
 * Handles all user interactions for the Itachi bot.
 */
public class Ui {
    private Scanner scanner;
    private List<String> messages;

    /**
     * Scanner for reading user input from console.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.messages = new ArrayList<>();
    }


    public void startNewCommand() {
        messages.clear();
    }

    /**
     * Welcome message is created and the method showMessage is called to
     * print and save the message
     */
    public void showWelcome() {
        String msg = "\"Hello! I'm Itachi\nWhat can I do for you?";
        showMessage(msg);
    }

    public void showLine() {
        showMessage("--------------------------------------");
    }

    /**
     * Displays an error message and saved the message
     * @param errorMsg
     */
    public void showError(String errorMsg) {
        String msg = "Error: " + errorMsg;
        this.showMessage(msg);
    }

    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    /**
     * Displays the results of a task search to the user
     * If no tasks match the search criteria, an appropriate message is shown.
     * Otherwise, the matching tasks are listed with their indices.
     * Each message is printed to the console and stored in the {@link #messages} list.
     * @param matchedTasks the {@link TaskList} containing tasks that match the search keyword;
     */
    public void showFindResult(TaskList matchedTasks) {
        assert matchedTasks != null : "TaskList must not be null";
        if (matchedTasks.getTasks().isEmpty()) {
            this.showMessage("-------------------------------------");
            String msg = " No matching tasks found.";
            this.showMessage(" No matching tasks found.");
            this.showMessage("-------------------------------------");
        } else {
            this.showMessage("-------------------------------------");
            this.showMessage(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchedTasks.size(); i++) {
                assert matchedTasks.getTasks().get(i) != null : "Task in TaskList should not be null";
                String msg = (i + 1) + ". " + matchedTasks.getTasks().get(i);
                this.showMessage(" " + (i + 1) + ". " + matchedTasks.getTasks().get(i));
            }
        }
    }

    public List<String> getMessage() {
        return new ArrayList<>(messages);
    }

    /**
     * Displays the given message and saves it to {@link #messages}.
     * @param message the message to display
     */
    public void showMessage(String message) {
        assert message != null : "Message cant be null";
        System.out.println(message); // optional for console output
        messages.add(message);
    }

}
