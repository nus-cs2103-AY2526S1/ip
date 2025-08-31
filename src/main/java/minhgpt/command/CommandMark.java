package minhgpt.command;

import minhgpt.storage.Storage;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulate the mark task command.
 */
class CommandMark extends Command {
    static {
        register("^mark \\d+$", CommandMark::new);
    }

    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        try {
            return ui.markResponse(taskList.mark(index));
        } catch (IndexOutOfBoundsException e) {
            return ui.indexErrorResponse();
        }
    }
}
