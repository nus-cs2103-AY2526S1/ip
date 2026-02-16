package luna.command;

import java.time.LocalDate;

import luna.task.Deadline;
import luna.task.Task;

/**
 * Represents the {@code deadline} command.
 */
public class DeadlineCommand extends AddTaskCommand {
    private final String name;
    private final LocalDate deadline;

    public DeadlineCommand(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    protected Task getTaskToAdd() {
        return new Deadline(name, deadline);
    }
}