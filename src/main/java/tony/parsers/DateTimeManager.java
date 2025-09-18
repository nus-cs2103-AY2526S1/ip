package tony.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a formatter that parses strings to different date time formats
 * for storage and displaying.
 */
public class DateTimeManager {
    private static final DateTimeFormatter DATE_ONLY = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter DATE_TIME_12H = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");
    private static final DateTimeFormatter DISPLAY_DATE = DateTimeFormatter.ofPattern("d MMM yy");
    private static final DateTimeFormatter DISPLAY_DATE_TIME = DateTimeFormatter.ofPattern("d MMM yy h:mma");

    /**
     * Parses a datetime string into LocalDateTime.
     *
     * @param input A datetime in string format.
     * @return The corresponding {@link LocalDateTime}.
     * @throws DateTimeParseException If the input does not match any supported format.
     */
    public static LocalDateTime parse(String input) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(input.toUpperCase(), DATE_TIME_12H);
        } catch (DateTimeParseException e) {
            LocalDate date = LocalDate.parse(input, DATE_ONLY);
            return date.atStartOfDay();
        }
    }

    /**
     * Formats a datetime to either date only or date and time in 12hr format.
     *
     * @param dateTime A datetime in LocalDateTime format.
     * @return The corresponding {@link String}.
     */
    public static String format(LocalDateTime dateTime) {
        if (dateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)) {
            return dateTime.toLocalDate().format(DATE_ONLY);
        } else {
            return dateTime.format(DATE_TIME_12H);
        }
    }

    /**
     * Formats a datetime to a more user-friendly format.
     *
     * @param dateTime A datetime in LocalDateTime format.
     * @return The corresponding {@link String}.
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        if (dateTime.toLocalTime().equals(java.time.LocalTime.MIDNIGHT)) {
            return dateTime.format(DISPLAY_DATE);
        } else {
            return dateTime.format(DISPLAY_DATE_TIME);
        }
    }
}
