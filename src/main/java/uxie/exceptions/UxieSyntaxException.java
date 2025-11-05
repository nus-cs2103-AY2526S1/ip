package uxie.exceptions;

/**
 * Indicates an error in user input syntax.
 * Details are provided in message.
 *
 * @author junyan-k
 */
public class UxieSyntaxException extends UxieException {

    /**
     * Generates UxieSyntaxException with provided message.
     * @see uxie.exceptions.UxieException#UxieException(String)
     */
    public UxieSyntaxException(String message) {
        super(message);
    }

    /**
     * Returns exception as String.
     * Format: "Didn't quite catch that, trainer. [message]"
     */
    @Override
    public String toString() {
        return String.format("Didn't quite catch that, trainer. %s", this.getMessage());
    }

}
