package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.Ui;
import watermelon.exception.InvalidDateTimeException;
import watermelon.exception.InvalidInputException;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

/**
 * Represents an event command that contains information on event task (task description, start & end date and storage).
 * Upon execution, event task is added to taskList, task added message is saved and task is saved to storage.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;
    private Storage storage;

    /**
     * Constructs a EventCommand object with given details.
     *
     * @param taskList Tasklist to add deadline task to.
     * @param description Description of event task.
     * @param from Start date & time of event task.
     * @param to End date & time of event task.
     * @param storage Storage where event task is stored.
     */
    public EventCommand(TaskList taskList, String description, String from, String to, Storage storage, Ui ui) {
        super.taskList = taskList;
        super.ui = ui;
        this.description = description;
        this.from = from;
        this.to = to;
        this.storage = storage;
    }

    /**
     * Adds event task to tasklist, prints task completion message and saves the task into storage.
     * @throws StorageOperationException {@inheritDoc}
     */
    @Override
    public void execute() throws StorageOperationException, InvalidDateTimeException, InvalidInputException {
        Task task = taskList.addEvent(description, from, to);
        assert task != null : "task should not be null";
        storage.saveTasks(taskList);
        message = ui.showTaskAddedMessage(task, taskList.getSize());
    }

    @Override
    public String getMessage() {
        return message;
    }
}
