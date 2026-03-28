package timmy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.TimmyDateParsingException;

/**
 * Represents a task with a start and end date.
 */
public class Deadline extends Task {
    protected static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected final LocalDate end;

    /**
     * Creates an incomplete task with a specified end date.
     *
     * @param desc  description of the event.
     * @param end   end date of event, in d/M/yyyy format.
     */
    Deadline(String desc, String end) throws TimmyDateParsingException {
        super(desc);
        try {
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
        return "[D]" + super.toStringWithStatusIcon()
                + " (by: " + end.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Returns the task as a formatted string for Timmy to save.
     *
     * @return task as a formatted string to save in storage.
     */
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + super.toString() + " | " + end.format(INPUT_DATE_FORMAT);
    }
}
