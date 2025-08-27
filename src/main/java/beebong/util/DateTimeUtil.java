package beebong.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import beebong.exception.InvalidDateException;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_FORMATTER_PARSER = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_PARSER = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
    private static final DateTimeFormatter DATE_FORMATTER_STRING = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_STRING = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER_SERIALIZE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_SERIALIZE = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Updated methods to be more flexible with Date and Time
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
