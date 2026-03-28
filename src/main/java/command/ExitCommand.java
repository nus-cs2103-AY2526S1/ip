package command;
import application.Storage;
import application.TaskList;
import application.Ui;

/**
 * Command implementation for terminating the application.
 * Handles graceful application shutdown without requiring additional cleanup.
 */
public class ExitCommand extends Command {
    
    /**
     * Executes the exit command.
     * No special cleanup required as the application will terminate naturally.
     * The main application loop checks isBye() to determine when to exit.
     * 
     * @param tasks The task list (not used for exit operation)
     * @param ui The user interface (not used for exit operation)
     * @param storage The storage component (not used for exit operation)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Exit command doesn't need to perform any operations
        // The application will terminate based on isBye() returning true
    }
    
    @Override
    public boolean isBye() {
        return true; // Exit commands terminate the application
    }
}
