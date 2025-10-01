package eve.tasks;

/**
 * Represents a task that must be completed within a specific period.
 * <p>
 * A {@code DoWithinPeriod} task stores a start and end time (as strings)
 * in addition to the base description inherited from {@link Task}.
 * </p>
 */
public class DoWithinPeriod extends Task {
    /** Start time or date of the period. */
    protected String start;

    /** End time or date of the period. */
    protected String end;

    /**
     * Constructs a new {@code DoWithinPeriod} task with a description and time
     * range.
     *
     * @param description the description of the task
     * @param start       the starting time/date of the period
     * @param end         the ending time/date of the period
     */
    public DoWithinPeriod(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the icon representing a period-based task.
     *
     * @return the character {@code "P"} to represent this task type
     */
    @Override
    public String getTypeIcon() {
        return "P"; // or any icon you like for 'period' tasks
    }

    /**
     * Returns a string representation of the task including
     * its description and period range.
     *
     * @return formatted string with task details and period range
     */
    @Override
    public String toString() {
        return super.toString() + " (between: " + start + " to " + end + ")";
    }

    /**
     * Gets the start time/date of the task period.
     *
     * @return the start token as a string
     */
    public String getStart() {
        return start;
    }

    /**
     * Gets the end time/date of the task period.
     *
     * @return the end token as a string
     */
    public String getEnd() {
        return end;
    }

    /**
     * Returns a token string representing the task period.
     * <p>
     * The format is: {@code start | end}.
     * </p>
     *
     * @return period token string
     */
    public String getPeriodToken() {
        return start + " | " + end;
    }
}
