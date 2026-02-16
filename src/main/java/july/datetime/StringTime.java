package july.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class for parsing and handling date/time strings in various formats.
 * This class attempts to parse input strings as either date-time or date-only formats
 * and provides methods for validation, comparison, and formatting of the parsed values.
 */
public class StringTime {
    private static final List<String> DATE_ONLY = Arrays.asList(
            "d/M/yyyy",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
            "dd-MM-yyyy"
    );
    private static final List<String> DATE_TIME = Arrays.asList(
            "dd/MM/yyyy HH:mm",
            "dd/MM/yyyy HH:mm",
            "yyyy-MM-dd HH:mm",
            "dd-MM-yyyy HH:mm"
    );
    private static final List<String> TIME_ONLY = Arrays.asList(
            "HH:mm",
            "H:mm"
    );

    private String input;
    private LocalDateTime dateTime = null;
    private boolean isTimeOnly = true;

    /**
     * Constructs a StringTime object by parsing the provided input string.
     * Attempts to parse the input as a date-time first, then as a date-only format
     * if the date-time parsing fails.
     *
     * @param s the input string to parse as a date/time
     */
    public StringTime(String s) {
        this.input = s;
        this.dateTime = parseTimeOnly(s);
    }

    /**
     * Attempts to parse the input string as a date-time using predefined patterns.
     * Tries each date-time format pattern until one succeeds or all fail.
     *
     * @param input the string to parse as a date-time
     * @return the parsed LocalDateTime object, or null if parsing fails
     */
    private LocalDateTime parse(String input) {
        for (String pattern : DATE_TIME) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException ignored) {
                // Parsing failed, continue to next pattern
            }
        }
        return null;
    }

    /**
     * Attempts to parse the input string as a date-only using predefined patterns.
     * Tries each date format pattern and converts successful parses to LocalDateTime
     * at the start of the day (00:00).
     *
     * @param input the string to parse as a date
     * @return the parsed LocalDateTime object at start of day, or null if parsing fails
     */
    private LocalDateTime parseDate(String input) {
        isTimeOnly = false;
        for (String pattern : DATE_ONLY) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                LocalDate date = LocalDate.parse(input, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException ignored) {
                // Parsing failed, continue to next pattern
            }
        }
        return parse(input);
    }

    /**
     * Attempts to parse the input string as a time-only using predefined patterns.
     * Tries each time format pattern and converts successful parses to LocalDateTime
     * using the current date.
     *
     * @param input the string to parse as a time
     * @return the parsed LocalDateTime object with current date and parsed time, or null if parsing fails
     */
    private LocalDateTime parseTimeOnly(String input) {
        for (String pattern : TIME_ONLY) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.of(LocalDate.now(),
                        java.time.LocalTime.parse(input, formatter));
            } catch (DateTimeParseException ignored) {
                // Parsing failed, continue to next pattern
            }
        }
        return parseDate(input);
    }

    /**
     * Checks the StringTime object is a string or can be parse as date/time
     *
     * @return true if the input remains as a string (parsing failed), false if successfully parsed
     */
    public boolean isString() {
        return this.dateTime == null;
    }

    /**
     * Compares this StringTime with another StringTime chronologically.
     *
     * @param other the other StringTime object to compare with
     * @return a negative integer, zero, or positive integer as this StringTime is
     *         before, equal to, or after the other StringTime
     */
    public int compareTo(StringTime other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    /**
     * Returns the original input string for saving purposes.
     *
     * @return the original unparsed input string
     */
    public String toSave() {
        return input;
    }

    /**
     * Returns a formatted string representation of this StringTime.
     * If successfully parsed, returns the date-time in appropriate format.
     * If parsing failed, returns the original input string.
     *
     * @return the formatted date-time string or original input if parsing failed
     */
    @Override
    public String toString() {
        if (this.dateTime != null) {
            if (isTimeOnly) {
                int hours = dateTime.getHour();
                int minutes = dateTime.getMinute();
                StringBuilder sb = new StringBuilder();
                if (hours > 0) {
                    sb.append(hours).append(hours == 1 ? " hour" : " hours");
                }
                if (minutes > 0) {
                    if (sb.length() > 0) sb.append(" ");
                    sb.append(minutes).append(minutes == 1 ? " minute" : " minutes");
                }
                if (sb.length() == 0) {
                    sb.append("0 minutes");
                }
                return sb.toString();
            }
            return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"));
        }
        return input;
    }

    /**
     * Returns true if the input was parsed as time-only.
     *
     * @return true if time-only, false otherwise
     */
    public boolean isTimeOnly() {
        return isTimeOnly;
    }

}
