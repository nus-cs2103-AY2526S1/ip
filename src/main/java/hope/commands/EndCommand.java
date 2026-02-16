package hope.commands;

/**
 * A command that signals the termination of the Hope chatbot.
 * This class implements the {@link Command} interface and is used to indicate that the
 * chatbot should exit its main interaction loop. The {@code execute} method is intentionally
 * empty, as the command's purpose is solely to trigger the shutdown process handled by the
 * calling context.
 */
public class EndCommand implements Command {

    /**
     * Executes the command to signal the termination of the chatbot.
     * This method is intentionally empty, as the command's role is to indicate the
     * end of the chatbot's operation, with the actual shutdown logic handled externally.
     *
     * @param o the input object (unused in this implementation)
     */
    @Override
    public String execute(Object o) {
        return "Fare thee well, noble friend!";
    }
}
