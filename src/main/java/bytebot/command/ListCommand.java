package bytebot.command;

import bytebot.storage.Storage;
import bytebot.ui.Ui;

/**
 * Lists all tasks currently stored.
 */
public class ListCommand extends Command {
    /**
     * Displays tasks using the UI.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.showTasks(storage.getTaskList());
    }
}


