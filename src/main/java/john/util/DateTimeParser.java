package john.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Parses human-friendly date-time strings into LocalDateTime.
 * <p>
 * Format:
 * - Expected pattern: "dd/MM/yyyy HH:mm:ss"
 * - Example: "05/09/2025 16:30:45"
 * <p>
 * Parsing rules:
 * - Uses a strict resolver style to reject impossible dates (e.g. 31/02/2025).
 * - Uses 'uuuu' for the year to ensure strict year handling.
 */
public final class DateTimeParser {

    /**
     * Human-readable pattern string for error/help messages.
     * Example value: "dd/MM/yyyy HH:mm:ss"
     */
    public static final String HUMAN_PATTERN = "dd/MM/yyyy HH:mm:ss";

    /**
     * Internal formatter matching HUMAN_PATTERN with strict resolution.
     * Rejects invalid dates and times (e.g., leap-day errors).
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.
            ofPattern("dd/MM/uuuu HH:mm:ss").withResolverStyle(ResolverStyle.STRICT);

    /**
     * Parses a date-time string into a LocalDateTime using the expected pattern.
     *
     * @param s the input string, e.g., "05/09/2025 16:30:45"
     * @return the parsed LocalDateTime
     * @throws DateTimeParseException if the input does not match the pattern
     *                                or represents an invalid date/time
     */
    public static LocalDateTime parseDateTime(String s) throws DateTimeParseException {
        return LocalDateTime.parse(s, FORMATTER);
    }
}