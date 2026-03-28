package clippy.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import clippy.ClippyException;

/**
 * Represents a date or date-time used in tasks.
 */
public class DateTime {
    private static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM d yyyy");

    private final LocalDate date;
    private LocalDateTime dateTime; // save for future use

    /**
     * Constructs a DateTime object from the given input string.
     * The input can be in the format "YYYY-MM-DD HHMM" for date and time,
     * or "YYYY-MM-DD" for date only.
     *
     * @param input The input string representing the date and time.
     * @throws ClippyException If the input format is invalid.
     */
    public DateTime(String input) throws ClippyException {
        try {
            this.date = LocalDate.parse(input, INPUT);
        } catch (DateTimeParseException e) {
            throw new ClippyException("Invalid date format. Please use YYYY-MM-DD (e.g. 2025-10-15).");
        }
    }

    /**
     * Returns the date in a format suitable for storage.
     *
     * @return The formatted date string.
     */
    public String toStorageString() {
        return date.format(INPUT);
    }

    @Override
    public String toString() {
        return date.format(OUTPUT);
    }


}
