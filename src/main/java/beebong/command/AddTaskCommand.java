package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.Task;
import beebong.task.TaskList;
import beebong.ui.UI;

/**
 * Represents an abstract Command for adding a new {@link Task} to the task list.
 */
public abstract class AddTaskCommand extends Command {
    protected String name;

    /**
     * Constructs an {@code AddTaskCommand} with the given task name.
     *
     * @param name the name of the task to be created.
     */
    public AddTaskCommand(String name) {
        this.name = name;
    }

    /**
     * Creates a new {@link Task} based on the specific subclass implementation.
     * <p>
     * Subclasses determines what type of task should be created.
     * </p>
     *
     * @return the newly created task.
     */
    protected abstract Task createTask();

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        // Create new Task
        Task newTask = createTask();
        taskList.addTask(newTask);
        ui.showMessage("Bing! Task added to my list:\n" + newTask + "\nYou now have "
                + taskList.length() + " task(s) " + "buzzing around in the list.");
    }
}
