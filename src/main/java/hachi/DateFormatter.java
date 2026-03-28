package hachi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Utility class for formatting and parsing {@link LocalDateTime} objects
 * using a fixed pattern: {@code "MMM d yyyy, ha"} (e.g., "Sep 19 2025, 3PM").
 *
 * <p>This class uses the {@link Locale#ENGLISH} locale to ensure month names
 * are parsed and formatted consistently.</p>
 */
public class DateFormatter {

    /**
     * Parses a string into a {@link LocalDateTime} using the fixed pattern.
     *
     * @param time the string representation of the time (e.g., "Sep 19 2025, 3PM")
     * @return the parsed {@link LocalDateTime}
     * @throws java.time.format.DateTimeParseException if the text cannot be parsed
     */
    public LocalDateTime parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, ha", Locale.ENGLISH);
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Formats a {@link LocalDateTime} into a string using the fixed pattern.
     *
     * @param time the {@link LocalDateTime} to format
     * @return the formatted string (e.g., "Sep 19 2025, 3PM")
     */
    public String formatTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, ha", Locale.ENGLISH);
        return time.format(formatter);
    }
}
