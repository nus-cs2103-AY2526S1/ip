package luna.exception;

/**
 * Represents a {@code RuntimeException} that is specific to Luna.
 */
public class LunaException extends RuntimeException {
    public LunaException(String errorMessage) {
        super(errorMessage);
    }
}