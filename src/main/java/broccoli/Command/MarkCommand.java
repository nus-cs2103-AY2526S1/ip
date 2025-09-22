package broccoli.Command;

import broccoli.Storage;
import broccoli.TaskList;
import broccoli.Tasks.Task;
import broccoli.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the mark command to set a task as completed.
     * Validates the index, retrieves the specified task, marks it as done,
     * saves the updated task list to storage, and returns a confirmation
     * message showing the completed task with its status icon.
     *
     * @param taskList The task list containing the task to be marked
     * @param ui The user interface for displaying messages
     * @param storage The storage system for persisting the updated task status
     * @return A confirmation message showing the task has been marked as done,
     *         or an error message if the index is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (index > taskList.getList().size()) {
                throw new IllegalArgumentException("Task does not exist, please re-enter a valid one!");
            }
            Task markTask = taskList.getList().get(index - 1);
            markTask.markAsDone();
            storage.writeToFile();
            String outPut = "Nice! I've marked this task as done:\n" + markTask.toString();
            return outPut;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }
}