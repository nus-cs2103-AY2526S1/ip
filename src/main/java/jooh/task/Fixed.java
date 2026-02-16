package jooh.task;

/**
 * Represents a task that requires a fixed duration of time
 * but does not have a fixed start or end date.
 * <p>
 * Examples include unscheduled tasks such as "Read sales report"
 * which may take 2 hours, but without a specified start time.
 * </p>
 */
public class Fixed extends Task {
    /** Duration required to complete the task (e.g., "2 hours"). */
    private final String duration;

    /**
     * Creates a {@code FixedDuration} task with the specified description,
     * completion status, and duration.
     *
     * @param desc Description of the task.
     * @param isDone {@code true} if the task is already completed,
     *               {@code false} otherwise.
     * @param duration String representing the time required to complete the task.
     */
    public Fixed(String desc, boolean isDone, String duration) {
        super(desc, isDone);
        this.duration = duration;
    }

    /**
     * Returns a string representation of this task, including
     * its type, description, status icon, and required duration.
     *
     * @return String representation of the fixed duration task.
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs: " + duration + ")";
    }

    public String getDuration() {
        return this.duration;
    }
}
