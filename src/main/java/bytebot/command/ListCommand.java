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
    public void execute(Ui ui, Storage storage) {
        ui.showTasks(storage.getAllTasks());
    }
}


