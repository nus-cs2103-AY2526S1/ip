package reboot.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import reboot.RebootException;
import reboot.command.ClearCommand;
import reboot.command.Command;
import reboot.command.Commands;
import reboot.command.ErrorCommand;

/**
 * Represents a parser that will make sense of user input.
 */
public class Parser {

    /**
     * Returns a command for the chatbot to complete from specified input.
     * If unable to understand input, throw IllegalArgumentException.
     * @param input Input of user to be converted to chatbot command.
     */
    public static Command parse(String input) throws RebootException {
        try {
            String[] words = input.split(" ", 2);
            Commands command = Commands.valueOf(words[0].toUpperCase());

            switch (command) {
            case BYE:
                return ByeParser.parse();
            case LIST:
                return ListParser.parse();
            case MARK:
                return MarkParser.parse(words);
            case UNMARK:
                return UnmarkParser.parse(words);
            case TODO:
                return TodoParser.parse(words);
            case DEADLINE:
                return DeadlineParser.parse(words);
            case EVENT:
                return EventParser.parse(words);
            case DELETE:
                return DeleteParser.parse(words);
            case CLEAR:
                return new ClearCommand();
            case FIND:
                return FindParser.parse(words);
            default:
                return new ErrorCommand("Invalid input, check the user guide for proper inputs.");
            }
        } catch (DateTimeParseException e) {
            return new ErrorCommand("Date Time format: dd mm yyyy HHmm");
        } catch (RebootException e) {
            return new ErrorCommand(e.getMessage());
        } catch (IllegalArgumentException e) {
            return new ErrorCommand("I do not understand your language");
        }
    }

    /**
     * Checks if a string is a valid integer.
     * @param str String to be converted to integer.
     *
     * @return true if str is an integer, false otherwise.
     */
    public static boolean isNotInteger(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Checks if the array has only one element.
     * @param arr Array to be checked
     *
     * @return true if array has only one element, false otherwise.
     */
    public static boolean isOneElement(String[] arr) {
        return arr.length == 1;
    }

    /**
     * Converts the given string to date format.
     *
     * @param input String to be converted to date format.
     */
    public static LocalDate parseDateOnly(String input) {
        DateTimeFormatter[] formats = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("dd MM yyyy"),
                DateTimeFormatter.ofPattern("yyyy MM dd")
        };

        for (DateTimeFormatter format : formats) {
            try {
                return LocalDate.parse(input, format);
            } catch (Exception e) {
                // Ignore and try next
            }
        }
        throw new IllegalArgumentException("Invalid date format.");
    }

    /**
     * Converts the given string to date time format.
     * @param input String to be converted to date time format.
     */
    public static LocalDateTime parseDateAndTime(String input) {
        DateTimeFormatter[] formats = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
                DateTimeFormatter.ofPattern("dd MM yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy MM dd HHmm")
        };

        for (DateTimeFormatter format : formats) {
            try {
                return LocalDateTime.parse(input, format);
            } catch (Exception e) {
                // Ignore and try next
            }
        }
        throw new IllegalArgumentException("Invalid date format.");
    }

    /**
     * Checks if the given date is before today's date.
     * @param date Date to be checked.
     */
    public static boolean isBeforeToday(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    /**
     * Checks if the given date is before today's date.
     * @param date Date to be checked.
     */
    public static boolean isBeforeToday(LocalDateTime date) {
        return date.isBefore(LocalDateTime.now());
    }

    /**
     * Checks if the given string is an accepted recurrence.
     * Accepted recurrences are daily, weekly and monthly.
     * @param str String to be checked.
     */
    public static boolean isNotValidRecurrence(String str) {
        str = str.toLowerCase();
        return !(str.equals("daily") || str.equals("monthly") || str.equals("weekly"));
    }
}
