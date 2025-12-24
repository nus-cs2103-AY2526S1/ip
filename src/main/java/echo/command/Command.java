package echo.command;

import echo.Echo;

/**
 * Represents a command. <code>Command</code> is abstract and stores echo.
 */
public abstract class Command {
    protected Echo echo;

    public Command(Echo echo) {
        this.echo = echo;
    }

    /**
     * Carries out the action based on the command
     *
     * @return String Message to be shown to user for the command
     */
    public abstract String execute();
}
