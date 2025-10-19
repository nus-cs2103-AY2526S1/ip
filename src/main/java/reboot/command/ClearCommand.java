package reboot.command;

import reboot.RebootException;
import reboot.Storage;
import reboot.TaskList;
import reboot.gui.Gui;

/**
 * Represents a command that clears the current tasklist.
 */
public class ClearCommand extends Command {

    /**
     * Clears the current tasklist.
     * Updates the tasklist file via storage.
     * Informs the user via the gui.
     *
     * @param tasks Tasklist to clear.
     * @param gui Gui outputs message.
     * @param storage Storage loads tasks into the file.
     */
    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws RebootException {
        tasks.clear();
        storage.writeFile(tasks.getAll());
        return gui.showMessage("Cleared tasklist");
    }
}
