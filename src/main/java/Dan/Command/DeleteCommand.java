package Dan.Command;

import Dan.Task.Task;
import Dan.Task.TaskList;

public class DeleteCommand extends Command {
    int index;

    /**
     * Constructs a DeleteCommand that will delete the task at the specified index.
     *
     * @param i the index of the task to be deleted (1-based indexing)
     */
    public DeleteCommand(int i) {
        this.index = i;
    }

    /**
     * Returns the command type for this command.
     *
     * @return CommandType.DELETE indicating this is a delete command
     */
    public CommandType getType() {
        return  CommandType.DELETE;
    }

    /**
     * Executes the delete command by removing the task at the specified index
     * from the task list. Validates that the index is within valid bounds before deletion.
     *
     * @param tasks the task list from which the task will be deleted
     * @return a confirmation message showing the deleted task and the updated
     *         task count, or an error message if the index is invalid
     * @throws AssertionError if the index is not greater than 0 (after validation)
     */
    public String execute(TaskList tasks) {

        if (index < 0) {
            return "Please key in a valid item number that is more than zero";
        } else if(index > tasks.size()) {
            return "This item number exceeds the tasklist size";
        }

        assert index > 0;
        Task task = tasks.getTask(index);
        tasks.delete(index);
        return "Noted. I've removed this task: \n " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
