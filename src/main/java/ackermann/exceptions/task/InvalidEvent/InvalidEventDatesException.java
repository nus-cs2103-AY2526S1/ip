package ackermann.exceptions.task.InvalidEvent;


/**
 * Invalid Event
 */
public class InvalidEventDatesException extends InvalidEventException {
    public InvalidEventDatesException() {
        super("Please make sure /to is after /from!");
    }
}
