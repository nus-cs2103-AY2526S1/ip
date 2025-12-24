package echo.command;

import echo.Echo;

/**
 * Represents a command to exit the application. A <code>ByeCommand</code> object
 * is a subtype of <code>Command</code>.
 */
public class ByeCommand extends Command {
    public ByeCommand(Echo echo) {
        super(echo);
    }

    @Override
    public String execute() {
        this.echo.getStorage().saveFile(echo.getTasklist());
        return echo.getUi().showExit();
    }
}
