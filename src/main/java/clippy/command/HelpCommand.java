package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Represents a command to display help information about available commands.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand.
     */
    public HelpCommand() {
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
