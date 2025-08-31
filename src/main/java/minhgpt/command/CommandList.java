package minhgpt.command;

import minhgpt.storage.Storage;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulate the list task command.
 */
class CommandList extends Command {
    static {
        register("^list$", CommandList::new);
    }

    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        return ui.listResponse(taskList);
    }
}
