package minhgpt.command;

import java.text.ParseException;

import minhgpt.storage.Storage;
import minhgpt.task.Task;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulate the add task command.
 */
class CommandAdd extends Command {
    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(Task.parseTask(input));
            return ui.addResponse(taskList.get(taskList.size() - 1));
        } catch (ParseException e) {
            return e.getMessage();
        }
    }
}
