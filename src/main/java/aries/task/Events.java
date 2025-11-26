package aries.task;

import java.time.LocalDateTime;

import aries.util.DateTime;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Events extends Task {
    private static final long serialVersionUID = 1L;
    private static final String TYPE_ICON = "E";

    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    /**
     * Constructs an Events object with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event in string format.
     * @param to          The end time of the event in string format.
     */
    public Events(String description, String from, String to) {
        super(description);
        assert from != null : "Start time cannot be null";
        assert !from.isEmpty() : "Start time cannot be empty";
        assert to != null : "End time cannot be null";
        assert !to.isEmpty() : "End time cannot be empty";
        assert DateTime.parse(from).isBefore(DateTime.parse(to)) : "Start time must be before end time";
        this.fromDate = DateTime.parse(from);
        this.toDate = DateTime.parse(to);
    }

    @Override
    public String getKey() {
        return "E:" + norm(description) + ":" + DateTime.format(fromDate) + ":" + DateTime.format(toDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String fromDateString = " (from: " + DateTime.format(fromDate) + ")";
        String toDateString = " (to: " + DateTime.format(toDate) + ")";
        sb.append("[").append(TYPE_ICON).append("] ").append(super.toString())
            .append(fromDateString).append(toDateString);
        return sb.toString();
    }
}
