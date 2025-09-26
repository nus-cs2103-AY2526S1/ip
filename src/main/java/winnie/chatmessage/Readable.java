package winnie.chatmessage;

/**
 * Interface for messages that can be read.
 */
public interface Readable {
    /**
     * Gets the content of the message.
     *
     * @return The message content.
     */
    String getMessageContent();
}
