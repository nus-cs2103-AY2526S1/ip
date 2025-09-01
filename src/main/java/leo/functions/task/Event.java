package leo.functions.task;

import leo.DateTimeParser;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private String startDate;
    private String endDate;

    /**
     * Instantiates an Event task.
     *
     * @param description The description of the deadline task.
     * @param startDate The start date and time of the task.
     * @param endDate The end date and time of the task.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);

        DateTimeParser dateTimeParser = new DateTimeParser();
        this.startDate = dateTimeParser.formatDateTimeFromInput(startDate);
        this.endDate = dateTimeParser.formatDateTimeFromInput(endDate);
    }

    /**
     * Returns the formatted string of the task to be written to a file.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String appendToFile() {
        return String.format(
                "Event | "
                + this.getStatusIcon()
                + " | "
                + this.description
                + " | "
                + this.startDate
                + "-"
                + this.endDate
        );
    }

    /**
     * Returns a formatted string of the task to be written to the UI.
     *
     * @return A formatted string of the task.
     */
    @Override
    public String toString() {
        return String.format(
                "[E]["
                + this.getStatusIcon()
                + "] "
                + this.description
                + " (from: "
                + this.startDate
                + " to: "
                + this.endDate
                + ")"
        );
    }
}
