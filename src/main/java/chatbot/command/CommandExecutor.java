package chatbot.command;

import chatbot.exception.BotException;

/**
 * CommandExecutor is an interface for commands that B33PBOP takes.
 */
public interface CommandExecutor {
    /**
     * Returns the String output of executing a user commands
     *
     * @param taskDescription User input for the task.
     * @return Returns a response from the bot in String format.
     * @throws BotException If there is an error with user input.
     */
    String execute(String taskDescription) throws BotException;
}
