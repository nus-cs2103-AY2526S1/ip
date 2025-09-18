package commands;

import exception.RainyException;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that displays the list of tasks to the user.
 * The {@code ListCommand} is responsible for showing all tasks currently
 * stored in the {@link TaskList} with proper formatting.
 */
public class ListCommand extends Command {
    @Override
    public void execute(Object... args) throws RainyException {
        Ui ui = (Ui) args[1];
        TaskList tasks = (TaskList) args[0];
        message = ui.showList(tasks) + "\n";
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
