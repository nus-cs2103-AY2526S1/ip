package balloon.exception;

import balloon.command.Command.CommandType;

/**
 * Represents an exception that is thrown when a command that requires
 * an integer argument is instead followed by a non-integer string.
 * This exception may be thrown on {@code MarkCommand}, {@code UnmarkCommand} and
 * {@code DeleteCommand}.
 */
public class StringConversionException extends BalloonException {
    private String commandName;

    /**
     * Creates a {@code StringConversionException} for the given command type.
     * This constructor determines the command name based on the provided type.
     *
     * @param commandType The type of command that failed string-to-integer conversion.
     */
    public StringConversionException(CommandType commandType) {
        switch(commandType) {
        case MARK:
            commandName = "<mark>";
            break;
        case UNMARK:
            commandName = "<unmark>";
            break;
        case DELETE:
            commandName = "<delete";
            break;
        default:
            commandName = "";
        }
    }

    @Override
    public String toString() {
        return commandName + " command has to be followed by an integer only";
    }
}
