package minhgpt.command;

import minhgpt.storage.Storage;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulate the delete task command.
 */
class CommandDelete extends Command {
    static {
        register("^delete \\d+$", CommandDelete::new);
    }

    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        super.execute(input, taskList, ui, storage);

        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        try {
            return ui.getDeleteResponse(taskList.delete(index));
        } catch (IndexOutOfBoundsException e) {
            return ui.getIndexErrorResponse();
        }
    }
}
