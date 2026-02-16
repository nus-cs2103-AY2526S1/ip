package bestie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility helpers for parsing, formatting, and serializing dates and times in
 * a user-friendly yet consistent manner.
 */
public final class DateTimeUtil {
    private DateTimeUtil() {}

    // Accepted input formats (you can add more if you like)
    private static final DateTimeFormatter[] DATE_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ISO_LOCAL_DATE,             // yyyy-MM-dd
            DateTimeFormatter.ofPattern("d/M/yyyy"),      // 2/12/2019
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),    // 02/12/2019
            DateTimeFormatter.ofPattern("M/d/yyyy"),      // 12/2/2019
            DateTimeFormatter.ofPattern("MM/dd/yyyy")     // 12/02/2019
    };

    private static final DateTimeFormatter[] DATETIME_PATTERNS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), // 2019-12-02 1800
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),   // 2/12/2019 1800
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"), // 02/12/2019 1800
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),   // 12/2/2019 1800
            DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm")  // 12/02/2019 1800
    };

    // Pretty output formats
    private static final DateTimeFormatter PRETTY_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter PRETTY_DATETIME = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    // Canonical storage formats
    private static final DateTimeFormatter CANON_DATE = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd
    private static final DateTimeFormatter CANON_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Tries to parse the supplied text into a {@link LocalDate} using several
     * common formats.
     *
     * @param s user-provided date text
     * @return parsed date, or {@code null} if none of the patterns match
     */
    public static LocalDate parseDate(String s) {
        String x = s.trim();
        for (DateTimeFormatter f : DATE_PATTERNS) {
            try {
                return LocalDate.parse(x, f);
            } catch (DateTimeParseException ignored) {
                // Try the next pattern.
            }
        }
        return null;
    }

    /**
     * Tries to parse the supplied text into a {@link LocalDateTime} using
     * several common patterns.
     *
     * @param s user-provided date/time text
     * @return parsed date and time, or {@code null} if parsing fails
     */
    public static LocalDateTime parseDateTime(String s) {
        String x = s.trim();
        for (DateTimeFormatter f : DATETIME_PATTERNS) {
            try {
                return LocalDateTime.parse(x, f);
            } catch (DateTimeParseException ignored) {
                // Try the next pattern.
            }
        }
        return null;
    }

    /**
     * Formats the provided date for display to the user.
     *
     * @param d date to format
     * @return pretty-printed representation such as {@code Dec 2 2019}
     */
    public static String pretty(LocalDate d) {
        return d.format(PRETTY_DATE);
    }

    /**
     * Formats the provided date/time for display to the user.
     *
     * @param dt date/time to format
     * @return human-friendly representation including the time component
     */
    public static String pretty(LocalDateTime dt) {
        return dt.format(PRETTY_DATETIME);
    }

    /**
     * Converts the provided date into a canonical storage representation.
     *
     * @param d date to serialize
     * @return normalized string form (ISO-8601 date)
     */
    public static String canonical(LocalDate d) {
        return d.format(CANON_DATE);
    }

    /**
     * Converts the provided date/time into a canonical storage representation.
     *
     * @param dt date/time to serialize
     * @return normalized string form with 24-hour time
     */
    public static String canonical(LocalDateTime dt) {
        return dt.format(CANON_DATETIME);
    }
}
