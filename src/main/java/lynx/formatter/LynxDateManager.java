package lynx.formatter;

import lynx.exception.LynxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Class for parsing date / time representations
public abstract class LynxDateManager {

    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
    private static final DateTimeFormatter TEXT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Creates a LocalDateTime object using a date / time represented as a string.
     * Argument must be of format "yyyy-MM-dd-HH-mm" or "yyyy-MM-dd-HH" or "yyyy-MM-dd".
     * Default value for hours and minutes is 0.
     *
     * @param input String representation of date / time.
     * @return LocalDateTime object.
     * @throws LynxException If argument format is invalid or date / time value is invalid.
     */
    public static LocalDateTime parseDateTime(String input) throws LynxException {
        try {
            if (input.matches("\\d{4}-\\d{2}-\\d{2}-\\d{2}-\\d{2}")) {
                // Full date-time
                return LocalDateTime.parse(input, DEFAULT_FORMAT);
            } else if (input.matches("\\d{4}-\\d{2}-\\d{2}-\\d{2}")) {
                // Date with hours, set seconds to 00
                return LocalDateTime.parse(input + "-00", DEFAULT_FORMAT);
            } else if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {
                // Date only, set time to 00:00
                return LocalDateTime.parse(input + "-00-00", DEFAULT_FORMAT);
            } else {
                throw new LynxException("Invalid date format. Please use yyyy-MM-dd-HH-mm.");
            }
        } catch (DateTimeParseException e) {
            throw new LynxException("Invalid date. Please retry using yyyy-MM-dd-HH-mm.");
        }
    }

    /**
     * Creates a string representation of a LocalDateTime object in the format "yyyy-MM-dd-HH-mm".
     *
     * @param dateTime LocalDateTime object.
     * @return String representation of date / time.
     */
    public static String defaultDateTime(LocalDateTime dateTime) {
        return dateTime.format(DEFAULT_FORMAT);
    }

    /**
     * Creates a string representation of a LocalDateTime object in the format "MMM d yyyy HH:mm".
     *
     * @param dateTime LocalDateTime object.
     * @return String representation of date / time.
     */
    public static String textDateTime(LocalDateTime dateTime) {
        return dateTime.format(TEXT_FORMAT);
    }

}