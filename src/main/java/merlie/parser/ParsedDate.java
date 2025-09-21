package merlie.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import merlie.exception.MerlieException;

/**
 * Represents a parsed date and whether it contains a specific time.
 */
public class ParsedDate {
    private static final DateTimeFormatter[] DATE_ONLY_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy")
    };
    private static final DateTimeFormatter[] TIME_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };
    private static final String INVALID_DATE_FORMAT_ERROR = "invalid date format. "
            + "Use yyyy-MM-dd (HHmm), d-M-yyyy (HHmm), or d/M/yyyy (HHmm)";
    public final LocalDateTime dateTime;
    public final boolean hasTime;

    /**
     * Constructs a ParsedDate object.
     *
     * @param dateTime The parsed LocalDateTime value.
     * @param hasTime  True if the parsed input included a specific time, false if only a date.
     */
    public ParsedDate(LocalDateTime dateTime, boolean hasTime) {
        this.dateTime = dateTime;
        this.hasTime = hasTime;
    }

    /**
     * Parses a string into a ParsedDate.
     *
     * @param input Date string to parse.
     * @return ParsedDate object containing the date and hasTime flag.
     * @throws MerlieException If the string cannot be parsed as a valid date.
     */
    public static ParsedDate parseDate(String input) throws MerlieException {
        for (DateTimeFormatter format : TIME_FORMATS) {
            try {
                LocalDateTime dt = LocalDateTime.parse(input, format);
                return new ParsedDate(dt, true);
            } catch (DateTimeParseException e) {
                // ignore and try next format
            }
        }

        for (DateTimeFormatter format : DATE_ONLY_FORMATS) {
            try {
                LocalDate localDate = LocalDate.parse(input, format);
                return new ParsedDate(localDate.atStartOfDay(), false);
            } catch (DateTimeParseException e) {
                // ignore and try next format
            }
        }

        throw new MerlieException(INVALID_DATE_FORMAT_ERROR);
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public boolean getHasTime() {
        return hasTime;
    }
}

