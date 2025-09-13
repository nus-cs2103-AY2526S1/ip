package hermione.parsers;

/**
 * Represents the result of parsing user input, containing both the response
 * message
 * and whether it represents an error.
 */
public class ResponseResult {
    private final String message;
    private final boolean isError;

    /**
     * Constructs a ResponseResult with the given message and error status.
     *
     * @param message The response message
     * @param isError Whether this response represents an error
     */
    public ResponseResult(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    /**
     * Gets the response message.
     *
     * @return The response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Checks if this response represents an error.
     *
     * @return true if this is an error response, false otherwise
     */
    public boolean isError() {
        return isError;
    }
}
