package joobot.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents a date/time value that may either be a valid {@link LocalDateTime}
 * or a raw string if parsing fails.
 * <p>
 * This class encapsulates the logic of parsing dates provided in the format
 * <code>d/M/yyyy HHmm</code>, and formatting them for display and file storage.
 */
public class DateTimeValue {
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
    private final String raw;
    private final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTimeValue} from the given input string.
     * <p>
     * If the input matches the expected date-time format {@code d/M/yyyy HHmm},
     * it is stored as a {@link LocalDateTime}. Otherwise, it is stored as a raw string.
     *
     * @param input the user provided string, which may or may not be a date
     */
    public DateTimeValue(String input) {
        this.raw = input;
        LocalDateTime parsed = null;
        try {
            parsed = LocalDateTime.parse(input, FORMAT);
        } catch (DateTimeParseException e) {
            // not a date, leave null
            // error
        }
        this.dateTime = parsed;
    }

    /**
     * Returns the value in a user-friendly display format.
     * <p>
     * - If the input was a valid date, it will be formatted as {@code dd MMM yyyy HHmm}.
     * - Otherwise, the raw input string is returned.
     *
     * @return the formatted string for display
     */
    public String toDisplayString() {
        return (dateTime != null) ? dateTime.format(DISPLAY_FORMAT) : raw;
    }

    /**
     * Returns the value in a file storage format.
     * <p>
     * - If the input was a valid date, it will be formatted as {@code d/M/yyyy HHmm}.
     * - Otherwise, the raw input string is returned.
     *
     * @return the formatted string for file saving
     */
    public String toFileString() {
        return (dateTime != null) ? dateTime.format(FORMAT) : raw;
    }
}
