package minhgpt.command;

import minhgpt.storage.Storage;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulates the sort command, which sort the tasks by deadlines / start
 * dates and print it.
 */
class CommandSort extends Command {
    static {
        register("^sort$", CommandSort::new);
    }

    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        super.execute(input, taskList, ui, storage);
        return ui.getListResponse(taskList.sort());
    }
}
