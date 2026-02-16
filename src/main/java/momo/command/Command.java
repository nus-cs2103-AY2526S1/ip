package momo.command;

import momo.exception.MomoException;
import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that can be executed in the application.
 */
public interface Command {
    /**
     * Executes this command using the given task list, user interface,
     * and storage handler.
     *
     * @param tasks the task list to operate on.
     * @param ui the user interface used to generate messages.
     * @param storage the storage handler that saves or loads tasks.
     * @return the result message of the command execution, to be shown to the user.
     * @throws MomoException if an error occurs during execution.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws MomoException;

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code true} if the command causes the program to exit,
     *         {@code false} otherwise.
     */
    boolean isExit();
}

