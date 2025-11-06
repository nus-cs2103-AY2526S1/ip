package bestbot;

import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;
import bestbot.task.Task;

/**
 * Handles all user interaction (input/output).
 * Can print to console or to a custom PrintStream (for GUI integration).
 */
public class Ui {
    private final Scanner scanner;
    private final PrintStream out; // Output stream (System.out or GUI)

    /**
     * Constructs a Ui object to handle console input/output.
     */
    public Ui() {
        this(System.out); // default to console
    }

    /**
     * Constructs a Ui object with a custom output stream.
     * Useful for GUI integration.
     *
     * @param out PrintStream to write output
     */
    public Ui(PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(System.in);
    }

    /** Prints the welcome message. */
    public void showWelcome() {
        out.println("Hello! I'm Bestbot");
        out.println("What can I do for you?");
    }

    /** Prints the exit message. */
    public void showGoodbye() {
        out.println("Bye. Hope to see you again soon!");
    }

    /** Reads the next input line from the user. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Prints a divider line between UI outputs. */
    public void showLine() {
        out.println("____________________________________________________________");
    }

    /**
     * Prints an error message to the user.
     *
     * @param message The error text.
     */
    public void showError(String message) {
        out.println("OOPS!!! " + message);
    }

    /** Informs the user that loading from file failed. */
    public void showLoadingError() {
        out.println("Error loading tasks, starting with an empty list.");
    }

    /**
     * Displays all tasks currently in the list.
     *
     * @param tasks The list of tasks to display.
     */
    /**
     * Displays all tasks currently in the list.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            out.println("Your task list is empty.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Displays a message when a task has been added.
     *
     * @param task       The task that was added.
     * @param totalTasks The updated total number of tasks.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        out.println("Got it. I've added this task:");
        out.println("  " + task);
        out.printf("Now you have %d tasks in the list.%n", totalTasks);
    }

    /**
     * Displays a message when a task has been deleted.
     *
     * @param task       The task that was removed.
     * @param totalTasks The updated total number of tasks.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        out.println("Noted. I've removed this task:");
        out.println("  " + task);
        out.printf("Now you have %d tasks in the list.%n", totalTasks);
    }

    /**
     * Displays a message when a task has been marked as done.
     *
     * @param task The task that was marked done.
     */
    public void showTaskDone(Task task) {
        out.println("Nice! I've marked this task as done:");
        out.println("  " + task);
    }

    /**
     * Displays the tasks matching a find keyword.
     *
     * @param tasks The list of matching tasks.
     */
    public void showFoundTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            out.println("No matching tasks found.");
        } else {
            out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        }
    }


    /**
     * Displays a message when a task has been marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        out.println("OK, I've marked this task as not done yet:");
        out.println("  " + task);
    }

    /**
     * Prints a plain message to the output stream without any formatting.
     *
     * <p>This is useful for displaying simple information such as
     * command headers (e.g., "Here are the tasks in your list:")
     * or feedback messages that are not task-related.</p>
     *
     * @param message The message to display. Must not be {@code null}.
     */
    public void showMessage(String message) {
        out.println(message);
    }
}
