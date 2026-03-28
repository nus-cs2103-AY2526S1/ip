package ackermann.exceptions;

/**
 * Invalid search
 */
public class InvalidFindException extends CheckedException {
    public InvalidFindException() {
        super("Unable to find task!");
    }
}
