package izayoi.exception;

/**
 * Represents an exception raised by Izayoi
 */
public class IzayoiException extends Exception {

    /**
     * Initializes a new IzayoiException with a message
     * @param message the message to display, preferably sassier than usual
     */
    public IzayoiException(String message) {
        super(message);
    }
}
