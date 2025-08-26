package beebong.command;

import java.time.LocalDateTime;

import beebong.task.Task;
import beebong.task.EventTask;

public class AddEventTaskCommand extends AddTaskCommand {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public AddEventTaskCommand(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    protected Task createTask() {
        return new EventTask(this.name, this.startDate, this.endDate);
    }
}
