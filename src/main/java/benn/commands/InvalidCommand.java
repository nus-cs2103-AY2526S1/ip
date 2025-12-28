package benn.commands;

import benn.tasks.TaskManager;
import benn.messages.MessageManager;

/**
 * Represents a command that is created when the user input
 * does not match any known or valid command pattern.
 *
 * <p>This command does not alter the state of the
 * {@link benn.tasks.TaskManager} and does not cause the chatbot
 * to exit. When executed, it simply returns an error message
 * indicating that the command was invalid.</p>
 */
public class InvalidCommand extends Command {

    /**
     * Constructs a new {@code InvalidCommand} with the raw user input.
     *
     * @param input the raw user input string that could not be parsed
     *              into a valid command
     */
    public InvalidCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Executes the invalid command by returning a standardized
     * invalid-command error message to the user.
     *
     * @param taskManager the {@link TaskManager}, unused in this command
     * @return an error message indicating the command was invalid
     */
    @Override
    public String execute(TaskManager taskManager) {
        return MessageManager.retrieveInvalidCommandMessage();
    }
}
