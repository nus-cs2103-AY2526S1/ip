package jimbot.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jimbot.exceptions.InvalidDateTimeException;
import jimbot.exceptions.NoSuchTaskException;

public class Parser {

    /**
     * Parses a user input string into an int.
     * Throws InvalidIndexException if user input string is invalid.
     *
     * @param input User input string.
     * @param command Command string found in user input to split.
     * @param taskCount Current number of tasks in user's task list.
     * @throws NoSuchTaskException If index < 0 or >= taskCount.
     */
    public static int parseIndex(String input, String command, int taskCount) throws NoSuchTaskException {
        try {
            int index = Integer.parseInt(input
                    .substring(command.length())
                    .trim()) - 1;

            if (index < 0 || index >= taskCount) {
                throw new NoSuchTaskException();
            }

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
            if (input.contains(" ")) { // input has both date + time
                return LocalDateTime.parse(input, dtFormat);
            } else if (input.contains("/")) { // input only has date
                LocalDate date = LocalDate.parse(input, dateFormat);
                return date.atStartOfDay();
            } else { // input only has time
                LocalTime time = LocalTime.parse(input, timeFormat);

                return time.atDate(LocalDate.now());
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    public static LocalDate parseDate(String input) throws InvalidDateTimeException {
        input = input.trim();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
