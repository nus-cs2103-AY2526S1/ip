package dwight.command;

/** Represents the result of executing a command. */
public class CommandResponse {
    private final String message;
    private final ResponseType type;

    /**
     * Creates a new {@code CommandResponse} with the specified message and response type.
     *
     * @param message The response message to be displayed to the user.
     * @param type The type of response indicating the nature of the result.
     */
    public CommandResponse(String message, ResponseType type) {
        this.message = message;
        this.type = type;
    }

    /**
     * Returns the response message.
     *
     * @return The message associated with this command response.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns the response type.
     *
     * @return The type of this command response.
     */
    public ResponseType getType() {
        return this.type;
    }

    /**
     * Returns a string representation of this command response in the format "[type] message".
     *
     * @return A formatted string containing the response type and message.
     */
    @Override
    public String toString() {
        return "[" + this.type + "] " + this.message;
    }
}
