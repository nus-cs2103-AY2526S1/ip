package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import exception.RainyException;

/**
 * Utility class for parsing and formatting dates and times used in the Rainy task manager.
 */
public class DateTimeUtil {
    /** Standard format used for displaying dates and times to the user. */
    public static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.ENGLISH);

    private static final DateTimeFormatter[] INPUT_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };

    /**
     * Formats a {@link LocalDateTime} into a consistent display string
     * with lowercase am/pm to avoid environment differences.
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        String formatted = dateTime.format(DISPLAY_FORMAT);
        return formatted.replace("AM", "am").replace("PM", "pm");
    }

    /**
     * Parses a date-time string using the supported input formats.
     *
     * @param input the date-time string to parse
     * @return the parsed {@link LocalDateTime} object
     * @throws RainyException if the input string does not match any supported format
     */
    public static LocalDateTime parse(String input) throws RainyException {
        for (DateTimeFormatter formatter : INPUT_FORMATS) {
            try {
                return LocalDateTime.parse(input.trim(), formatter);
            } catch (Exception ignored) {
                // try next format
            }
        }
        throw new RainyException("Invalid date format. Please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.");
    }
}
