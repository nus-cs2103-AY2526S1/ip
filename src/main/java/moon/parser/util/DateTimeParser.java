package moon.parser.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import moon.models.MoonDateTime;
import moon.parser.exceptions.DateTimeException;
import moon.parser.exceptions.ParseException;

/**
 * Utility class for parsing date and time strings into {@link MoonDateTime} objects.
 * <p>
 * Supports two contexts:
 * <ul>
 *   <li><b>User input</b>: expects {@code dd/MM/yyyy HHmm} or {@code dd/MM/yyyy}</li>
 *   <li><b>Storage</b>: expects {@code MMM d yyyy HH:mm} or {@code MMM d yyyy}</li>
 * </ul>
 * If both date-time and date-only parsing fail, a {@link ParseException} is thrown.
 */
public class DateTimeParser {
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATETIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter MOONDATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter MOONDATETIME = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Parses a string into a {@link MoonDateTime}.
     * <p>
     * Tries date-time first, then falls back to date-only parsing.
     *
     * @param input        the string to parse
     * @param isFromStorage true if the input string comes from storage (already formatted),
     *                      false if the input comes directly from user input
     * @return a {@link MoonDateTime} representing the parsed date or date-time
     * @throws DateTimeException if the input cannot be parsed into either a date or a date-time
     */
    public static MoonDateTime parse(String input, boolean isFromStorage) throws DateTimeException {
        return tryParseDateTime(input, isFromStorage)
                .map(dt -> MoonDateTime.of(dt.toLocalDate(), dt.toLocalTime()))
                .or(() -> tryParseDate(input, isFromStorage).map(MoonDateTime::ofDate))
                .orElseThrow(() -> new DateTimeException("Could not parse date/time: " + input));
    }

    /**
     * Attempts to parse a full date-time string into a {@link LocalDateTime}.
     *
     * @param s             the string to parse
     * @param isFromStorage true if the input string comes from storage, false if from user input
     * @return an {@link Optional} containing the parsed {@link LocalDateTime}, or empty if parsing fails
     */
    private static Optional<LocalDateTime> tryParseDateTime(String s, boolean isFromStorage) {
        DateTimeFormatter format = isFromStorage ? MOONDATETIME : DATETIME;
        try {
            LocalDateTime dt = LocalDateTime.parse(s, format);
            return Optional.of(dt);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Attempts to parse a date-only string into a {@link LocalDate}.
     *
     * @param s             the string to parse
     * @param isFromStorage true if the input string comes from storage, false if from user input
     * @return an {@link Optional} containing the parsed {@link LocalDate}, or empty if parsing fails
     */
    private static Optional<LocalDate> tryParseDate(String s, boolean isFromStorage) {
        DateTimeFormatter format = isFromStorage ? MOONDATE : DATE;
        try {
            LocalDate d = LocalDate.parse(s, format);
            return Optional.of(d);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
