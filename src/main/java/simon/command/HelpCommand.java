package simon.command;

import simon.storage.Storage;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Represents a command to display help information.
 * A <code>HelpCommand</code> shows the user available commands and their usage.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command by setting the help message.
     *
     * @param tasks The task list (unused for help command).
     * @param ui The user interface for displaying the help message.
     * @param storage The storage system (unused for help command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setString(ui.showHelp());
    }
}