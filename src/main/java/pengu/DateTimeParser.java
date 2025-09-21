package pengu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pengu.exception.InvalidFieldException;

/**
 * Class to help with parsing date time strings and re-outputting in various formats.
 */
public class DateTimeParser {
    public static final String INPUT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT);
    public static final String OUTPUT_DATE_TIME_FORMAT = "MMM dd yyyy HH:mm";
    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT);

    /**
     * Converts string to LocalDateTime object.
     *
     * @param str String in "yyyy-mm-dd HH:mm" format;
     * @return LocalDateTime object corresponding to string.
     * @throws InvalidFieldException If string isn't in correct format.
     */
    public static LocalDateTime fromDateTimeString(String str) throws InvalidFieldException {
        try {
            return LocalDateTime.parse(str, INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            String errorMessage = String.format(
                    "Expected: date time string in format %s (e.g. 2025-07-23 14:30)\n", INPUT_DATE_TIME_FORMAT)
                    + "Got: " + str;
            throw new InvalidFieldException(errorMessage);
        }
    }

    /**
     * Converts LocalDateTime object to string in "MMM dd yyyy HH:mm" format
     * (e.g. Oct 15 2025 12:30)
     *
     * @param dateTime LocalDateTime object
     * @return String in target format.
     */
    public static String toInputFormatString(LocalDateTime dateTime) {
        return dateTime.format(INPUT_DATE_TIME_FORMATTER);
    }

    /**
     * Converts LocalDateTime object to string in "yyyy-MM-dd HH:mm" format
     * (e.g. 2025-07-23 14:30)
     *
     * @param dateTime LocalDateTime object
     * @return String in target format.
     */
    public static String toOutputFormatString(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_DATE_TIME_FORMATTER);
    }
}
