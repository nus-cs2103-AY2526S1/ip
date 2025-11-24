package shaduke.commands;

import shaduke.Storage;
import shaduke.tasks.TaskList;
import shaduke.ui.Ui;

/**
 * Represents the ending of the current run of the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showAdios();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
