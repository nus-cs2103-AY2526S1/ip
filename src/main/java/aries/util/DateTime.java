package aries.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing and formatting date and time strings.
 */
public class DateTime {
    private static final String[] DATE_TIME_PATTERN = new String[] {
        "yyyy-MM-dd HHmm", "yyyy-MM-dd", "dd-MM-yyyy HHmm", "dd-MM-yyyy",
        "dd/MM/yyyy HHmm", "dd/MM/yyyy", "yyyy/MM/dd HHmm", "yyyy/MM/dd",
        "yyyy.MM.dd HHmm", "yyyy.MM.dd", "dd.MM.yyyy HHmm", "dd.MM.yyyy",
        "dd MMM yyyy HHmm", "dd MMM yyyy", "yyyy MMM dd HHmm", "yyyy MMM dd"
    };

    private DateTime() {}

    /**
     * Parses a date/time string into a LocalDateTime object.
     * Supports multiple formats including "yyyy-MM-dd HHmm", "dd/MM/yyyy", etc.
     * If the time component is missing, it defaults to 00:00 (start of the day).
     *
     * @param dateTimeStr the date/time string to parse
     * @return the corresponding LocalDateTime object
     * @throws DateTimeParseException if the input string does not match any supported format
     */
    public static LocalDateTime parse(String dateTimeStr) throws DateTimeParseException {
        for (String pattern : DATE_TIME_PATTERN) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                if (pattern.contains("HHmm")) {
                    return LocalDateTime.parse(dateTimeStr, formatter);
                } else {
                    LocalDate date = LocalDate.parse(dateTimeStr, formatter);
                    return date.atStartOfDay();
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new DateTimeParseException("Invalid date/time format: " + dateTimeStr, dateTimeStr, 0);
    }

    /**
     * Formats a LocalDateTime object into a string with the pattern "MMM d yyyy, h:mma".
     * Example: "Jan 1 2023, 5:30PM"
     *
     * @param dateTime the LocalDateTime object to format
     * @return the formatted date/time string
     */
    public static String format(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return dateTime.format(formatter).toUpperCase();
    }
}
