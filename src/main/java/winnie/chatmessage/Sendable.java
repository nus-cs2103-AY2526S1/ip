package winnie.chatmessage;

/**
 * Interface for messages that can be sent.
 */
public interface Sendable {
    /**
     * Gets the content of the message.
     *
     * @return The message content.
     */
    String getMessageContent();
}
