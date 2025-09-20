package rafayel.command;

import rafayel.Rafayel;
import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.Task;
import rafayel.task.TaskList;

/**
 * Delete Command that deletes task with the task number.
 */
public class DeleteCommand extends Command {

    /* Stores the task number to delete. */
    private final int taskNumber;

    /**
     * Constructor for deleting tasks.
     *
     * @param taskNumber the task number to delete from the list of tasks.
     * @throws RafayelException if the input cannot be parsed into a valid task number.
     */
    public DeleteCommand(String taskString) throws RafayelException {
        super(CommandHandle.CommandType.DELETE);
        try {
            this.taskNumber = Integer.parseInt(taskString.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new RafayelException(
                    "Please state the task number so I can delete the task for you :c e.g. (delete 1)");
        }
    }

    /**
     * Executes the delete command by removing the specified task.
     *
     * @param tasks the task list.
     * @param storage the storage handler.
     * @return confirmation message of the deleted task.
     * @throws RafayelException if the task number is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RafayelException {
        if (taskNumber < 0 || taskNumber >= tasks.getSize()) {
            throw new RafayelException(Rafayel.INVALID_TASK_NUM);
        }

        Task deletedTask = tasks.remove(taskNumber);
        storage.save(tasks.getAll());

        return "Okay, I've removed this task from our gallery:\n  " + deletedTask.toString() + "\nWe are now left with "
                + tasks.getSize() + " tasks. A more curated collection, I suppose.";
    }
}