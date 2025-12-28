package thecoolerduke.feature.tasks;

import java.time.LocalDateTime;

import thecoolerduke.feature.Task;
import thecoolerduke.main.Priority;

/**
 * Subclass of taskFeature.Task with additional start and end date/time field.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Initialises an Event task.
     *
     * @param name Name of the event
     * @param isDone Boolean for if the task is done
     * @param priority Priority level of the task
     * @param start Starting datetime as a LocalDateTime object
     * @param end Ending datetime as a LocalDateTime object
     */
    public Event(String name, boolean isDone, Priority priority, LocalDateTime start, LocalDateTime end) {
        super(name, isDone, priority);
        this.start = start;
        this.end = end;
    }

    @Override
    public String showTask() {
        return String.format(
                "[E] %s (from: %s, to: %s)",
                super.showTask(),
                start.format(DATETIME_OUTPUT_FORMAT),
                end.format(DATETIME_OUTPUT_FORMAT)
        );
    }

    @Override
    public String saveTask() {
        return String.format(
                "E,%s,%s,%s",
                super.saveTask(),
                start.format(DATETIME_INPUT_FORMAT),
                end.format(DATETIME_INPUT_FORMAT)
        );
    }
}
