package geegar.command;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.TaskList;

/**
 * A Command that exits a chatbot
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {
        assert gui != null : "Gui must not be null";
        gui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
