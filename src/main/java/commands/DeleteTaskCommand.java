package commands;

import exceptions.JackException;
import storage.Storage;
import tasklists.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * The DeleteTaskCommand class represents a command to delete a task from the task list.
 * It now accepts a raw argument string (task number) and parses/validates in execute().
 */
public class DeleteTaskCommand extends Command {
    private final String rawArg;

    /**
     * Constructs a DeleteTaskCommand with the raw argument string (expected a number).
     *
     * @param rawArg raw argument string (may be null)
     */
    public DeleteTaskCommand(String rawArg) {
        this.rawArg = rawArg;
    }

    /**
     * Executes the DeleteTaskCommand. Parses and validates the task number, deletes the task,
     * saves the task list, and returns the UI message. Throws JackException for invalid input.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JackException {
        if (rawArg == null || rawArg.trim().isEmpty()) {
            throw new JackException("Task number is required for delete command.");
        }
        int num;
        try {
            num = Integer.parseInt(rawArg.trim());
        } catch (NumberFormatException e) {
            throw new JackException("Invalid task number. Please provide a valid task number.");
        }
        int taskNumber = num - 1; // convert to 0-based index
        if (taskNumber < 0 || taskNumber >= taskList.getTaskCount()) {
            throw new JackException("Invalid task number. Please provide a valid task number.");
        }
        // Delete the task from the task list
        Task removedTask = taskList.getTasks().get(taskNumber); // Get the task to be removed
        taskList.deleteTask(taskNumber); // Delete the task
        // Provide feedback to the user
        System.out.println("Noted. I've removed this task:\n" + removedTask);
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
        // Save the updated task list to storage
        storage.saveTasks(taskList);
        return ui.showDelete(removedTask, taskList.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false; // This command doesn't cause the program to exit
    }
}
