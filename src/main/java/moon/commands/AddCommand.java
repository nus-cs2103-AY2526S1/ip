package moon.commands;

import moon.models.Task;

/**
 * Abstract base class for commands that add new tasks.
 * <p>
 * Provides a helper method to append a task to the current task list.
 */
public abstract class AddCommand extends BaseCommand {

    /**
     * Adds the given task to the task list.
     *
     * @param task Task to add
     */
    public void addToStorage(Task task) {
        super.getList().add(task);
    }
}
