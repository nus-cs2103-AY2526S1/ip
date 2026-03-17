package objectclasses.task;

import java.time.LocalDateTime;

import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;
import objectclasses.formatter.LynxDateManager;

/**
 * Represents a task with a <code>TaskType</code>, <code>Status</code>,
 * name, priority, <code>LocalDateTime</code> deadline, and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for creating a <code>DeadlineTask</code>
     *
     * @param name Name of the task.
     * @param priority Priority of the task.
     * @param deadline Deadline of the task.
     */
    public DeadlineTask(String name, int priority, LocalDateTime deadline) {
        super(name, priority, TaskType.DEADLINE);
        this.deadline = deadline;
        if (deadline.isBefore(LocalDateTime.now())) {
            setExpired();
        }
    }

    /**
     * Creates a <code>DeadlineTask</code> and returns it.
     *
     * @param parts Parsed representation of a <code>DeadlineTask</code>.
     * @return <code>DeadlineTask</code> created.
     * @throws LynxException If input is of invalid format.
     */
    public static Task of(String[] parts) throws LynxException {
        if (parts.length != 6) {
            throw new MissingArgumentException("deadline");
        }

        String status = parts[1];
        String name = parts[3];
        int priority = parsePriority(parts[4]);
        LocalDateTime by = LynxDateManager.parseDateTime(parts[5].replace("by:", ""));

        Task task = new DeadlineTask(name, priority, by);
        if (status.equals("COMPLETE")) {
            task.setComplete();
        }
        return task;
    }

    /**
     * Creates a <code>DeadlineTask</code> and returns it.
     *
     * @param input User command in the form "deadline [name] /by [date]".
     * @return <code>DeadlineTask</code> created.
     * @throws LynxException If command, name, priority or date is invalid.
     */
    public static Task of(String input) throws LynxException {
        if (!input.startsWith("deadline ")) {
            throw new MissingArgumentException("deadline");
        }
        String[] parts = input.substring(8).split(" /by ", 2);
        if (parts.length < 2) {
            throw new MissingArgumentException("deadline");
        }

        String name = parts[0].trim();
        checkName(name);

        parts = parts[1].split(" /p ", 2);
        LocalDateTime by = LynxDateManager.parseDateTime(parts[0].trim());

        int priority = 0;
        if (parts.length > 1) {
            priority = parsePriority(parts[1].trim());
        }

        return new DeadlineTask(name, priority, by);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Checks if the task is active on the given date.
     *
     * @return True if deadline lies on the given date.
     */
    public boolean isActive(LocalDateTime dateTime) {
        return dateTime.toLocalDate().equals(deadline.toLocalDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String storageRepresentation() {
        StringBuilder taskString = new StringBuilder(super.storageRepresentation());
        taskString.append("|by:").append(LynxDateManager.defaultDateTime(deadline));
        return taskString.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String testRepresentation() {
        return String.format("%s (by: %s)", super.testRepresentation(), LynxDateManager.textDateTime(deadline));
    }

}
