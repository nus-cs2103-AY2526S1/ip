package johnny.commands;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.ui.Ui;

/**
 * This class is meant to represent a command that cannot be run.
 * Mainly used to return error messages to the GUI
 */
public class ErrorCommand extends Command {
    protected String msg;

    /**
     * Creates a new ErrorCommand instance with the particular string to pass to the
     * GUI.
     * 
     * @param str The string to return
     */
    public ErrorCommand(String str) {
        this.msg = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return this.msg;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
