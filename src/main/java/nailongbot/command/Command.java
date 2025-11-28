package nailongbot.command;

import nailongbot.exception.JkBotException;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;


/**
 * Represents an executable command in the Jkbot application.
 */
public interface Command {
    /**
     * Executes the command with the given dependencies.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface for displaying messages
     * @param storage the storage component for persistence
     * @throws JkBotException if command execution fails
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException;

    /**
     * Indicates whether this command should exit the application.
     *
     * @return true if this is an exit command, false otherwise
     */
    boolean isExit();
}
