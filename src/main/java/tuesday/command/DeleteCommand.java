package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.Task;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

/**
 * Represent a command to delete a task from the task list.
 * Take an index, finds the corresponding task in the list,
 * remove it, and display a confirmation message to the user.
 */
public class DeleteCommand extends Command {
    private final Integer INDEX;
    private final String SUCCESS_MESSAGE = "Noted. I've removed this task: \n";
    private final String ERROR_MESSAGE = "ERROR: ";

    /**
     * Construct a DeleteCommand with the specified task index.
     *
     * @param index Task index
     */
    public DeleteCommand(String index) {
        this.INDEX = Integer.parseInt(index) - 1;
    }

    /**
     * Print the message when successfully delete a task
     * @param task: deleting task
     * @param tasks: TaskList
     * @return String of response that would be print
     */
    private String printSuccessMessage(Task task, TaskList tasks) {
        String response = SUCCESS_MESSAGE + task.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list";
        System.out.println(response);
        return response;
    }

    /**
     * Delete the specify task from TaskList
     * @param tasks: List of tasks
     * @param index: The index of the task needed to be delete
     * @return
     */
    private Task deleteTaskAtIndex(TaskList tasks, Integer index) {
        Task task = tasks.getTask(index);
        tasks.deleteTask(task);
        return task;
    }


    /**
     * Executes the delete command by removing the task.
     *
     * @param tasks   Task list
     * @param ui      UI handler
     * @param storage Storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = deleteTaskAtIndex(tasks, INDEX);
            printSuccessMessage(task, tasks);
        } catch (NumberFormatException e) {
            ui.showError("Invalid task index! Please enter a number.");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Task index out of range! Please enter a valid index.");
        }

    }

    /**
     * Executes and returns the response.
     *
     * @param tasks   Task list
     * @param ui      UI handler
     * @param storage Storage handler
     * @return Response string
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        String response = "";
        try {
            Task task = deleteTaskAtIndex(tasks, INDEX);
            response = printSuccessMessage(task, tasks);
        } catch (NumberFormatException e) {
            String msg = ERROR_MESSAGE + "Invalid task index.";
            ui.showError(msg);
            return msg;
        } catch (IndexOutOfBoundsException e) {
            String msg = ERROR_MESSAGE + "Task index out of range.";
            ui.showError(msg);
            return msg;
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
