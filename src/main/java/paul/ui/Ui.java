package paul.ui;

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
        return "Hello I'm\n" + LOGO + "\nReady to get stuff done?";
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
        if (tasks.size() == 0) {
            return "There are no tasks in your list :(\n"
                    + "Add a task for me to keep track!";
        }
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
        return "Let's go! Task added to the system:\n" + task
                + "\nNow tracking " + total + " tasks in total.";
    }

    /**
     * Shows a message when a task is marked.
     *
     * @param task The task to mark.
     * @return The marked task message.
     */
    public String showTaskMarked(Task task) {
        return "Good job! I've marked this task as done:\n" + task;
    }

    /**
     * Shows a message when a task is unmarked.
     *
     * @param task The task to unmark.
     * @return The unmarked task message.
     */
    public String showTaskUnmarked(Task task) {
        return "Okay, I've reactivated this task for you:\n" + task;
    }

    /**
     * Shows a message when a task is deleted.
     *
     * @param task The task to delete.
     * @param total The total number of tasks after deleting.
     * @return The deleted task message.
     */
    public String showTaskDeleted(Task task, int total) {
        return "OK! Deleting task from the system:\n" + task
                + "\nNow tracking " + total + " tasks in total.";
    }

    /**
     * Shows a message with the all tasks found using the find method.
     *
     * @param tasks The TaskList containing the tasks found.
     * @return All tasks found from the keyword.
     */
    public String showTaskFound(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Hmm… no matching tasks found. Maybe try a different keyword?";
        }
        return "Yay! I've found these matches:\n" + tasks;
    }

    /**
     * Shows a message when there is an error loading tasks.
     * @return The error message when loading tasks.
     */
    public String showLoadingError() {
        return "Uh oh, something glitched while loading your tasks. Try again?";
    }
}
