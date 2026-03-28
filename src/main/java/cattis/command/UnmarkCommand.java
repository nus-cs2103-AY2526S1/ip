package cattis.command;

import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;

/**
 * Represents the command to mark <code>Task</code>
 * indexed at <code>taskIndex</code> as unfinished.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        cattis.getTaskList().unmark(taskIndex);
        cattis.getUi().showMessage(String.format(Constants.UNMARK_TASK_MSG,
                cattis.getTaskList().get(taskIndex)));
    }
}
