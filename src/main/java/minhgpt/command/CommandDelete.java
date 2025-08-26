package minhgpt.command;

import minhgpt.ui.Ui;
import minhgpt.storage.Storage;
import minhgpt.task.TaskList;

/**
 * Encapsulate the delete task command.
 */
class CommandDelete extends Command {
    static {
        registry.put("^delete \\d+$", CommandDelete::new);
    }

    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        try {
            ui.printDelete(taskList.delete(index));
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexError();
        }
    }
}
