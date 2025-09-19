package bot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class for handling various date and time conversion tasks.
 * <p>
 * This class provides a set of static methods to convert between
 * {@link LocalDateTime} objects and their {@link String} representations. It
 * supports parsing from multiple formats and formatting into specific,
 * pre-defined patterns for file storage and user display.
 * </p>
 * <p>
 * The class uses a prioritized list of {@link DateTimeFormatter} objects
 * to handle flexible parsing of different date-time string formats.
 * </p>
 */
public class DateTimeUtils {
    /**
     * Define a formatter matching the "dd-MM-yyyy HHmm" pattern.
     * * The valid format to be converted into date is '02-05-2025 1300'
     * <p>
     * This formatter is used for parsing user input and read file input from
     * string to {@code LocalDateTime}
     **/
    private static final DateTimeFormatter FILE_FORMATTER
            = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Define a formatter matching the "dd MMM yy HH:mm" pattern.
     * * The valid format to be converted into date is '02 May 25 13:00'
     * <p>
     * This formatter is used for displaying date time in
     * user-friendly format
     **/
    private static final DateTimeFormatter DISPLAY_FORMATTER
            = DateTimeFormatter.ofPattern("dd MMM yy HH:mm");

    /**
     * A list of {@code DateTimeFormatter} objects used for parsing a variety of
     * common date and time string formats.
     * <p>
     * The supported formats are:
     * <ul>
     *   <li>{@code fileFormatter}: "dd-MM-yyyy HHmm" (e.g., '02-05-2025 1300')</li>
     *   <li>{@code displayFormatter}: "dd MMM yy HH:mm" (e.g., '02 May 25 13:00')</li>
     *   <li>"dd-MM-yyyy" (e.g., '02-05-2025')</li>
     *   <li>"dd/MM/yyyy" (e.g., '02/05/2025')</li>
     *   <li>"dd/MM/yyyy HH:mm" (e.g., '02/05/2025 13:00')</li>
     * </ul>
     * </p>
     */
    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            FILE_FORMATTER,
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            // Add formatters that append a default time to handle date-only inputs
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter(),
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter(),
            DISPLAY_FORMATTER
    );

    /**
     * Parses a date-time string using a list of supported formatters.
     * <p>
     * The method iterates through the predefined list of {@code formatters} and
     * attempts to parse the input string. The first formatter that successfully
     * parses the string is used.
     * </p>
     *
     * @param dateStr The date-time string to parse.
     * @return The parsed {@code LocalDateTime} object.
     * @throws IllegalArgumentException if the date string does not match any of the
     *                                  supported formats. The error message will list the expected formats.
     */
    public static LocalDateTime fromString(String dateStr)
            throws IllegalArgumentException {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                // Attempt to parse with the current formatter
                return LocalDateTime.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // Ignore the exception and try the next formatter
            }
        }

        // Date string doesn't match with any of the supported formatters
        throw new IllegalArgumentException(
                """
                        Oh no! I don't support this date format, but you can choose one of these format:\
                        
                        dd-mm-yyyy\
                        
                        dd-mm-yyyy HHmm\
                        
                        dd/mm/yyyy\
                        
                        dd/mm/yyyy HHmm""");
    }

    /**
     * Converts a {@code LocalDateTime} object into a user-friendly string format.
     * <p>
     * This method uses the {@code displayFormatter} to format the date and time
     * into the "dd MMM yy HH:mm" pattern, which is suitable for displaying to users.
     * For example, '02-05-2025 1300' would be formatted as '02 May 25 13:00'.
     * </p>
     *
     * @param dateTime The {@code LocalDateTime} object to format.
     * @return A string representing the date and time in a human-readable format.
     */
    public static String toDisplayString(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMATTER);
    }

    /**
     * Converts a {@code LocalDateTime} object into a string suitable for file storage.
     * <p>
     * This method uses the {@code fileFormatter} to format the date and time
     * into the "dd-MM-yyyy HHmm" pattern. The resulting string can be
     * converted back to a {@code LocalDateTime} object using the {@link #fromString(String)}
     * method.
     * </p>
     *
     * @param dateTime The {@code LocalDateTime} object to format.
     * @return A string representing the date and time in a machine-readable format.
     */
    public static String toFileString(LocalDateTime dateTime) {
        return dateTime.format(FILE_FORMATTER);
    }

    /**
     * Checks if the start time is before or equal to the end time.
     *
     * @param startTime the start time to check
     * @param endTime the end time to check against
     * @return true if startTime is before or equal to endTime, false otherwise
     */
    public static boolean isValidTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return !startTime.isAfter(endTime);
    }
}
