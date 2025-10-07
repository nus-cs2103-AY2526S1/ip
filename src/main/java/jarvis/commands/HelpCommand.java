package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to display help information about available commands.
 */
public class HelpCommand extends Command {
    private String message;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        message = ui.getHelpMessage();
    }

    @Override
    public String getString() {
        return message != null ? message : "Help information unavailable.";
    }
}