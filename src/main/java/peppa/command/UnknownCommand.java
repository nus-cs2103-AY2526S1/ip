package peppa.command;

/**
 * Represents an unknown or unsupported command.
 */
public class UnknownCommand implements Command {
    /**
     * Returns a polite error message indicating the command is not recognized.
     *
     * @return unknown command message.
     */
    @Override
    public String execute() {
        return "Oopsies, I don't know what that means!";
    }
}
