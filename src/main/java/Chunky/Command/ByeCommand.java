package Chunky.Command;

import Chunky.Storage.Storage;
import Chunky.Task.TaskList;
import Chunky.Ui.Ui;

public class ByeCommand implements Command {
    @Override
    public String execute(String input, TaskList tasks, Storage storage) throws Exception {
        storage.save(tasks.getTasks());
        return "Bye! Hope to see you again soon!\nTasks have been saved.";
    }
}

