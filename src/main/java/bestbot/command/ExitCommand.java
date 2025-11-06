package bestbot.command;

import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.TaskList;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by showing the goodbye message to the user.
     *
     * @param tasks   The task list (unused in this command).
     * @param ui      The UI used to display messages to the user.
     * @param storage The storage (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns {@code true} as this command terminates the program.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
