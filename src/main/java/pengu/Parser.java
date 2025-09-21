package pengu;

import java.time.LocalDateTime;

import pengu.exception.InvalidFieldException;
import pengu.exception.MissingFieldException;
import pengu.exception.PenguException;

// JavaDocs and code were improved using ChatGPT:
// "Help me improve the code quality and javadocs"

/**
 * Parser class that extracts fields and commands from user input.
 * <p>
 * Provides utility methods to parse string, integer, and datetime fields
 * with built-in error handling for missing or invalid fields.
 */
public class Parser {
    private final String input;
    private final String command;
    private int curIndex;

    /**
     * Constructs a Parser instance for the given input string.
     * The first token (separated by space) is treated as the command.
     *
     * @param input The raw user input string. Must not be null.
     */
    public Parser(String input) {
        // ChatGPT suggested null check
        assert input != null : "Input cannot be null";

        this.input = input.trim();

        int index = input.indexOf(" ");
        if (index == -1) {
            this.command = this.input;
            curIndex = this.input.length();
        } else {
            this.command = this.input.substring(0, index);
            curIndex = index + 1;
        }
    }

    /**
     * Returns the command part of the input.
     *
     * @return The parsed command string.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Parses a string field up to a specified delimiter.
     *
     * @param delim         Delimiter string; must not be null.
     * @param commandFormat Format of the command, used in error messages.
     * @return The extracted field string.
     * @throws MissingFieldException if the delimiter is not found or the field is empty.
     */
    public String getField(String delim, String commandFormat) throws MissingFieldException {
        // ChatGPT suggested null check
        assert delim != null : "Delimiter cannot be null";
        assert curIndex <= input.length() : "Parser: curIndex is larger than input length.";

        int delimIndex = delim.isEmpty() ? input.length() : input.indexOf(delim, curIndex);

        if (delimIndex == -1) {
            throw new MissingFieldException(commandFormat);
        }

        String field = input.substring(curIndex, delimIndex).trim();
        if (field.isEmpty()) {
            throw new MissingFieldException(commandFormat);
        }

        curIndex = delimIndex + delim.length();
        return field;
    }

    /**
     * Parses an integer field up to a specified delimiter.
     *
     * @param delim         Delimiter string; must not be null.
     * @param commandFormat Format of the command, used in error messages.
     * @return The parsed integer.
     * @throws MissingFieldException if the field is missing.
     * @throws InvalidFieldException if the field is not a valid integer.
     */
    public int getIntField(String delim, String commandFormat) throws PenguException {
        String field = getField(delim, commandFormat);

        try {
            return Integer.parseInt(field);
        } catch (NumberFormatException e) {
            String errorMessage = "Expected: integer value field\nGiven: " + field;
            throw new InvalidFieldException(errorMessage);
        }
    }

    /**
     * Parses a datetime field up to a specified delimiter.
     *
     * @param delim         Delimiter string; must not be null.
     * @param commandFormat Format of the command, used in error messages.
     * @return The parsed LocalDateTime object.
     * @throws MissingFieldException if the field is missing.
     * @throws InvalidFieldException if the field is not in a valid datetime format.
     */
    public LocalDateTime getDateTimeField(String delim, String commandFormat) throws PenguException {
        String field = getField(delim, commandFormat);
        return DateTimeParser.fromDateTimeString(field);
    }
}
