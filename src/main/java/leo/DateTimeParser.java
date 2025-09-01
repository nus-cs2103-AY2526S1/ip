package leo;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DateTimeParser class formats the date and time.
 */
public class DateTimeParser {
    /**
     * Return a string containing the date and time formatted to an easier to read format.
     *
     * @param dateTimeToFormat The date and time to be formatted.
     * @return Readable date and time text.
     * @throws DateTimeException If incorrect date and time format was given.
     */
    public String formatDateTimeFromInput(String dateTimeToFormat) throws DateTimeException {
        try {
            //Solution adapted from
            // https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeToFormat, formatter);

            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
            return dateTime.format(newFormat);
        } catch (DateTimeException e) {
            throw new DateTimeException(
                    "Incorrect date or time format for /from or /to..."
                            + "\n\tIt should be <dd/MM/yyyy HHmm>"
            );
        }
    }

    /**
     * Return a string containing the date and time formatted for file storage.
     *
     * @param dateTimeToFormat The date and time to be formatted.
     * @return Readable date and time text.
     * @throws DateTimeException If incorrect date and time format was given.
     */
    public String formatDateTimeFromFile(String dateTimeToFormat) throws DateTimeException {
        try {
            //Solution adapted from
            // https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeToFormat, formatter);

            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return dateTime.format(newFormat);
        } catch (DateTimeException e) {
            throw new DateTimeException(
                    "Incorrect date or time format for /from or /to..."
                            + "\n\tIt should be <dd/MM/yyyy HHmm>"
            );
        }
    }
}
