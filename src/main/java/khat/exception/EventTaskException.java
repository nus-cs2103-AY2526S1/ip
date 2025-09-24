package khat.exception;

/**
 * Represents an exception thrown when an event task is invalid or missing required information.
 */
public class EventTaskException extends KhatException {

    public EventTaskException(String e) {
        super(e);
    }

}
