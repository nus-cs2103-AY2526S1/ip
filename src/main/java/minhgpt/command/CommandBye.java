package minhgpt.command;

import minhgpt.storage.Storage;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulate the bye command.
 */
class CommandBye extends Command {
    static {
        register("^bye$", CommandBye::new);
    }

    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        System.exit(0);
        return ui.exitMessage();
    }
}
