package vicky.command;

import vicky.storage.Storage;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command to list the task list.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
