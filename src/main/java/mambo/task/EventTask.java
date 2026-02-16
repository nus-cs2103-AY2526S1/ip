package mambo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import mambo.parser.DateTimeParser;

/**
 * Represents an event task that has a start and end time
 *
 * @author kentalim2
 */
public class EventTask extends Task {
    private String start;
    private String end;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Creates a new instance of the event task.
     * If start and end time passed through into input follows a recognized date time format, represent the
     * start and end time as a LocalDateTime
     *
     * @param description Description of task
     * @param isDone Whether the task is done or not
     * @param start Start of task in input
     * @param end End of task in input
     */
    public EventTask(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;

        try {
            startDateTime = DateTimeParser.parseDateTime(start);
        } catch (DateTimeParseException e) {
            startDateTime = null;
        }

        try {
            endDateTime = DateTimeParser.parseDateTime(end);
        } catch (DateTimeParseException e) {
            endDateTime = null;
        }
    }

    /**
     * Returns string representation of the date and time if start is represented as a LocalDateTime.
     * Else return start.
     *
     * @return Start of task as String
     */
    public String getStart() {
        if (this.startDateTime != null) {
            return DateTimeParser.formatDateTime(startDateTime);
        } else {
            return this.start;
        }
    }

    /**
     * If end is represented as a LocalDateTime, return string representation of the date and time.
     * Else return end.
     *
     * @return End of task as String
     */
    public String getEnd() {
        if (this.endDateTime != null) {
            return DateTimeParser.formatDateTime(endDateTime);
        } else {
            return this.end;
        }

    }

    @Override
    public String convertToFileFormat() {
        return String.format("E / %s / %s / %s / %s",
                this.isMarked(), this.getDescription(), this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("(E) %s (from: %s -> to: %s)",
                super.toString(), getStart(), getEnd());
    }
}
