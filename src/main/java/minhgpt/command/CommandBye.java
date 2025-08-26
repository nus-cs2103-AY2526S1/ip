package minhgpt.command;

import minhgpt.ui.Ui;
import minhgpt.storage.Storage;
import minhgpt.task.TaskList;

class CommandBye extends Command {
    static {
        registry.put("^bye$", CommandBye::new);
    }

    @Override
    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        ui.printExit();
    }
}
