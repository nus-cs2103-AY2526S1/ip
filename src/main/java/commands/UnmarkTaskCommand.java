package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Task;
import ui.Ui;


/**
 * The UnmarkTaskCommand class represents a command to unmark a task as not done in the task list.
 * It accepts a raw argument string (task number) and parses/validates in execute().
 */
public class UnmarkTaskCommand extends Command {

    private final String rawArg; // raw argument string (expected to be a number)

    public UnmarkTaskCommand(String rawArg) {
        this.rawArg = rawArg;
    }

    /**
     * Executes the UnmarkTaskCommand. This method parses the task number, validates it,
     * unmarks the corresponding task as not done, and saves the updated task list to storage.
     * If the task number is invalid, a JackException is thrown.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (rawArg == null || rawArg.trim().isEmpty()) {
            throw new JackException("Task number is required for unmark command.");
        }
        int num;
        try {
            num = Integer.parseInt(rawArg.trim());
        } catch (NumberFormatException e) {
            throw new JackException("Invalid task number. Please enter a valid task number.");
        }
        int taskNumber = num - 1; // convert to 0-based index
        if (taskNumber < 0 || taskNumber >= taskList.getTaskCount()) {
            throw new JackException("Invalid task number. Please enter a valid task number.");
        }
        // Unmark the task as not done in the TaskList
        taskList.unmark(taskNumber);

        // Get the task and save
        Task task = taskList.getTasks().get(taskNumber);
        storage.saveTasks(taskList);
        return ui.showUnmark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
