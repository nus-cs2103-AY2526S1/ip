package cattis.command;

import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;

/**
 * Represents the command to mark <code>Task</code>
 * indexed at <code>taskIndex</code> as finished.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        cattis.getTaskList().mark(taskIndex);
        cattis.getUi().showMessage(String.format(Constants.MARK_TASK_MSG,
                cattis.getTaskList().get(taskIndex)));
    }
}
