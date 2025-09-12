package dobby.exceptions;

public class InvalidTaskException extends DobbyException {
    public InvalidTaskException() {
        super("Invalid task input!");
    }

    public InvalidTaskException(String message) {
        super(message);
    }
}
