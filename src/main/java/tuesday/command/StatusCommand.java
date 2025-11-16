package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.Task;
import tuesday.task.TaskList;
import tuesday.ui.Ui;
import tuesday.command.CommandEnums.StatusAction;

/**
 * Represent a command to update the completion status of a task
 * Support mark/unmark actions
 */
public class StatusCommand extends Command {

    private final int TASK_INDEX;
    private final StatusAction ACTION;
    private static final String ERROR_MESSAGE = "ERROR: ";

    /**
     * Construct a StatusCommand with the specified operation type and index
     * @param type: MARK or UNMARK
     * @param index: index of the task that we want to alter
     */
    public StatusCommand(StatusAction type, String index) {
        this.ACTION = type;
        this.TASK_INDEX = Integer.parseInt(index) - 1;
    }

    /**
     * Toggle the status of a Task
     * @param task: Specified Task
     * @param action: MARK or UNMARK
     */
    private void toggleStatus(Task task, StatusAction action) {
        switch (action) {
        case MARK:
            task.markDone();
            break;
        case UNMARK:
            task.unmarkDone();
            break;
        default:
            throw new IllegalArgumentException(ERROR_MESSAGE + ACTION);
        }
    }
    /**
     * Execute the status command by marking or unmarking the specified task
     * @param tasks: List of Tasks
     * @param ui: UI
     * @param storage: Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(TASK_INDEX);
            toggleStatus(task, ACTION);
            ListCommand listCommand = new ListCommand();
            listCommand.execute(tasks, ui, storage);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.showError(ERROR_MESSAGE + e.getMessage());
        }
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        String response;
        try {
            Task task = tasks.getTask(TASK_INDEX);
            toggleStatus(task, ACTION);

            ListCommand listCommand = new ListCommand();
            response = listCommand.getResponse(tasks, ui, storage);
            listCommand.execute(tasks, ui, storage);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            response = ERROR_MESSAGE + e.getMessage();
            ui.showError(e.getMessage());
        }
        return response;
    }
    /**
     * Indicate whether this command should exit
     * @return Always false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
