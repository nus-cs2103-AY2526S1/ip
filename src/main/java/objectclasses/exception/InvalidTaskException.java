package objectclasses.exception;

/**
 * Represents an invalid task format.
 */
public class InvalidTaskException extends LynxException {

    private InvalidTaskException(String message) {
        super(message);
    }

    public static InvalidTaskException blankName() {
        return new InvalidTaskException("Please describe the task to me.");
    }

    public static InvalidTaskException invalidName() {
        return new InvalidTaskException("Task name cannot contain the \"/\" character. Sorry, these are conventions.");
    }

    public static InvalidTaskException longName() {
        return new InvalidTaskException("My memory is limited and I can't remember anything past 150 characters.");
    }

    public static InvalidTaskException invalidDuration() {
        return new InvalidTaskException("Does your task require time travel? "
                + "If not, please ensure start is before end.");
    }

}
