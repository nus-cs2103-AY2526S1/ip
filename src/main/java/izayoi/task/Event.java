package izayoi.task;

import java.time.LocalDate;
import java.util.List;

import izayoi.exception.IzayoiException;
import izayoi.input.TaskDescriptor;

/**
 * Represents a task with a start and end time
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Initializes a new uncompleted Event task
     * @param input the InputManager reading the task description
     * @throws IzayoiException if the input is invalid
     */
    public Event(TaskDescriptor input) throws IzayoiException {
        super(input);
        this.start = LocalDate.parse(super.getArgument("from"));
        this.end = LocalDate.parse(super.getArgument("to"));
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                start.format(DATETIME_FORMAT), end.format(DATETIME_FORMAT));
    }

    @Override
    public List<String> commandify() {
        return List.of(String.format("event %s /from %s /to %s", getMessage(),
                start, end));
    }
}
