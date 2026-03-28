package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that unmarks a given task in the tasklist.
 * The task is chosen through the given index.
 */
public class UnmarkCommand extends Command {
    protected String description;

    /**
     * Constructs an UnmarkCommand with the given description.
     *
     * @param description Contains index of task to be unmarked.
     */
    public UnmarkCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Unmark command by unmarking the given task index.
     * The stored task list is also updated with the unmark.
     * Displays a confirmation message through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @param storage Storage used in current instance.
     * @throws ChoiceBotException If task number given is out of range, not given, or task list is empty.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws ChoiceBotException {
        try {
            handleBlankDescription();
            handleEmptyTasklist(tasks);
            int taskNumber = Integer.parseInt(description.trim()) - 1;
            handleTaskNumberInput(taskNumber, tasks);
            Task task = tasks.getTask(taskNumber);
            task.markAsUndone();
            storage.saveFile(tasks);
            return ui.unmarkTaskMessage(task);
        } catch (NumberFormatException e) {
            throw new ChoiceBotException("Sorry! Task number must be an integer.");
        }
    }

    /**
     * Throws a ChoiceBotException if description is null or blank.
     */
    public void handleBlankDescription() throws ChoiceBotException {
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("Please provide a task number to unmark.");
        }
    }

    /**
     * Throws a ChoiceBotException if tasks list is empty.
     */
    public void handleEmptyTasklist(TaskList tasks) throws ChoiceBotException {
        if (tasks.isEmpty()) {
            throw new ChoiceBotException("No tasks available to unmark.");
        }
    }

    /**
     * Throws a ChoiceBotException if task number input is invalid.
     */
    public void handleTaskNumberInput(int taskNumber, TaskList tasks) throws ChoiceBotException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new ChoiceBotException(String.format(
                    "Invalid task number. Please input a task number from 1 to %d",
                    tasks.size()));
        }
    }
}
