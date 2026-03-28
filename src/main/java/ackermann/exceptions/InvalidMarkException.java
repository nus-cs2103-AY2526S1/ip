package ackermann.exceptions;

/**
 * Invalid selection for mark/unmarking of tasks
 */
public class InvalidMarkException extends CheckedException {
    public InvalidMarkException() {
        super("Invalid Selection.\nE.g. mark 1, unmark 1");
    }
}
