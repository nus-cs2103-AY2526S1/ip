package beebong.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import beebong.exception.InvalidDateException;

/**
 * Utility class for parsing and formatting {@link LocalDateTime} values.
 */
public class DateTimeUtil {
    private static final DateTimeFormatter DATE_FORMATTER_PARSER = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_PARSER = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
    private static final DateTimeFormatter DATE_FORMATTER_STRING = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_STRING = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER_SERIALIZE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_SERIALIZE = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Parses a date-time string into a {@link LocalDateTime}.
     * <p>
     * This method first attempts to parse the input string as a full
     * date and time (e.g., {@code "25/08/2025 14:30"}). If that fails,
     * it will attempt to parse only the date (e.g., {@code "25/08/2025"})
     * and return a {@link LocalDateTime} at midnight ({@code 00:00}).
     * </p>
     *
     * @param dateStr the string representing a date or date-time.
     * @return the parsed {@link LocalDateTime}.
     * @throws InvalidDateException If dateStr cannot be parsed into either a date-time or a date.
     */
    public static LocalDateTime parseDateTime(String dateStr) throws InvalidDateException {
        // In order to make the method flexible we need to
        // try parsing it as a LocalDateTime first, if not try LocalDate
        try {
            return LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER_PARSER);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(dateStr, DATE_FORMATTER_PARSER).atStartOfDay(); // Default 00:00
            } catch (DateTimeParseException e2) {
                throw new InvalidDateException();
            }
        }
    }

    /**
     * Formats a {@link LocalDateTime} into a human-readable string.
     * <p>
     * - If the time is {@code 00:00}, only the date is included (e.g., {@code "25 Aug 2025"}).
     * - Otherwise, both date and time are included (e.g., {@code "25 Aug 2025 14:30"}).
     * </p>
     *
     * @param dateTime the {@link LocalDateTime} to format.
     * @return the formatted string representation.
     */
    public static String toString(LocalDateTime dateTime) {
        // Check if time is 00:00
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            // Only Date
            return dateTime.toLocalDate().format(DATE_FORMATTER_STRING);
        } else {
            // Date and Time
            return dateTime.format(DATE_TIME_FORMATTER_STRING);
        }
    }

    /**
     * Formats a {@link LocalDateTime} into a serialized string representation
     * to store in the chatbot's save file.
     * <p>
     * If the time is {@code 00:00}, only the date is included (format: {@code dd/MM/yyyy}).
     * Otherwise, both date and time are included (format: {@code dd/MM/yyyy HH:mm}).
     * </p>
     *
     * @param dateTime the {@link LocalDateTime} to format.
     * @return the serialized string representation.
     */
    public static String toSerializedString(LocalDateTime dateTime) {
        // Check if time is 00:00
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            // Only Date
            return dateTime.toLocalDate().format(DATE_FORMATTER_SERIALIZE);
        } else {
            // Date and Time
            return dateTime.format(DATE_TIME_FORMATTER_SERIALIZE);
        }
    }
}
