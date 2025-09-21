package jeff.task;

/**
 * Represents a task with a fixed duration that must be completed within a
 * specific time frame.
 */
public class FixedDurationTask extends Task {

    private final int duration;

    /**
     * Creates a new FixedDurationTask with the specified description and
     * duration.
     *
     * @param description the task description
     * @param duration the duration of the task
     */
    public FixedDurationTask(String description, int duration) {
        super(description, "FD");

        assert duration > 0 : "Duration must be greater than 0";
        this.duration = duration;
    }

    /**
     * Gets the duration of the task.
     *
     * @return the duration of the task
     */
    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "[FD]" + super.toString() + description + " (duration: " + duration + " hours)";
    }

}
