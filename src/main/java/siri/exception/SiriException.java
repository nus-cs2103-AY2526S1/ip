package siri.exception;

public class SiriException extends RuntimeException {

    /**
     * Constructs a new siri.siri.exception.SiriException with a detail message.
     *
     * @param message the error detail message
     */
    public SiriException(String message) {
        super(message);
    }

    /**
     * Constructs a new siri.siri.exception.SiriException with a detail message
     * that includes the invalid input that caused the error.
     *
     * @param message the error detail message
     * @param cause the invalid user input
     */
    public SiriException(String message,String hint, String cause) {
        super(message + " | Usage: " + hint + " | Input: \"" + cause + "\"");
    }

}
