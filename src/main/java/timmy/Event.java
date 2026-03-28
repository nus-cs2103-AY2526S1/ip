package timmy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.TimmyDateParsingException;

/**
 * Represents a task with a start and end date.
 */
public class Event extends Task {
    protected static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected final LocalDate start;
    protected final LocalDate end;

    /**
     * Creates an incomplete event with a specified start and end date.
     *
     * @param desc  description of the event.
     * @param start start date of event, in d/M/yyyy format.
     * @param end   end date of event, in d/M/yyyy format.
     */
    Event(String desc, String start, String end) throws DateTimeParseException {
        super(desc);
        try {
            this.start = LocalDate.parse(start, INPUT_DATE_FORMAT);
            this.end = LocalDate.parse(end, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new TimmyDateParsingException();
        }
    }

    /**
     * Returns the task as a formatted string for Timmy to output.
     *
     * @return task as a formatted string to output on UI.
     */
    public String toCompleteString() {
        return "[E]" + super.toStringWithStatusIcon()
                + " (from: " + start.format(OUTPUT_DATE_FORMAT)
                + " to: " + end.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Returns the task as a formatted string for Timmy to save.
     *
     * @return task as a formatted string to save in storage.
     */
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + super.toString()
                + " | " + start.format(INPUT_DATE_FORMAT) + " | " + end.format(INPUT_DATE_FORMAT);
    }
}
