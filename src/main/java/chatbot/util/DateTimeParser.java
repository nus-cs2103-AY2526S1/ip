package chatbot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exception.BotException;
import chatbot.exception.InvalidArgumentException;

/**
 * DateTimeParser helps parse datetime.
 */
public class DateTimeParser {
    /**
     * Array of supported datetime formats for user input.
     */
    private static final DateTimeFormatter[] SUPPORTED_DATETIME_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
    };

    /**
     * Returns the parsed format of datetime for tasks.
     *
     * @param dateTime Datetime in String.
     * @return LocalDateTime object.
     * @throws BotException If input format is not part of the array of supported datetime formats.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws BotException {
        for (DateTimeFormatter fmt : SUPPORTED_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(dateTime, fmt);
            } catch (DateTimeParseException e) {
                // can be ignored since we want to continue trying different formats
                // invalid inputs are thrown after this for loop
            }
        }
        throw new InvalidArgumentException("Invalid date/time format: " + dateTime + "\n");
    }

    /**
     * Combines date and time together into a single datetime instance.
     *
     * @param date Date following the supported datetime format.
     * @param time Time in HH:mm:ss.
     * @return Merged LocalDateTime object.
     * @throws BotException If input is invalid.
     */
    public static LocalDateTime combineDateAndTime(String date, String time) throws BotException {
        return parseDateTime(date + " " + time);
    }
}
