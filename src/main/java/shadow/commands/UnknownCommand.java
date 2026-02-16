package shadow.commands;

/**
 * Represents a command that handles unknown or unrecognized user input.
 * This command is used as a fallback when the provided input does not match any known commands.
 */
public class UnknownCommand extends Command {
    private static final UnknownCommand unknownCommand = new UnknownCommand();

    @Override
    public String execute() {
        return "Unknown command";
    }

    /**
     * Provides access to the singleton instance of the {@code UnknownCommand} class.
     * This method ensures that only one instance of {@code UnknownCommand} exists
     * and is reused whenever needed.
     *
     * @return the singleton instance of {@code UnknownCommand}
     */
    public static UnknownCommand getInstance() {
        return UnknownCommand.unknownCommand;
    }
}
