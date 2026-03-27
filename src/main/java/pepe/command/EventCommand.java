package pepe.command;

import java.io.IOException;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Task;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

/**
 * Command to add an Event task to the task list.
 * <p>
 * Extends the {@link Command} abstract class and implements the {@link #execute(TaskList, Ui, Storage)}
 * method to add the task, update the UI, and save the updated task list.
 */
public class EventCommand extends Command {
    private final Task task;

    /**
     * Constructs an EventCommand with the specified task.
     *
     * @param task the Event task to be added
     */
    public EventCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Adds the Event task to the task list, shows a confirmation message via {@link Ui},
     * and saves the updated task list to {@link Storage}.
     *
     * @param tasks   the task list to add the task to
     * @param ui      the UI object to display messages
     * @param storage the storage object to save the updated task list
     * @throws PepeExceptions if there is an error saving the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            tasks.addTask(this.task);
            super.setResponse(ui.showUiAddEvent(tasks, this.task));
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }
    }
}
