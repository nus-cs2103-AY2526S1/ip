package friday.ui;

import friday.tasklist.FridayTaskList;
import friday.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the UI of friday
 */

public class FridayUi {

    public FridayUi() { }

    /**
     * Returns a String of welcome message
     * @return a string of message.
     */
    public String showWelcome() {
        return "Hello boss, Friday here!\nWhat can I do for you?";
    }

    /**
     * Return a String of goodbye message
     * @return a string of message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Return a String of the store task.
     * @param taskList the list of tasks stored.
     * @return a String of list of message.
     */
    public String showList(FridayTaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        sb.append(taskList.listAsString());
        return sb.toString();
    }

    /**
     * Return a String of the message that a task has been deleted.
     * @param task is the task to be deleted.
     * @param tasklist is the list that the task resides.
     * @return a String of message that the task has been deleted.
     */
    public String showTaskHasBeenDeleted(Task task, FridayTaskList tasklist) {
        return "Noted. I've removed this task:\n"
                + task.taskAsString() + "\n"
                + "Now you have " + tasklist.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * Return a String of message that a task has been added.
     * @param task is the task that the user wants to add.
     * @param tasklist is the list that the user wants to add the task to.
     * @return a String of message that the task has been added.
     */
    public String showTaskHasBeenAdded(Task task, FridayTaskList tasklist) {
        return "Got it. I've added this task:\n"
                + task.taskAsString() + "\n"
                + "Now you have " + tasklist.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * Returns a String of message that a task in the list has been marked.
     * @param task is the task that the user wants it to be marked.
     * @return a String of message indicating to user that a task has been marked.
     */
    public String showTaskHasBeenMarked(Task task) {
        return "Nice! I've marked this task as Done:\n"
                + task.getStatusIcon() + " " + task.getDescription();
    }

    /**
     * Returns a String of message that a task in the list has been unmarked.
     * @param task is the task to be unmarked
     * @return a String of message indicating to user that a task has been unmarked.
     */
    public String showTaskHasBeenUnmarked(Task task) {
        return "Nice! I've unmarked this task as Done:\n"
                + task.getStatusIcon() + " " + task.getDescription();
    }

    /**
     * Returns a String of the lst of task with matching results as the input.
     * @param matchingResults is the matching String the user wants to find.
     * @return a String of message showing the list of matching tasks.
     */
    public String showMatchingResults(ArrayList<String> matchingResults) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (String task : matchingResults) {
            sb.append(task).append("\n");
        }
        return sb.toString().trim();
    }
}