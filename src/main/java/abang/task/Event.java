package abang.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a event task with a description and a specific start and end.
 * The start and end may be stored as either a LocalDate, a LocalDateTime,
 * or as a free-text detail string if parsing fails.
 */
public class Event extends Task {
    String start;
    String end;
    LocalDate startdate;
    LocalDate enddate;
    LocalDateTime startTime;
    LocalDateTime endTime;

    DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter fmtDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private static final DateTimeFormatter PARSE_DATE = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter PARSE_DATETIME = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Creates a Event task with the given description and detail.
     * <p>
     * The start string is first attempted to be parsed as a date (d/M/yyyy).
     * If that fails, it is attempted to be parsed as a date and time (d/M/yyyy HHmm).
     * If both parsing attempts fail, the detail is stored as a free-text string.
     *
     * The end string is parsed in the same way
     *
     * @param description the description of the task
     * @param start    the start detail as a string
     * @param end      the end detail as a string
     */
    public Event(String description, String start, String end) {
        super(description);

        try {
            this.startdate = LocalDate.parse(start, PARSE_DATE);
        } catch (DateTimeParseException e) {
            try {
                this.startTime = LocalDateTime.parse(start, PARSE_DATETIME);
            } catch (DateTimeParseException e2) {
                this.start = start;
            }
        }

        try {
            this.enddate = LocalDate.parse(end, PARSE_DATE);
        } catch (DateTimeParseException e) {
            try {
                this.endTime = LocalDateTime.parse(end, PARSE_DATETIME);
            } catch (DateTimeParseException e2) {
                this.end = end;
            }
        }
    }

    /**
     * Returns a string representation of this event task,
     * including the formatted start and end times.
     *
     * @return the string representation of the event task
     */
    @Override
    public String toString() {
        String startStr;
        if (startTime != null) {
            startStr = startTime.format(fmtDateTime);
        } else if (startdate != null) {
            startStr = startdate.format(fmtDate);
        } else {
            startStr = start;
        }

        String endStr;
        if (endTime != null) {
            endStr = endTime.format(fmtDateTime);
        } else if (enddate != null) {
            endStr = enddate.format(fmtDate);
        } else {
            endStr = end;
        }

        return "[E]" + super.toString() +
                String.format(" (from: %s to: %s)", startStr, endStr);
    }

    /**
     * Returns the file format representation of this event task,
     * used for saving to persistent storage.
     *
     * @return the event task as a string suitable for file saving
     */
    @Override
    public String toFileFormat() {

        String startStr;
        if (startTime != null) {
            startStr = startTime.format(fmtDateTime);
        } else if (startdate != null) {
            startStr = startdate.format(fmtDate);
        } else {
            startStr = start;
        }

        String endStr;
        if (endTime != null) {
            endStr = endTime.format(fmtDateTime);
        } else if (enddate != null) {
            endStr = enddate.format(fmtDate);
        } else {
            endStr = end;
        }

        return "E | " + getStatusIcon() + " | " + getTaskDescription() + " | " +
                (Objects.isNull(this.getTag()) ? "null" : this.getTag().split("#")[1]) +
                " | from: " + startStr + " to: " + endStr;
    }

}