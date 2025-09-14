package objectclasses.task;

import java.time.LocalDateTime;

import objectclasses.exception.InvalidTaskException;
import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;
import objectclasses.formatter.LynxDateManager;

/**
 * Represents a task with a <code>TaskType</code>, <code>Status</code>,
 * name, priority, <code>LocalDateTime</code> start, <code>LocalDateTime</code> end, and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public class EventTask extends Task {

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for creating an <code>EventTask</code>
     *
     * @param name Name of the task.
     * @param priority Priority of the task.
     * @param start Start date of the task.
     * @param end End date of the task.
     */
    public EventTask(String name, int priority, LocalDateTime start, LocalDateTime end) {
        super(name, priority, TaskType.EVENT);
        this.start = start;
        this.end = end;
        if (end.isBefore(LocalDateTime.now())) {
            setExpired();
        }
    }

    /**
     * Creates an <code>EventTask</code> and returns it.
     *
     * @param parts Parsed representation of an <code>EventTask</code>.
     * @return <code>EventTask</code> created.
     * @throws LynxException If input is of invalid format.
     */
    public static Task of(String[] parts) throws LynxException {
        if (parts.length != 7) {
            throw new MissingArgumentException("event");
        }

        String status = parts[1];
        String name = parts[3];
        int priority = parsePriority(parts[4]);
        LocalDateTime from = LynxDateManager.parseDateTime(parts[5].replace("from:", ""));
        LocalDateTime to = LynxDateManager.parseDateTime(parts[6].replace("to:", ""));

        Task task = new EventTask(name, priority, from, to);
        if (status.equals("COMPLETE")) {
            task.setComplete();
        }
        return task;
    }

    /**
     * Creates a <code>EventTask</code> and returns it.
     *
     * @param input User command in the form "event [name] /from [start] /to [end]".
     * @return <code>EventTask</code> created.
     * @throws LynxException If command, name, priority or date is invalid.
     */
    public static Task of(String input) throws LynxException {
        if (!input.startsWith("event ")) {
            throw new MissingArgumentException("event");
        }

        String[] parts = input.substring(5).split(" /from ", 2);
        if (parts.length < 2) {
            throw new MissingArgumentException("event");
        }

        String name = parts[0].trim();
        checkName(name);

        parts = parts[1].split(" /to ", 2);
        if (parts.length < 2) {
            throw new MissingArgumentException("event");
        }
        LocalDateTime from = LynxDateManager.parseDateTime(parts[0].trim());

        parts = parts[1].split(" /p ");
        LocalDateTime to = LynxDateManager.parseDateTime(parts[0].trim());
        if (from.isAfter(to)) {
            throw InvalidTaskException.invalidDuration();
        }

        int priority = 0;
        if (parts.length > 1) {
            priority = parsePriority(parts[1].trim());
        }

        return new EventTask(name, priority, from, to);
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
    public String storageRepresentation() {
        StringBuilder taskString = new StringBuilder(super.storageRepresentation());
        taskString.append("|from:").append(LynxDateManager.defaultDateTime(start));
        taskString.append("|to:").append(LynxDateManager.defaultDateTime(end));
        return taskString.toString();
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
