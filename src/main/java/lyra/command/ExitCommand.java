package lyra.command;

import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

/**
 * Command implementation for exiting the Lyra application.
 * Displays a goodbye message and signals the application to terminate.
 */
public class ExitCommand extends Command {
    
    /**
     * Executes the exit command by displaying a goodbye message.
     *
     * @param tasks the task list (not used in this command)
     * @param ui the user interface for displaying the goodbye message
     * @param storage the storage component (not used in this command)
     * @throws LyraException never thrown by this command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        ui.showGoodbye();
    }
    
    /**
     * Indicates that this command should cause the application to exit.
     *
     * @return true to signal application termination
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
