package lynx.task;

import lynx.formatter.LynxDateManager;

import java.time.LocalDateTime;

/**
 * Represents a task with a <code>TaskType</code>, <code>Status</code>,
 * name, <code>LocalDateTime</code> start, <code>LocalDateTime</code> end, and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public class EventTask extends Task {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public EventTask(String name, LocalDateTime start, LocalDateTime end) {
        super(name, TaskType.EVENT);
        this.start = start;
        this.end = end;
        if (end.isBefore(LocalDateTime.now())) {
            setExpired();
        }
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Checks if the task is active on the given date.
     *
     * @return True if given date lies within event period.
     */
    public boolean isActive(LocalDateTime dateTime) {
        return !dateTime.isBefore(start) && !dateTime.isAfter(end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String testRepresentation() {
        return String.format("%s (from: %s to: %s)", super.testRepresentation(),
                LynxDateManager.textDateTime(start), LynxDateManager.textDateTime(end));
    }

}