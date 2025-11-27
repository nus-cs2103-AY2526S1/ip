package whchenyicn.command;

import whchenyicn.task.TaskList;
import whchenyicn.ui.Storage;
import whchenyicn.ui.Ui;

/**
 * A command to terminate the bot.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tlist, Ui ui, Storage storage) {
        assertUi (tlist, ui, storage);
        return ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
