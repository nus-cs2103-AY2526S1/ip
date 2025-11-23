package conversal.task;

/**
 * Represents a task that must be done within a certain time period.
 *
 * A DoWithinTask has a description and a do within time frame.
 * It is displayed with the task type symbol [W].
 *
 */
public class DoWithinPeriodTask extends Task {
    private String period;

    /**
     * Creates a DoWithinPeriodTask with a description and a period string.
     *
     * @param description description of the task
     * @param period the period within which the task should be completed
     */
    public DoWithinPeriodTask(String description, String period) {
        super(description, TaskType.DOWITHIN);
        assert period != null && !period.isEmpty() : "period must not be empty";
        this.period = period;
    }

    /**
     * Returns the period within which the task should be completed.
     *
     * @return the due date as a LocalDate
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Returns the string representation of this Deadline.
     *
     * @return the string form of this deadline task
     */
    @Override
    public String toString() {
        return super.toString() + " (within: " + period + ")";
    }
}
