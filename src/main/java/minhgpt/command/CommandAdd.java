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
    public void execute(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.add(Task.parseTask(input));
            ui.printAdd(taskList.get(taskList.size() - 1));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
