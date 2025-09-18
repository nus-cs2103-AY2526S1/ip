package marcus.exception;

public class InvalidIndexError extends Exception {
    public InvalidIndexError() {
        super("That chapter does not exist in your story");
    }
}
