package exceptions;

public class DawaeException extends Exception {
    /**
     * Creates a new dawae exception carrying a user-facing error message.
     *
     * @param msg the detail message.
     */
    public DawaeException(String msg) {
        super(msg);
    }
}