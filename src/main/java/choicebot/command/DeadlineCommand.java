package choicebot.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import choicebot.ChoiceBotException;
import choicebot.storage.Storage;
import choicebot.task.Deadline;
import choicebot.task.Task;
import choicebot.task.TaskList;
import choicebot.ui.UI;

/**
 * Represents a command that creates and adds a Deadline task to tasklist.
 * A deadline follows the format: deadline {description} /by {yyyy-mm-dd}
 */
public class DeadlineCommand extends Command {
    protected String description;

    /**
     * Constructs a DeadlineCommand with the given description.
     *
     * @param description Contains name of Deadline, and due date.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the deadline command by creating new deadline instance.
     * The deadline instance is added to given task list and saved to storage.
     * Displays a confirmation message through given UI.
     *
     * @param tasks Task list in current instance.
     * @param ui User interface in current instance.
     * @param storage Storage used in current instance.
     * @throws ChoiceBotException If command does not have /by, or deadline name or due date is blank.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws ChoiceBotException {
        handleDescription();
        String dueDateString = description.split("/by ")[1].trim();
        String deadlineName = description.split("/by ")[0].trim();
        handleDeadlineName(dueDateString, deadlineName);

        try {
            LocalDate dueDate = LocalDate.parse(dueDateString);
            Task deadlineTask = new Deadline(deadlineName, false, dueDate);
            tasks.addTask(deadlineTask);
            assert tasks.getTaskList().contains(deadlineTask) : "Deadline task was not added to task list";
            storage.saveFile(tasks);
            return ui.addTaskMessage(deadlineTask, tasks);
        } catch (DateTimeParseException e) {
            throw new ChoiceBotException("Please use format \"yyyy-mm-dd\" for deadline.");
        }
    }

    /**
     * Throws a ChoiceBotException if description does not contain /by flag.
     */
    public void handleDescription() throws ChoiceBotException {
        if (!description.contains("/by ")) {
            throw new ChoiceBotException(
                    "Please follow format: deadline {description} /by {yyyy-mm-dd}.");
        }
    }

    /**
     * Throws a ChoiceBotException if deadline name or due date is blank.
     */
    public void handleDeadlineName(String deadlineName, String dueDateString) throws ChoiceBotException {
        if (deadlineName.isBlank() || dueDateString.isBlank()) {
            throw new ChoiceBotException(
                    "Please follow format: deadline {description} /by {yyyy-mm-dd}.");
        }
    }
}
