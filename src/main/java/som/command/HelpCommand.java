package som.command;

import som.SomException;
import som.TaskList;
import som.Ui;

/**
 * Represents a command to display the help message.
 * <p>When executed, this command prints a formatted list of all available commands
 * and their usage.</p>
 *
 * @author Darien Tan
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) throws SomException {
        return ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
