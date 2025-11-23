package conversal.command;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Lists all tasks in the current task list to the user.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by displaying all tasks.
     *
     * @param tasks   the task list to show
     * @param storage storage (not used here but required by signature)
     * @param ui      UI component to display the list
     * @throws ConversalException not thrown in this command
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        ui.showList(tasks.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
