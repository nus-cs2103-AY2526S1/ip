package chash.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Utility class for parsing and formatting task dates. */
public class TaskDateParser {
    private static final DateTimeFormatter PRINT_FORMAT = 
        DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Attempts to parse a date-time string in ISO-8601 format.
     *
     * @param input Input string
     * @return Parsed {@code LocalDateTime}, or {@code null} if parsing fails
     */
    public static LocalDateTime tryParse(String input) {
        assert input != null;

        try {
            return LocalDateTime.parse(input);
        } catch (DateTimeParseException e) {
            //Allowed to swallow this error based on prog design
            //User can choose not to submit a ISO-8601 date string
            return null;
        }
    }

    /**
     * Formats a date-time into a human-readable string, or returns fallback text if null.
     *
     * @param dateTime Parsed {@code LocalDateTime}, may be {@code null}
     * @param dateStr Original raw date string
     * @return Formatted string
     */
    public static String format(LocalDateTime dateTime, String dateStr) {
        assert dateStr != null;

        return (dateTime != null) ? dateTime.format(TaskDateParser.PRINT_FORMAT) :
            dateStr;
    }
}
