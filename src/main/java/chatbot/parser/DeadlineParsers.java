package chatbot.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

import chatbot.exception.InvalidDatetimeException;


/**
 * Utility class for parsing deadline strings into {@link java.time.LocalDateTime}.
 *
 * Supported formats:
 * - yyyy-MM-dd'T'HH:mm (ISO local datetime, e.g., 2019-12-11T23:59)
 * - yyyy-MM-dd HHmm (e.g., 2019-12-11 2359)
 * - dd-MM-uuuu HHmm (e.g., 11-12-2019 2359)
 * - yyyy-MM-dd (date-only, defaults to 23:59)
 * - dd-MM-uuuu (date-only, defaults to 23:59)
 *
 * Date-only inputs default to 23:59, meaning the deadline is set to the end of the day.
 */
public class DeadlineParsers {

    private static final DateTimeFormatter DMY_DATE =
            new DateTimeFormatterBuilder().parseCaseInsensitive()
                    .appendPattern("dd-MM-uuuu")
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter DMY_DATETIME =
            new DateTimeFormatterBuilder().parseCaseInsensitive()
                    .appendPattern("dd-MM-uuuu HHmm")
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter ISO_DT =
                 DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private static final DateTimeFormatter YMD_DATE =
            new DateTimeFormatterBuilder().parseCaseInsensitive()
                    .appendPattern("uuuu-MM-dd")
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter YMD_DATETIME =
            new DateTimeFormatterBuilder().parseCaseInsensitive()
                    .appendPattern("uuuu-MM-dd HHmm")
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);

    private DeadlineParsers() {}


    /**
     * Parses a raw deadline string into a {@link java.time.LocalDateTime}.
     *
     * <p>Supported formats:
     * - yyyy-MM-dd'T'HH:mm (ISO local datetime, e.g. 2019-12-11T23:59)
     * - yyyy-MM-dd HHmm (e.g. 2019-12-11 2359)
     * - dd-MM-uuuu HHmm (e.g. 11-12-2019 2359)
     * - yyyy-MM-dd (date-only, defaults to 23:59)
     * - dd-MM-uuuu (date-only, defaults to 23:59)
     *
     * @param raw raw input string to parse; may include extra spaces or '/' separators
     * @return the corresponding LocalDateTime
     * @throws IllegalArgumentException if the string does not match any supported format
     */
    public static LocalDateTime parseToDateTime(String raw) throws InvalidDatetimeException {
        String s = normalize(raw);

        List<DateTimeFormatter> order = List.of(
                ISO_DT, YMD_DATETIME, DMY_DATETIME, YMD_DATE, DMY_DATE
        );

        for (DateTimeFormatter fmt : order) {
            try {
                if (fmt == YMD_DATE || fmt == DMY_DATE) {
                    // Date-only -> default time to 23:59
                    LocalDate d = LocalDate.parse(s, fmt);
                    return d.atTime(23, 59);
                } else {
                    // Date + time
                    return LocalDateTime.parse(s, fmt);
                }
            } catch (DateTimeParseException ignored) {
                // Try next format
            }
        }

        throw new InvalidDatetimeException();
    }

    // Normalize separators to '-' and collapse spaces
    private static String normalize(String raw) {
        return raw.trim().replace('/', '-').replaceAll("\\s+", " ");
    }
}
