package jimbot.command;

import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

/**
 * Represents a command to greet the user.
 * Returns a greeting message using the provided name.
 *
 * @author limjimin-nus
 */
public class GreetCommand implements Command {
    private final String name;

    /**
     * Constructs a new {@code GreetCommand}.
     *
     * @param name Name of the bot.
     */
    public GreetCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the greet command by returning a greeting message.
     *
     * @param userList Current task list of the user (not used in this command).
     * @param userStorage Storage handler for persisting tasks (not used in this command).
     * @param user UI component for formatting responses.
     * @return A greeting message for the user.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) {
        return user.greet(name);
    }
}
