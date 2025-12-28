package benn.commands;

import benn.messages.MessageManager;
import benn.tasks.TaskManager;

public class InitializationCommand extends Command {
    /**
     * Initializes a command that lists all tasks.
     *
     * @param input User input.
     */
    public InitializationCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Execution instructions for the command.
     * Welcomes the user and shows all current tasks and returns the message for Duke to show.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        return String.format("%s", MessageManager.retrieveIntroductionMessage());
    }
}
