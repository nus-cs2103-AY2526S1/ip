package stewie.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import stewie.command.CommandType;
import stewie.exceptions.InvalidCommandException;

/**
 * Utility class providing helper methods for parsing and validation.
 */
public class Helper {
    private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";
    private static final String DATE_ONLY_PATTERN = "dd/MM/yyyy";
    private static final String DISPLAY_DATE_TIME_PATTERN = "dd MMM yyyy HH:mm";
    private static final String DISPLAY_DATE_ONLY_PATTERN = "dd MMM yyyy";
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final DateTimeFormatter INPUT_DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_ONLY_PATTERN);
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DISPLAY_DATE_TIME_PATTERN, Locale.ENGLISH);
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DISPLAY_DATE_ONLY_PATTERN, Locale.ENGLISH);
    private static final DateTimeFormatter FILE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final LocalTime MIDNIGHT = LocalTime.MIDNIGHT;

    /**
     * Parses a string argument to an integer index.
     *
     * @param args The string argument to parse.
     * @param usage The usage message to display if parsing fails.
     * @return The parsed integer index.
     * @throws InvalidCommandException If the argument is null, blank, or not a valid integer.
     */
    public static int parseIndexOrThrow(String args, String usage) throws InvalidCommandException {
        if (args == null || args.isBlank()) {
            throw new InvalidCommandException(usage);
        }

        try {
            return Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(usage);
        }
    }

    /**
     * Parses a string input to a CommandType enum value.
     *
     * @param input The string input to parse.
     * @return The corresponding CommandType, or UNKNOWN if the input is not recognized.
     */
    public static CommandType parseCommandType(String input) {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Parses a date-time string in various formats to LocalDateTime.
     *
     * @param dateTimeString The date-time string to parse.
     * @return LocalDateTime object if parsing succeeds, null otherwise.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        assert dateTimeString != null : "Date Time should not be null";
        LocalDateTime dateTime = tryParseDateTime(dateTimeString);
        if (dateTime != null) {
            return dateTime;
        }

        return tryParseDateOnly(dateTimeString);
    }

    /**
     * Converts a LocalDateTime to a human-readable string format.
     *
     * @param dateTime The LocalDateTime to convert.
     * @return Formatted date-time string for display.
     */
    public static String dateTimeToString(LocalDateTime dateTime) {
        assert dateTime != null : "Date Time should not be null";
        if (isStartOfDay(dateTime)) {
            return formatDateOnly(dateTime);
        } else {
            return formatDateTime(dateTime);
        }
    }

    /**
     * Converts a LocalDateTime to file storage format.
     *
     * @param dateTime The LocalDateTime to convert.
     * @return Formatted date-time string for file storage.
     */
    public static String dateTimeToFileFormat(LocalDateTime dateTime) {
        assert dateTime != null : "Date Time should not be null";
        return dateTime.format(FILE_FORMATTER);
    }

    private static LocalDateTime tryParseDateTime(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static LocalDateTime tryParseDateOnly(String dateTimeString) {
        try {
            return LocalDate.parse(dateTimeString, INPUT_DATE_FORMATTER).atStartOfDay();
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static boolean isStartOfDay(LocalDateTime dateTime) {
        return dateTime.toLocalTime().equals(MIDNIGHT);
    }

    private static String formatDateOnly(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_DATE_FORMATTER);
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_DATE_TIME_FORMATTER);
    }
}
