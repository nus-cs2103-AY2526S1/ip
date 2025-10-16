package lebron.command;

import lebron.common.Constants;
import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * When executed, it displays all tasks with their corresponding numbers.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        validateTaskList(taskList);
        return getTaskListString(taskList);
    }

    private void validateTaskList(TaskList taskList) throws LeBronException {
        if (taskList.isEmpty()) {
            throw new LeBronException(Constants.NO_TASKS_ERROR);
        }
    }

    private String getTaskListString(TaskList taskList) {
        StringBuilder sb = new StringBuilder(Constants.TASK_LIST_HEADER);
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            Task temp = taskList.getTasks().get(i);
            sb.append(String.format("%d. %s\n", i + 1, temp));
        }
        return sb.toString();
    }
}
