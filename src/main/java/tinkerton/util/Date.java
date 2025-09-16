package tinkerton.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import tinkerton.core.TinkertonException;

/**
 * Represents a date and time, providing parsing and formatted output. Accepts input in "yyyy-MM-dd
 * HHmm" format and outputs as "dd MMMM yyyy HH:mm".
 */
public class Date {
    /** Formatter for parsing input date strings. */
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /** Formatter for outputting formatted date strings. */
    private final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
    /** The formatted output string. */
    private String output;
    /** The parsed LocalDateTime object. */
    private LocalDateTime afterFormat;

    /**
     * Constructs a Date object from the given input string.
     *
     * @param input The input date string in "yyyy-MM-dd HHmm" format.
     * @throws TinkertonException If the input string is not a valid date.
     */
    public Date(String input) throws TinkertonException {
        try {
            this.afterFormat = LocalDateTime.parse(input, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new TinkertonException("Are you sure this is a valid date?");
        }

        this.output = afterFormat.format(outputFormatter);
    }

    /**
     * Returns the formatted string representation of the date.
     *
     * @return The formatted date string.
     */
    @Override
    public String toString() {
        return output;
    }

    /**
     * Returns the parsed LocalDateTime object.
     *
     * @return The LocalDateTime representation of the date.
     */
    public LocalDateTime date() {
        return afterFormat;
    }
}
