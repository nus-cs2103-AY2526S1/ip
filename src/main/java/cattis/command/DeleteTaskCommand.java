package cattis.command;

import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;
import cattis.task.Task;

/**
 * Represents the command to remove <code>Task</code>
 * from <code>Cattis.TaskList</code>
 */
public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        Task deletedTask = cattis.getTaskList().delete(taskIndex);
        cattis.getUi().showMessage(String.format(Constants.REMOVE_TASK_MSG, deletedTask));
        cattis.getTaskList().taskListSummary(cattis);
    }
}
