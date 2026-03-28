package peanutbutter.commands;

import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.TaskList;
import peanutbutter.ui.Ui;

/**
 * Abstract class representing a user command in the Juin chatbot.
 * Each command defines its own behavior when executed.
 */
public abstract class Command {
    /**
     * Executes the command using the provided task list and UI.
     *
     * @param taskList The TaskList containing the user's tasks.
     * @param ui The Ui instance for displaying messages to the user.
     * @return True if the command was executed successfully, false otherwise.
     * @throws JuinException If an error occurs during command execution.
     */
    public abstract boolean run(TaskList taskList, Ui ui) throws JuinException;

    /**
     * Determines if the program exits after a command
     *
     * @return True if the command should terminate, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
