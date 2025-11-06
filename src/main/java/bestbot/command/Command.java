package bestbot.command;

import bestbot.Storage;
import bestbot.Ui;
import bestbot.task.TaskList;
import bestbot.exception.BestbotException;

/**
 * Represents an executable command in Bestbot.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The task list to apply the command on.
     * @param ui      The UI handler for user interaction.
     * @param storage The storage handler for saving or loading tasks.
     * @throws BestbotException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException;

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return true if this command ends the program; false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
