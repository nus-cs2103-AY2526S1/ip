package Dan.Command;

import Dan.Task.TaskList;
import Dan.Task.Task;

public class MarkCommand extends Command {
    int index;

    /**
     * Constructs a MarkCommand that will mark or unmark the task at the specified index.
     *
     * @param i the index of the task to be marked/unmarked (1-based indexing)
     */
    public MarkCommand(int i) {
        this.index = i;
    }

    /**
     * Returns the command type for this command.
     *
     * @return CommandType.MARK indicating this is a mark command
     */
    public CommandType getType() {
        return CommandType.MARK;
    }

    /**
     * Executes the mark command by toggling the completion status of the task
     * at the specified index. Validates that the index is within valid bounds.
     *
     * @param tasks the task list containing the task to be marked/unmarked
     * @return a confirmation message indicating whether the task was marked as done
     *         or undone, or an error message if the index is invalid
     * @throws AssertionError if the index is not greater than 0 (after validation)
     */
    public String execute(TaskList tasks) {
        if (index < 0) {
            return "Please key in a valid item number that is more than zero";
        } else if(index > tasks.size()) {
            return "This item number exceeds the tasklist size";
        }

        assert index > 0;
        tasks.mark(index);
        Task task = tasks.getTask(index);

        if (task.isDone()) {
            return "Got it. I've marked this task as done: \n " + task + "\n";
        } else {
            return "Got it. I've marked this task as undone: \n " + task + "\n";
        }
    }
}
