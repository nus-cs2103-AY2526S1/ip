package lynx.formatter;

import lynx.exception.LynxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Class for parsing date / time representations
public class LynxDateManager {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
    private static final DateTimeFormatter TEXT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public static LocalDateTime parseDateTime(String input) throws LynxException {
        try {
            if (input.matches("\\d{4}-\\d{2}-\\d{2}-\\d{2}-\\d{2}")) {
                // Full date-time
                return LocalDateTime.parse(input, DEFAULT_FORMAT);
            } else if (input.matches("\\d{4}-\\d{2}-\\d{2}-\\d{2}")) {
                // Date with hours, set seconds to 00
                return LocalDateTime.parse(input + "-00", DEFAULT_FORMAT);
            }else if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {
                // Date only, set time to 00:00
                return LocalDateTime.parse(input + "-00-00", DEFAULT_FORMAT);
            } else {
                throw new LynxException("Invalid date format. Please use yyyy-MM-dd-HH-mm.");
            }
        } catch (DateTimeParseException e) {
            throw new LynxException("Invalid date. Please retry using yyyy-MM-dd-HH-mm.");
        }
    }

    public static String defaultDateTime(LocalDateTime dateTime) {
        return dateTime.format(DEFAULT_FORMAT);
    }

    public static String textDateTime(LocalDateTime dateTime) {
        return dateTime.format(TEXT_FORMAT);
    }
}