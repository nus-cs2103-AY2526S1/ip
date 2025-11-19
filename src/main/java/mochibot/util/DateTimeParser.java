package mochibot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mochibot.MochiBotException;

/**
 * The {@code DateTimeParser} class is responsible for handling the
 * format patterns for {@code LocalDateTime} objects.
 */
public class DateTimeParser {
    private static final DateTimeFormatter defaultFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

    /**
     * Parses the String input and outputs a {@code LocalDateTime} object
     * based on a specified format.
     *
     * @param input the date and time in string
     * @return a {@code LocalDateTime} object in the format of yyyy-MM-dd HH:mm
     */
    public static LocalDateTime parseInput(String input) throws MochiBotException {
        try {
            return LocalDateTime.parse(input, defaultFormat);
        } catch (DateTimeParseException e) {
            throw new MochiBotException.InvalidDateTimeException();
        }
    }

    /**
     * Parses a date-time string from stored task data into a {@link LocalDateTime} object
     * using the default format {@code yyyy-MM-dd HH:mm}.
     * <p>
     * This method is typically used when loading tasks from persistent storage, such as
     * text files, where date-time values were previously saved in the default format.
     * </p>
     *
     * @param taskDateTime the date-time string to parse, e.g. "2025-09-15 14:30"
     * @return a {@link LocalDateTime} object representing the parsed date and time
     * @throws java.time.format.DateTimeParseException if the input string does not match
     *         the expected format
     */
    public static LocalDateTime parseLoadTask(String taskDateTime) {
        return LocalDateTime.parse(taskDateTime, defaultFormat);
    }

    /**
     * Formats the given {@code LocalDateTime} object into a string
     * for storage in a text file using the pattern: {@code MMM-dd-yyyy HH:mm}.
     *
     * @param dateTime the {@link LocalDateTime} to format
     * @return a formatted date-time string suitable for storage
     */
    public static String formatDateTimeStorage(LocalDateTime dateTime) {
        return dateTime.format(defaultFormat);
    }

    /**
     * Formats the given {@link LocalDateTime} object into a string for display
     * using the pattern {@code MMM-dd-yyyy HH:mm}.
     *
     * @param dateTime the {@link LocalDateTime} to format
     * @return a formatted date-time string suitable for user display
     */
    public static String formatDateTimeDisplay(LocalDateTime dateTime) {
        return dateTime.format(displayFormat);
    }
}
