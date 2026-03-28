package ackermann.exceptions;

/**
 * Invalid selection for delete
 */
public class InvalidDeleteException extends CheckedException {
    public InvalidDeleteException() {
        super("Invalid Selection.\nE.g. delete 1");
    }
}
