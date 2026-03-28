package vicky.command;

import vicky.storage.Storage;
import vicky.tasklist.TaskList;
import vicky.ui.Ui;

/**
 * Represents a command.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Checks if command should exit program.
     * @return boolean
     */
    public boolean isExit() {
        return false;
    }
}



