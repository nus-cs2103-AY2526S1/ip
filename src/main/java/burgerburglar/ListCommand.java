package burgerburglar;

/**
 * Represents a command that lists all tasks in the task list.
 * <p>
 * When executed, this command retrieves all tasks from the TaskList
 * and returns a formatted string representing the current tasks.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        return tasks.toString();
    }
}
