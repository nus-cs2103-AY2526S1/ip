package banana.command;

import banana.utils.Storage;
import banana.utils.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
