package jone;

/**
 * Handles all user interface interactions for the chatbot.
 * The {@code Ui} class is responsible for generating messages,
 * task updates, errors, and greetings for the user.
 */
public class Ui {
    /**
     * Number of spaces to indent messages for consistent formatting.
     */
    private static final String INDENT = "      ";

    /**
     * Returns the welcome message when the chatbot starts.
     */
    public String showWelcome() {
        return "Hello! I'm Jone!\nWhat can I do for you?";
    }

    /**
     * Returns the exit message when the chatbot is terminated.
     */
    public String showExit() {
        return INDENT + "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a general message with indentation.
     *
     * @param msg The message to be displayed.
     */
    public String showMessage(String msg) {
        assert msg != null : "Message cannot be null";
        return INDENT + msg;
    }

    /**
     * Returns a message when a task is added to the task list.
     *
     * @param t The task that was added.
     * @param size The new number of tasks in the list.
     */
    public String showTaskAdded(Task t, int size) {
        assert t != null : "Task cannot be null when added";
        assert size >= 0 : "Size should not be negative";
        return "I've added this task:\n   " + t
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message when a task is removed from the task list.
     *
     * @param t The task that was removed.
     * @param size The new number of tasks remaining in the list.
     */
    public String showTaskRemoved(Task t, int size) {
        return "I've removed this task:\n   " + t
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message when a task is marked as done or undone.
     *
     * @param t The task that was updated.
     * @param done {@code true} if the task is now done,
     *             {@code false} if marked as not done.
     */
    public String showTaskMarked(Task t, boolean done) {
        if (done) {
            return "I've marked this task as done:\n   " + t;
        } else {
            return "I've marked this task as not done yet:\n   " + t;
        }
    }

    /**
     * Returns an error message.
     *
     * @param msg The error message to be displayed.
     */
    public String showError(String msg) {
        return INDENT + msg;
    }

    public String showHelp() {
        return "Here are the available commands:\n"
                + "   1. todo <desc> - Add a todo task\n"
                + "   2. deadline <desc> /by <yyyy-MM-dd> - Add a deadline task\n"
                + "   3. event <desc> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm> - Add an event task\n"
                + "   4. list - Show all tasks\n"
                + "   5. mark <index> - Mark a task as done\n"
                + "   6. unmark <index> - Unmark a task\n"
                + "   7. delete <index> - Delete a task\n"
                + "   8. find <keyword> - Find tasks containing keyword\n"
                + "   9. help - Show this help message\n"
                + "  10. bye - Exit Jone";
    }
}

