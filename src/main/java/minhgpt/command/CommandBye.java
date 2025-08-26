package minhgpt.command;

import minhgpt.ui.Ui;
import minhgpt.storage.Storage;
import minhgpt.task.TaskList;

/**
 * Encapsulate the bye command.
 */
class CommandBye extends Command {
    static {
        register("^bye$", CommandBye::new);
    }

    @Override
    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        ui.printExit();
    }
}
