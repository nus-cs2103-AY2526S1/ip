package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Class that represents a command.
 * <p>
 * It should be inherited by other Command classes. The subclass should override
 * the abstract execute method.
 */
public abstract class Command {
    /**
     * Executes the command, when called by the Bob application.
     *
     * @param tasks   the TaskList to use.
     * @param ui      the user interface.
     * @param storage the storage.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the provided task numbers are valid for the given TaskList.
     *
     * @param tasks       the TaskList to check against.
     * @param taskNumbers the task numbers to validate (1-indexed).
     * @return true if all task numbers are valid, false otherwise.
     */
    public boolean isValidTasks(TaskList tasks, Integer[] taskNumbers) {
        for (int taskNumber : taskNumbers) {
            if (!tasks.isValidTaskIndex(taskNumber - 1)) {
                return false;
            }
        }
        return true;
    }
}
