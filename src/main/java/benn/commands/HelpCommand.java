package benn.commands;

import benn.tasks.TaskManager;
import benn.messages.MessageManager;

/**
 * Displays a help/usage message to the user.
 *
 * <p>This command does not modify the {@link benn.tasks.TaskManager}
 * and does not exit the app.</p>
 */
public class HelpCommand extends Command {

    /**
     * Constructs a new {@code HelpCommand} with the raw user input.
     *
     * @param input the raw user input string
     */
    public HelpCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Executes the help command by returning a standardized help message.
     *
     * @param taskManager the {@link TaskManager}, unused here
     * @return a help/usage message for the user
     */
    @Override
    public String execute(TaskManager taskManager) {
        return MessageManager.retrieveHelpMessage();
    }
}
