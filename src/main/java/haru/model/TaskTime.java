package haru.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import haru.exception.HaruException;
import haru.exception.InvalidTimeException;

/**
 * Represents a task time with parsing and formatting.
 */
public class TaskTime implements Serializable {
    private static final List<DateTimeFormatter> FORMATS = List.of(
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    );

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.ENGLISH);

    private LocalDateTime time;

    /**
     * Constructs a TaskTime by parsing a string with supported formats.
     *
     * @param alias   the argument name
     * @param strTime the string representation of time
     * @throws HaruException if parsing fails
     */
    public TaskTime(String alias, String strTime) throws HaruException {
        for (DateTimeFormatter fmt : FORMATS) {
            try {
                this.time = LocalDateTime.parse(strTime, fmt);
                return;
            } catch (DateTimeParseException e) {
                // continue and check the next format
            }
        }
        throw new InvalidTimeException(alias);
    }

    /**
     * Returns the string representation of the time.
     *
     * @return the formatted time string
     */
    @Override
    public String toString() {
        return this.time.format(OUTPUT_FORMAT);
    }
}
