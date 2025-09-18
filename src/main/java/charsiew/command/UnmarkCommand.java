package charsiew.command;

import java.io.IOException;

import charsiew.storage.Storage;
import charsiew.task.Task;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the TaskList.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the given task index.
     *
     * @param index The 1-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the given index as not done, saves the TaskList,
     * and informs the user via the UI.
     *
     * @param tasks   The TaskList containing the task.
     * @param ui      The UI object used to display messages.
     * @param storage The Storage object used to persist the TaskList.
     * @throws IOException If saving to storage fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.get(index - 1);
        t.markAsNotDone();
        storage.save(tasks);
        return ui.showUnmark(t);
    }

    /**
     * Undo a Unmark Command
     *
     * @param tasks   The {@link TaskList} containing all current tasks.
     * @param ui      The {@link Ui} instance responsible for user interaction.
     * @param storage The {@link Storage} instance used to save or load tasks.
     * @throws Exception If an error occurs during command execution.
     */
    @Override
    public void undo(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Command c = new MarkCommand(index);
        c.execute(tasks, ui, storage);
    }

    /**
     * @return true, as an Unmark Command can be undone.
     **/
    @Override
    public boolean canUndo() {
        return true;
    }
}
