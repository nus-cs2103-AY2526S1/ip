package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

/**
 * Represents a command that can be executed in the HawkerUncle application.
 */
public interface Command {
    /**
     * Executes the command by performing the action defined by the command, such as adding, removing, listing tasks.
     * @param tasks The task list where tasks are stored
     * @param ui The user interface where messages are shown to the user.
     * @param storage The storage object where tasks are saved and loaded.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the command is an exit command.
     * @return true if the command is an exit command and false otherwise/
     */
    public abstract boolean isExit();
}
