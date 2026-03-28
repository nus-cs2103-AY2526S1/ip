package Meal;

/**
 * GUI implementation for Meal
 */
public class GuiUi extends Ui {
    private final StringBuilder buf = new StringBuilder();
    private static final String SEP = "____________________________________________________________";

    /**
     * Adds a separator line to the buffer
     */
    private void line() { buf.append(SEP).append('\n'); }

    /**
     * Displays an error message
     */
    @Override
    public void showError(String msg) {
        line();
        buf.append(msg).append('\n');
        line();
    }

    /**
     * Displays an error message saying loading failed
     */
    @Override
    public void showLoadingError() {
        showError("Load failed.");
    }

    /**
     * Displays a welcome message when application starts
     */
    @Override
    public void showWelcome() {
        buf.append("Hello! I'm Meal.Meal\nWhat can I do for you?\n\n");
    }

    /**
     * Displays a goodbye message when application exits
     */
    @Override
    public void showGoodbye() {
        line();
        buf.append("Bye. Hope to see you again soon!\n");
        line();
    }

    /**
     * Displays the full list of tasks in numbered order
     */
    @Override
    public void showList(TaskList tasks) {
        line();
        buf.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            buf.append(i + 1).append(". ").append(tasks.get(i)).append('\n');
        }
        line();
    }

    /**
     * Displays confirmation message when task is added
     * @param t the task that was added
     * @param size total number of tasks after adding
     */
    @Override
    public void showAdd(Task t, int size) {
        line();
        buf.append("Got it. I've added this task:\n").append(t).append('\n');
        buf.append("Now you have ").append(size).append(" tasks in the list.\n");
        line();
    }

    /**
     * Displays confirmation message when task is removed
     * @param removed the task that was removed
     * @param size total number of tasks after deleting
     */
    @Override
    public void showDelete(Task removed, int size) {
        line();
        buf.append("Got it. I've removed this task:\n").append(removed).append('\n');
        buf.append("Now you have ").append(size).append(" tasks in the list.\n");
        line();
    }

    /**
     * Displays a block of text, wrapped between separator lines
     * @param lines lines of text to display
     */
    @Override
    public void showTextBlock(String... lines) {
        line();
        for (String s : lines) {
            buf.append(s).append('\n');
        }
        line();
    }

    /**
     * Displays the results of a search, showing all matching tasks
     * @param matches list of tasks which matched the search
     */
    @Override
    public void showFindResults(java.util.List<Task> matches) {
        line();
        buf.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            buf.append(i + 1).append(". ").append(matches.get(i)).append('\n');
        }
        line();
    }

    /**
     * Clears the buffer
     * @return
     */
    public String flush() {
        String out = buf.toString().trim();
        buf.setLength(0);
        return out;
    }

}
