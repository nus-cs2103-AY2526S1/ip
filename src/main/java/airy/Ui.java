package airy;

import java.util.ArrayList;

/**
 * This class handles all UI methods the bot has
 */
public class Ui {
    private String name = "Airy";

    /**
     * Sends welcome message
     */
    public String getWelcomeMessage() {
        return "Hello! I'm " + name + "\nWhat can I do for you?\n";
    }

    /**
     * Sends bye message
     */
    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the complete list of tasks to the user.
     *
     * @param tasks the ArrayList of tasks to display
     */
    public String showTaskList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        // Print out saved tasks
        for (int i = 0; i < tasks.size(); i++) {
            Task taskObj = tasks.get(i);
            sb.append(String.format("%d. %s\n",
                    i + 1,
                    taskObj.toString()));
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Confirms that a task has been successfully added to the list.
     * Shows the added task and updates the user on the total task count.
     *
     * @param task the task that was added
     * @param total the new total number of tasks in the list
     */
    public String showTaskAdded(Task task, int total) {
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n\n",
                task,
                total
        );
    }

    /**
     * Confirms that a task has been successfully removed from the list.
     * Shows the removed task and updates the user on the total task count.
     *
     * @param task the task that was removed
     * @param total the new total number of tasks in the list
     */
    public String showTaskRemoved(Task task, int total) {
        return String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n\n",
                task,
                total
        );
    }

    /**
     * Confirms that a task has been marked as completed.
     *
     * @param task the task that is to be marked as done
     */
    public String showMark(Task task) {
        return String.format(
                "Nice! I've marked this task as done:\n%s\n\n",
                task
        );
    }

    /**
     * Confirms that a task has been marked as not completed.
     *
     * @param task the task that is to be marked as not done
     */
    public String showUnmark(Task task) {
        return String.format(
                "OK, I've marked this task as not done yet:\n%s\n\n",
                task
        );
    }

    /**
     * Displays the list of tasks that match the search criteria.
     * Displays a message if no matching tasks are found.
     *
     * @param matchingTasks the list of tasks that match the search
     */
    public String showMatchingTasks(TaskList matchingTasks) {
        StringBuilder sb = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            sb.append("No matching tasks found.\n\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.getSize(); i++) {
                Task taskObj = matchingTasks.get(i);
                sb.append(String.format("%d. %s\n", i + 1, taskObj));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
