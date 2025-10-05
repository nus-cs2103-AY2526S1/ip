package sage.task;

import java.util.ArrayList;

import sage.util.UiMessages;

/**
 * Deals with interactions with the user.
 * Handles displaying messages to the user and reading user input.
 */
public class Ui {

    /**
     * Displays the welcome message to the user.
     */
    public String getWelcomeMessage() {
        return UiMessages.WELCOME_MESSAGE;
    }

    /**
     * Displays the goodbye message to the user.
     */
    public String getGoodbyeMessage() {
        return UiMessages.GOODBYE_MESSAGE;
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public String getErrorMessage(String message) {
        return UiMessages.ERROR_PREFIX + message;
    }

    /**
     * Displays a generic error message for loading tasks.
     */
    public String getLoadingError() {
        return getErrorMessage(UiMessages.LOADING_ERROR);
    }

    /**
     * Displays a general message to the user, surrounded by lines.
     * @param message The message to display.
     */
    public String getMessage(String message) {
        return message;
    }
  
    public String getMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(UiMessages.MATCHING_TASKS_HEADER);
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    public String getUpcomingDeadlinesMessage(ArrayList<Deadline> deadlines) {
        if (deadlines.isEmpty()) {
            return UiMessages.NO_UPCOMING_DEADLINES;
        }

        StringBuilder sb = new StringBuilder(UiMessages.UPCOMING_DEADLINES_HEADER);
        for (int i = 0; i < deadlines.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(deadlines.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a help message with available commands and their usage.
     * @return A formatted string containing help information.
     */
    public String getHelpMessage() {
        return "Available commands:\n" +
               "- todo <description>\n" +
               "- deadline <description> /by <date>\n" +
               "- event <description> /from <date> /to <date>\n" +
               "- list\n" +
               "- mark <number>\n" +
               "- unmark <number>\n" +
               "- delete <number>\n" +
               "- find <keyword>\n" +
               "- help\n" +
               "- bye\n\n" +
               "Date format: d/M/yyyy HHmm";
    }

    /**
     * Shows a message and returns it.
     * @param message The message to display and return.
     * @return The message.
     */
    public String showMessageAndReturn(String message) {
        return message;
    }

    /**
     * Shows an error message and returns it.
     * @param message The error message to display and return.
     * @return The formatted error message.
     */
    public String showErrorAndReturn(String message) {
        return getErrorMessage(message);
    }

    /**
     * Shows the goodbye message and returns it.
     * @return The goodbye message.
     */
    public String showGoodbyeAndReturn() {
        return getGoodbyeMessage();
    }

    /**
     * Shows matching tasks and returns the formatted string.
     * @param matchingTasks The list of matching tasks to display.
     * @return The formatted string of matching tasks.
     */
    public String showMatchingTasksAndReturn(ArrayList<Task> matchingTasks) {
        return getMatchingTasks(matchingTasks);
    }
}

