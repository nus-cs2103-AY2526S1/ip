package jooh.ui;

import java.util.List;
import java.util.stream.IntStream;

import jooh.task.Task;

/**
 * Handles all user interface interactions for Jooh.
 * Provides methods to print greetings, farewells, and status updates
 * about tasks to the console in a consistent format, as well as
 * string-returning counterparts for GUI usage.
 */
public class Ui {
    private final String line = "_______________________________________";

    // =========================================================================
    // String formatters (for GUI)
    // =========================================================================

    /**
     * Returns the welcome message shown when the application starts, formatted as a String.
     *
     * @return The formatted welcome message.
     */
    public String formatWelcomeMsg() {
        return String.join("\n",
                line,
                "Hello! I'm Jooh, a pre-programmed ChatBot",
                "What can I do for you today?",
                line
        );
    }

    /**
     * Returns the goodbye message shown when the application exits, formatted as a String.
     *
     * @return The formatted goodbye message.
     */
    public String formatGoodbyeMsg() {
        return String.join("\n",
                line,
                "Have a nice day! Goodbye!",
                line
        );
    }

    /**
     * Returns a formatted list of all tasks in the current task list.
     *
     * @param tasks The list of tasks to format.
     * @return The formatted task list message.
     */
    public String formatListTasksMsg(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(line).append("\n");
        sb.append("Here are the tasks in your list:").append("\n");
        if (tasks.isEmpty()) {
            sb.append("(no tasks yet)").append("\n");
        } else {
            IntStream.range(0, tasks.size())
                    .forEach(i -> sb.append(i + 1).append(": ").append(tasks.get(i)).append("\n"));
        }
        sb.append(line);
        return sb.toString();
    }

    /**
     * Returns a confirmation message that a task was added,
     * including the updated total number of tasks.
     *
     * @param task The task that was added.
     * @param size The updated number of tasks in the list.
     * @return The formatted confirmation message.
     */
    public String formatAddTaskMsg(Task task, int size) {
        return String.join("\n",
                line,
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + size + " tasks in the list.",
                line
        );
    }

    /**
     * Returns a confirmation message that a task was marked as completed.
     *
     * @param task The task that was marked done.
     * @return The formatted confirmation message.
     */
    public String formatTaskMarkedDoneMsg(Task task) {
        return String.join("\n",
                line,
                "Nice! I've marked this task as done:",
                "  " + task,
                line
        );
    }

    /**
     * Returns a confirmation message that a task was marked as not completed.
     *
     * @param task The task that was marked undone.
     * @return The formatted confirmation message.
     */
    public String formatTaskMarkedUndoneMsg(Task task) {
        return String.join("\n",
                line,
                "OK, I've marked this task as not done yet:",
                "  " + task,
                line
        );
    }

    /**
     * Returns a confirmation message that a task was removed from the list.
     *
     * @param rmv The string representation of the removed task.
     * @return The formatted confirmation message.
     */
    public String formatTaskRemovedMsg(String rmv) {
        return String.join("\n",
                line,
                "Noted. I've removed this task",
                rmv,
                line
        );
    }

    /**
     * Returns the tasks that match a search keyword, formatted as a String.
     *
     * @param matches The list of matching tasks.
     * @return The formatted search results message.
     */
    public String formatFindTasksMsg(List<Task> matches) {
        StringBuilder sb = new StringBuilder();
        sb.append(line).append("\n");
        if (matches.isEmpty()) {
            sb.append("No matching tasks found.").append("\n");
        } else {
            sb.append("Here are the matching tasks in your list:").append("\n");
            for (int i = 0; i < matches.size(); i++) {
                sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
            }
        }
        sb.append(line);
        return sb.toString();
    }

    // =========================================================================
    // Print methods (for CLI) — preserved API and behavior
    // =========================================================================

    /**
     * Prints the welcome message shown when the application starts.
     */
    public void welcomeMsg() {
        System.out.println(formatWelcomeMsg());
    }

    /**
     * Prints the goodbye message shown when the application exits.
     */
    public void goodbyeMsg() {
        System.out.println(formatGoodbyeMsg());
    }

    /**
     * Prints a formatted list of all tasks in the current task list.
     *
     * @param tasks The list of tasks to display.
     */
    public void listTasksMsg(List<Task> tasks) {
        System.out.println(formatListTasksMsg(tasks));
    }

    /**
     * Prints a confirmation message that a task was added,
     * including the updated total number of tasks.
     *
     * @param task The task that was added.
     * @param size The updated number of tasks in the list.
     */
    public void addTaskMsg(Task task, int size) {
        System.out.println(formatAddTaskMsg(task, size));
    }

    /**
     * Prints a confirmation message that a task was marked as completed.
     *
     * @param task The task that was marked done.
     */
    public void taskMarkedDoneMsg(Task task) {
        System.out.println(formatTaskMarkedDoneMsg(task));
    }

    /**
     * Prints a confirmation message that a task was marked as not completed.
     *
     * @param task The task that was marked undone.
     */
    public void taskMarkedUndoneMsg(Task task) {
        System.out.println(formatTaskMarkedUndoneMsg(task));
    }

    /**
     * Prints a confirmation message that a task was removed from the list.
     *
     * @param rmv The string representation of the removed task.
     */
    public void taskRemovedMsg(String rmv) {
        System.out.println(formatTaskRemovedMsg(rmv));
    }

    /**
     * Prints the tasks that match a search keyword.
     *
     * @param matches The list of matching tasks.
     */
    public void findTasksMsg(List<Task> matches) {
        System.out.println(formatFindTasksMsg(matches));
    }
}

