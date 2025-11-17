package bin.exception;

/**
 * Signals an error caused by no or invalid task description input.
 */
public class NoTaskDescriptionException extends NoInputException{
    public NoTaskDescriptionException() {
        super("description of task");
    }
}
