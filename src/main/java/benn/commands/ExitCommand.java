package benn.commands;

import benn.tasks.TaskManager;
import benn.messages.MessageManager;

/**
 * Represents a command that terminates Benn the Chatbot.
 *
 * <p>This command is created when the user input matches the
 * {@code bye} keyword. When executed, it signals the chatbot to exit
 * by setting the {@code shouldExit} flag to {@code true} and returns
 * a farewell message.</p>
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new {@code ExitCommand} with the raw user input.
     *
     * @param input the raw user input string that triggered this command
     */
    public ExitCommand(String input) {
        this.input = input;
        this.shouldExit = true;
    }

    /**
     * Executes the exit command, returning a farewell message
     * to the user.
     *
     * @param taskManager the {@link TaskManager}, unused in this command
     * @return a farewell message string
     */
    @Override
    public String execute(TaskManager taskManager) {
        return MessageManager.retrieveByeMessage();
    }
}
