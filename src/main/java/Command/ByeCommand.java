package Command;

import JohnException.JohnException;
import Task.TaskList;
import UI.Ui;

/**
 * Command to terminate the application.
 */
public class ByeCommand extends Command {
    /**
     * Returns true to indicate that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the bye message.
     *
     * @param tasks Current task list (not used but must be non-null).
     * @param ui User interface used to show the goodbye message.
     * @throws JohnException If execution fails for any reason.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws JohnException {
        assert tasks != null : "Command: tasks must not be null";
        assert ui != null : "Command: ui must not be null";
        ui.showBye();
    }
}
