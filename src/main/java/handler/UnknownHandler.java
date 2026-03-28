package handler;

import hachi.CommandCode;
import hachi.TaskList;
import hachi.Ui;

/**
 * Handles unrecognized user commands.
 *
 * <p>This handler is invoked when the input does not match any known
 * {@link CommandCode}. It simply returns a failure message indicating
 * that the command is unknown.</p>
 */
public class UnknownHandler implements Manager {

    /**
     * Executes the unknown command handler.
     *
     * @param tasks the task list (not used in this handler)
     * @return a failure message for {@link CommandCode#UNKNOWN}
     */
    @Override
    public String execute(TaskList tasks) {
        return Ui.failure(CommandCode.UNKNOWN);
    }
}

