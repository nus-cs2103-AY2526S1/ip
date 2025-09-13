package hermione.utils;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hermione.exceptions.DateUtilsException;

/**
 * Utility class for handling date and time operations in the Hermione application.
 */
public class DateUtils {

    /**
     * Parses a date string in the format "d/M/yyyy HHmm" to a LocalDateTime object.
     * This method uses a DateTimeFormatter to parse the string and returns a LocalDateTime
     * object representing the date and time.
     * If the string is not in the correct format, it throws a DateUtilsException with
     * a descriptive error message.
     *
     * @param dateString The date string to be parsed, expected format: "d/M/yyyy HHmm".
     * @return LocalDateTime object representing the parsed date and time.
     * @throws DateUtilsException If the date string is not in the expected format.
     */
    public static LocalDateTime parseDateString(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new DateUtilsException(
                    "Failed to parse date: %s. Make sure to follow the format: d/M/yyyy HHmm"
                            .formatted(dateString)
            );
        }
    }

    /**
     * Formats a LocalDateTime object into a string in the format "MMM dd yyyy HH:mm".
     * This method uses a DateTimeFormatter to format the LocalDateTime
     * object and returns a string representation.
     * If the LocalDateTime object is null or if formatting fails, it throws a Date
     * UtilsException with a descriptive error message.
     *
     * @param date The LocalDateTime object to be formatted.
     * @return String representation of the date in the format "MMM dd yyyy HH:mm".
     * @throws DateUtilsException If the LocalDateTime object is null or formatting fails.
     */
    public static String formatDate(LocalDateTime date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            return date.format(formatter);
        } catch (DateTimeException e) {
            throw new DateUtilsException("Failed to format date: %s".formatted(date));
        }
    }

    /**
     * Parses a date string in the format "MMM dd yyyy HH:mm" to a LocalDateTime object.
     * This method uses a DateTimeFormatter to parse the string and returns a LocalDateTime
     * object representing the date and time.
     * If the string is not in the correct format, it throws a DateUtilsException with
     * a descriptive error message.
     *
     * @param dateString The date string to be parsed, expected format: "MMM dd yyyy HH:mm".
     * @return LocalDateTime object representing the parsed date and time.
     * @throws DateUtilsException If the date string is not in the expected format.
     */
    public static LocalDateTime undoFormatDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new DateUtilsException(
                    "Failed to parse date: %s. Make sure to follow the format: MMM dd yyyy HH:mm"
                            .formatted(dateString)
            );
        }
    }
}
