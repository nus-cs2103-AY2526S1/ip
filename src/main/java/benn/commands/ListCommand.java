package benn.commands;

import benn.tasks.TaskManager;
import benn.messages.MessageManager;

/**
 * Represents a command that lists all tasks currently stored
 * in the {@link benn.tasks.TaskManager}.
 *
 * <p>This command is created when the user input matches the
 * {@code list} keyword. When executed, it retrieves the current
 * tasks from the TaskManager and formats them into a user-facing
 * message. This command does not modify the task list and does
 * not signal the chatbot to exit.</p>
 */
public class ListCommand extends Command {

    /**
     * Constructs a new {@code ListCommand} with the raw user input.
     *
     * @param input the raw user input string that triggered this command
     */
    public ListCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Executes the list command by retrieving all tasks from
     * the {@link benn.tasks.TaskManager} and formatting them
     * into a user-facing message.
     *
     * @param taskManager the {@link TaskManager} containing the tasks
     * @return a formatted string containing the list of tasks
     */
    @Override
    public String execute(TaskManager taskManager) {
        return MessageManager.retrieveListMessageFrom(taskManager);
    }
}
