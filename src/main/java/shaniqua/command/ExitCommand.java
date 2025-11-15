package shaniqua.command;

import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.ui.Ui;

public class ExitCommand extends Command {
    /**
     * Executes the command by setting the exit flag to true.
     *
     * @param tasks the task list (unused in this command)
     * @param ui the user interface (unused in this command)
     * @param storage the storage system (unused in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        ui.farewell();
    }
}
