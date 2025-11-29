package larry.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Utilities for parsing and pretty-printing date/time strings.
 * Accepts ISO-like inputs (e.g., {@code yyyy-MM-dd}, {@code yyyy-MM-dd HH:mm/HHmm}).
 * Falls back to the original string if parsing fails.
 */
public final class DateTimeFormats {
    private DateTimeFormats() {}

    private static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter ISO_DATETIME_COLON = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter ISO_DATETIME_COMPACT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static final DateTimeFormatter OUT_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter OUT_DATETIME = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    /** Returns a prettier form of {@code raw} if supported; otherwise returns {@code raw}. */
    public static String pretty(String raw) {
        if (raw == null || raw.isBlank()) return raw;

        try {
            LocalDate d = LocalDate.parse(raw.trim(), ISO_DATE);
            return OUT_DATE.format(d);
        } catch (DateTimeParseException ignore) {
        }

        try {
            LocalDateTime dt = LocalDateTime.parse(raw.trim(), ISO_DATETIME_COLON);
            return OUT_DATETIME.format(dt);
        } catch (DateTimeParseException ignore) {
        }

        try {
            LocalDateTime dt = LocalDateTime.parse(raw.trim(), ISO_DATETIME_COMPACT);
            return OUT_DATETIME.format(dt);
        } catch (DateTimeParseException ignore) {
        }

        return raw;
    }
}
