package mon.command;

import mon.storage.Storage;
import mon.task.TaskList;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    private static final String MESSAGE_GOODBYE = "Mon: See you again!";
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        return MESSAGE_GOODBYE;
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
