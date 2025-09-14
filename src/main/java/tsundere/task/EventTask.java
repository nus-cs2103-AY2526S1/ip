package tsundere.task;

import static tsundere.Config.TASK_EVENT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


/**
 * A task that contains two dates, specifying the start date and end date of the task.
 *
 * @see tsundere.task.Task
 */
public class EventTask extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a new task.
     * @param name Name of the task
     * @param from Start date of the task
     * @param to End date of the task
     */
    public EventTask(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageString() {
        return TASK_EVENT + "," + (super.isDone() ? "T" : "F") + "," + super.getName()
                + "," + from + "," + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From "
                + from.format(DateTimeFormatter.ofPattern("dd-MMM-yy' 'HH:mm")) + " To "
                + to.format(DateTimeFormatter.ofPattern("dd-MMM-yy' 'HH:mm")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventTask eventTask) {
            return this.from.equals(eventTask.from)
                    && this.to.equals(eventTask.to)
                    && super.equals(eventTask);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.from, this.to);
    }
}
