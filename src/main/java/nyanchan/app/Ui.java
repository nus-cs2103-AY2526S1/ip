package nyanchan.app;

import nyanchan.tasks.Task;

import java.util.Scanner;

/**
 * Handles all user interface messages and text output.
 */
public class Ui {

    /**
     * Returns the welcome message.
     *
     * @return welcome message
     */
    public String showWelcome() {
        return "MEOW! I'm NyanChan!\nWhat can I do for you?";
    }

    /**
     * Returns a formatted error message.
     *
     * @param message error text
     * @return formatted error message
     */
    public String showError(String message) {
        return "HISS! " + message;
    }

    /**
     * Returns the goodbye message.
     *
     * @return goodbye message
     */
    public String showGoodbye() {
        return "Purr... Hope to see you again!";
    }

    /**
     * Returns a formatted list of tasks.
     *
     * @param tasks list of tasks
     * @return formatted task list string
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Nothing stored yet!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * Returns the results of a find command.
     *
     * @param matchedTasks tasks matching the keyword
     * @param keyword search keyword
     * @return formatted list of matching tasks
     */
    public String showFindResults(TaskList matchedTasks, String keyword) {
        if (matchedTasks.isEmpty()) {
            return "No matching tasks found for: " + keyword;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(matchedTasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }


    /**
     * Returns a message for marking a task as done.
     *
     * @param t task to mark
     * @return formatted message
     */
    public String showMarkTask(Task t) {
        return "Meow, I've marked this task as done:\n  " + t;
    }

    /**
     * Returns a message for unmarking a task.
     *
     * @param t task to unmark
     * @return formatted message
     */
    public String showUnmarkTask(Task t) {
        return "Meow, I've marked this task as not done yet:\n  " + t;
    }

    /**
     * Returns a message after deleting a task.
     *
     * @param tasks current task list
     * @param t deleted task
     * @return formatted delete confirmation
     */
    public String showDeleteTask(TaskList tasks, Task t) {
        return "Meow, I've removed this task:\n  " + t + "\nNyow you have " + tasks.size() + " tasks.";
    }

    /**
     * Returns a message after adding a task.
     *
     * @param tasks current task list
     * @param t added task
     * @return formatted add confirmation
     */
    public String showAddTask(TaskList tasks, Task t) {
        return "Nyan! I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks.";
    }
}
