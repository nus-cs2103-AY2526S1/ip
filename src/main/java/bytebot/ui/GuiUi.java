package bytebot.ui;

import java.util.List;

import bytebot.task.Task;

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
        return "Hello! I'm Byte.\nWhat can I do for you?";
    }

    /**
     * Shows the farewell banner.
     *
     * @return Farewell message as string
     */
    @Override
    public String showFarewell() {
        return "Bye, hope to see you again soon!";
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
     * @param tasks Tasks to display
     * @return Task list as formatted string
     */
    @Override
    public String showTasks(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n").append(i + 1).append(".")
                    .append(tasks.get(i).toString());
        }
        return output.toString();
    }

    /**
     * Displays tasks that match a search term.
     *
     * @param tasks Tasks matching the search
     * @return Matching tasks as formatted string
     */
    @Override
    public String showMatching(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n").append(i + 1).append(".")
                  .append(tasks.get(i).toString());
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
     * Shows news after a task is marked as done.
     *
     * @param task The task that was marked done
     * @return Success message as string
     */
    @Override
    public String showMarked(Task task) {
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
