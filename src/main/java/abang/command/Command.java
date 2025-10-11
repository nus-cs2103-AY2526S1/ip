package abang.command;

import abang.task.TaskList;
import abang.ui.UI;
import abang.storage.Storage;
import abang.exception.AbangException;

/**
 * Abstract base class for all commands.
 * Each command must implement the execute method.
 */
public abstract class Command {
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param taskList the current task list
     * @param ui       the UI object for interaction
     * @param storage  the storage object for saving tasks
     * @throws AbangException if the command cannot be executed
     */
    public abstract String execute(TaskList taskList, UI ui, Storage storage) throws AbangException;
}
