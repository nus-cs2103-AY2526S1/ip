package evansbot.command;

import evansbot.Exceptions.EvansBotException;
import evansbot.Exceptions.InvalidTodoException;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.task.ToDo;
import evansbot.ui.Ui;


/**
 * Represents a command to add a ToDo task to the task list.
 * When executed, this command creates a new ToDo task with the specified
 * description and adds it to the provided TaskList.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand with the specified description and time frame (From and to).
     *
     * @param description Description of the deadline task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by creating a ToDo task and adding it to the task list.
     * If the description throws an InvalidTodoException.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user (not used in this command).
     * @param storage Storage used to save the updated task list.
     * @throws EvansBotException If the description is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException {
        if (description.isEmpty()) {
            throw new InvalidTodoException();
        }
        tasks.addTask(new ToDo(description));
    }
}
