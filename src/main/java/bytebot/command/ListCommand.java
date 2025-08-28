package bytebot.command;

import bytebot.ui.Ui;
import bytebot.storage.Storage;

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


