package momo.command;

import momo.storage.Storage;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command by returning the farewell message.
     *
     * @param tasks the task list (not used in this command).
     * @param ui the user interface used to generate messages.
     * @param storage the storage handler (not used in this command).
     * @return the farewell message when exiting the application.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";

        return ui.getByeMessage();
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code true}, as this command exits the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
