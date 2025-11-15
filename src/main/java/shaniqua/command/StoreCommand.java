package shaniqua.command;

import shaniqua.storage.Storage;
import shaniqua.storage.StorageException;
import shaniqua.taskcore.TaskList;
import shaniqua.ui.Ui;

public class StoreCommand extends Command {
    /**
     * Executes the command to store tasks in local storage.
     *
     * @param tasks the task list to store (unused in current implementation)
     * @param ui the user interface for interaction (unused in current implementation)
     * @param storage the storage system for persistence (unused in current implementation)
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveToFile(tasks);
            ui.saveSuccess();
        } catch (StorageException e) {
            ui.error(e);
        }
    }
}
