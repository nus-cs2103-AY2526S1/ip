package shaniqua.command;

import shaniqua.storage.Storage;
import shaniqua.taskcore.TaskList;
import shaniqua.ui.Ui;

public class ListCommand extends Command {
    /**
     * Executes the command by listing all tasks in the task list.
     *
     * @param tasks the task list to display
     * @param ui the user interface for interaction (unused in this command)
     * @param storage the storage system (unused in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list(ui);
    }
}
