package command;


/**
 * Represents the {@code help} command which displays usage instructions
 * for all available commands in the Penny application.
 *
 * <p>When executed, this command returns a formatted string that lists
 * the usage messages of all supported commands, serving as a quick
 * reference for the user.</p>
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Executes this command by returning a list of usage instructions
     * for all supported commands.
     *
     * @return the help message containing usage information
     */
    @Override
    public String respond() {
        return "Here are the available commands:\n\n"
                + "\n\n" + ToDoCommand.MESSAGE_USAGE
                + "\n\n" + DeadlineCommand.MESSAGE_USAGE
                + "\n\n" + EventCommand.MESSAGE_USAGE
                + "\n\n" + ListCommand.MESSAGE_USAGE
                + "\n\n" + MarkCommand.MESSAGE_USAGE
                + "\n\n" + UnmarkCommand.MESSAGE_USAGE
                + "\n\n" + DeleteCommand.MESSAGE_USAGE
                + "\n\n" + HelpCommand.MESSAGE_USAGE
                + "\n\n" + ByeCommand.MESSAGE_USAGE;
    }
}
