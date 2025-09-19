package bot.command;

import bot.service.FileService;
import bot.task.TaskList;

/**
 * Represents a command to exit the application.
 * This command displays an exit message to the user and signals that the application
 * should terminate after execution.
 */
public class ExitCommand extends Command {
    private boolean isExecuted = false;

    public ExitCommand() {}

    /**
     * Executes the exit command by displaying an exit message to the user
     * and marking the command as executed.
     *
     * @param taskList the task list (not used in this command)
     * @param fileService the file services (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, FileService fileService) {
        isExecuted = true;
    }

    /**
     * Determines if this command should exit the application.
     *
     * @return true if the command has been executed, false otherwise
     */
    @Override
    public boolean isExit() {
        return isExecuted;
    }
}
