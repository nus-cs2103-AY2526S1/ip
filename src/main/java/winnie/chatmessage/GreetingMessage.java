package winnie.chatmessage;

/**
 * Message of greeting, show up when the user starts a conversation.
 */
public class GreetingMessage implements Sendable {

    private static final String GREETING = "Hello! I'm " + Utils.BOT_NAME
            + "\n     What can I do for you?";

    @Override
    public String getMessageContent() {
        return GREETING;
    }
}
