package dume.task;

import dume.util.DateTimeHelper;

/**
 * Represents a scheduled event that has a start and end date/time.
 */
public class Event extends Task {
    private final String start;
    private final String end;

    /**
     * Creates a new Event.
     *
     * @param details description of the event
     * @param start start date/time string
     * @param end end date/time string
     */
    public Event(String details, String start, String end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeHelper.convert(start)
                + " to: " + DateTimeHelper.convert(end) + ")";
    }

    /**
     * Gets the start time of this event.
     *
     * @return the start date/time string
     */
    public String getFrom() {
        return start;
    }

    /**
     * Gets the end time of this event.
     *
     * @return the end date/time string
     */
    public String getTo() {
        return end;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isCompleted ? "1" : "0") + " | " + details + " | " + start + " to " + end;
    }
}
