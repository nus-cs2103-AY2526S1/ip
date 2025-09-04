package evansbot.command;

import evansbot.Exceptions.EvansBotException;
import evansbot.Exceptions.InvalidDeadlineException;
import evansbot.task.Deadline;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 * When executed, this command creates a new Deadline task with the specified
 * description and due date, and adds it to the provided TaskList.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs an AddDeadlineCommand with the specified description and due date.
     *
     * @param description Description of the deadline task.
     * @param by Due date of the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by creating a task and adding it to the task list.
     * If either the description or due date is empty, throws an InvalidDeadlineException.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user (not used in this command).
     * @param storage Storage used to save the updated task list.
     * @return String of that AddDeadlineCommand.
     * @throws EvansBotException If the description or due date is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new InvalidDeadlineException();
        }
        return tasks.addTask(new Deadline(description, by));
    }
}
