package cattis.command;

import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;
import cattis.task.DeadlineTask;
import cattis.task.Task;

/**
 * Represents the command to add <code>DeadlineTask</code> to the
 * task list <code>Cattis.getTaskList()</code> instance.
 */
public class AddDeadlineTaskCommand extends AddTaskCommand {
    private final String taskName;

    public AddDeadlineTaskCommand(String taskName) {
        this.taskName = taskName.trim();
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        Task newTask = DeadlineTask.createFromPrompt(taskName);
        cattis.getTaskList().add(newTask);
        cattis.getUi().showMessage(String.format(Constants.ADD_TASK_MSG, newTask));
        cattis.getTaskList().taskListSummary(cattis);
    }
}
