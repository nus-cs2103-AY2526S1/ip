package minhgpt.command;

import minhgpt.ui.Ui;
import minhgpt.storage.Storage;
import minhgpt.task.TaskList;

/**
 * Encapsulate the list task command.
 */
class CommandList extends Command {
    static {
        register("^list$", CommandList::new);
    }

    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
