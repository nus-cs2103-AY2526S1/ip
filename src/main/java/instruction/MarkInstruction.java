package instruction;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import util.ShrekException;

/**
 * Represents an instruction to mark or unmark a task as done.
 * This instruction handles both marking tasks as completed and unmarking them.
 */
public class MarkInstruction extends Instruction {
    private int index;
    private boolean markAsDone;

    /**
     * Constructs a MarkInstruction with the specified task index and mark status.
     *
     * @param index      the zero-based index of the task to mark/unmark
     * @param markAsDone true to mark as done, false to unmark
     */
    public MarkInstruction(int index, boolean markAsDone) {
        this.index = index;
        this.markAsDone = markAsDone;
    }

    /**
     * Executes the mark instruction by updating the task's completion status,
     * saving the updated list to storage, and returning a confirmation message.
     *
     * @param tasks   the task list containing the task to be updated
     * @param ui      the user interface for generating messages
     * @param storage the storage system for persisting task data
     * @return confirmation message about the marked/unmarked task
     * @throws ShrekException if the index is invalid or out of bounds
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException {
        Task task = tasks.get(index);
        assert task != null : "Task should not be null at index " + index;
        if (markAsDone) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
        storage.save(tasks.getAllTasks());
        return ui.printMarkUnmark(task, markAsDone);
    }
}
