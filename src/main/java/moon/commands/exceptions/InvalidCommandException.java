package moon.commands.exceptions;

import moon.commands.enums.Command;
import moon.parser.exceptions.ParseException;

/**
 * Exception thrown when the user enters an unrecognised command keyword.
 * <p>
 * For example, typing {@code todone} instead of {@code todo}.
 * <p>
 * The exception stores the invalid command string and attempts
 * to suggest a similar valid command in its error message.
 */
public class InvalidCommandException extends ParseException {
    /** Invalid command keyword entered by the user. */
    private final String invalidCommand;

    /**
     * Creates an InvalidCommandException with the given invalid keyword and message.
     *
     * @param invalidCommand The unrecognised command keyword.
     * @param message        Description of the error.
     */
    public InvalidCommandException(String invalidCommand, String message) {
        super(message);
        this.invalidCommand = invalidCommand;
    }

    /**
     * Attempts to find a similar valid command to suggest to the user.
     * <p>
     * Current implementation is primitive: it compares the first letter
     * of the invalid keyword with the first letters of all known commands.
     *
     * @return A suggestion string if a similar command is found,
     *         or an empty string otherwise.
     */
    private String findSimilarCommand() {
        return Command.getCommandStream()
                .map(Command::getKeyword)
                .filter(c -> c.startsWith(this.invalidCommand.substring(0, 1)))
                .findFirst()
                .map(c -> String.format(" Do you mean '%s'?", c))
                .orElse("");
    }

    /**
     * Returns the formatted error message including the invalid keyword
     * and, if applicable, a suggested valid command.
     *
     * @return Detailed error message with the invalid command and suggestion.
     */
    @Override
    public String getMessage() {
        return String.format("%s: '%s'.%s",
                super.getMessage(), this.invalidCommand, this.findSimilarCommand());
    }
}
