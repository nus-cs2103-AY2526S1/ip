package state;

import misc.PepeException;

/**
 * Class that formats messages nicely.
 */
public class Ui {
    public String handleException(PepeException e) {
        return formatMessage(e.getMessage());
    }

    /**
     * Prints a message from a command to the UI.
     * @param message a message that a command wants to display.
     */
    public String formatMessage(String message) {
        return String.format("\n%s\n", message);
    }

    /**
     * * Print the contents of the provided task list.
     * @param taskList The tasklist whose contents to print
     */
    public String displayTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You currently do not have any tasks yet!";
        }
        return this.displayTaskList("", taskList);
    }

    /**
     * Print the provided message, then display the contents of the provided task list
     * @param message The initial message to display if not empty.
     * @param taskList The tasklist whose contents to print
     */
    public String displayTaskList(String message, TaskList taskList) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(message).append("\n");
        for (int i = 0; i < taskList.size(); i++) {
            messageBuilder.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return this.formatMessage(messageBuilder.toString());
    }
}
