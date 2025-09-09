package objectclasses.task;

import java.time.LocalDateTime;

import objectclasses.exception.LynxException;
import objectclasses.exception.MissingArgumentException;
import objectclasses.formatter.LynxDateManager;

/**
 * Represents a task with a <code>TaskType</code>, <code>Status</code>,
 * name, <code>LocalDateTime</code> deadline, and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for creating a <code>DeadlineTask</code>
     *
     * @param name Name of the task.
     * @param deadline Deadline of the task.
     */
    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name, TaskType.DEADLINE);
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
     * @throws LynxException If input is of invalid format or deadline is invalid.
     */
    public static Task of(String[] parts) throws LynxException {
        if (parts.length < 5) {
            throw new LynxException("");
        }
        String status = parts[1];
        String name = parts[3];
        LocalDateTime by = LynxDateManager.parseDateTime(parts[4].replace("by:", ""));
        Task task = new DeadlineTask(name, by);
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
     * @throws LynxException If command, name or date is invalid.
     */
    public static Task of(String input) throws LynxException {
        if (input.length() <= 8) {
            throw new MissingArgumentException("deadline");
        }
        String[] parts = input.substring(8).split(" /by ", 2);
        if (parts.length < 2) {
            throw new LynxException("Please specify a deadline using ' /by '.");
        }
        String name = parts[0].trim();
        if (name.isEmpty()) {
            throw new LynxException("Please specify a task name.");
        }

        checkName(name);
        LocalDateTime by = LynxDateManager.parseDateTime(parts[1].trim());
        return new DeadlineTask(name, by);
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
