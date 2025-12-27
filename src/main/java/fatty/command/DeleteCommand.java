package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;
import fatty.task.Task;

/**
 * Deletes task from tasklist at index
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        Task removed = taskList.get(index);

        taskList.delete(index);
        storage.saveTasks(taskList);
        return ui.showDelete(removed, taskList);
    }
}
