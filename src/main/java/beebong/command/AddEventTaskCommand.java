package beebong.command;

import java.time.LocalDateTime;

import beebong.task.Task;
import beebong.task.EventTask;
/**
 * Represents a Command for adding a new {@link EventTask} to the task list.
 */
public class AddEventTaskCommand extends AddTaskCommand {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Constructs an {@code AddToDoTaskCommand} with the given task name.
     *
     * @param name the name of the task to be created.
     * @param startDate the start date of the task to be created.
     * @param endDate the end date of the task to be created.
     */
    public AddEventTaskCommand(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates a new {@link EventTask}.
     *
     * @return the newly created task.
     */
    @Override
    protected Task createTask() {
        return new EventTask(this.name, this.startDate, this.endDate);
    }
}
