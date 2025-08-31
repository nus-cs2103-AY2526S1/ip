package minhgpt.command;

import java.util.ArrayList;

import minhgpt.storage.Storage;
import minhgpt.task.Task;
import minhgpt.task.TaskList;
import minhgpt.ui.Ui;

/**
 * Encapsulate the delete task command.
 */
class CommandFind extends Command {
    static {
        register("^find .+$", CommandFind::new);
    }

    @Override
    public String execute(String input, TaskList taskList, Ui ui, Storage storage) {
        String query = input.split("\\s+", 2)[1];
        ArrayList<Task> match = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).matchRegex(query)) {
                match.add(taskList.get(i));
            }
        }
        return ui.findResponse(match);
    }
}
