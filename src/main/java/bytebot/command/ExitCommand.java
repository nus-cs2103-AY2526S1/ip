package bytebot.command;

import bytebot.storage.Storage;
import bytebot.ui.Ui;

/**
 * Exits the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a farewell message.
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.showFarewell();
    }

    /**
     * Indicates that the application should terminate after this command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}


