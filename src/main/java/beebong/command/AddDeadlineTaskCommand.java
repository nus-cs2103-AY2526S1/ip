package beebong.command;

import java.time.LocalDateTime;

import beebong.task.Task;
import beebong.task.DeadlineTask;

public class AddDeadlineTaskCommand extends AddTaskCommand {
    private final LocalDateTime deadline;

    public AddDeadlineTaskCommand(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    protected Task createTask() {
        return new DeadlineTask(this.name, this.deadline);
    }
}
