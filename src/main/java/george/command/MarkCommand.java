package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents a command to mark a task as done in the task manager.
 * The task to be marked is identified by its task number.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a MarkCommand with the specified task number.
     *
     * @param taskNumber The number of the task to be marked as done
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        return manager.markTaskAsDone(taskNumber);
    }

    @Override
    public String getCommandWord() {
        return "mark";
    }
}
