package scribbles.command;

import scribbles.Scribbles;
import scribbles.exception.ScribblesException;
import scribbles.storage.Storage;
import scribbles.task.Task;
import scribbles.tasklist.TaskList;

/**
 * Provides the command logic to mark a specified task as completed.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a command to mark a specified task as completed.
     *
     * @param taskIndex Task index number to mark from taskList.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) throws ScribblesException {
        try {
            Task task = taskList.markTask(taskIndex);
            taskList.assertValidTaskIndex(taskIndex);
            storage.saveFile(taskList);
            return """
                    Hoorah! I shall mark this task as completed! XD
                      %s
                    """.formatted(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new ScribblesException("I cannot find this task number :(");
        }
    }
}
