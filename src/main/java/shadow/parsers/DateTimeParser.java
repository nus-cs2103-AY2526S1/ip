package shadow.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * A utility class providing methods to parse, format, and calculate time-related operations
 * with {@link LocalDateTime} objects.
 * <p>
 * This class handles multiple recognized date-time formats, allowing flexible parsing
 * of input strings. It also provides functionality to convert {@link LocalDateTime} objects
 * to a consistent string representation and compare dates for time calculations.
 */
public class DateTimeParser {
    private static final List<DateTimeFormatter> formatters = List.of(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("h:mm a dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
    );
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy, EEEE, HH:mm");

    /**
     * Parses a date-time string into a {@link LocalDateTime} object.
     * <p>
     * Tries multiple predefined formats until one successfully parses the input.
     * If none match, throws an {@link IllegalArgumentException}.
     * </p>
     *
     * @param input the date-time string to parse
     * @return the parsed {@link LocalDateTime} object
     * @throws IllegalArgumentException if the input does not match any supported format
     */
    public static LocalDateTime parse(String input) {
        for (DateTimeFormatter formatter : DateTimeParser.formatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
               // continue
            }
        }
        throw new IllegalArgumentException("Unrecognized date/time format: " + input);
    }

    /**
     * Formats a {@link LocalDateTime} object into a string using a consistent pattern.
     *
     * @param dateTime the {@link LocalDateTime} to format
     * @return the formatted date-time string
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeParser.formatter);
    }

    /**
     * Calculates the number of whole days between the current time and the given {@link LocalDateTime}.
     *
     * @param dateTime the target date-time to compare with now
     * @return the number of days left (positive if in the future, negative if in the past)
     */
    public static long timeLeft(LocalDateTime dateTime) {
        return ChronoUnit.DAYS.between(LocalDateTime.now(), dateTime);
    }
}
