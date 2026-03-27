package exceptions;

/**
 * Thrown when a deadline command is missing the required {@code /by} parameter.
 */
public class DeadlineMissingByException extends SundayException {
    public DeadlineMissingByException() {
        super("When is your task due by? Did you forget?");
    }
}
