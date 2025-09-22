package broccoli.Command;

import broccoli.Storage;
import broccoli.TaskList;
import broccoli.Tasks.Task;
import broccoli.Ui;


public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the unmark command to set a task as not completed.
     * Validates the index, retrieves the specified task, marks it as undone,
     * saves the updated task list to storage, and returns a confirmation
     * message showing the task with its updated status icon.
     *
     * @param taskList The task list containing the task to be unmarked
     * @param ui The user interface for displaying messages
     * @param storage The storage system for persisting the updated task status
     * @return A confirmation message showing the task has been marked as not done,
     *         or an error message if the index is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try{
        if (index > taskList.getList().size()) {
            throw new IllegalArgumentException("Task does not exist, please re-enter a valid one!");
        }
        Task markTask = taskList.getList().get(index - 1);
        markTask.markAsUndone();
        storage.writeToFile();
        String output = "OK, I've marked this task as not done yet:\n" + markTask.toString();
        return output;
    } catch(IllegalArgumentException e) {
        return e.getMessage();
    }
    }

}
