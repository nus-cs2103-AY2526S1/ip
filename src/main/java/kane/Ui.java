package kane;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interaction by displaying messages
 * and reading user input from the command line.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Kane.\n" + "What can I do for you?");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye! Hope to see you soon!");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return the user command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a loading error message when tasks cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("OOPS!!! Could not load tasks from the data file.");
    }

    /**
     * Displays all tasks currently in the task list.
     *
     * @param tasks list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task the task added.
     * @param size the new size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task the task deleted.
     * @param size the new size of the task list.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task the task marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task the task marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Closes the scanner and releases resources.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Displays the list of tasks found from a search.
     * If no tasks are found, it prints a corresponding message.
     *
     * @param tasks The list of tasks that matched the search keyword.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        if (tasks.isEmpty()) {
            System.out.println("No tasks found matching your keyword.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }
}