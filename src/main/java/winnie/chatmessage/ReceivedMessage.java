package winnie.chatmessage;

/**
 * Message indicating that a message has been received.
 */
public class ReceivedMessage implements Readable {

    private final String content;

    /**
     * Creates a new ReceivedMessage with the given content.
     *
     * @param content The content of the message.
     */
    public ReceivedMessage(String content) {
        this.content = content;
    }

    @Override
    public String getMessageContent() {
        return content;
    }
}
