package ozil.command;

import ozil.exception.OzilException;
import ozil.main.TaskList;

/**
 * The parent class of all commands.
 */
public class Command {
    public String run(TaskList tasks) throws OzilException {
        return "";
    }

    public boolean isTerminatingCommand() {
        return false;
    }
}
