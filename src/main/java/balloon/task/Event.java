package balloon.task;

/**
 * Represents a task that has a description, a start date/time, and an end date/time.
 * <p>
 * This class extends {@link Task} and adds {@link StringDateTime} fields
 * representing the start and end dates/times of the event.
 */
public class Event extends Task {

    private StringDateTime from;
    private StringDateTime to;


    /**
     * Constructs an {@code Event} task with the given description, start time, and end time.
     *
     * @param description the task description
     * @param fromInput the start date/time in string form
     * @param toInput the end date/time in string form
     */
    public Event(String description, String fromInput, String toInput) {
        super(description);
        from = new StringDateTime(fromInput);
        to = new StringDateTime(toInput);
    }

    /**
     * Returns a string representation of this task for displaying to the user.
     * <p>
     * Format: "[E][status] description (from: start to: end)"
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getOutputString()
                + " to: " + to.getOutputString() + ")";
    }

    /**
     * Returns the string representation of this task for saving to a file.
     * <p>
     * Format: "EVENT | status | description | from | to"
     *
     * @return a string representing the task in save-file format
     */
    @Override
    public String toSaveFormat() {
        return "EVENT | " + getDoneStatusIndicator() + " | " + description + " | "
                + from.getAsRawString() + " | " + to.getAsRawString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Event) {
            Event other = (Event) object;
            return this.description.equals(other.description)
                    && this.from.equals(other.from)
                    && this.to.equals(other.to);
        }
        return false;
    }
}
