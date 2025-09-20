package marquess.command;

import marquess.Storage;
import marquess.TaskList;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList taskList) {
        String exit = "Bye. Hope to see you again soon!";
        return exit;
    }
}
