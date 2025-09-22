package chash.command;

import chash.exception.ChashException;
import chash.storage.ChashDb;
import chash.task.TaskList;
import chash.ui.ChashUi;

/** Represents an executable command in CHASH. */
public abstract class Command {
    /**
     * Executes this command.
     *
     * @param tasks Current task list
     * @param ui User interface
     * @param db Database handler
     * @throws ChashException If execution fails
     */
    public abstract void execute(TaskList tasks, ChashUi ui, ChashDb db) throws ChashException;

    /**
     * Indicates if this command will terminate the program.
     *
     * @return {@code true} if exit command, otherwise {@code false}
     */
    public boolean isExit() {
        //Default implementation is false
        return false;
    }
}
