package paul.ui;

import java.util.Scanner;

import paul.task.Task;
import paul.task.TaskList;

/**
 * Handles user interaction for Paul.
 */
public class Ui {
    private static final String LOGO = """
                 ____   _   _   _ _
                |  _ \\ / \\ | | | | |
                | |_) / _ \\| | | | |
                |  __/ ___ \\ |_| | |___
                |_| /_/   \\_\\___/|_____|
                """;

    /**
     * Greets user with a welcome message and logo.
     *
     * @return The welcome message.
     */
    public String greetUser() {
        return "Hello I'm\n" + LOGO + "\nWhat can I do for you?";
    }

    /**
     * Says goodbye to the user before exiting the program.
     *
     * @return The goodbye message.
     */
    public String byeUser() {
        return "Goodbye! Paul will miss you :(";
    }

    /**
     * Returns all the tasks currently in the TaskList.
     *
     * @param tasks The TaskList to display.
     * @return The tasks in the TaskList.
     */
    public String showTasks(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks;
    }

    /**
     * Shows a message when a task is added.
     *
     * @param task The task to add.
     * @param total The total number of tasks after adding.
     * @return The added task message.
     */
    public String showTaskAdded(Task task, int total) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + total + " tasks in the list.";
    }

    /**
     * Shows a message when a task is marked.
     *
     * @param task The task to mark.
     * @return The marked task message.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Shows a message when a task is unmarked.
     *
     * @param task The task to unmark.
     * @return The unmarked task message.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Shows a message when a task is deleted.
     *
     * @param task The task to delete.
     * @param total The total number of tasks after deleting.
     * @return The deleted task message.
     */
    public String showTaskDeleted(Task task, int total) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + total + " tasks in the list.";
    }

    /**
     * Shows a message with the all tasks found using the find method.
     *
     * @param list The TaskList containing the tasks found.
     * @return All tasks found from the keyword.
     */
    public String showTaskFound(TaskList list) {
        if (list.size() == 0) {
            return "There are no matches found!";
        } else {
            return "Here are the matching tasks in your list:\n" + list;
        }
    }

    /**
     * Shows a message when there is an error loading tasks.
     * @return The error message when loading tasks.
     */
    public String showLoadingError() {
        return "Error in Loading Tasks!";
    }
}
