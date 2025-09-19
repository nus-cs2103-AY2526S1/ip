package bot.command;

import bot.service.FileService;
import bot.task.TaskList;

/**
 * Abstract base class for all commands in the bot application.
 * This class defines the common interface that all command classes must implement.
 * Each concrete command class should extend this class and provide specific
 * implementations for executing the command and determining if it should exit the application.
 */
public abstract class Command {
    private String response;

    /**
     * Executes the command with the given parameters.
     * This method should contain the specific logic for each command type.
     *
     * @param taskList the task list to operate on
     * @param fileService the file services for reading from and writing to storage
     */
    public abstract void execute(TaskList taskList, FileService fileService);

    /**
     * Determines whether this command should cause the application to exit.
     *
     * @return true if the application should exit after executing this command, false otherwise
     */
    public abstract boolean isExit();

    /**
     * Return the generated response message after task execution
     *
     * @return The command response string message
     */
    public String getResponse() {
        return response;
    }

    /**
     * Set the generated response message
     *
     * @param response The command response string message
     */
    protected void setResponse(String response) {
        this.response = response;
    }
}
