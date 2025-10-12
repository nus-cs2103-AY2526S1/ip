package simon.command;

import simon.storage.Storage;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Represents a command to exit the Simon application.
 * An <code>ExitCommand</code> displays a goodbye message and signals program termination.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by setting the goodbye message.
     *
     * @param tasks The task list (unused for exit command).
     * @param ui The user interface for displaying the goodbye message.
     * @param storage The storage system (unused for exit command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setString(ui.showGoodbye());
    }

    /**
     * Indicates that this command causes the application to exit.
     *
     * @return True, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}