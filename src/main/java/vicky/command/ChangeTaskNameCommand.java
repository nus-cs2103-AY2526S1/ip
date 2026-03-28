package vicky.command;

import java.io.IOException;

import vicky.storage.Storage;
import vicky.tasklist.Task;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to change a task's name.
 */
public class ChangeTaskNameCommand extends Command {

    private int index;
    private String name;

    public ChangeTaskNameCommand(int index, String name) {
        this.index = index;
        this.name = name;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.getTask(this.index);
            assert this.name != null && !this.name.isEmpty() : "Name should not be null or empty.";
            t.changeName(this.name);
            storage.save(tasks);
            return ui.showChangeTask(t);
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexOutOfBounds();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
