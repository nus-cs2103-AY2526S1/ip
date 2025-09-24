package cortana.command;

import java.io.IOException;

import cortana.exception.CortanaException;
import cortana.storage.FileHandler;
import cortana.task.TaskList;

/**
 * Deletes a task from the list based on its index.
 */
public class DeleteCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs a cortana.command.DeleteCommand for deleting a specific task.
     *
     * @param taskNumber The index of the task to delete (1-based)
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete the specified task. Updates the task list and persists the task
     * deletion using cortana.storage.FileHandler.
     */
    @Override
    public String execute(TaskList taskList, FileHandler fileHandler)
            throws CortanaException, IOException {
        String output = taskList.delete(taskNumber);
        fileHandler.saveDelete(taskNumber);
        return output;
    }
}
