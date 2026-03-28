package vicky.exception;

/**
 * Represents an exception for invalid inputs.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
