package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that displays the current task list.
 */
public class ListCommand extends Command {
    protected String description;

    /**
     * Constructs a ListCommand.
     *
     * @param description Must be empty, not required here.
     */
    public ListCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the List command by displaying the current task list through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @param storage Storage used in current instance.
     * @throws ChoiceBotException If description is not blank.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws ChoiceBotException {
        if (!description.isBlank()) {
            throw new ChoiceBotException("Please only type the command \"list\"");
        }
        return ui.displayList(tasks.getTaskList(), false);
    }
}
