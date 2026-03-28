package simon.command;

import java.util.ArrayList;

import simon.storage.Storage;
import simon.task.Task;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * A <code>DeleteCommand</code> object contains the index of the task to be removed.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted (0-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index,
     * saving the updated list to storage, and setting the response message.
     *
     * @param tasks The task list to remove the task from.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving tasks.
     * @throws Exception If an error occurs during removal or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task removed = tasks.remove(index);
        storage.save(new ArrayList<>(tasks.getTasks()));
        setString(ui.showDeleteTask(removed, tasks.size()));
    }
}