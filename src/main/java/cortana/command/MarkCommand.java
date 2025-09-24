package cortana.command;

import java.io.IOException;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;

/**
 * Marks a task as done based on its index.
 */
public class MarkCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs a cortana.command.MarkCommand for marking a specific task.
     *
     * @param taskNumber The index of the task to mark (1-based)
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark the specified task as done. Updates the task list and persists the
     * mark status using cortana.storage.FileHandler.
     */
    @Override
    public String execute(TaskList taskList, FileHandler fileHandler)
            throws CortanaException, IOException {
        String output = taskList.mark(taskNumber);
        fileHandler.saveMarkValue(taskNumber, "1");
        return output;
    }
}
