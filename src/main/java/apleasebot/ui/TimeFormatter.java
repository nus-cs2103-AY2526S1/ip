package apleasebot.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import apleasebot.exceptions.WrongTimeFormatException;

/**
 * Utility class for formatting and parsing date and time strings.
 * Provides methods to convert between {@link String} and {@link LocalDateTime}
 * using specific formats for input and display.
 */
public class TimeFormatter {
    public static final DateTimeFormatter DISPLAY_FORMAT_DATE = DateTimeFormatter.ofPattern("dd MMM yy");
    public static final DateTimeFormatter DISPLAY_FORMAT_TIME = DateTimeFormatter.ofPattern("dd MMM yy HH:mm a");
    private static final DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

    /**
     * Parses a date-time string to a {@link LocalDateTime} object.
     * If only the date is provided (length 10), assumes time as "00:00 am".
     *
     * @param in the input date-time string in the format "yyyy-MM-dd hh:mm a" or "yyyy-MM-dd"
     * @return the corresponding {@link LocalDateTime} object
     * @throws WrongTimeFormatException if the input string does not match the expected format
     */
    public static LocalDateTime getStandard(String in) {
        LocalDateTime out;

        try {
            if (in.length() == 10) {
                in += " 00:00 am";
            }
            out = LocalDateTime.parse(in, INPUT_TIME_FORMAT);
        } catch (Exception e) {
            throw new WrongTimeFormatException("Oops wrong date format! Try YYYY-MM-DD HH:MM am/pm");
        }
        return out;
    }

    /**
     * Formats a {@link LocalDateTime} object to a date string for display.
     *
     * @param dateTime the {@link LocalDateTime} to format
     * @return a formatted date string in the format "dd MMM yy"
     */
    public static String getDate(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMAT_DATE);
    }

    /**
     * Formats a {@link LocalDateTime} object to a date-time string for display.
     *
     * @param dateTime the {@link LocalDateTime} to format
     * @return a formatted date-time string in the format "dd MMM yy HH:mm a"
     */
    public static String getTime(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMAT_TIME);
    }

}
