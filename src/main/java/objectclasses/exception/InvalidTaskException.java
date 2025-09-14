package objectclasses.exception;

/**
 * Represents an invalid task format.
 */
public class InvalidTaskException extends LynxException {

    private InvalidTaskException(String message) {
        super(message);
    }

    public static InvalidTaskException blankName() {
        return new InvalidTaskException("Task name cannot be blank.");
    }

    public static InvalidTaskException invalidName() {
        return new InvalidTaskException("Task name cannot contain the \"/\" character.");
    }

    public static InvalidTaskException longName() {
        return new InvalidTaskException("Task name cannot exceed 150 characters.");
    }

    public static InvalidTaskException invalidDuration() {
        return new InvalidTaskException("The start date/time cannot be after the end date/time.");
    }

}
