package finixx.exception;

/**
 * FinixxException is a custom exception class that extends the standard Exception class.
 * It is used to represent specific errors that occur specifically within the Finixx application.
 * This class provides a constructor to create an exception with a detailed message
 * and a method to retrieve that message.
 */
public class FinixxException extends Exception {

    /**
     * Constructs a FinixxException with a specified error message.
     *
     * @param message The detail message describing the exception
     */
    public FinixxException(String message) {
        super(message);
    }

    /**
     * Returns the error message associated with this exception.
     *
     * @return The exception message string
     */
    public String printMessage() {
        return this.getMessage();
    }
}
