package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents an abstract command that can be executed by the George chatbot.
 * This is the base class for all other specific command implementations.
 * Each command knows how to execute itself and provide its command word identifier.
 */
public abstract class Command {
    /**
     * Executes the command using the provided TaskManager.
     *
     * @param manager The TaskManager that handles task operations and state
     * @return The response text that will be sent to the GUI.
     * @throws GeorgeException If an error occurs during command execution
     */
    public abstract String execute(TaskManager manager) throws GeorgeException;
    /**
     * Returns the command word that identifies this specific command type.
     * This is typically the first word entered by the user that triggers this command.
     *
     * @return The command word as a String
     */
    public abstract String getCommandWord();
}
