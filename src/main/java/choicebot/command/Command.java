package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that can be executed by ChoiceBot application.
 * Each command type inherits this superclass and overrides the execute method.
 */
public class Command {
    /**
     * Executes the command using the given task list and user interface.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @param storage Storage used in current instance.
     * @throws ChoiceBotException If an error occurs during command execution.
     */
    public String execute(TaskList tasks, UI ui, Storage storage) throws ChoiceBotException {
        return null;
    }
}
