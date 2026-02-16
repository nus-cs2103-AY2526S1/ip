package shadow.commands;

/**
 * Represents an abstract command in the application.
 * Subclasses of this class define specific actions to be executed.
 * Provides a default behavior for determining whether executing this command should terminate the application.
 */
public abstract class Command {
    /**
     * Executes the main logic or action of this command.
     * This method is abstract and must be implemented by subclasses.
     */
    public abstract String execute();

    /**
     * Indicates whether this command signals an exit condition.
     *
     * @return {@code true} if this command causes the application to exit; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

