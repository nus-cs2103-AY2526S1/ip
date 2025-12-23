package george.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import george.exceptions.GeorgeException;

/**
 * Utility class for parsing date and time strings into LocalDateTime objects.
 * Supports multiple common datetime formats and provides flexible parsing.
 */
public class DateTimeParser {

    private static final List<DateTimeFormatter> TIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
    );

    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd")
    );

    /**
     * Parses a datetime string into a LocalDateTime object.
     * Tries multiple common formats until one succeeds.
     *
     * @param dateTimeString the input datetime string
     * @return LocalDateTime object representing the parsed datetime
     * @throws GeorgeException if the string cannot be parsed with any known format
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws GeorgeException {
        if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
            throw new GeorgeException("DateTime string cannot be null or empty");
        }
        String trimmedInput = dateTimeString.trim();
        for (DateTimeFormatter formatter : TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(trimmedInput, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(trimmedInput, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new GeorgeException(
                "Unable to parse datetime string: '" + dateTimeString
                        + "'. Supported formats include (yyyy/MM/dd), "
                        + "(yyyy/MM/dd hh:mm)."
        );
    }

    /**
     * Parses a date string in the format "MMM dd yyyy" (e.g., "Apr 25 2025") into a LocalDateTime object.
     * This is specifically for parsing dates that were stored in the file.
     *
     * @param dateString the input date string in "MMM dd yyyy" format
     * @return LocalDateTime object representing the parsed date at start of day
     * @throws GeorgeException if the string cannot be parsed with the expected format
     */
    public static LocalDateTime parseStoredDate(String dateString) throws GeorgeException {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new GeorgeException("Date string cannot be null or empty");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate date = LocalDate.parse(dateString.trim(), formatter);
            return date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new GeorgeException(
                    "Unable to parse stored date string: '" + dateString
                    + "'. Expected format: MMM dd yyyy (e.g., 'Apr 25 2025')"
            );
        }
    }

    /**
     * Parses a datetime string in the format "MMM dd yyyy HH:mm" (e.g., "Dec 05 2025 15:00")
     * into a LocalDateTime object. This is specifically for parsing datetime strings that
     * were stored in the file for events.
     *
     * @param dateTimeString the input datetime string in "MMM dd yyyy HH:mm" format
     * @return LocalDateTime object representing the parsed datetime
     * @throws GeorgeException if the string cannot be parsed with the expected format
     */
    public static LocalDateTime parseStoredDateTime(String dateTimeString) throws GeorgeException {
        if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
            throw new GeorgeException("DateTime string cannot be null or empty");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            return LocalDateTime.parse(dateTimeString.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new GeorgeException(
                    "Unable to parse stored datetime string: '" + dateTimeString
                            + "'. Expected format: MMM dd yyyy HH:mm (e.g., 'Dec 05 2025 15:00')"
            );
        }
    }
}
