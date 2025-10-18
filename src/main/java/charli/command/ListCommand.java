package charli.command;

import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Command to display all tasks in the task list.
 * Handles the 'rotation' command for task listing.
 */
public class ListCommand implements Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.showRotation();
    }

    public boolean isExit() {
        return false;
    }
}
