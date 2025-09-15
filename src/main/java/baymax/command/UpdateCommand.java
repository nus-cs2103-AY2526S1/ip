package baymax.command;

import baymax.exception.BaymaxException;
import baymax.task.TaskList;

/**
 * Represents a command that updates an existing task in the task list.
 * <p>
 * The update can be one of the following actions:
 * <ul>
 *   <li>mark — mark a task as completed</li>
 *   <li>unmark — unmark a completed task</li>
 *   <li>delete — remove a task from the list</li>
 * </ul>
 * </p>
 */
public class UpdateCommand extends Command {

    private String type;
    private int index;

    public UpdateCommand(String action, int index) {
        this.type = action;
        this.index = index;
    }

    /**
     * Executes the update command by performing the specified action
     * on the task at the given index.
     *
     * @param tasks The {@link TaskList} containing all tasks.
     * @return A confirmation message describing the result of the update command.
     * @throws BaymaxException If the index is invalid or the update cannot be performed.
     */
    @Override
    public String execute(TaskList tasks) throws BaymaxException {
        switch (type) {
        case "mark":
            return tasks.mark(index);
        case "unmark":
            return tasks.unmark(index);
        case "delete":
            return tasks.delete(index);
        default:
            throw new BaymaxException.InvalidCommandException();
        }
    }
}
