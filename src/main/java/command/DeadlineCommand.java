package command;

import exception.BaymaxException;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import task.TaskType;
import ui.Ui;

/**
 * Represents a command to add a {@link Deadline} task to the task list.
 * A deadline task has a description and a due date/time.
 */
public class DeadlineCommand extends Command {
    private final String desc;
    private final String by;

    /**
     * Creates a new {@code DeadlineCommand} with the specified description and deadline.
     *
     * @param desc the description of the deadline task
     * @param by   the due date/time of the deadline task
     */
    public DeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }

    /**
     * Executes the deadline command by creating a new deadline task,
     * adding it to the task list, saving the updated list to storage,
     * and returning a confirmation message.
     *
     * @param tasks   the task list to add the deadline to
     * @param ui      the user interface to display the confirmation message
     * @param storage the storage to save the updated task list
     * @return a message confirming the task was added, or an error message if failed
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Deadline t = new Deadline(desc, TaskType.DEADLINE, by);
            tasks.add(t);
            storage.save(tasks.getAll());
            return ui.showTaskAdded(t, tasks.size());
        } catch (BaymaxException e) {
            return ui.showError(e.getMessage());
        }
    }
}
