package cortana.command;

import java.io.IOException;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;

/**
 * Unmarks a task (marks it as not done) based on its index.
 */
public class UnMarkCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs a cortana.command.UnMarkCommand for unmarking a specific task.
     *
     * @param taskNumber The index of the task to unmark (1-based)
     */
    public UnMarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to unmark the specified task. Updates the task list and persists the mark
     * status using cortana.storage.FileHandler.
     */
    @Override
    public String execute(TaskList taskList, FileHandler fileHandler)
            throws CortanaException, IOException {
        String output = taskList.unmark(taskNumber);
        fileHandler.saveMarkValue(taskNumber, "0");
        return output;
    }
}
