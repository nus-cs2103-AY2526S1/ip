package jimbot.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jimbot.exception.InvalidDateTimeException;
import jimbot.exception.NoSuchTaskException;

/**
 * Utility class for parsing user input strings into indexes, dates, and times.
 *
 * @author limjimin-nus
 */
public class Parser {

    /**
     * Parses a user input string into an integer index.
     * Throws InvalidIndexException if user input string is invalid.
     *
     * @param input User input string.
     * @param command Command string found in user input to split.
     * @param taskCount Current number of tasks in user's task list.
     * @return Index parsed from the input (0-based).
     * @throws NoSuchTaskException If index invalid or out of bounds
     */
    public static int parseIndex(String input, String command, int taskCount) throws NoSuchTaskException {
        try {
            int index = Integer.parseInt(input
                    .substring(command.length())
                    .trim()) - 1;

            if (index < 0 || index >= taskCount) {
                throw new NoSuchTaskException();
            }

            assert index >= 0 && index < taskCount : "Index must be within valid task range";
            return index;
        } catch (NumberFormatException e) {
            throw new NoSuchTaskException();
        }
    }

    /**
     * Parses user input string into a LocalDateTime.
     * Throws invalidDateTimeException if user string format is invalid.
     *
     * @param input User input string in dd/MM/yyyy HHmm format.
     * @return Parsed LocalDate object.
     * @throws InvalidDateTimeException If in the wrong format.
     */
    public static LocalDateTime parseDateTime(String input) throws InvalidDateTimeException {
        input = input.trim();

        // If input contains both date and time
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        // If input only contains date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // If input only contains time
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");

        try {
            LocalDateTime result;
            if (input.contains(" ")) { // input has both date + time
                result = LocalDateTime.parse(input, dtFormat);
            } else if (input.contains("/")) { // input only has date
                LocalDate date = LocalDate.parse(input, dateFormat);
                result = date.atStartOfDay();
            } else { // input only has time
                LocalTime time = LocalTime.parse(input, timeFormat);
                result = time.atDate(LocalDate.now());
            }

            assert result != null : "Parsed LocalDateTime should never be null";
            return result;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Parses user input string into a LocalDate.
     * Throws invalidDateTimeException if user string format is invalid.
     *
     * @param input User input string in dd/MM/yyyy format.
     * @return Parsed LocalDate object.
     * @throws InvalidDateTimeException If in the wrong format.
     */
    public static LocalDate parseDate(String input) throws InvalidDateTimeException {
        input = input.trim();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate result = LocalDate.parse(input, formatter);

            assert result != null : "Parsed LocalDate should never be null";
            return result;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
