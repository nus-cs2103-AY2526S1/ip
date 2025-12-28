package bytebuddy.ui;

import java.util.Scanner;

import bytebuddy.task.Task;
import bytebuddy.task.TaskList;

/**
 * Handles user interactions for the ByteBuddy application.
 * Provides methods for reading commands and generating
 * response messages.
 */
public class Ui {
    public static final String LINE = "__________________________________";
    private final Scanner scanner;

    /**
     * Creates a new {@code Ui} instance with a {@link Scanner}
     * to read input from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input and trims leading/trailing whitespace.
     *
     * @return the user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Returns the greeting message when the program starts.
     */
    public String getGreetingMessage() {
        return wrapWithLines("Hello! I'm bytebuddy.ByteBuddy\nWhat can I do for you?");
    }

    /**
     * Returns the farewell message when the program ends
     * and closes the scanner.
     */
    public String getFarewellMessage() {
        scanner.close();
        return wrapWithLines("Bye. Hope to see you again soon!");
    }

    /**
     * Returns the help message when the program ends
     * and closes the scanner.
     */
    public String showHelpMessage() {
        scanner.close();
        String helpMsg = "Here are some commands you can use:\n"
                + "1. list - Lists all tasks\n"
                + "2. todo <description> - Adds a todo task\n"
                + "3. deadline <description> /by <date (yyyy-mm-dd)> - Adds a deadline task\n"
                + "4. event <description> /from <date time (yyyy-mm-dd HHmm) /to <date time (yyyy-mm-dd HHmm)> - Adds an event task\n"
                + "5. mark <task number> - Marks a task as done\n"
                + "6. unmark <task number> - Marks a task as not done\n"
                + "7. delete <task number> - Deletes a task\n"
                + "8. find <keyword> - Finds tasks containing the keyword\n"
                + "9. bye - Exits the application";
        return wrapWithLines(helpMsg);
    }

    /**
     * Returns an error message indicating that the saved
     * tasks file could not be loaded.
     */
    public String getLoadingErrorMessage() {
        return "Error loading tasks.txt file.";
    }

    /**
     * Returns a custom error message between divider lines
     * and closes the scanner.
     *
     * @param msg the error message to display.
     */
    public String getErrorMessage(String msg) {
        scanner.close();
        return wrapWithLines(msg);
    }

    /**
     * Returns a message showing all tasks in the given {@link TaskList}.
     *
     * @param tasks the list of tasks to display.
     */
    public String getTaskListMessage(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Returns a confirmation message when a task is marked
     * or unmarked as done.
     *
     * @param t      the task that was updated.
     * @param marked {@code true} if the task was marked as done,
     *               {@code false} if marked as not done.
     */
    public String getMarkedMessage(Task t, boolean marked) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        if (marked) {
            sb.append("Nice! I've marked this task as done:\n");
        } else {
            sb.append("OK, I've marked this task as not done yet:\n");
        }
        sb.append("  ").append(t).append("\n");
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Returns a confirmation message when a new task is added.
     *
     * @param tasks the updated list of tasks.
     * @param t     the newly added task.
     */
    public String getAddedMessage(TaskList tasks, Task t) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        sb.append("Got it. I've added this task:\n");
        sb.append("  ").append(t).append("\n");
        String ts = tasks.getSize() > 1 ? "tasks" : "task";
        sb.append("Now you have ").append(tasks.getSize()).append(" ").append(ts).append(" in the list.\n");
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Returns a confirmation message when a task is deleted.
     *
     * @param tasks   the updated list of tasks.
     * @param removed the task that was deleted.
     * @return the formatted deletion message.
     */
    public String getDeletedMessage(TaskList tasks, Task removed) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE).append("\n");
        sb.append("Noted. I've removed this task:\n");
        sb.append("  ").append(removed).append("\n");
        String ts = tasks.getSize() > 1 ? "tasks" : "task";
        sb.append("Now you have ").append(tasks.getSize()).append(" ").append(ts).append(" in the list.\n");
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Helper method to wrap a message with divider lines.
     *
     * @param msg Message to wrap
     */
    private String wrapWithLines(String msg) {
        return LINE + "\n" + msg + "\n" + LINE;
    }
}
