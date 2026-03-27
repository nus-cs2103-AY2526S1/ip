package instruction;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import util.ShrekException;

/**
 * Represents an instruction to delete a task from the task list.
 * This instruction handles the removal of tasks by their index position.
 */
public class DeleteInstruction extends Instruction {
    private int index;

    /**
     * Constructs a DeleteInstruction with the specified task index to be deleted.
     *
     * @param index the zero-based index of the task to be deleted
     */
    public DeleteInstruction(int index) {
        this.index = index;
    }

    /**
     * Executes the delete instruction by removing the task at the specified index,
     * saving the updated list to storage, and returning a confirmation message.
     *
     * @param tasks   the task list from which the task will be removed
     * @param ui      the user interface for generating messages
     * @param storage the storage system for persisting task data
     * @return confirmation message about the deleted task
     * @throws ShrekException if the index is invalid or out of bounds
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException {
        Task removedTask = tasks.remove(index);
        storage.save(tasks.getAllTasks());
        return ui.printDeleteTask(tasks.getAllTasks(), removedTask);
    }
}
