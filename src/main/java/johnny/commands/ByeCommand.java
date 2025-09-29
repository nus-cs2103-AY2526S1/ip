package johnny.commands;

import johnny.storage.Storage;
import johnny.tasklist.TaskList;
import johnny.ui.Ui;

/**
 * A command that saves all tasks in the current TaskList inside the save file
 * and exits the program.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        return ui.printByeMessage();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
