package conversal.task;

/**
 * Represents an Event task in the Conversal chatbot.
 *
 * An Event has a description, a start time, and an end time.
 * It is displayed with the task type symbol [E].
 * Shows both the start and end schedule when listed.
 *
 */
public class Event extends Task {
    // Fields
    protected String start;
    protected String end;

    /**
     * Creates a new Event task with the given description,
     * start time and end time.
     *
     * @param description description of the event
     * @param start       the start date/time of the event
     * @param end         the end date/time of the event
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        assert start != null && !start.isEmpty() : "start must not be empty or null";
        assert end != null && !end.isEmpty() : "end must not be empty or null";
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the schedule of the event, in the format "start-end".
     *
     * @return a string combining the start and end times
     */
    public String getSchedule() {
        assert start != null && end != null : "schedule parts must not be null";
        return (start + "-" + end);
    }

    /**
     * Returns the string representation of this Event.
     * Includes the task description and the schedule in the format:
     * (from: start to: end).
     *
     * @return the string form of this event task
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
