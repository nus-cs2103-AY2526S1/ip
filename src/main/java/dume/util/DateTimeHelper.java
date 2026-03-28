package dume.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for converting raw date-time strings into
 * user-friendly formats.
 */
public class DateTimeHelper {
    private DateTimeHelper() {}

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Converts d/M/yyyy HHmm to MMM dd yyyy HH:mm.
     *
     * @param raw date-time string
     * @return formatted date-time string if succeeds, otherwise the original raw string
     */
    public static String convert(String raw) {
        if (raw == null || raw.isBlank()) {
            return raw;
        }
        try {
            LocalDateTime ldt = LocalDateTime.parse(raw.trim(), INPUT_FORMAT);
            return OUTPUT_FORMAT.format(ldt);
        } catch (DateTimeParseException e) {
            return raw;
        }
    }
}
