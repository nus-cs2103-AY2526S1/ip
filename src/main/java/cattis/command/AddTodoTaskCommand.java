package cattis.command;

import cattis.CattisInterface;
import cattis.Constants;
import cattis.exception.CattisException;
import cattis.task.Task;
import cattis.task.TodoTask;

/**
 * Represents the command to add <code>TodoTask</code> to the
 * task list <code>Cattis.getTaskList()</code> instance.
 */
public class AddTodoTaskCommand extends AddTaskCommand {
    private final String taskName;

    public AddTodoTaskCommand(String taskName) {
        this.taskName = taskName.trim();
    }

    @Override
    public void execute(CattisInterface cattis) throws CattisException {
        Task newTask = TodoTask.createFromPrompt(taskName);
        cattis.getTaskList().add(newTask);
        cattis.getUi().showMessage(String.format(Constants.ADD_TASK_MSG,
                newTask));
        cattis.getTaskList().taskListSummary(cattis);
    }
}
