package duke;
import java.util.Scanner;

/**
 * Handles interactions with the user.
 * Deals with reading user input and displaying messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a new Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Penguin\nWhat can I do for you?\n");
    }

    /**
     * Shows the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows an error message to the user.
     * @param message The error message to display
     */
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with empty task list.");
    }

    /**
     * Shows a message when a task is added.
     * @param task The task that was added
     * @param taskCount The total number of tasks
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:\n  " + task
                + "\nNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows a message when a task is marked as done.
     * @param task The task that was marked
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Shows a message when a task is unmarked.
     * @param task The task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Nice! I've marked this task as not done yet:\n" + task);
    }

    /**
     * Shows a message when a task is deleted.
     * @param task The task that was deleted
     * @param taskCount The remaining number of tasks
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:\n  " + task
                + "\nNow you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows the list of tasks.
     * @param tasks The TaskList containing all tasks
     */
    public void showTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Shows the matching tasks found by search.
     * @param matchingTasks The TaskList containing matching tasks
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.size() == 0) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Shows an invalid task error.
     */
    public void showInvalidTask() {
        System.out.println("invalid task");
    }

    /**
     * Reads a command from the user.
     * @return The user input as a string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Checks if there are more commands to read.
     * @return true if there are more lines, false otherwise
     */
    public boolean hasNextCommand() {
        return scanner.hasNextLine();
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Shows a message when tasks have been sorted.
     */
    public void showTasksSorted() {
        System.out.println("Tasks have been sorted! Deadlines are now arranged chronologically (earliest to latest).");
    }
}
