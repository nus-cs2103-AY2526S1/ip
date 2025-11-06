package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.ui.Ui;

/**
 * Represents a generic command with common properties and abstract methods for commands.
 * This class cannot be instantiated directly and must be extended by concrete command implementations.
 * Subclasses are responsible for providing specific execution logic.
 *
 */
public abstract class Command {

    /**
     * Executes logic for subclasses of commands.
     * To be implemented by child classes.
     *
     * @param tasks The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     * @throws YappyException Exceptions thrown by child classes implementing this method.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws YappyException;

    /**
     * Check if the command is the exit command.
     *
     * @return Boolean value for if the command is the ExitCommand
     */
    public Boolean isExit() {
        return false;
    }

}
