package eureka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for converting date-time strings into {@link LocalDateTime} objects.
 * <p>
 * The expected format is {@code yyyy-MM-dd HH:mm}, for example:
 * {@code 2025-09-01 18:00}.
 * If the string cannot be parsed, an {@link IllegalArgumentException} is thrown.
 */
public class DateTimeConverter {

    /**
     * Converts a date-time string into a {@link LocalDateTime} object using the format
     * {@code yyyy-MM-dd HH:mm}.
     *
     * @param timeToBeConverted the date-time string to be converted (must not be null)
     * @return the parsed {@link LocalDateTime} object
     * @throws IllegalArgumentException if the string cannot be parsed
     */
    public static LocalDateTime dateTimeConverter(String timeToBeConverted) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(timeToBeConverted.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date format. Please use yyyy-MM-dd HH:mm, e.g. 2025-09-01 18:00"
            );
        }
    }
}
