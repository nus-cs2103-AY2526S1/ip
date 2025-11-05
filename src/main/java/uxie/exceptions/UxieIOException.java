// CHECKSTYLE.OFF: AbbreviationAsWordInName
package uxie.exceptions;

/**
 * Represents UxieExceptions related to file reading and I/O.
 * Details are provided in message.
 *
 * @author junyan-k
 */
public class UxieIOException extends UxieException {

    /**
     * Generates UxieIOException with provided message.
     * @see uxie.exceptions.UxieException#UxieException(String)
     */
    public UxieIOException(String msg) {
        super(msg);
    }

    /**
     * Returns exception as String.
     * Format: "This prison angers me, trainer. [msg]"
     */
    @Override
    public String toString() {
        return String.format("This prison angers me, trainer. %s", this.getMessage());
    }

}
// CHECKSTYLE.ON: AbbreviationAsWordInName
