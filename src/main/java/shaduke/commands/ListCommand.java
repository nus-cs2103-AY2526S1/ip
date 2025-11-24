package shaduke.commands;

import shaduke.Storage;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

/**
 * Represents the command to list out all current tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
