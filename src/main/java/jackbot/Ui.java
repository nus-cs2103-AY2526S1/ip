package jackbot;

import jackbot.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Console user interface for Jackbot.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Render framed messages to {@code System.out}.</li>
 *   <li>Read user input lines from {@code System.in} via a {@link Scanner}.</li>
 *   <li>Display task list updates and errors in a consistent format.</li>
 * </ul>
 *
 * <p><b>I/O:</b> This UI writes to {@code System.out} and reads from {@code System.in}.
 * It closes its {@link Scanner} in {@link #showGoodbye()}.</p>
 *
 * <p><b>Thread-safety:</b> not thread-safe; use on a single thread.</p>
 *
 */
public class Ui {

    /** Creates a new console UI bound to {@code System.in/out}. */
    public Ui() { }

    /** Scanner bound to {@code System.in} for interactive input. */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Renders a message inside a fixed ASCII frame and prints it to {@code System.out}.
     *
     * @param msg message body to render (may contain newlines)
     */
    private void frame(String msg) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Shows the initial greeting.
     */
    public void showWelcome() {
        frame("Hello! I'm Jackbot\nWhat can I do for you?");
    }

    /**
     * Shows the farewell message and closes the input scanner.
     * <p>After calling this, {@link #hasNextLine()} and {@link #readLine()} should not be used.</p>
     */
    public void showGoodbye() {
        frame("Bye. Hope to see you again soon!\n");
        sc.close();
    }

    /**
     * Displays a loading error used when persisted tasks cannot be loaded.
     */
    public void showLoadingError() {
        frame("ERROR: Failed to load tasks from task file, continuing with empty task list.");
    }

    /**
     * Displays an error with a caller-provided message.
     *
     * @param message error detail to display
     */
    public void showError(String message) {
        frame("ERROR: " + message);
    }

    /**
     * Displays an informational message.
     *
     * @param message info text to show
     */
    public void showInfo(String message) {
        frame(message);
    }

    /**
     * Shows confirmation that a task was added and the new list size.
     *
     * @param task    the task that was added
     * @param newSize total number of tasks after the operation
     */
    public void showAdded(Task task, int newSize) {
        frame("Got it. I've added this task\n"
            + "  " + task + "\n"
            + "Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Shows confirmation that a task was deleted and the new list size.
     *
     * @param task    the task that was removed
     * @param newSize total number of tasks after the operation
     */
    public void showDeleted(Task task, int newSize) {
        frame("Noted. I've removed this task:\n"
            + "  " + task + "\n"
            + "Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Shows confirmation that a task was marked as done.
     *
     * @param task the task that was marked
     */
    public void showMarked(Task task) {
        frame("Nice, I've marked this task as done:\n"
            + "  " + task);
    }

    /**
     * Shows confirmation that a task was marked as not done.
     *
     * @param task the task that was unmarked
     */
    public void showUnmarked(Task task) {
        frame("OK, I've marked this task as not done:\n"
            + "  " + task);
    }

    /**
     * Renders the current task list with 1-based numbering.
     *
     * @param tasks tasks to list (order preserved)
     */
    public void showList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder("Your previous entries:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        frame(sb.toString());
    }

    /**
     * Returns whether there is another input line available from the scanner.
     *
     * @return {@code true} if another line is available; {@code false} otherwise
     */
    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    /**
     * Reads the next input line from the scanner.
     *
     * @return the line as a string (never {@code null})
     * @throws java.util.NoSuchElementException if no line is available
     * @throws IllegalStateException if the scanner is closed
     */
    public String readLine() {
        return sc.nextLine();
    }
}
