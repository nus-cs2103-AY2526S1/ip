package gene.command;

import gene.TaskList;
import gene.tasks.Task;

import java.time.LocalDateTime;

public class RemindCommand extends Command {
    private final LocalDateTime cutOffDateTime;

    public RemindCommand(String s) {
        super(false);
        this.cutOffDateTime = Task.dateTimeParser(s);
    }

    @Override
    public String execute(TaskList tasksList) {
        assert tasksList != null;
        return tasksList.getReminderTasks(cutOffDateTime);
    }
}
