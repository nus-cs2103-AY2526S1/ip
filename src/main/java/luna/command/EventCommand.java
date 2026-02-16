package luna.command;

import java.time.LocalDate;

import luna.task.Event;
import luna.task.Task;

/**
 * Represents the {@code event} command.
 */
public class EventCommand extends AddTaskCommand {
    private final String name;
    private final LocalDate start;
    private final LocalDate end;

    public EventCommand(String name, LocalDate start, LocalDate end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Task getTaskToAdd() {
        return new Event(name, start, end);
    }
}
