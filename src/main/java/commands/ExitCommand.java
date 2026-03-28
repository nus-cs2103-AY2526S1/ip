package commands;

import exceptions.DawaeException;
import storage.Storage;
import taskstuff.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    /** {@inheritDoc} */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DawaeException {
        return ui.showBye();
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean isExit() { return true; }
}
