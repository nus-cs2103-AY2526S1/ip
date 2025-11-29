package cate.exception;

/**
 * Exception thrown when a command that requires a description is given
 * without one in the Cate application.
 * <p>
 * For example, {@code todo }, {@code deadline }, or {@code event } without
 * any description would trigger this exception.
 * </p>
 */
public class MissingDescriptionException extends CateException {

    /**
     * Constructs a {@code MissingDescriptionException} for a specific command.
     *
     * @param command The name of the command missing a description.
     */
    public MissingDescriptionException(String command) {
        super("The description of a " + command + " cannot be empty.");
    }
}
