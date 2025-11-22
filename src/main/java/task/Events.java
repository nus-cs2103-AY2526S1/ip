package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.GenieweenieException;

/**
 * Represents an Event task with a start and end time.
 */
public class Events extends Task {

    /**
     * Formatter for parsing date-only strings in the format {@code yyyy-MM-dd}.
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates a new Event task.
     *
     * @param description description of the event
     * @param start       start time in yyyy-MM-dd HH:mm format
     * @param end         end time in yyyy-MM-dd HH:mm format
     * @throws GenieweenieException if description is empty, invalid date format,
     *                              or dates are before the current time
     */
    public Events(String description, String start, String end) throws GenieweenieException {
        super(validateDescription(description, start, end));
        this.start = parseDate(start, "start");
        this.end = parseDate(end, "end");

        // Ensure logical and valid time range
        LocalDateTime now = LocalDateTime.now();
        if (this.start.isBefore(now)) {
            throw new GenieweenieException("Start time cannot be before the current time: " + start);
        }
        if (this.end.isBefore(now)) {
            throw new GenieweenieException("End time cannot be before the current time: " + end);
        }
        if (this.end.isBefore(this.start)) {
            throw new GenieweenieException("End time cannot be before start time (from: " + start + " to: "
                    + end + ")");
        }
    }

    private static String validateDescription(String description,
                                              String start,
                                              String end) throws GenieweenieException {
        if (description == null || description.trim().isEmpty()) {
            throw new GenieweenieException("The description of an event cannot be empty. (from: "
                    + start + " to: " + end + ")");
        }
        return description;
    }

    /**
     * Parses a date string into a {@link LocalDateTime}.
     * <ul>
     *     <li>If the string is in the format {@code yyyy-MM-dd}, the time will default to {@code 00:00}.</li>
     *     <li>If the string is in the format {@code yyyy-MM-dd HH:mm}, the time will be preserved.</li>
     * </ul>
     *
     * @param dateTime the string to parse
     * @return the parsed {@link LocalDateTime}
     * @throws GenieweenieException if the format is invalid
     */
    private static LocalDateTime parseDate(String dateTime, String field) throws GenieweenieException {
        try {
            DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            if (dateTime.trim().length() == 10) {
                // yyyy-MM-dd → set time to 00:00
                return LocalDate.parse(dateTime, dateOnly).atStartOfDay();
            } else {
                // yyyy-MM-dd HH:mm
                return LocalDateTime.parse(dateTime, dateTimeFormat);
            }
        } catch (DateTimeParseException e) {
            throw new GenieweenieException(
                    "Invalid date/time format. Use yyyy-MM-dd or yyyy-MM-dd HH:mm, got: " + dateTime
            );
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + start.format(FORMATTER)
                + " to: " + end.format(FORMATTER) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + start.format(FORMATTER)
                + " | " + end.format(FORMATTER);
    }
}
