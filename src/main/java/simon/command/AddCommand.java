package simon.command;

import java.util.ArrayList;

import simon.storage.Storage;
import simon.task.Task;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Represents a command to add a new task to the task list.
 * An <code>AddCommand</code> object contains the task to be added.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list,
     * saving the updated list to storage, and setting the response message.
     *
     * @param tasks The task list to add the task to.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving tasks.
     * @throws Exception If an error occurs during saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.add(task);
        storage.save(new ArrayList<>(tasks.getTasks()));
        setString(ui.showAddTask(task, tasks.size()));
    }
}