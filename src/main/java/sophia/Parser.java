package sophia;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
/**
 * Represents a parser interface for interpreting user input into {@link Task} objects.
 * <p>
 * Implementations of {@code Parser} are responsible for parsing specific command strings
 * and converting them into the appropriate {@code Task} or triggering the relevant actions.
 * </p>
 * <p>
 * This interface also provides static utility methods to validate user input
 * against expected command formats using regular expressions.
 * </p>
 */
public interface Parser {
    /**
     * Parses the user input and returns a corresponding {@link Task}.
     *
     * @return a {@code Task} object created from the parsed input
     * @throws SophiaException if the input cannot be parsed or is invalid
     */
    public Task parse() throws SophiaException;

    /**
     * Checks whether the given date string can be parsed into a valid {@link LocalDateTime}.
     * <p>
     * This method attempts to parse the input using {@link Parser#stringToLocalDateTime(String)}.
     * If parsing succeeds, it returns {@code true}. If parsing fails, it throws a
     * {@link SophiaException} with a descriptive error message.
     *
     * @param date the date string to validate, expected in one of the supported formats
     *             (e.g. {@code yyyy-MM-dd} or {@code yyyy-MM-dd HH:mm})
     * @return {@code true} if the date string is valid
     * @throws SophiaException if the input cannot be parsed into a valid {@link LocalDateTime}
     */
    public static boolean isValidDate(String date) throws SophiaException {
        try {
            LocalDateTime time = Parser.stringToLocalDateTime(date);
            return true;
        } catch (DateTimeParseException e) {
            throw new SophiaException("Date: " + date + " is invalid!");
        }
    }


    /**
     * Validates the given string against a specified regular expression pattern.
     *
     * @param str     the input string to validate
     * @param pattern the regex pattern to match against
     * @return {@code true} if the string matches the pattern, {@code false} otherwise
     */
    public static boolean validateInput(String str, Pattern pattern) {
        return pattern.matcher(str).matches();
    }

    /**
     * Validates whether the input string is a valid {@code mark} command.
     * <p>Expected format: {@code mark <index>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateMarkInput(String str) {
        return validateInput(str, Pattern.compile("^mark\\s+\\d+$"));
    }

    /**
     * Validates whether the input string is a valid {@code unmark} command.
     * <p>Expected format: {@code unmark <index>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateUnmarkInput(String str) {
        return validateInput(str, Pattern.compile("^unmark\\s+\\d+$"));
    }

    /**
     * Validates whether the input string is a valid {@code delete} command.
     * <p>Expected format: {@code delete <index>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateDeleteInput(String str) {
        return validateInput(str, Pattern.compile("^delete\\s+\\d+$"));
    }

    /**
     * Validates whether the input string is a valid {@code todo} command.
     * <p>Expected format: {@code todo <description>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateTodoInput(String str) {
        return validateInput(str, Pattern.compile("^todo\\s+.+$"));
    }

    /**
     * Validates whether the input string is a valid {@code deadline} command.
     * <p>Expected format: {@code deadline <description> /by <date>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateDeadlineInput(String str) {
        //System.out.println(str);
        return validateInput(str, Pattern.compile(
                "^deadline\\s+.+\\s+/by\\s+\\d{4}-\\d{2}-\\d{2}(?: ([01]\\d|2[0-3]):[0-5]\\d)?$"));
    }

    /**
     * Converts a date string into a {@link LocalDateTime} using the appropriate formatter.
     * <p>
     * The method automatically selects the formatter based on the format of the input:
     * <ul>
     *   <li>{@code yyyy-MM-dd} → parsed as a {@link LocalDate}, returned as a
     *       {@link LocalDateTime} at the start of day (00:00)</li>
     *   <li>{@code yyyy-MM-dd HH:mm} → parsed as a {@link LocalDateTime}</li>
     * </ul>
     * If the input does not match any supported format, this method returns {@code null}.
     *
     * @param date the date string to parse
     * @return the parsed {@link LocalDateTime}, or {@code null} if the format is unsupported
     */
    public static LocalDateTime stringToLocalDateTime(String date) {
        if (validateInput(date, Pattern.compile("\\d{4}-\\d{2}-\\d{2}"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter).atStartOfDay();
        }
        if (validateInput(date, Pattern.compile("\\d{4}-\\d{2}-\\d{2} ([01]\\d|2[0-3]):[0-5]\\d"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(date, formatter);
        }
        return null;
    }
    /**
     * Validates whether the input string is a valid {@code event} command.
     * <p>Expected format: {@code event <description> /from <start> /to <end>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateEventInput(String str) {
        //System.out.println(str);
        return validateInput(str,
                Pattern.compile(
                        "^event\\s+.+\\s+"
                                + "/from\\s+\\d{4}-\\d{2}-\\d{2}(?: ([01]\\d|2[0-3]):[0-5]\\d)?"
                                + "\\s+/to\\s+\\d{4}-\\d{2}-\\d{2}(?: ([01]\\d|2[0-3]):[0-5]\\d)?$"));
    }
    /**
     * Validates whether the input string is a valid {@code list} command.
     * <p>Expected format: {@code list}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateListInput(String str) {
        return validateInput(str, Pattern.compile("^list$"));
    }

    /**
     * Validates whether the input string is a valid {@code bye} command.
     * <p>Expected format: {@code bye}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateByeInput(String str) {
        return validateInput(str, Pattern.compile("^bye$"));
    }

    /**
     * Validates whether the input string is a valid {@code save} command.
     * <p>Expected format: {@code save}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateSaveInput(String str) {
        return validateInput(str, Pattern.compile("^save$"));
    }

    public static boolean validateFindInput(String str) {
        return validateInput(str, Pattern.compile("^find\\s+.*\\S.*$"));
    }
}
