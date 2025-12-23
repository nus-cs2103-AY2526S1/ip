package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents a command to unmark a task as not done in the task manager.
 * The task to be unmarked is identified by its task number.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNumber The number of the task to be marked as not done
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        return manager.markTaskAsNotDone(taskNumber);
    }

    @Override
    public String getCommandWord() {
        return "unmark";
    }
}
