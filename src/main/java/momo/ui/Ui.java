package momo.ui;

import momo.task.Task;
import momo.task.TaskList;

/**
 * Handles all user interactions in the Momo application.
 * Provides methods that return messages as strings.
 */
public class Ui {
    /** ASCII art logo of Momo displayed at startup. */
    private static final String LOGO = " __  __\n"
            + "|  \\/  |\n"
            + "| \\  / | ___  _ __ ___   ___\n"
            + "| |\\/| |/ _ \\| '_ ` _ \\ / _ \\\n"
            + "| |  | | (_) | | | | | | (_) |\n"
            + "|_|  |_|\\___/|_| |_| |_|\\___/\n";

    /** Welcome message displayed at startup. */
    private static final String MESSAGE_WELCOME = "Hello! I'm\n"
            + LOGO + "\n"
            + "What can I do for you today?\n\n"
            + "Type 'help' to see the list of available commands.\n";

    /** Farewell message displayed when exiting the application. */
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    /**
     * Adds a leading space to each line of a string.
     *
     * @param text the input text.
     * @return the text with added spaces.
     */
    private static String space(String text) {
        return " " + text.replace("\n", "\n ");
    }

    /**
     * Returns a help message listing all available commands and their usage.
     *
     * @return a formatted string containing command descriptions.
     */
    public String getHelpMessage() {
        return "Here are the available commands:\n"
                + "1.list                  - Show all tasks\n"
                + "2.todo <description>    - Add a todo task\n"
                + "3.deadline <description> /by <yyyy-MM-dd HHmm> - Add a deadline\n"
                + "4.event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm> - Add an event\n"
                + "5.mark <task number>    - Mark a task as done\n"
                + "6.unmark <task number>  - Mark a task as not done\n"
                + "7.delete <task number>  - Delete a task\n"
                + "8.find <keyword>        - Find tasks containing the keyword\n"
                + "9.help                  - Show this help message\n"
                + "10.bye                  - Exit the application";
    }

    /**
     * Returns the farewell message when exiting the application.
     *
     * @return the farewell message as a string.
     */
    public String getByeMessage() {
        return MESSAGE_BYE;
    }

    /**
     * Returns the welcome message when starting the application.
     *
     * @return the welcome message as a string.
     */
    public String getWelcomeMessage() {
        return MESSAGE_WELCOME;
    }

    /**
     * Processes and returns the user's input.
     *
     * @param input the raw user input string.
     * @return the trimmed user input string.
     */
    public String readCommand(String input) {
        return input.trim();
    }

    /**
     * Returns a message confirming that a task has been added.
     *
     * @param task the task that was added.
     * @param tasks the current task list.
     * @return the message confirming task addition as a string.
     */
    public String getAddTaskMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n" + space(space(task.toString()))
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a message confirming that a task has been deleted.
     *
     * @param task the task that was deleted.
     * @param tasks the current task list.
     * @return the message confirming task deletion as a string.
     */
    public String getDeleteTaskMessage(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + space(space(task.toString()))
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
