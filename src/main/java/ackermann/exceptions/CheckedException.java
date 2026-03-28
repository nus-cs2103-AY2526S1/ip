package ackermann.exceptions;

/**
 * Superclass for all checked exceptions
 */
public class CheckedException extends Exception {
    public CheckedException(String message) {
        super(message);
    }
}
