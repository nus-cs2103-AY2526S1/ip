package ui;

import java.util.ArrayList;

import task.Task;
import task.TaskList;

/**
 * The Ui class is responsible for formatting and displaying messages to the user.
 * It provides methods to show welcome messages, task additions/removals,
 * task list display, errors, and search results in a consistent format.
 */
public class Ui {
    private static final String HORIZONTAL = "___________________________________________________";

    /**
     * Formats a message with a header, a task, and an optional footer.
     *
     * @param header the header string to display above the task
     * @param t the task to display
     * @param footer an optional footer string (can be null)
     * @return the formatted message string
     */
    private String formatMessage(String header, Task t, String footer) {
        StringBuilder sb = new StringBuilder();
        sb.append(HORIZONTAL).append("\n");
        sb.append(header).append("\n");
        sb.append(t).append("\n");

        if (footer != null) {
            sb.append(footer).append("\n");
        }

        sb.append(HORIZONTAL);
        return sb.toString();
    }

    /**
     * Displays a welcome message to the user.
     *
     * @return the welcome message string
     */
    public String showWelcome() {
        return HORIZONTAL + "\n"
                + "Hello! I'm Baymax, your personal healthcare companion...\n"
                + "and chatbot. How may I assist you today?\n"
                + HORIZONTAL;
    }

    /**
     * Displays a horizontal separator line.
     *
     * @return the horizontal line string
     */
    public String showLine() {
        return HORIZONTAL;
    }

    /**
     * Displays a message when a task is added.
     *
     * @param t the task added
     * @param taskCount the total number of tasks after addition
     * @return the formatted task-added message
     */
    public String showTaskAdded(Task t, int taskCount) {
        assert t != null : "Task should never be null";
        return formatMessage("Task successfully added. I will monitor it with care:", t,
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message when a task is removed.
     *
     * @param t the task removed
     * @param taskCount the total number of tasks after removal
     * @return the formatted task-removed message
     */
    public String showTaskRemoved(Task t, int taskCount) {
        assert t != null : "Task should never be null";
        return formatMessage("Task removed, your task is now lighter:", t,
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param t the task marked as done
     * @return the formatted message
     */
    public String showTaskMarked(Task t) {
        assert t != null : "Task should never be null";
        return formatMessage("Task successfully marked. I will monitor it with care:", t, null);
    }

    /**
     * Displays a message when a task is unmarked as not done.
     *
     * @param t the task marked as not done
     * @return the formatted message
     */
    public String showTaskUnmarked(Task t) {
        assert t != null : "Task should never be null";
        return formatMessage("Task successfully unmarked. I will monitor it with care:", t, null);
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks the TaskList object containing tasks
     * @return the formatted list of tasks
     */
    public String showList(TaskList tasks) {
        assert tasks != null : "TaskList should never be null";

        StringBuilder sb = new StringBuilder();
        sb.append(HORIZONTAL).append("\n");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        sb.append(HORIZONTAL);
        return sb.toString();
    }

    /**
     * Displays a goodbye message to the user.
     *
     * @return the goodbye message string
     */
    public String showBye() {
        return HORIZONTAL + "\n"
                + "Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays an error message.
     *
     * @param msg the error message to display
     * @return the formatted error message
     */
    public String showError(String msg) {
        return HORIZONTAL + "\n"
                + msg + "\n"
                + HORIZONTAL;
    }

    /**
     * Displays all tasks matching a search query.
     *
     * @param tasks the list of tasks that match the search
     * @return the formatted list of found tasks
     */
    public String showFoundTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should never be null";
        StringBuilder sb = new StringBuilder();
        sb.append(HORIZONTAL).append("\n");
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        sb.append(HORIZONTAL);
        return sb.toString();
    }
}
