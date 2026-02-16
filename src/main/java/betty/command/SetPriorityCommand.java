package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.Priority;
import betty.task.Task;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents the command object that sets the priority of a given task in the task list
 */
public class SetPriorityCommand extends Command {
    private int taskNum;
    private Priority priority;

    /**
     * Constructs the command object with the given task number and priority level
     * @param taskNum task number in task list to set priority
     * @param priority priority of task to be set
     */
    public SetPriorityCommand(int taskNum, Priority priority) {
        super();
        this.taskNum = taskNum;
        this.priority = priority;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        Task task = taskList.get(this.taskNum - 1);
        task.setPriority(this.priority);
        return ui.setPriority(task, this.priority);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
