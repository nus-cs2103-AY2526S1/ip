package commands;

import exception.RainyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that displays the list of tasks to the user.
 * The {@code ListCommand} is responsible for showing all tasks currently
 * stored in the {@link TaskList} with proper formatting.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RainyException {
        message = ui.showLine()
            + ui.showList(tasks) + "\n"
            + ui.showLine();
    }
}
