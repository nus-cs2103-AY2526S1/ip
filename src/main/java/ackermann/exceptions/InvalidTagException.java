package ackermann.exceptions;

/**
 * Exception for invalid tag input
 */
public class InvalidTagException extends CheckedException {
    public InvalidTagException() {
        super("Invalid Selection.\nE.g. tag 1 something");
    }
}
