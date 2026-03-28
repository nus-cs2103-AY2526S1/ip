package choicebot.command;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that deletes a task from tasklist.
 */
public class DeleteCommand extends Command {
    protected String description;

    /**
     * Constructs a DeleteCommand with the given task index.
     *
     * @param description Index of task to be deleted from task list.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the delete command by deleting specified task number from task list.
     * The task instance is also deleted from storage.
     * Displays a confirmation message through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @param storage Storage used in current instance.
     * @throws ChoiceBotException If index of task is not found in task list, or if description is blank.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws ChoiceBotException {
        try {
            handleDescription();
            handleTaskList(tasks);
            int taskNumber = Integer.parseInt(description.trim()) - 1;
            handleTaskNumber(taskNumber, tasks);
            Task task = tasks.getTask(taskNumber);
            tasks.deleteTask(task);
            assert !tasks.getTaskList().contains(task) : "Task was not deleted from task list";
            storage.saveFile(tasks);
            return UI.deleteTaskMessage(task, tasks);
        } catch (NumberFormatException e) {
            throw new ChoiceBotException("Sorry! Task number must be an integer.");
        }
    }

    /**
     * Throws a ChoiceBotException if description is null or blank.
     */
    public void handleDescription() throws ChoiceBotException {
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException("Please provide a task number to delete.");
        }
    }

    /**
     * Throws a ChoiceBotException if task list is empty.
     */
    public void handleTaskList(TaskList tasks) throws ChoiceBotException {
        if (tasks.isEmpty()) {
            throw new ChoiceBotException("No tasks available to delete.");
        }
    }

    /**
     * Throws a ChoiceBotException if task number is invalid.
     */
    public void handleTaskNumber(int taskNumber, TaskList tasks) throws ChoiceBotException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new ChoiceBotException(String.format(
                    "Invalid task number. Please input a task number from 1 to %d",
                    tasks.size()));
        }
    }
}
