package benn.messages;

import benn.tasks.TaskManager;
import benn.tasks.Task;

import java.util.List;

/**
 * Utility class that manages all user-facing messages in Benn the Chatbot.
 *
 * <p>This class provides static helper methods to construct consistent,
 * formatted strings for various chatbot interactions, such as greeting
 * the user, listing tasks, confirming task modifications, and reporting
 * errors. By centralizing message formatting here, the rest of the
 * application can focus on logic while ensuring a uniform user interface.</p>
 */
public class MessageManager {

    /**
     * Returns the introduction message, including the ASCII logo
     * and a greeting to the user.
     *
     * @return the chatbot's introduction message
     */
    public static String retrieveIntroductionMessage() {
        String logo =
                " ____                      _  _  _\n" +
                        "| __ )  ___ _ __  _ __   _| || || |\n" +
                        "|  _ \\ / _ \\ '_ \\| '_ \\ / _` || || |\n" +
                        "| |_) |  __/ | | | | | | (_| || || |\n" +
                        "|____/ \\___|_| |_|_| |_|\\__,_||_||_|\n";

        return logo
                + "____________________________________________________________\n"
                + "Hello! I'm BENN\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a confirmation message after adding a new task.
     *
     * @param task the task that was added
     * @param taskManager the task manager holding the updated list
     * @return a formatted string confirming the addition
     */
    public static String retrieveTaskMessageFrom(Task task, TaskManager taskManager) {
        return "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + "   " + task + "\n"
                + "Now you have " + taskManager.size() + " tasks in the list.\n"
                + "____________________________________________________________";
    }

    /**
     * Returns the message that lists all tasks currently managed
     * by the {@link TaskManager}.
     *
     * @param taskManager the task manager containing the tasks
     * @return a formatted string containing the task list
     */
    public static String retrieveListMessageFrom(TaskManager taskManager) {
        return "____________________________________________________________\n"
                + taskManager
                + "____________________________________________________________";
    }

    /**
     * Returns the farewell message displayed when the chatbot exits.
     *
     * @return the exit message
     */
    public static String retrieveByeMessage() {
        return "____________________________________________________________\n"
                + "bye, hope to see you soon!\n"
                + "____________________________________________________________";
    }

    /**
     * Returns the message displayed when the user enters an
     * invalid command.
     *
     * @return the invalid command error message
     */
    public static String retrieveInvalidCommandMessage() {
        return "____________________________________________________________\n"
                + "invalid command, please try again!\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a confirmation message after marking a task as done.
     *
     * @param task the task that was marked as done
     * @return a formatted string confirming the operation
     */
    public static String retrieveMarkTaskAsDoneMessageFrom(Task task) {
        return "____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + task + "\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a confirmation message after unmarking a task as done.
     *
     * @param task the task that was unmarked
     * @return a formatted string confirming the operation
     */
    public static String retrieveUnmarkTaskAsDoneMessageFrom(Task task) {
        return "____________________________________________________________\n"
                + "Nice! I've unmarked this task as done:\n"
                + task + "\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a confirmation message after deleting a task.
     *
     * @param task the task that was deleted
     * @param taskManager the task manager holding the updated list
     * @return a formatted string confirming the deletion
     */
    public static String retrieveDeletedTaskMessageFrom(Task task, TaskManager taskManager) {
        return "____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + taskManager.size() + " tasks in the list.\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a formatted message displaying all tasks that match a search keyword.
     *
     * <p>If the provided list of tasks is empty, a placeholder message indicating
     * no matches is included. Otherwise, each task is displayed with its
     * 1-based index and formatted string representation.</p>
     *
     * <p>The message is enclosed within divider lines for consistency with
     * other chatbot outputs.</p>
     *
     * @param matches the list of tasks whose descriptions matched the keyword
     * @return a formatted string containing the search results or a no-matches notice
     */
    public static String retrieveFindMessageFrom(List<Task> matches) {
        String result = "____________________________________________________________\n";
        result += "Here are the matching tasks in your list:\n";

        if (matches.isEmpty()) {
            result += "(no matching tasks)\n";
        } else {
            for (int i = 0; i < matches.size(); i++) {
                result += (i + 1) + "." + matches.get(i) + "\n";
            }
        }

        result += "____________________________________________________________";
        return result;
    }

    /**
     * Returns a message that displays an error reported
     * by an {@link Exception}.
     *
     * @param exception the exception that caused the error
     * @return a formatted error message string
     */
    public static String retrieveErrorMessageFrom(Exception exception) {
        return "____________________________________________________________\n"
                + exception.getMessage() + "\n"
                + "____________________________________________________________";
    }

    /**
     * Returns a message that displays usage help for the user.
     *
     * @return a formatted help message string
     */
    public static String retrieveHelpMessage() {
        return "____________________________________________________________\n"
                + "Here are the available commands:\n"
                + "\n"
                + "  list\n"
                + "      Show all tasks in the list.\n"
                + "\n"
                + "  todo DESCRIPTION\n"
                + "      Add a todo task.\n"
                + "\n"
                + "  deadline DESCRIPTION /by DD/MM/YYYY HH:MM\n"
                + "      Add a deadline task.\n"
                + "      Example: deadline submit report /by 25/12/2025 23:59\n"
                + "\n"
                + "  event DESCRIPTION /from DD/MM/YYYY HH:MM /to DD/MM/YYYY HH:MM\n"
                + "      Add an event task.\n"
                + "      Example: event project meeting /from 20/09/2025 14:00 /to 20/09/2025 16:00\n"
                + "\n"
                + "  mark INDEX\n"
                + "      Mark the task at INDEX (1-indexed) as done.\n"
                + "\n"
                + "  unmark INDEX\n"
                + "      Mark the task at INDEX (1-indexed) as not done.\n"
                + "\n"
                + "  delete INDEX\n"
                + "      Delete the task at INDEX (1-indexed).\n"
                + "\n"
                + "  find KEYWORD\n"
                + "      Find all tasks containing KEYWORD.\n"
                + "\n"
                + "  bye\n"
                + "      Exit the program.\n"
                + "\n"
                + "  help\n"
                + "      Show this help message.\n"
                + "____________________________________________________________";
    }




}
