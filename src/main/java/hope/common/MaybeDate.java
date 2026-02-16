package hope.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.List;

/**
 * A utility class for parsing and representing a date or a description string.
 * The {@code MaybeDate} class attempts to parse an input string as a date using multiple supported
 * date formats. If parsing succeeds, it stores the date as a {@link Temporal} object; otherwise, it
 * stores the input as a description string. The class supports formatting dates for output and
 * provides a string representation of either the parsed date or the description.
 */
public class MaybeDate {

    /** A list of supported date formats for parsing input strings. */
    private static final List<DateTimeFormatter> SUPPORTED_FORMATS = List.of(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("M/dd/yyyy"),
            DateTimeFormatter.ofPattern("MM/d/yyyy"),
            DateTimeFormatter.ofPattern("M/d/yyyy")
    );

    /** The formatter for outputting dates in the format "MMM dd yyyy". */
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /** The formatter for outputting date-times in the format "MMM dd yyyy HHmm". */
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /** The description string, used if the input cannot be parsed as a date. */
    private String description;

    /** The parsed date, represented as a {@link Temporal} object, or null if parsing fails. */
    private Temporal date;

    /**
     * Constructs a {@code MaybeDate} instance with the specified date and description.
     *
     * @param date        the parsed date as a {@link Temporal} object, or null if no date is parsed
     * @param description the description string, used if no date is parsed
     */
    private MaybeDate(Temporal date, String description) {
        this.date = date;
        this.description = description;
    }

    /**
     * Parses an input string to create a {@code MaybeDate} instance.
     * Attempts to parse the input as a date using supported formats (e.g., "yyyy-MM-dd", "MM/dd/yyyy")
     * with optional time components ("HH:mm" or "HHmm"). If parsing succeeds, a {@code MaybeDate}
     * with the parsed {@link LocalDateTime} is returned. If parsing fails, a {@code MaybeDate} with
     * the input as a description is returned.
     *
     * @param input the input string to parse as a date or store as a description
     * @return a {@code MaybeDate} instance containing either a parsed date or the input description
     */
    @SuppressWarnings("checkstyle:EmptyCatchBlock")
    public static MaybeDate parse(String input) {
        LocalDateTime temp = null;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        for (DateTimeFormatter format : SUPPORTED_FORMATS) {
            dateTimeFormatterBuilder.appendOptional(format);
        }
        dateTimeFormatterBuilder.optionalStart()
                .appendOptional(DateTimeFormatter.ofPattern(" HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern(" HHmm"))
                .optionalEnd();
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, dateTimeFormatterBuilder.toFormatter());
            return new MaybeDate(dateTime, null);
        } catch (DateTimeParseException ignored) {
            try {
                LocalDate date = LocalDate.parse(input, dateTimeFormatterBuilder.toFormatter());
                return new MaybeDate(date, null);
            } catch (DateTimeParseException ignored2) {
                //purposefully empty
            }
        }

        return new MaybeDate(null, input);
    }

    /**
     * Returns a string representation of the {@code MaybeDate}.
     * If a date is present, it is formatted using {@link #OUTPUT_DATE_FORMAT} for {@link LocalDate}
     * or {@link #OUTPUT_DATE_TIME_FORMAT} for {@link LocalDateTime}. If no date is present, the
     * description string is returned.
     *
     * @return a formatted date string or the description
     */
    @Override
    public String toString() {
        if (date == null) {
            return description;
        } else {
            if (date instanceof LocalDate) {
                return OUTPUT_DATE_FORMAT.format(date);
            }
            if (date instanceof LocalDateTime) {
                return OUTPUT_DATE_TIME_FORMAT.format(date);
            }
        }
        return description;
    }
}
