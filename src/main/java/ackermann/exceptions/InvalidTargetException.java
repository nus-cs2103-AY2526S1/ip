package ackermann.exceptions;

/**
 * Invalid target
 */
public class InvalidTargetException extends CheckedException {
    public InvalidTargetException() {
        super("Unable to locate task!");
    }
}
