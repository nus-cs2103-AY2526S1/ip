package ui;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

/**
 * Handles printing content to screen.
 */
public class Ui {
    private static final String name = "Ineffa";
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays welcome message
     *
     * @return Welcome message string
     */
    public static String showWelcome() {
        String message = "Hello! I'm " + name + "\n" + "What can I do for you? Enter \"help\" if needed";
        displayMessage(message);
        return message;
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        displayMessage(message);
    }

    public void exit() {
        displayMessage(exitMessage);
    }

    /**
     * Prints all tasks in a specific format
     *
     * @param tasks List of tasks
     * @return String concatenated with all tasks in specific format
     */
    public String displayTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            String emptyMessage = "No tasks found";
            Ui.displayMessage(emptyMessage);
            return emptyMessage;
        }

        ArrayList<String> messages = new ArrayList<>();
        String headerMessage = "Here are the tasks in your list:\n";
        messages.add(headerMessage);
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = (i + 1) + ": " + tasks.get(i);
            Ui.displayMessage(taskString);
            messages.add(taskString);
        }
        assert messages.size() > 1 : "List of messages should not only contain headerMessage";
        return String.join("\n", messages);
    }

    public static String getExitMessage() {
        return exitMessage;
    }
}
