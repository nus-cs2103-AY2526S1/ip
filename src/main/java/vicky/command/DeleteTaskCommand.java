package vicky.command;

import java.io.IOException;

import vicky.storage.Storage;
import vicky.tasklist.Task;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {

    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.deleteTask(this.index);
            storage.save(tasks);
            return ui.showDeleteTask(t, tasks.len());
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexOutOfBounds();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
