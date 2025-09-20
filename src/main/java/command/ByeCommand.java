package command;

/**
 * Represents the {@code bye} command which terminates the Penny application.
 *
 * <p>When executed, this command sets {@link Command#shouldExit} to {@code true}
 * and returns a farewell message to the user.</p>
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String BYE_REPLY = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Constructs a {@code ByeCommand} and signals Penny to exit.
     */
    public ByeCommand() {
        shouldExit = true;
    }
    
    /**
     * Returns the farewell message to the user.
     *
     * @return the predefined farewell message
     */
    @Override
    public String respond() {
        return BYE_REPLY;
    }
}
