package reboot.command;

import reboot.RebootException;
import reboot.Storage;
import reboot.TaskList;
import reboot.gui.Gui;
import reboot.task.Deadline;
import reboot.task.Event;
import reboot.task.Task;
import reboot.task.Todo;
import reboot.task.recurring.RecurringDeadline;
import reboot.task.recurring.RecurringEvent;
import reboot.task.recurring.RecurringTodo;

/**
 * Represents a command to add a new task to the tasklist.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs a new AddCommand with the given Todo task.
     * @param task Todo task to be added to the tasklist.
     */
    public AddCommand(Todo task) {
        this.task = task;
    }

    /**
     * Constructs a new AddCommand with the given Deadline task.
     * @param task Deadline task to be added to the tasklist.
     */
    public AddCommand(Deadline task) {
        this.task = task;
    }

    /**
     * Constructs a new AddCommand with the given Event task.
     * @param task Event task to be added to the tasklist.
     */
    public AddCommand(Event task) {
        this.task = task;
    }

    /**
     * Constructs a new AddCommand with the given RecurringTodo task.
     * @param task RecurringTodo task to be added to the tasklist.
     */
    public AddCommand(RecurringTodo task) {
        this.task = task;
    }

    /**
     * Constructs a new AddCommand with the given RecurringDeadline task.
     * @param task RecurringDeadline task to be added to the tasklist.
     */
    public AddCommand(RecurringDeadline task) {
        this.task = task;
    }

    /**
     * Constructs a new AddCommand with the given RecurringEvent task.
     * @param task RecurringEvent task to be added to the tasklist.
     */
    public AddCommand(RecurringEvent task) {
        this.task = task;
    }

    /**
     * Adds the specified task to the tasklist.
     * Updates the tasklist file via storage.
     * Informs the user via the gui.
     *
     * @param tasks Tasklist takes in the task.
     * @param gui Gui outputs message.
     * @param storage Storage appends line to file.
     */
    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws RebootException {
        tasks.add(task);
        storage.appendLine(task.toFileString());
        return gui.showMessage("Updated" + task
                + "\n" + tasks.size() + " tasks in the list");

    }
}
