package simon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that has a time period.
 * An <code>Event</code> object contains a description, start, and end date/time.
 */
public class Event extends Task {
    protected String start;
    protected String end;
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Constructs an Event task with the inputted description, start, and end date/time strings.
     *
     * @param description Description of the event task.
     * @param start       Start date/time as a string.
     * @param end         End date/time as a string.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.startDateTime = parseDateTime(start);
        this.endDateTime = parseDateTime(end);
    }

    /**
     * Tries to parse the input string into a LocalDateTime using a fixed set of accepted patterns.
     * Returns null if parsing fails for all patterns.
     *
     * @param input Date or time string to parse.
     * @return Parsed LocalDateTime, or null if parsing fails.
     */
    private LocalDateTime parseDateTime(String input) {
        String[] patterns = {
                "d/M/yyyy HHmm",
                "d/M/yyyy",
                "yyyy-MM-dd HHmm",
                "yyyy-MM-dd"
        };
        for (String pattern : patterns) {
            try {
                if (pattern.contains("H")) {
                    return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(pattern));
                } else {
                    return LocalDateTime.parse(input + " 0000", DateTimeFormatter.ofPattern(pattern + " HHmm"));
                }
            } catch (DateTimeParseException e) {
            }
        }
        return null;
    }

    /**
     * Returns the start date/time string for this event task.
     *
     * @return Start date/time as a string.
     */
    public String getStart() {
        return start;
    }

    /**
     * Returns the end date/time string for this event task.
     *
     * @return End date/time as a string.
     */
    public String getEnd() {
        return end;
    }

    /**
     * Returns the start date/time as a LocalDateTime object, or null if parsing failed.
     *
     * @return Start date/time as a LocalDateTime, or null if not parsable.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Returns the end date/time as a LocalDateTime object, or null if parsing failed.
     *
     * @return End date/time as a LocalDateTime, or null if not parsable.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Returns the string representation of the event task, including its type, status icon, start and end date/time.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        String startStr = start;
        String endStr = end;
        if (startDateTime != null) {
            startStr = startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
        }
        if (endDateTime != null) {
            endStr = endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
        }
        return "[E]" + super.toString() + " (from: " + startStr + " to: " + endStr + ")";
    }
}