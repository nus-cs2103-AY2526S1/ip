package uxie.exceptions;

/**
 * Represents UxieExceptions to do with illegal operations.
 * Details are provided in error message.
 *
 * @author junyan-k
 */
public class UxieIllegalOpException extends UxieException {

    /**
     * Generates UxieIllegalOpException with provided message.
     * @see uxie.exceptions.UxieException#UxieException(String)
     */
    public UxieIllegalOpException(String message) {
        super(message);
    }

    /**
     * Returns exception as String.
     * Format: "Can't do that, trainer. [message]"
     */
    @Override
    public String toString() {
        return String.format("Can't do that, trainer. %s", this.getMessage());
    }

}
