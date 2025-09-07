package dobby;

import java.util.Scanner;
import dobby.task.Task;
import java.util.List;

/**
 * Handles all user interaction by printing messages and reading input.
 */
public class Ui {

    /** Displays a welcome message. */
    public void showWelcome() {
        System.out.println("Hello! I'm Dobby.");
        System.out.println("What can I do for you?");
    }

    /** Displays a goodbye message. */
    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Reads the next command from the user.
     *
     * @param sc Scanner object for reading input.
     * @return The raw user input line.
     */
    public String readCommand(Scanner sc) {
        System.out.print("> ");
        return sc.nextLine().trim();
    }

    /**
     * Displays the current task list.
     *
     * @param tasks List of tasks.
     */
    public void showTasks(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println();
    }

    /**
     * Displays an error message.
     *
     * @param message Error description.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task Task that was added.
     * @param total Number of tasks in the list.
     */
    public void showTaskAdded(Task task, int total) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + total + " tasks in the list.\n");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task Task that was deleted.
     */
    public void showTaskDeleted(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task + "\n");
    }

    /** Displays a message when a task is marked done. */
    public void showTaskMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done.\n");
    }

    /** Displays a message when a task is marked undone. */
    public void showTaskMarkedUndone(Task task) {
        System.out.println("OK, I've marked this task as not done yet.\n");
    }
}
