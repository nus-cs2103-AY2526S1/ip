package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Utility class for parsing and formatting date-time strings.
 * Handles conversion between string representations and LocalDateTime objects.
 */
public class DateTimeParser {
    private static final DateTimeFormatter formatter = createFormatter();

    private static DateTimeFormatter createFormatter() {
        return new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .optionalStart()
                .appendPattern(" HH:mm")
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     * Supports both date-only ("yyyy-MM-dd") and date-time ("yyyy-MM-dd HH:mm") formats.
     * For date-only strings, defaults time to midnight (00:00:00).
     *
     * @param dateTimeString the string to parse in "yyyy-MM-dd" or "yyyy-MM-dd HH:mm" format
     * @return the parsed LocalDateTime object
     * @throws DateTimeParseException if the string cannot be parsed
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Formats a LocalDateTime object into a human-readable display string.
     * The format is "MMM dd yyyy h.mmAM/PM" (e.g., "Jan 15 2023 2.30PM").
     *
     * @param dateTime the LocalDateTime object to format
     * @return the formatted display string in uppercase
     */
    public static String formatDisplay(LocalDateTime dateTime) {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h'.'mma");
        return displayFormatter.format(dateTime).toUpperCase();
    }
}
