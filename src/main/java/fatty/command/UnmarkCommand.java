package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;

/**
 * Unmarks task at index in tasklist.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        taskList.unmark(index);
        storage.saveTasks(taskList);
        return ui.showUnmark(taskList.get(index));
    }
}

