package winnie.chatmessage;

/**
 * Message indicating that an error has occurred.
 */
public class ErrorMessage implements Sendable {

    private final String errorMessage;

    public ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessageContent() {
        return "OOPS!!! " + errorMessage;
    }
}
