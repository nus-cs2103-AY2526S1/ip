package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.task.Todo;
import choicebot.ui.UI;

/**
 * Represents a command that creates and adds a Todo Event to the task list.
 * A Todo follows the format: todo {description}
 */
public class TodoCommand extends Command {
    protected String description;

    /**
     * Constructs a Todo Event with the given description.
     *
     * @param description Contains name of the Todo Event.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Todo command by creating new Todo instance.
     * The Todo instance is added to given task list and saved to storage.
     * Displays a confirmation message through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @param storage Storage used in current instance.
     * @throws ChoiceBotException If description is null or blank.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws ChoiceBotException {
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("Please add a description for toDo event.");
        }
        Task todoTask = new Todo(description.trim(), false);
        tasks.addTask(todoTask);
        assert tasks.getTaskList().contains(todoTask) : "Todo task was not added to task list";
        storage.saveFile(tasks);
        return ui.addTaskMessage(todoTask, tasks);
    }
}

