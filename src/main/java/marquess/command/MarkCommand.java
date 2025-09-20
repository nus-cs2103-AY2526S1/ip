package marquess.command;

import marquess.Storage;
import marquess.TaskList;
import marquess.exception.MarquessException;

/**
 * Command to add a task to the task list.
 */
public class MarkCommand extends Command {
    private final Integer[] indices;
    private final boolean isDone;

    /**
     * Constructor for command to mark task as complete or incomplete.
     *
     * @param isDone Whether to mark task as complete
     * @param indices Indices of tasks to be marked.
     */
    public MarkCommand(boolean isDone, Integer ... indices) {
        this.isDone = isDone;
        this.indices = indices;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) throws MarquessException {
        StringBuilder res = new StringBuilder();
        if (isDone) {
            for (int idx : indices) {
                res.append(taskList.markTask(idx)).append("\n");
            }
        } else {
            for (int idx : indices) {
                res.append(taskList.unmarkTask(idx)).append("\n");
            }
        }
        storage.save(taskList);
        return res.toString();
    }
}
