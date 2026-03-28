package alex.command;

/**
 * Represents the command to exit the chatbot.
 * Responds with a farewell message to the user.
 */
public class ByeCommand implements Command {

    /**
     * Returns the chatbot's farewell message when the user exits.
     *
     * @return Farewell message.
     */
    @Override
    public String response() {
        return "Need to leave is it?\n" + "Goodbye then, see you again soon!";
    }
}
