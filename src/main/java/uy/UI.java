// UI text messages were enchanced by COPILOT
package uy;

/**
 * UI is a simple utility class that formats messages shown to the user.
 * It does not perform any IO itself; it only returns strings that callers
 * may choose to display in a console or GUI.
 */
public class UI {
    private String lines = "---------------------------------------";

    /**
     * Constructs a UI helper instance.
     */
    public UI() {

    }

    /**
     * Returns the welcome message shown when the app starts.
     *
     * @return welcome message
     */
    public String showWelcome() {
        return "Welcome, I am Uy—your Zen Productivity Coach. Take a deep breath, and let’s begin your mindful journey.";
    }

    /**
     * Returns the goodbye message shown when the app exits.
     *
     * @return goodbye message
     */
    public String showGoodbye() {
        return "Farewell for now. Remember: progress is a path, not a race. Be kind to yourself.";
    }

    /**
     * Returns a message used when loading tasks from storage fails.
     *
     * @return loading error message
     */
    public String showLoadingError() {
        return "Hmm, a ripple in the pond. I couldn’t load your tasks. Let’s try again, calmly.";
    }

    /**
     * Formats a list of tasks for display.
     *
     * @param tasks the task list to format
     * @return formatted tasks string
     */
    public String showTasks(TaskList tasks) {
        return "Here are your pebbles for today:\n"
                + tasks.toString() + "\n"
                + "(Remember: move one pebble at a time.)\n"
                + lines;
    }

    /**
     * Returns tasks matching a keyword in a user-friendly message.
     *
     * @param tasks matching tasks
     * @return formatted matching tasks message
     */
    public String showMatchingTasks(TaskList tasks) {
        return "These tasks align with your current path:\n"
                + tasks.toString();
    }

    /**
     * Returns a message that a task was marked done.
     *
     * @param task the task that was marked
     * @return confirmation message
     */
    public String showMarkedTask(Task task) {
        return "Another pebble moved. Well done.\n"
                + task.toString();
    }

    /**
     * Returns a message that a task was unmarked.
     *
     * @param task the task that was unmarked
     * @return unmark confirmation message
     */
    public String showUnmarkedTask(Task task) {
        return "This pebble is back on your path. Every journey has its turns.\n"
                + task.toString();
    }

    /**
     * Returns a message for a newly added task.
     *
     * @param task the newly added task
     * @param tasks current task list
     * @return add confirmation message
     */
    public String showAddTask(Task task, TaskList tasks) {
        return "A new pebble on your path:\n"
                + task.toString() + "\n"
                + "Now you have " + tasks.getTaskCount() + " pebbles to move. Walk gently.";
    }

    /**
     * Returns a message after deleting a task.
     *
     * @param task the deleted task
     * @param tasks current task list
     * @return delete confirmation message
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return "Letting go is part of the journey:\n"
                + task.toString() + "\n"
                + "Now you have " + tasks.getTaskCount() + " pebbles remaining.";
    }

    /**
     * Return a formatted error message for display.
     *
     * @param errorMessage raw error text
     * @return formatted error message
     */
    public String showError(String errorMessage) {
        return "Even the calmest pond has ripples: '" + errorMessage + "'. Lets try again, mindfully.";
    }

}
