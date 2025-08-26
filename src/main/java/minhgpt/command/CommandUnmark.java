package minhgpt.command;

import minhgpt.ui.Ui;
import minhgpt.storage.Storage;
import minhgpt.task.TaskList;

/**
 * Encapsulate the unmark task command.
 */
class CommandUnmark extends Command {
    static {
        register("^unmark \\d+$", CommandUnmark::new);
    }

    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(input.split("\\s+", 2)[1]) - 1;
        try {
            ui.printUnmark(taskList.unmark(index));
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexError();
        }
    }
}
