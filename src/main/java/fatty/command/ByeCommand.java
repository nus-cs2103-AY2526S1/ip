package fatty.command;

import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;

/**
 * Exits the program
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}