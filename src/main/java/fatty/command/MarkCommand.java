package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;

/**
 * Marks task at index in tasklist as done.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        taskList.mark(index);
        storage.saveTasks(taskList);
        return ui.showMark(taskList.get(index));
    }
}
