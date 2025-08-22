public class InvalidTaskException extends Exception {
    public InvalidTaskException() {
        super("Invalid task input!");
    }

    public InvalidTaskException(String message) {
        super(message);
    }
}
