package bestbot.command;

import bestbot.exception.BestbotException;
import bestbot.Ui;
import bestbot.Storage;
import bestbot.task.Task;
import bestbot.task.TaskList;
import bestbot.task.Deadline;

/**
 * Adds a new {@link Deadline} task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Creates an {@code AddDeadlineCommand} with the given description and deadline.
     *
     * @param description Description of the task.
     * @param by          Deadline of the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by creating a {@link Deadline} task and adding it to the task list.
     * Updates the UI and saves the task list to storage.
     *
     * @param tasks   The task list to add the new deadline task into.
     * @param ui      The UI used to display feedback.
     * @param storage The storage used to save tasks persistently.
     * @throws BestbotException If description or deadline is blank.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BestbotException {
        if (description.isBlank() || by.isBlank()) {
            throw new BestbotException("Deadline needs a description and /by <date>.");
        }

        Task deadline = new Deadline(description, by);
        tasks.add(deadline);

        ui.showTaskAdded(deadline, tasks.size());
        storage.save(tasks.getTasks());
    }

    /**
     * Returns whether this command causes the program to exit.
     *
     * @return Always {@code false}, since adding a deadline does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
