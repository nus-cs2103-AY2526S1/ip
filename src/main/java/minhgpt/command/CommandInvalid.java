package minhgpt.command;

import minhgpt.storage.Storage;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulate the delete task command.
 */
class CommandInvalid extends Command {
    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        return ui.getInvalidInputResponse();
    }
}
