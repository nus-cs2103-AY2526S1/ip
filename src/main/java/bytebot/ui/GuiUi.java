package bytebot.ui;

import bytebot.task.Task;
import bytebot.task.TaskList;

/**
 * GUI-specific UI implementation that returns strings instead of printing to console.
 * Extends the base Ui class to maintain compatibility with existing command structure.
 */
public class GuiUi extends Ui {

    /**
     * Shows the greeting banner.
     *
     * @return Greeting message as string
     */
    @Override
    public String showGreeting() {
        return "Hey there! I'm here to byte down your tasks.\nWhat can I do for you today?\n";
    }

    /**
     * Shows the farewell banner.
     *
     * @return Farewell message as string
     */
    @Override
    public String showFarewell() {
        return "Bytebye, Looking forward to helping you again soon.\n";
    }

    /**
     * Shows an error message.
     *
     * @param message Error to display
     * @return Error message as string
     */
    @Override
    public String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Displays a numbered list of tasks.
     *
     * @param taskList TaskList to display
     * @return Task list as formatted string
     */
    @Override
    public String showTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the byte tasks what's on your list:");
        for (int i = 0; i < taskList.size(); i++) {
            output.append("\n").append(i + 1).append(".")
                    .append(taskList.get(i).toString());
        }
        return output.toString();
    }

    /**
     * Displays tasks that match a search term.
     *
     * @param taskList Tasks matching the search
     * @return Matching tasks as formatted string
     */
    public String showMatchingTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Here are your byte sized tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            output.append("\n").append(i + 1).append(".")
                  .append(taskList.get(i).toString());
        }
        return output.toString();
    }

    /**
     * Shows news after a task is added.
     *
     * @param task The added task
     * @param total Total number of tasks after addition
     * @return Success message as string
     */
    @Override
    public String showAddedTask(Task task, int total) {
        return "Got it, I've added this task:\n  " + task
                + "\nNow you have " + total + " tasks in the list.";
    }

    /**
     * Shows a notification after a task is marked as done.
     *
     * @param task The task that was marked done
     * @return Success message as string
     */
    public String showTaskMarkedNotification(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Shows news after a task is unmarked.
     *
     * @param task The task that was unmarked
     * @return Success message as string
     */
    @Override
    public String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Shows news after a task is deleted.
     *
     * @param removed The removed task
     * @param total Total number of tasks after deletion
     * @return Success message as string
     */
    @Override
    public String showDeleted(Task removed, int total) {
        return "I have removed this task:\n  " + removed
                + "\nNow you have " + total + " tasks in the list.";
    }
}
