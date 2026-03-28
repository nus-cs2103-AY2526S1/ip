package ackermann.exceptions.task.InvalidDeadline;

import ackermann.exceptions.task.InvalidTaskException;

/**
 * Exception for invalid deadline
 */
public class InvalidDeadlineException extends InvalidTaskException {
    //0 is no space, 1 is no /by
    public InvalidDeadlineException() {
        super("Invalid Deadline Input!\nE.g. deadline project meeting /by 2025-12-01");
    }

    public InvalidDeadlineException(String message) {
        super("Invalid Deadline Input!\n" + message);
    }
}
