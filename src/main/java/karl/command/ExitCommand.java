package karl.command;

import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;

/**
 * Command to exit the chatbot application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command by showing goodbye message.
     *
     * @param tasks Task list (not used here)
     * @param ui to interact with user
     * @param storage storage (not used here)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates this command terminates the program.
     *
     * @return true as this command always exits the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
