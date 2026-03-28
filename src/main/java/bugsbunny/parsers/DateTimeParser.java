package bugsbunny.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bugsbunny.exception.BugsBunnyException;

/**
 * Helpers for parsing/formatting date-time strings used by the chatbot.
 */
public class DateTimeParser {
    /**
     * Date/time formatter to convert input String to LocalDateTime.
     * Expected input pattern for user-typed date/time, e.g., {@code 2025-08-30 1300}.
     */
    public static final DateTimeFormatter INPUT_TO_DATE_TIME =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Date time formatter to convert LocalDateTime to pretty output string and vice versa.
     * Used when printing date/times to the console, and saving to the hard disk.
     */
    public static final DateTimeFormatter DATE_TIME_STRING_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy, EEE h.mma"); // Aug 30 2025, Sat 1.00pm

    /**
     * Parses a string into a {@link java.time.LocalDateTime} using the given formatter.
     *
     * @param s The input string (trimmed by {@link bugsbunny.parsers.Parser#parse(String)}).
     * @param formatter The formatter to use.
     * @return Parsed date-time.
     * @throws IllegalArgumentException If the input does not match the formatter.
     */
    public static LocalDateTime parseStringToDateTime(String s, DateTimeFormatter formatter) throws BugsBunnyException {
        s = s.trim();

        try {
            return LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new BugsBunnyException("Unrecognized date time format: " + s
                    + "\nAccepted date time example: 2025-08-30 1300");
        }
    }
}
