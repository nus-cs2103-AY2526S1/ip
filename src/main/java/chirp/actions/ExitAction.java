package chirp.actions;

import chirp.io.Ui;
import chirp.tasks.TaskList;

/**
 * Represents action of exiting chat bot
 */
public class ExitAction extends Action {

    /**
     * Terminates the chat bot
     *
     * @param taskList List of tasks
     * @param ui       UI interface
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.exit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
