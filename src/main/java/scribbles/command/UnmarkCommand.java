package scribbles.command;

import scribbles.Scribbles;
import scribbles.exception.ScribblesException;
import scribbles.storage.Storage;
import scribbles.task.Task;
import scribbles.tasklist.TaskList;

/**
 * Provides the command logic to unmark a specified task as completed.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a command to unmark a specified task as completed.
     *
     * @param taskIndex Task index number to unmark from taskList.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) throws ScribblesException {
        try {
            Task task = taskList.unmarkTask(taskIndex);
            taskList.assertValidTaskIndex(taskIndex);
            storage.saveFile(taskList);
            return """
                    Aw man.. I shall unmark this task for you. D:
                     %s
                    """.formatted(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new ScribblesException("I cannot find this task number :(");
        }
    }
}
