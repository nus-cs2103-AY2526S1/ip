/**
 * Utility class for parsing and formatting dates/times
 * from user input into {@code LocalDateTime}.
 * Supports multiple input formats.
 */

package salah;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeParser {
    /**
     * Private constructor to prevent instantiation.
     */
    private DateTimeParser() {
        // empty
    }

    private static final DateTimeFormatter[] DATE_TIME_PATTERNS = new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("d/M/uuuu HHmm"),
        DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"),
        DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
    };
    private static final DateTimeFormatter[] DATE_ONLY_PATTERNS = new DateTimeFormatter[] {
        DateTimeFormatter.ISO_LOCAL_DATE,              
        DateTimeFormatter.ofPattern("d/M/uuuu")
    };

    /**
     * Parses a raw date/time string into a {@link LocalDateTime}.
     * <p>Supports multiple patterns such as:
     * <ul>
     *   <li>d/M/yyyy HHmm (e.g., 2/12/2019 1800)</li>
     *   <li>yyyy-MM-dd HH:mm (e.g., 2019-12-15 18:00)</li>
     *   <li>yyyy-MM-dd HHmm (e.g., 2019-12-15 1800)</li>
     *   <li>yyyy-MM-dd (date only, defaults to 00:00)</li>
     * </ul>
     *
     * @param raw the raw string input to parse
     * @return the parsed {@code LocalDateTime}
     * @throws IllegalArgumentException if the input does not match any supported format
     */
    public static LocalDateTime parseFlexible(String raw) {
        String s = raw.trim();

        // try datetime first
        for (DateTimeFormatter fmt : DATE_TIME_PATTERNS) {
            try { 
                return LocalDateTime.parse(s, fmt); 
            } catch (DateTimeParseException ignored) {
                // Expected: move on to next formatter
            }
        }
        // then date-only → atStartOfDay
        for (DateTimeFormatter fmt : DATE_ONLY_PATTERNS) {
            try { return LocalDate.parse(s, fmt).atStartOfDay(); 
            } catch (DateTimeParseException ignored) {
                // Expected: move on to next formatter
            }
        }
        // last resort: fail clearly
        throw new IllegalArgumentException("Unrecognized date/time format: \"" + raw + "\"");
    }

    /**
     * Formats a {@link LocalDateTime} into a human-readable string.
     * If the time is midnight, only the date is shown.
     *
     * @param dt the {@code LocalDateTime} to format
     * @return formatted string in the form "MMM dd yyyy" or "MMM dd yyyy, h:mm a"
     */
    public static String formatPretty(LocalDateTime dt) {
        if (dt.toLocalTime().equals(LocalTime.MIDNIGHT)) {
            return dt.format(DateTimeFormatter.ofPattern("MMM dd uuuu"));
        }
        return dt.format(DateTimeFormatter.ofPattern("MMM dd uuuu, h:mm a"));
    }
}
