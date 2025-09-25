package heimerdinger.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import heimerdinger.HeimerdingerException;


/**
 * Utility class for parsing, formatting, and comparing date/time values.
 *
 * <p>Supports two input formats:</p>
 * <ul>
 *   <li><b>Date only:</b> {@code yyyy-MM-dd}</li>
 *   <li><b>Date + time:</b> {@code yyyy-MM-dd HH:mm}</li>
 * </ul>
 *
 * <p>Internally stores the value as a {@link LocalDateTime} with the formatter
 * that was used to parse it. Provides both user-facing display formatting
 * and machine-friendly encoding.</p>
 *
 * <p>Examples:</p>
 * <pre>{@code
 * DateTimeParser d1 = DateTimeParser.parse("2025-10-31");
 * DateTimeParser d2 = DateTimeParser.parse("2025-10-31 18:00");
 * }</pre>
 */
public class DateTimeParser {

    private static final DateTimeFormatter DATE_ONLY =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_HH_MM =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final LocalDateTime date;
    private final DateTimeFormatter format;

    /**
     * Constructs a {@code DateTimeParser} with a parsed date and format.
     *
     * @param date   the parsed {@link LocalDateTime}
     * @param format the formatter originally used to parse the date
     */
    public DateTimeParser(LocalDateTime date, DateTimeFormatter format) {
        this.date = date;
        this.format = format;
    }

    /**
     * Parses a string into a {@code DateTimeParser}.
     *
     * <p>Accepted formats are:</p>
     * <ul>
     *   <li>{@code yyyy-MM-dd}</li>
     *   <li>{@code yyyy-MM-dd HH:mm}</li>
     * </ul>
     *
     * @param input the date string to parse
     * @return a {@code DateTimeParser} wrapping the parsed date
     * @throws HeimerdingerException if the input cannot be parsed into either format
     */
    public static DateTimeParser parse(String input) throws HeimerdingerException {
        if (isDateOnly(input)) {
            return new DateTimeParser(LocalDate.parse(input, DATE_ONLY).atStartOfDay(), DATE_ONLY);
        }

        if (isDateWithTime(input)) {
            return new DateTimeParser(LocalDateTime.parse(input, DATE_HH_MM), DATE_HH_MM);
        }

        throw new HeimerdingerException("Would you like some assistance in learning how to write dates? "
                + "(It's in the 'yyyy-mm-dd'/'yyyy-mm-dd hh:mm' format!)");
    }

    private static boolean isDateOnly(String input) {
        try {
            LocalDate.parse(input, DATE_ONLY);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isDateWithTime(String input) {
        try {
            LocalDateTime.parse(input, DATE_HH_MM);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns the underlying {@link LocalDateTime}.
     *
     * @return the stored date/time
     */
    public LocalDateTime getLocalDateTime() {
        return this.date;
    }

    public DateTimeFormatter getFormat() {
        return this.format;
    }

    /**
     * Checks whether this date is strictly before another.
     *
     * @param d the other {@code DateTimeParser} to compare against
     * @return {@code true} if this date is before the other; {@code false} otherwise
     */
    public boolean isBefore(DateTimeParser d) {
        if (this.date.isBefore(d.date)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a human-readable string for display to the user.
     *
     * <p>If stored as a date only, shows {@code "MMM d yyyy"} (e.g., {@code Oct 31 2025}).<br>
     * If stored as a date with time, shows {@code "MMM d yyyy, HH:mm hrs"}.</p>
     *
     * @return a formatted string suitable for UI display
     */
    public String toString() {
        if (this.format.equals(DATE_ONLY)) {
            DateTimeFormatter displayDate = DateTimeFormatter.ofPattern("MMM d yyyy");
            return date.format(displayDate);
        } else {
            DateTimeFormatter displayDateTime = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
            return date.format(displayDateTime) + " hrs";
        }
    }

    /**
     * Returns a machine-friendly encoded string, suitable for storage in files.
     *
     * <p>If stored as a date only, encodes as {@code yyyy-MM-dd}.<br>
     * If stored as a date with time, encodes as {@code yyyy-MM-dd HH:mm}.</p>
     *
     * @return an encoded string suitable for persistence
     */
    public String encode() {
        if (this.format.equals(DATE_ONLY)) {
            DateTimeFormatter displayDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(displayDate);
        } else {
            DateTimeFormatter displayDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return date.format(displayDateTime);
        }
    }

    /**
     * Returns just the date portion (without time).
     *
     * @return a {@link LocalDate} representing only the date part.
     */
    public LocalDate toLocalDate() {
        return this.date.toLocalDate();
    }
}
