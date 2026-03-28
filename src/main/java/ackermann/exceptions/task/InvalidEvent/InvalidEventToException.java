package ackermann.exceptions.task.InvalidEvent;

/**
 * Invalid date format for from
 */
public class InvalidEventToException extends InvalidEventException {
    public InvalidEventToException() {
        super("Please include /to in yyyy-MM-dd format");
    }
}
