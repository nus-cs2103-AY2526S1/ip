package lynx.task;

import lynx.formatter.LynxDateManager;

import java.time.LocalDateTime;

/**
 * Represents a task with a <code>TaskType</code>, <code>Status</code>,
 * name, <code>LocalDateTime</code> deadline, and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name, TaskType.DEADLINE);
        this.deadline = deadline;
        if (deadline.isBefore(LocalDateTime.now())) {
            setExpired();
        }
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
    public String testRepresentation() {
        return String.format("%s (by: %s)", super.testRepresentation(), LynxDateManager.textDateTime(deadline));
    }

}