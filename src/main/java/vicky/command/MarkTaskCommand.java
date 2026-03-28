package vicky.command;

import java.io.IOException;

import vicky.storage.Storage;
import vicky.tasklist.Task;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkTaskCommand extends Command {

    private int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.markTask(this.index);
            storage.save(tasks);
            return ui.showMarkedTask(t);
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexOutOfBounds();
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
