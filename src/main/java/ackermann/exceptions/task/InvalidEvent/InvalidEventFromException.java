package ackermann.exceptions.task.InvalidEvent;

/**
 * Invalid date format for from
 */
public class InvalidEventFromException extends InvalidEventException {
    public InvalidEventFromException() {
        super("Please include /from in yyyy-MM-dd format");
    }
}
