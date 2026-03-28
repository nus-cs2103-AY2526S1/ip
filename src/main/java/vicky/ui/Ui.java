package vicky.ui;

import vicky.tasklist.Task;
import vicky.tasklist.TaskList;

/**
 * Represents the main user interface window of the application,
 * providing a graphical interface for user interaction and displaying messages.
 *
 * @author Rachel Wong
 */
public class Ui {

    /** Indentation used for printing formatted messages */
    public static final String INDENT = "    ";

    /**
     * Returns a line.
     */
    public static String printLine() {
        return "\n" + INDENT + "__________________________________________________________________________\n";
    }

    /**
     * Returns a welcome message.
     */
    public String showGreeting() {
        return "Hello! I'm Vicky!\n" + "What can I do for you?";
    }

    /**
     * Returns a goodbye message.
     */
    public String showGoodbye() {
        return "Awwww :( Bye bye!!";
    }

    /**
     * Returns a message begging user not to leave.
     */
    public String showDesperateGoodbye() {
        return "NOO PLEASE DON'T LEAVE ME!!! \uD83D\uDE2D";
    }

    /**
     * Returns an error message.
     *
     * @param message is the error message to be shown to user.
     */
    public String showError(String message) {
        return "\uD83D\uDCA9 OH SHIT!!! " + message;
    }

    /**
     * Returns a loading error message to be shown to user.
     *
     * @param message is the error message.
     * @return loading error message.
     */
    public String showLoadingError(String message) {
        return "Failed to load tasks: " + message + "\n" + "Starting with empty list.";
    }

    /**
     * Returns a string message displaying the task added.
     *
     * @param t task that was added.
     * @param counter number of tasks in the task list.
     * @return message.
     */
    public String showAddedTask(Task t, int counter) {
        return "Ok! I've added this task:\n" + INDENT + t.toString() + "\n"
                + "Now you have " + counter + " tasks in your list. :)";
    }

    /**
     * Returns a message displaying the task list.
     *
     * @param tasks task list to be displayed.
     * @return message.
     */
    public String showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "You have no tasks in your list!";
        } else {
            return "Here's your todo list:\n" + tasks.toString();
        }
    }

    /**
     * Returns a message displaying the marked task.
     *
     * @param task task to be marked.
     * @return message.
     */
    public String showMarkedTask(Task task) {
        return "YAY! I've marked this task as done:\n" + INDENT + task;
    }

    /**
     * Returns a message displaying the unmarked task.
     *
     * @param task task to be unmarked.
     * @return message.
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + INDENT + task;
    }

    /**
     * Returns a message displaying the deleted task.
     *
     * @param task task to be deleted.
     * @param counter number of tasks currently in the task list.
     * @return message.
     */
    public String showDeleteTask(Task task, int counter) {
        return "Noted with many thanks! I've removed this task:\n"
                + INDENT + task.toString() + "\n"
                + "You now have " + counter + " tasks left!";
    }

    /**
     * Returns message telling user that the index is out of bounds.
     *
     * @return message.
     */
    public String showIndexOutOfBounds() {
        return "Nah cuh your list too short. Try again hoe!";
    }

    /**
     * Returns message showing user the matching tasks that have been found.
     *
     * @param matchingTasks The task list containing the tasks that match the keyword.
     * @return message.
     */
    public String showFindTasks(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "Awww :( There are no matching tasks...";
        } else {
            return "Found it! Here are the matching tasks in your list:\n"
                    + matchingTasks.toString();
        }
    }

    /**
     * Returns message telling user that all tasks have been deleted.
     *
     * @return message.
     */
    public String showClearTasks() {
        return "Okie dokie I've cleared your list! \uD83D\uDC31";
    }

    /**
     * Returns message showing a heart emoji.
     *
     * @param str The noun that vicky loves.
     * @return message.
     */
    public String showLove(String str) {
        return "Hehe I love " + str + " too pookie!! \u2764\uFE0F";
    }

    /**
     * Returns message showing the updated task.
     *
     * @param task Task that is updated.
     * @return message.
     */
    public String showChangeTask(Task task) {
        return "\uD83D\uDD95 Ok I've changed your task:\n" + INDENT + task;
    }

}
