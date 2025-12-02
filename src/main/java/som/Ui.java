package som;

import java.util.List;

import som.task.Task;

/**
 * Handles all user interactions with the Som chatbot.
 * <p>This class is responsible for displaying messages, reading user input, and formatting output
 * consistently. It encapsulates all console-based I/O, ensuring separation of concerns between
 * user interaction and business logic.</p>
 *
 * @author Darien Tan
 */
public class Ui {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    /**
     * Displays the welcome message when the app starts.
     * <p>Prints a greeting, a brief description of the app, and an instruction to type 'help'.</p>
     *
     * @return formatted welcome message as a string
     */
    public String showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm Som\n");
        sb.append("What can I do for you?\n");
        sb.append("Type 'help' for a list of commands.");
        return sb.toString();
    }

    /**
     * Displays an error message in a formatted way.
     *
     * @param exception the error message to display
     * @return formatted error message
     */
    public String showError(String exception) {
        return exception.trim();
    }

    /**
     * Displays the goodbye message when the user exits.
     * <p>Prints a farewell message.</p>
     *
     * @return goodbye message
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays all tasks matching a keyword search.
     *
     * @param matches the list of matching tasks
     * @param keyword the search keyword
     * @return formatted result message
     */
    public String showFindResults(List<Task> matches, String keyword) {
        if (matches.isEmpty()) {
            return "No tasks found matching '" + keyword + "'.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Confirms the deletion of a task.
     *
     * @param task the task that was deleted
     * @param total the number of tasks remaining in the list
     * @return confirmation message
     */
    public String showDelete(Task task, int total) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(task).append("\n");
        sb.append("Now you have ").append(total).append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Confirms the addition of a new task.
     *
     * @param task the task that was added
     * @param total the total number of tasks in the list after addition
     * @return confirmation message
     */
    public String showAdd(Task task, int total) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(task).append("\n");
        sb.append("Now you have ").append(total).append(" tasks in the list.");
        return sb.toString();
    }

    /**
     * Displays all tasks in the current list with their index and status.
     * <p>If the list is empty, a message is shown indicating no tasks exist.
     * Each task is printed with its ID (1-based) and string representation.</p>
     *
     * @param tasks the list of tasks to display
     * @return formatted list message
     */
    public String showList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Oops! Your task list is empty!";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Confirms that a task has been marked as done.
     *
     * @param task the task that was marked as done
     * @return confirmation message
     */
    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Confirms that a task has been marked as not done.
     *
     * @param task the task that was unmarked
     * @return confirmation message
     */
    public String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Displays an error message when loading tasks fails.
     *
     * @param error the error message to display
     * @return formatted loading error message
     */
    public String showLoadingError(String error) {
        return "Error loading: " + error + "\nStarting with empty task list.";
    }

    /**
     * Displays a confirmation message after sorting tasks.
     *
     * @return success message indicating tasks were sorted
     */
    public String showSortedMessage() {
        return "Tasks sorted by deadline/event time!";
    }

    /**
     * Displays a formatted list of all available commands to the user.
     * <p>This method prints a help menu that explains the syntax and purpose.</p>
     *
     * @return formatted help message
     */
    public String showHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");
        sb.append("todo <desc>                     – Add a todo\n");
        sb.append("deadline <desc> /by <time>      – Add a deadline\n");
        sb.append("event <desc> /from <start> /to <end> – Add an event\n");
        sb.append("list                            – Show all tasks\n");
        sb.append("mark <index>                    – Mark task as done\n");
        sb.append("unmark <index>                  – Mark task as not done\n");
        sb.append("delete <index>                  – Delete a task\n");
        sb.append("find <keyword>                  – Find tasks by keyword\n");
        sb.append("sort                            – Sort deadlines/events by date\n");
        sb.append("bye                             – Exit the app");

        return sb.toString().trim();
    }
}
