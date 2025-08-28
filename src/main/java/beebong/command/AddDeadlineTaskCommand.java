package beebong.command;

import java.time.LocalDateTime;

import beebong.task.Task;
import beebong.task.DeadlineTask;

/**
 * Represents a Command for adding a new {@link DeadlineTask} to the task list.
 */
public class AddDeadlineTaskCommand extends AddTaskCommand {
    private final LocalDateTime deadline;

    /**
     * Constructs an {@code AddTaskCommand} with the given task name.
     *
     * @param name the name of the task to be created.
     * @param deadline the deadline of the task to be created.
     */
    public AddDeadlineTaskCommand(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Creates a new {@link DeadlineTask}.
     *
     * @return the newly created task.
     */
    @Override
    protected Task createTask() {
        return new DeadlineTask(this.name, this.deadline);
    }
}
