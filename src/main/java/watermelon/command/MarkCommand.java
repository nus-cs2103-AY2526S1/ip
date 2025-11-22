package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.Ui;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

/**
 * Represents a mark command that contains information on tasklist, task number and storage.
 * Upon execution, task corresponding to task number is marked as done in tasklist.
 * Mark completion message is saved and changes made are saved to storage.
 */
public class MarkCommand extends Command {
    private int taskNumber;
    private Storage storage;

    /**
     * Constructs a MarkCommand object with given details.
     *
     * @param taskList Tasklist containing task to be marked.
     * @param taskNumber Task number of task to be marked as done.
     * @param storage Storage where changes made are saved.
     */
    public MarkCommand(TaskList taskList, int taskNumber, Storage storage, Ui ui) {
        super.taskList = taskList;
        super.ui = ui;
        this.taskNumber = taskNumber;
        this.storage = storage;
    }

    /**
     * Marks task as done in tasklist, prints mark completion message and saves changes into storage.
     * @throws StorageOperationException {@inheritDoc}
     */
    @Override
    public void execute() throws StorageOperationException {
        Task task = taskList.markTask(taskNumber);
        assert task != null : "task should not be null";
        storage.saveTasks(taskList);
        message = ui.showTaskMarkedMessage(task);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
