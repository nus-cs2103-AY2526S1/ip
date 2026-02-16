package luna.command;

import java.time.Duration;

import luna.task.FixedDurationTask;
import luna.task.Task;

public class FixedDurationTaskCommand extends AddTaskCommand {
    private final String name;
    private final Duration duration;

    public FixedDurationTaskCommand(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
    }

    @Override
    protected Task getTaskToAdd() {
        return new FixedDurationTask(name, duration);
    }
}
