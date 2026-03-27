package pepe.command;

import java.io.IOException;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Task;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

/**
 * Command to add a ToDo task to the task list.
 * <p>
 * Extends the {@link Command} abstract class. When executed, it adds
 * the specified {@link Task} to the {@link TaskList}, updates the UI,
 * and saves the updated task list to storage.
 */
public class TodoCommand extends Command {

    private final Task task;

    /**
     * Creates a new TodoCommand for the specified task.
     *
     * @param task the ToDo task to be added
     */
    public TodoCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the ToDo command.
     * <p>
     * Adds the task to the task list, shows feedback on the UI,
     * and saves the updated task list to storage.
     *
     * @param tasks   the current task list
     * @param ui      the UI object to show feedback
     * @param storage the storage object to save updated tasks
     * @throws PepeExceptions if an I/O error occurs during saving
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            tasks.addTask(this.task);
            super.setResponse(ui.showUiAddToDo(tasks, this.task));
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }
    }

}
