package angus.command;

import java.time.LocalDate;

import angus.exception.AngusException;
import angus.task.TaskList;

/**
 * Represents the command to add a Deadline Task to the TaskList.
 * <p>
 * This class is responsible for passing the deadlineName and endDate to the
 * addDeadline method implemented by the TaskList class when the execute method
 * is called. This class is not an exit command.
 */
public class DeadlineCommand extends Commands {
    private final TaskList tasks;
    private final String deadlineName;
    private final LocalDate endDate;

    /**
     * Constructs a new instance of the DeadlineCommand class with the given TaskList, name and end date.
     * @param tasks The current list of tasks.
     * @param deadlineName The name of the new deadline task to be created.
     * @param endDate The end date of the new deadline task to be created.
     */
    public DeadlineCommand(TaskList tasks, String deadlineName, LocalDate endDate) {
        this.tasks = tasks;
        this.deadlineName = deadlineName;
        this.endDate = endDate;
    }

    @Override
    public String execute() throws AngusException {
        if (this.endDate.isBefore(LocalDate.now())) {
            throw new AngusException("Deadline cannot be before today!");
        }
        return tasks.addDeadline(deadlineName, endDate);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
