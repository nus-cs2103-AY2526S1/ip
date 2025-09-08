package phuc.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import phuc.exception.PhucException;

/**
 * Utility class for parsing and formatting date-time strings.
 * Supports multiple input formats and provides consistent output formatting.
 */
public class DateTimeProcess {
    private static final DateTimeFormatter[] INPUT_FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d M yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"), DateTimeFormatter.ISO_LOCAL_DATE
    };

    private static final DateTimeFormatter OUTPUT_DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private static final DateTimeFormatter OUTPUT_FILE_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Parses a date-time string using multiple supported formats.
     *
     * @param dateTimeStr the date-time string to parse
     * @return the parsed LocalDateTime object
     * @throws PhucException if no supported format matches the input string
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws PhucException {
        assert dateTimeStr != null;
        String trimmedStr = dateTimeStr.trim();

        if (!trimmedStr.contains(" ")) {
            trimmedStr += " 0000";
        }

        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                return LocalDateTime.parse(trimmedStr, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new PhucException("Invalid date format: " + dateTimeStr);
    }

    /**
     * Formats a LocalDateTime object for display purposes.
     *
     * @param dateTime the date-time to format
     * @return a formatted string for display
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        assert dateTime != null;
        return dateTime.format(OUTPUT_DISPLAY_FORMAT);
    }

    /**
     * Formats a LocalDateTime object for file storage.
     *
     * @param dateTime the date-time to format
     * @return a formatted string for file storage
     */
    public static String formatForFile(LocalDateTime dateTime) {
        assert dateTime != null;
        return dateTime.format(OUTPUT_FILE_FORMAT);
    }
}
