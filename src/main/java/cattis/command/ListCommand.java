package cattis.command;

import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;

/**
 * Represents the command to list all <code>Task</code>
 * instances from the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        cattis.getUi().showMessage(Constants.LIST_TASK_MSG + "\n"
            + cattis.getTaskList());
    }
}
