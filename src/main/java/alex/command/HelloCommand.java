package alex.command;

/**
 * Represents the command to greet the user.
 * Responds with a simple greeting message from the chatbot.
 */
public class HelloCommand implements Command {

    /**
     * Returns the chatbot's greeting message.
     *
     * @return Greeting message.
     */
    @Override
    public String response() {
        return "Hello, I'm Alex. What do you want from me";
    }
}