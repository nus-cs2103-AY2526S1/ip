package sora;

/**
 * Represents an exception specific to the Sora application.
 */
public class SoraException extends Exception {
    /**
     * Constructs a new {@code SoraException} with the specified detail message.
     *
     * @param str the detail message explaining the cause of the exception.
     */
    public SoraException(String str) {
        super(str);
    }
}
