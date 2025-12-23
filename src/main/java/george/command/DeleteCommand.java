package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents a command to delete a task from the task manager.
 * The task to be deleted is identified by its task number.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber The number of the task to be deleted
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        return manager.deleteTask(taskNumber);
    }

    @Override
    public String getCommandWord() {
        return "delete";
    }
}
