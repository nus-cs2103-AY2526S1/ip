package minhgpt.command;

import minhgpt.storage.Storage;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulates the delete task command.
 */
class CommandInvalid extends Command {
    // NOTE: It is not necessary to have a static {} block here as this should
    // be the 'default' catch-all command when parsing user commands.

    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        return ui.getInvalidInputResponse();
    }
}
