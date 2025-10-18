package peppa.command;

/**
 * Command representing the user exiting the application.
 * This command does not mutate state and only returns the goodbye message.
 */
public class Bye implements Command {
    /**
     * Returns the goodbye message to be displayed to the user.
     *
     * @return goodbye message.
     */
    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!";
    }
}
