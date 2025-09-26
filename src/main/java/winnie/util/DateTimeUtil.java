package winnie.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import winnie.exception.InvalidDateTimeException;

/**
 * Utility class for handling date and time operations.
 */
public class DateTimeUtil {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter OUTPUT_DATE_ONLY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTimeString The date-time string to parse.
     * @return The parsed LocalDateTime object.
     * @throws InvalidDateTimeException If the date-time string is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws InvalidDateTimeException {
        assert dateTimeString != null : "DateTime string cannot be null";
        dateTimeString = dateTimeString.trim();

        try {
            return LocalDateTime.parse(dateTimeString, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(dateTimeString + " 0000", INPUT_DATE_FORMAT);
            } catch (DateTimeParseException e2) {
                throw new InvalidDateTimeException(dateTimeString);
            }
        }
    }

    /**
     * Formats a LocalDateTime object for display.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted date-time string.
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            return dateTime.format(OUTPUT_DATE_ONLY_FORMAT);
        }
        return dateTime.format(OUTPUT_FORMAT);
    }

    /**
     * Formats a LocalDateTime object for storage.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted date-time string.
     */
    public static String formatForStorage(LocalDateTime dateTime) {
        return dateTime.format(STORAGE_FORMAT);
    }

    /**
     * Parses a date-time string from storage into a LocalDateTime object.
     *
     * @param storedDateTime The stored date-time string to parse.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException If the stored date-time string is invalid.
     */
    public static LocalDateTime parseFromStorage(String storedDateTime) throws DateTimeParseException {
        return LocalDateTime.parse(storedDateTime, STORAGE_FORMAT);
    }
}
