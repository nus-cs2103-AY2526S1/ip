package balloon.exception;

/**
 * Represents a custom exception used in the Balloon program.
 * This serves as the parent class for all Balloon-specific exceptions.
 */
public class BalloonException extends Exception {
    private String message;

    public BalloonException() {}

    public BalloonException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
