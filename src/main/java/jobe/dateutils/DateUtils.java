package jobe.dateutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jobe.exception.JobeException;

/**
 * Utility class for date manipulation functions
 */
public class DateUtils {
    
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("d/MM/yyyy HHmm");
    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("MMM d yyyy, HH:mm");
    
    /**
     * Transforms input date into a pattern specified by OUTPUT_DATE_TIME_FORMATTER.
     *
     * @param input Date to be formatted.
     * @return Date formatted to pattern OUTPUT_DATE_TIME_FORMATTER.
     * @throws JobeException If date cant be parsed due to incorrect input format.
     */
    public static String convertToDateTime(String input) throws JobeException {
        try {
            LocalDateTime date = LocalDateTime.parse(input.trim(), INPUT_DATE_TIME_FORMATTER);
            return date.format(OUTPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JobeException("OOPS!!!! Failed to parse DateTime, please format as dd/mm/yyyy hhmm.");
        }
    }
    
    /**
     * Checks whether the start date of an event task is before the end date.
     *
     * @param startDate Start date of an event task.
     * @param endDate End date of an event task.
     * @return A boolean representing whether the start date is before the end date.
     */
    public static boolean isStartDateBeforeEndDate(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate.trim(), INPUT_DATE_TIME_FORMATTER);
        LocalDateTime end = LocalDateTime.parse(endDate.trim(), INPUT_DATE_TIME_FORMATTER);
        return start.isBefore(end);
    }
}
