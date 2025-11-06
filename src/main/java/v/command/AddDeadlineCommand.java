package v.command;

import java.time.LocalDate;

import v.storage.Storage;
import v.task.Deadline;
import v.task.DukeException;
import v.task.Task;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Command to add a new deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    /**
     * Creates a new AddDeadlineCommand.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date.
     */
    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes this command by creating a new {@code Deadline} and adding it to the list,
     * then persisting the updated list.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to display feedback.
     * @param storage The storage used to persist changes.
     * @return False to continue running the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new Deadline(description, by);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }
}
