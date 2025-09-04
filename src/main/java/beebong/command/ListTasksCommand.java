package beebong.command;

import beebong.exception.BBongException;
import beebong.storage.Storage;
import beebong.task.TaskList;
/**
 * Represents an abstract Command for listing tasks from the task list.
 */
public abstract class ListTasksCommand extends Command {
    /**
     * Creates a filtered or transformed version of the given {@link TaskList}.
     *
     * <p>Subclasses should implement this method to specify how tasks should
     * be selected or transformed before being displayed to the user.</p>
     *
     * @param taskList the original list of tasks.
     * @return a new {@code TaskList} containing the tasks to be displayed.
     */
    protected abstract TaskList createTaskList(TaskList taskList);

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws BBongException {
        String emptyListMessage = "Bong! I searched high and low… still nothing to show right now.";

        // If there are no Tasks to list
        if (taskList.length() == 0) {
            return emptyListMessage;
        }

        TaskList updatedList = createTaskList(taskList);
        // If there are no Tasks to list
        if (updatedList.length() == 0) {
            return emptyListMessage;
        }

        return "Bing! Found these tasks for you:\n" + updatedList.toString().trim();
    }
}
