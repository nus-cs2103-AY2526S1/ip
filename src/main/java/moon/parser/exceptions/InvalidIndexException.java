package moon.parser.exceptions;

import moon.commands.enums.Command;

/**
 * Exception thrown when a task index is invalid.
 * <p>
 * This occurs if:
 * <ul>
 *   <li>The index is not an integer.</li>
 *   <li>The index is out of range for the current task list.</li>
 * </ul>
 */
public class InvalidIndexException extends ParseException {
    /**
     * Creates an InvalidIndexException with a message.
     *
     * @param message Description of the error.
     */
    public InvalidIndexException(String message) {
        super(message);
    }

    /**
     * Creates an InvalidIndexException for a specific command.
     *
     * @param command The command where the invalid index was found.
     * @param message Description of the error.
     */
    public InvalidIndexException(Command command, String message) {
        super(command, message);
    }
}
