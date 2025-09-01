package aurora.util;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for parsing, formatting, and converting date and datetime.
 * <p>
 * Provides methods to read a date/datetime string in multiple formats
 * and to produce a readable representation of dates.
 */
public class DateUtil {
    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("ddMMyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    );

    /**
     * Converts the given input string into a {@link Temporal} object.
     *
     * @param input the date or datetime string
     * @return a {@link LocalDate} if the input has no time, or a {@link LocalDateTime} if it includes time
     * @throws IllegalArgumentException if the input does not match any supported format
     */
    public static Temporal readDate(String input) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e2) {
                try {
                    return LocalDate.parse(input, formatter);
                } catch (DateTimeParseException ignored) {}
            }
        }

        throw new IllegalArgumentException("Invalid date/datetime format " + input);
    }

    /**
     * Formats the given {@link TemporalAccessor} into a readable string.
     *
     * @param date the date or datetime to format
     * @return a human-readable string representation of the date/datetime
     */
    public static String prettierDate(TemporalAccessor date) {
        if (date instanceof LocalDateTime) {
            return DateTimeFormatter.ofPattern("MMM d yyyy HH:mm").format(date);
        } else {
            return DateTimeFormatter.ofPattern("MMM d yyyy").format(date);
        }
    }
}
