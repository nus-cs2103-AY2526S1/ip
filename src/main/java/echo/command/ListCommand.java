package echo.command;

import echo.Echo;

/**
 * Represents a command to show user task list. A <code>FindCommand</code> object
 * is a subtype of <code>Command</code>.
 */
public class ListCommand extends Command {
    public ListCommand(Echo echo) {
        super(echo);
    }

    @Override
    public String execute() {
        return echo.getUi().showList(this.echo.getTasklist());
    }
}
