package moon.parser.exceptions;

import moon.commands.enums.Command;

/**
 * Exception thrown when a required argument for a command is missing or empty.
 * <p>
 * For example:
 * <ul>
 *   <li>{@code todo} without a task name.</li>
 *   <li>{@code deadline} without a "/by" value.</li>
 * </ul>
 */
public class EmptyArgumentException extends ParseException {
    /**
     * Creates an EmptyArgumentException with a message.
     *
     * @param message Description of the error.
     */
    public EmptyArgumentException(String message) {
        super(message);
    }

    /**
     * Creates an EmptyArgumentException for a specific command.
     *
     * @param command The command with missing arguments.
     * @param message Description of the error.
     */
    public EmptyArgumentException(Command command, String message) {
        super(command, message);
    }
}
