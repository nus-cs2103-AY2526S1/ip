package marcus.exception;

public class MissingDescriptionError extends Exception {
    public MissingDescriptionError(String errorMessage) {
        super("Description cannot be empty\n" + errorMessage);
    }
}
