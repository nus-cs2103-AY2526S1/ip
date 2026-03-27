package pepe.command;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

/**
 * Command to list all tasks in the current task list.
 * <p>
 * Extends the {@link Command} abstract class. When executed, it displays
 * all tasks stored in the {@link TaskList} via the {@link Ui}.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * <p>
     * Displays all tasks in the current task list.
     *
     * @param tasks   the current task list
     * @param ui      the UI object to display the list
     * @param storage the storage object (not used in this command)
     * @throws PepeExceptions never thrown by this command, included for consistency
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        super.setResponse(ui.showUiListTask(tasks));
    }

}

