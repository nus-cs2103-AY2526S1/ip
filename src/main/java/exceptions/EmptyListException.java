package exceptions;

/**
 * Thrown when an operation requiring tasks fails because the list is empty.
 */
public class EmptyListException extends SundayException {
    public EmptyListException() {
        super("Your list is empty. Add something in now!");
    }
}
