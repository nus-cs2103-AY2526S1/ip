package vicky.command;

import java.io.IOException;

import vicky.storage.Storage;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to clear all tasks from task list.
 */
public class ClearAllTasksCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.clearAllTasks();
        storage.save(tasks);
        return ui.showClearTasks();
    }
}
