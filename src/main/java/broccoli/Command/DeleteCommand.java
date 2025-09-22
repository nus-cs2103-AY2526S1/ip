
package broccoli.Command;

import broccoli.Storage;
import broccoli.TaskList;
import broccoli.Tasks.Task;
import broccoli.Ui;


public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command to remove a task from the list.
     * Validates the index, retrieves the task to be deleted, removes it from
     * the task list, saves the updated list to storage, and returns a confirmation
     * message along with the count of remaining unfinished tasks.
     *
     * @param taskList The task list to remove the task from
     * @param ui The user interface for displaying messages
     * @param storage The storage system for persisting tasks
     * @return A confirmation message showing the deleted task and remaining unfinished count,
     *         or an error message if the index is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try{
        if (index > taskList.getList().size()) {
            throw new IllegalArgumentException("Task does not exist, please re-enter a valid one!");
        }
        Task deleteTask = taskList.getList().get(index - 1);
        taskList.remove(index - 1);
        storage.writeToFile();
        int undone = (int) taskList.getList().stream().filter(a -> !a.getDone()).count();
       String output = "Noted. I've removed this task:\n" + deleteTask.toString() +"\n" +
               "Hurry up! You have " + undone + " tasks unfinished!";
       return output;
    } catch (IllegalArgumentException e) {
        return e.getMessage();
    }
    }

}
