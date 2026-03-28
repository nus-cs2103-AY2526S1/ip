package ackermann.exceptions.task;

import ackermann.exceptions.CheckedException;

/**
 * Superclass for all task exceptions
 */
public class InvalidTaskException extends CheckedException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
