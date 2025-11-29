package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Lists all current tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list operation.
     *
     * @param tasks   The task list.
     * @param ui      UI for output.
     * @param storage Persistent storage (unchanged).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(tasks.renderList());
    }
}
