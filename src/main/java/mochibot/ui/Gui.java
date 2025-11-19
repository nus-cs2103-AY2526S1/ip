package mochibot.ui;

import mochibot.task.Task;
import mochibot.task.TaskList;

/**
 * This class contains a list of methods to print out display messages
 * corresponding to the command given by the user.
 */
public class Gui {

    /**
     * Returns the greeting message displayed when the application starts.
     *
     * @return the greeting message
     */
    public String displayGreeting() {
        return "Hello! I'm MochiBot. ( '3')9.\n" + "What can I do for you?";
    }

    /**
     * Returns the farewell message displayed when the application exits.
     *
     * @return the exit message
     */
    public String displayExit() {
        return "Bye! Hope to see you again soon (>;-;)>.";
    }

    /**
     * Returns a formatted list of all tasks currently in the {@code TaskList}.
     *
     * @param tasks the list of tasks to display
     * @return a formatted string of tasks with numbering
     */
    public String displayList(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n"));
        return stringBuilder.toString();
    }

    /**
     * Returns a message indicating that the task list is empty.
     *
     * @return the message for an empty task list
     */
    public String displayEmptyList() {
        return "Your list is empty! Feel free to add tasks 6('v')9.";
    }

    /**
     * Returns a message confirming that a new task has been added.
     *
     * @param task  the task that was added
     * @param tasks the current task list after the task was added
     * @return a confirmation message including the updated task count
     */
    public String displayAddTask(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + String.format("Now you have %d task(s) in the list.", tasks.getSize());
    }

    /**
     * Returns a message confirming that a task has been removed.
     *
     * @param task  the task that was removed
     * @param tasks the current task list after the task was removed
     * @return a confirmation message including the updated task count
     */
    public String displayRemoveTask(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + String.format("Now you have %d task(s) in the list.", tasks.getSize());
    }

    /**
     * Returns a message confirming that a task has been marked as completed.
     *
     * @param task the task that was marked as done
     * @return a confirmation message for marking the task
     */
    public String displayMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toString() + "\n";
    }

    /**
     * Returns a message confirming that a task has been unmarked as completed.
     *
     * @param task the task that was marked as not done
     * @return a confirmation message for unmarking the task
     */
    public String displayUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task.toString() + "\n";
    }

    /**
     * Returns a formatted list of tasks that match a search keyword.
     *
     * @param tasks the list of matching tasks
     * @return a formatted string of matching tasks with numbering
     */
    public String displayFoundTasks(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n"));
        return stringBuilder.toString();
    }

    /**
     * Returns a message indicating that no tasks matched the search keyword.
     *
     * @param keyword the search keyword used
     * @return a message stating no matches were found
     */
    public String displayNoFoundTasks(String keyword) {
        return String.format("No tasks were found given your keyword: %s", keyword);
    }

    /**
     * Returns the provided error message for display.
     *
     * @param errorMessage the error message to display
     * @return the same error message string
     */
    public String displayErrorMessage(String errorMessage) {
        return errorMessage;
    }
}
