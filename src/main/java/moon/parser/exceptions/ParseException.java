package moon.parser.exceptions;

import moon.commands.enums.Command;
import moon.logic.exceptions.MoonException;

/**
 * Exception thrown when user input cannot be parsed into a valid command.
 * <p>
 * Examples include:
 * <ul>
 *   <li>Missing flags or separators (e.g. "/by", "/from").</li>
 *   <li>Typos in keywords.</li>
 *   <li>Invalid or malformed arguments.</li>
 * </ul>
 */
public class ParseException extends MoonException {
    private String parseError;
    private Command command;

    /**
     * Creates a ParseException with only an error message.
     *
     * @param message Description of the parsing error.
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Creates a ParseException for a specific command type.
     *
     * @param errorCommandType The command that failed to parse.
     * @param message          Description of the parsing error.
     */
    public ParseException(Command errorCommandType, String message) {
        super(message);
        this.command = errorCommandType;
    }

    /**
     * Creates a ParseException with more detailed error information.
     *
     * @param parseError       Specific problematic input.
     * @param errorCommandType The command that failed to parse.
     * @param message          Description of the parsing error.
     */
    public ParseException(String parseError, Command errorCommandType, String message) {
        super(message);
        this.command = errorCommandType;
        this.parseError = parseError;
    }

    /**
     * Returns the specific erroneous input that caused the parse failure, if available.
     *
     * @return Erroneous input string, or {@code null} if not provided.
     */
    public String getParseError() {
        return this.parseError;
    }

    /**
     * Returns the command type that failed to parse, if available.
     *
     * @return The {@link Command}, or {@code null} if not provided.
     */
    public Command getErrorCommandType() {
        return this.command;
    }

    @Override
    public String getMessage() {
        if (command != null) {
            return super.getMessage() + "\nYou should follow this syntax:\n" + command.getFormat();
        } else {
            return super.getMessage();
        }
    }
}
