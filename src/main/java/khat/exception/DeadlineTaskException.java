package khat.exception;

/**
 * Represents an exception thrown when a deadline task is invalid or missing required information.
 */
public class DeadlineTaskException extends KhatException {

    public DeadlineTaskException(String e) {
        super(e);
    }

}
