package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.Ui;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

/**
 * Represents a delete command that contains information on task number and storage.
 * Upon execution, task corresponding to task number is deleted from tasklist.
 * Deletion completion message is saved and changes made to task list are saved to storage.
 */
public class DeleteCommand extends Command {
    private int taskNumber;
    private Storage storage;

    /**
     * Constructs a DeleteCommand object with given details.
     *
     * @param taskList Tasklist to delete task from.
     * @param taskNumber Task number of task to be deleted.
     * @param storage Storage where changes made are saved.
     */
    public DeleteCommand(TaskList taskList, int taskNumber, Storage storage, Ui ui) {
        super.taskList = taskList;
        super.ui = ui;
        this.taskNumber = taskNumber;
        this.storage = storage;
    }

    /**
     * Deletes task from tasklist, prints deletion completion message and saves changes into storage.
     * @throws StorageOperationException {@inheritDoc}
     */
    @Override
    public void execute() throws StorageOperationException {
        Task task = taskList.deleteTask(taskNumber);
        assert task != null : "task should not be null";
        storage.saveTasks(taskList);
        message = ui.showTaskDeletedMessage(task, taskList.getSize());
    }

    @Override
    public String getMessage() {
        return message;
    }
}
