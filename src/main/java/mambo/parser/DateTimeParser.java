package mambo.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a parser used to handle all operations which involve parsing out a LocalDateTime
 * from a given input
 *
 * @author kentalim2
 */
public class DateTimeParser {
    // All the potential date formats which can be recognized by the parser
    private static final DateTimeFormatter[] DATE_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yy"),
            DateTimeFormatter.ofPattern("dd-MM-yy"),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd MMM yyyy").toFormatter(),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMM dd yyyy").toFormatter(),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("MMMM dd yyyy").toFormatter(),
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd MMMM yyyy").toFormatter(),
            DateTimeFormatter.ofPattern("dd/M/yy"),
            DateTimeFormatter.ofPattern("M/dd/yy"),
            DateTimeFormatter.ofPattern("yyyy-M-dd"),
            DateTimeFormatter.ofPattern("dd/M/yyyy"),
            DateTimeFormatter.ofPattern("M/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd-M-yyyy"),
            DateTimeFormatter.ofPattern("M-dd-yyyy"),
            DateTimeFormatter.ofPattern("M-dd-yy"),
            DateTimeFormatter.ofPattern("dd-M-yy")
    };

    // All the potential time formats which can be recognized by the parser
    private static final DateTimeFormatter[] TIME_FORMATS = {
            DateTimeFormatter.ofPattern("HHmm"),
            DateTimeFormatter.ofPattern("HH:mm"),
            DateTimeFormatter.ofPattern("hh:mm a"),
            DateTimeFormatter.ofPattern("h:mm a"),
            DateTimeFormatter.ofPattern("ha")
    };

    // Format for chatbot to display date and time
    private static final DateTimeFormatter DISPLAY_DATETIME = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    // Used for pattern matching to spot if input has "date time" pattern
    private static final Pattern DATETIME_PATTERN =
        Pattern.compile("^(.*?)\\s+"
                        + "(\\d{3,4}"
                        + "|"
                        + "\\d{1,2}:\\d{2}"
                        + "|"
                        + "\\d{1,2}:\\d{2}\\s*[ap]m"
                        + "|"
                        + "\\d{1,2}\\s*[ap]m)$",
                Pattern.CASE_INSENSITIVE);

    /**
     * Returns a LocalDate object if the given input string contains a date format that is recognized
     * by the system.
     * Throws an exception otherwise.
     *
     * @param input Input to parse date out of
     * @return LocalDate object with the respective date in input
     * @throws DateTimeParseException Thrown when no date format is recognized in given input
     */
    public static LocalDate parseDate(String input) throws DateTimeParseException {
        for (DateTimeFormatter format : DATE_FORMATS) {
            try {
                return LocalDate.parse(input, format);

            } catch (DateTimeParseException e) {
                continue;
            }

        }
        throw new DateTimeParseException("no date", input, 0);
    }

    /**
     * Returns a LocalTime object if the given input string contains a time format that is recognized
     * by the system.
     * Throws an exception otherwise.
     *
     * @param input Input to parse time out of
     * @return LocalTime object with the respective time in input
     * @throws DateTimeParseException Thrown when no time format is recognized in given input
     */
    public static LocalTime parseTime(String input) throws DateTimeParseException {
        for (DateTimeFormatter format : TIME_FORMATS) {
            try {
                return LocalTime.parse(input, format);

            } catch (DateTimeParseException e) {
                continue;
            }

        }
        throw new DateTimeParseException("no time", input, 0);
    }

    /**
     * Returns a LocalDateTime object if the given input string contains a date time format that is recognized
     * by the system.
     * Splits the input string into the date and time portions if it follows a "date time" format.
     * If input does not have both a date and a time, function will try to parse out just a date
     * from input.
     * If input contains a date but no time, it will set the time to 2359 and return a
     * LocalDateTime with the given date and time as 2359.
     * Throws an exception otherwise.
     *
     * @param input Input to parse date time out of
     * @return LocalDate object with the respective date time in input
     * @throws DateTimeParseException Thrown when no date time format is recognized in given input
     */
    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        try {
            Matcher matcher = DATETIME_PATTERN.matcher(input.trim());

            // if input matches format, split into date and time inputs and parse through each to get date/time
            if (matcher.matches()) {
                String date = matcher.group(1).trim();
                String time = matcher.group(2).trim();
                LocalDate localDate = parseDate(date);
                LocalTime localTime = parseTime(time);
                return LocalDateTime.of(localDate, localTime);
            }

            LocalDate date = parseDate(input);
            return LocalDateTime.of(date, LocalTime.of(23, 59));

        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("no valid date or time", input, 0);
        }
    }

    /**
     * Returns string representation of date and time which follows the format predeclared by
     * chatbots system.
     *
     * @param dateTime LocalDateTime object with given date and time
     * @return String representation of date and time following preferred format
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_DATETIME);
    }
}
