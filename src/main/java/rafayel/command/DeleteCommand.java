package rafayel.command;

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
            throw new RafayelException("Invalid input format.");
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
        if (taskNumber < 0 || taskNumber > tasks.getSize()) {
            throw new RafayelException("Invalid task number.");
        }

        Task deletedTask = tasks.remove(taskNumber);
        storage.save(tasks.getAll());

        return "Noted. I've removed this task:\n  " + deletedTask.toString() + "\nNow you have " + tasks.getSize()
                + " tasks in the list.";
    }
}