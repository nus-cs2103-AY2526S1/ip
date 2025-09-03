package evansbot.command;

import evansbot.Exceptions.EvansBotException;
import evansbot.Exceptions.InvalidEventException;
import evansbot.task.Event;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;


/**
 * Represents a command to add an event task to the task list.
 * When executed, this command creates a new Event task with the specified
 * description and time frame (from and to), and adds it to the provided TaskList.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;
    /**
     * Constructs an AddEventCommand with the specified description and time frame (From and to).
     *
     * @param description Description of the deadline task.
     * @param from beginning date/time of task.
     * @param to ending date/time of task.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by creating a Event task and adding it to the task list.
     * If either the description or from or to is empty, throws an InvalidEventException.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user (not used in this command).
     * @param storage Storage used to save the updated task list.
     * @throws EvansBotException If the description or from or to is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EvansBotException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidEventException();
        }
        tasks.addTask(new Event(description, from, to));
    }
}
