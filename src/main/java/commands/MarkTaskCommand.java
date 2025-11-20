package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * The MarkTaskCommand class represents a command to mark a task as done in the task list.
 * It now accepts the raw argument string (task number) and parses/validates in execute().
 */
public class MarkTaskCommand extends Command {

    private final String rawArg; // raw argument string (expected to be a number)

    public MarkTaskCommand(String rawArg) {
        this.rawArg = rawArg;
    }

    /**
     * Executes the MarkTaskCommand. Parses the task number, validates it, marks the corresponding
     * task as done, and saves the updated task list to storage. If the task number is invalid,
     * a JackException is thrown.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (rawArg == null || rawArg.trim().isEmpty()) {
            throw new JackException("Task number is required for mark command.");
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
        // Mark the task as done in the TaskList
        taskList.markAsDone(taskNumber);

        // Display success message
        Task task = taskList.getTasks().get(taskNumber);
        System.out.println("Nice! I've marked this task as done:\n" + task.getStatusIcon() + " " + task.getDescription());

        // Save the updated tasks to the file
        storage.saveTasks(taskList);
        return ui.showMark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
