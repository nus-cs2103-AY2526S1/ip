package ui;
import task.TaskList;
import task.Task;

/**
 * @author Anand Bala
 * Primarily deals with user-facing logic, generating the UI components to be shown to the user.
 */

public class Ui {
    public static final String INDENTATION = "_______________________________________\n";

    private static final String LOGO = "\n █████╗ ██╗     ███████╗██████╗ ███████╗██████╗\n" +
                                        "██╔══██╗██║     ██╔════╝██╔══██╗██╔════╝██╔══██╗\n" +
                                        "███████║██║     █████╗  ██████╔╝█████╗  ██║  ██║\n" +
                                        "██╔══██║██║     ██╔══╝  ██╔══██╗██╔══╝  ██║  ██║\n" +
                                        "██║  ██║███████╗██║     ██║  ██║███████╗██████╔╝\n" +
                                        "╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝  ╚═╝╚══════╝╚═════╝\n";

    /**
     * Wraps a message with a header and footer line for display.
     *
     * @param msg the message body to wrap
     * @return the wrapped message, with {@link #INDENTATION} above and below
     */
    public static String wrapText(String msg) {
        return INDENTATION + msg + "\n" + INDENTATION;
    }

    /**
     * Prints the welcome banner and greeting to standard output.
     */
    public void showWelcome() {
        String message = LOGO + "Good day, Master Bruce. How may I assist you?";
        System.out.println(wrapText(message));
    }

    /**
     * Prints the current task list to standard output.
     *
     * @param tasks the tasks to display
     */
    public void showList(TaskList tasks) {
        System.out.println(wrapText(tasks.toString()));
    }

    /**
     * Prints a message indicating a task has been marked done (or was already done),
     * followed by the task's string representation.
     *
     * @param task   the task being marked
     * @param marked {@code true} if the operation changed the state to done; {@code false} if it was already done
     */
    public void showMarked(Task task, boolean marked) {
        String message = marked
                ? "Task has been marked as done, Master Bruce.\n"
                : "Task has already been marked as done, Master Bruce.\n";
        message += task.toString();
        System.out.println(wrapText(message));
    }

    /**
     * Prints a message indicating a task has been marked undone (or was already undone),
     * followed by the task's string representation.
     *
     * @param task   the task being unmarked
     * @param marked {@code true} if the operation changed the state to undone; {@code false} if it was already undone
     */
    public void showUnmarked(Task task, boolean marked) {
        String message = marked
                ? "Task has been marked as undone, Master Bruce.\n"
                : "Task has already been marked as undone, Master Bruce.\n";
        message += task.toString();
        System.out.println(wrapText(message));
    }

    /**
     * Prints a confirmation message for a newly added task.
     *
     * @param task the task that was added
     */
    public void showAddedTask(Task task) {
        String message = "This task has been successfully added:\n" + task;
        System.out.println(wrapText(message));
    }

    /**
     * Prints a deletion confirmation message and the remaining task count.
     *
     * @param tasks the current task list (used to show remaining count)
     * @param task  the task that was deleted
     */
    public void showDelete(TaskList tasks, Task task) {
        String message = "Noted, Master Bruce. This task has been successfully deleted:\n" + task + "\n";
        message += "There are " + tasks.size() + " tasks left.";
        System.out.println(Ui.wrapText(message));
    }

    /**
     * Prints the default prompt instructing the user to use supported commands.
     */
    public void showDefault() {
        System.out.println("Good Evening, Master Bruce. Please use the commands to continue.");
    }

    /**
     * Prints an error message wrapped for display.
     *
     * @param message the error message to show
     */
    public void showError(String message) {
        System.out.println(Ui.wrapText(message));
    }

    /**
     * Prints the tasks that are found by a simple keyword search.
     *
     * @param tasks the tasks to show, that match
     */
    public void showFind(TaskList tasks) {
        System.out.println(Ui.wrapText("These are the tasks that matches your search, sir.\n" + tasks));
    }

    /**
     * Prints the farewell message and exits the interaction context.
     */
    public void showBye() {
        System.out.println(Ui.wrapText("Thank you, Master Bruce. See you soon."));
    }

}
