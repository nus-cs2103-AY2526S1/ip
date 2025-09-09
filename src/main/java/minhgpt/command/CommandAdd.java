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
    static {
        register("^(todo|deadline|event).*$", CommandAdd::new);
    }

    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(Task.parseTask(input));
            return ui.getAddResponse(taskList.get(taskList.size() - 1));
        } catch (ParseException e) {
            return e.getMessage();
        }
    }
}
