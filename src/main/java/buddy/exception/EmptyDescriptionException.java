package buddy.exception;

/** Thrown when a command is recognized but lacks a required description. */

public class EmptyDescriptionException extends BuddyException {
    public EmptyDescriptionException(String taskType) {
        super("OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}