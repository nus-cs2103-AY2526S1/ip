package winnie.chatmessage;

/**
 * Message indicating that the user has said goodbye.
 */
public class GoodByeMessage implements Sendable {

    private final String goodbyeMessage = "Bye. Hope to see you again soon!";

    public GoodByeMessage() {
        return;
    }

    @Override
    public String getMessageContent() {
        return goodbyeMessage;
    }
}
