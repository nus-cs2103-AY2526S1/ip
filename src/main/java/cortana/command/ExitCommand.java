package cortana.command;

import cortana.storage.FileHandler;
import cortana.task.TaskList;

/**
 * Represents the exit command which ends the chatbot session.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command, displaying a farewell message.
     */
    @Override
    public String execute(TaskList tasks, FileHandler fileHandler) {
        return "Exit";
    }

    /**
     * Indicates that this command signals program termination.
     *
     * @return true since this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
