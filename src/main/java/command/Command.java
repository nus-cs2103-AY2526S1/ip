package command;

import alfred.Alfred;
import task.TaskList;
import exceptions.AlfredException;
import ui.Ui;

/**
 * @author Anand Bala
 * This class outlines the various commands that are actually executed within the program.
 * Each subclass of the command class refers to a specific type of command, and implements
 * the execute method accordingly.
 *
 * Note: GitHub Copilot was used to assist in implementing the command pattern structure
 * and suggesting the execute method signature for consistent command execution.
 */
public abstract class Command {
    /**
     * Executes this command against the given task list and UI.
     *
     * @param tasks the task list to read from or mutate
     * @param ui    the UI used to present results or messages
     * @return String message describing the execution result
     */
    public abstract String execute(TaskList tasks, Ui ui) throws AlfredException;

    /**
     * Indicates whether executing this command should terminate the application loop.
     *
     * @return {@code true} if this command signals exit; {@code false} otherwise
     */
    public boolean isExit() {
        return false;
    }
}