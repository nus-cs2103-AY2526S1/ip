package hermione.tasks;

/**
 * Represents a task with a fixed duration in the Hermione application.
 */
public class FixedDurationTask extends Task {

    /**
     * The duration of the task in hours.
     */
    private final int duration;

    /**
     * Constructs a FixedDurationTask with the specified description,
     * completion status, and duration.
     *
     * @param description The description of the task.
     * @param isCompleted Whether the task is completed.
     * @param duration    The duration of the task in hours.
     */
    public FixedDurationTask(String description, boolean isCompleted, int duration) {
        super(description, isCompleted);
        this.duration = duration;
    }

    /**
     * Gets the duration of the task in hours.
     *
     * @return The duration of the task.
     */
    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "[" + TaskType.FIXED_DURATION_TASK.getCode() + "]"
                + super.toString()
                + " (duration: %d hours)".formatted(this.duration);
    }
}
