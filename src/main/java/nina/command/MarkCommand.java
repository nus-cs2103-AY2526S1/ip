package nina.command;

import nina.CommandException;
import nina.task.Task;
import nina.task.TaskList;
/**
 * Represents a MarkCommand that can mark a task as done in the TaskList.
 */
public class MarkCommand implements Command {
    protected int index;

    /**
     * Constructs a MarkCommand object.
     *
     * @param index index of the task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) throws CommandException {
        try {
            Task t = tasks.get(index - 1);
            t.markDone();
            return "Nice! I've marked this task as done:\n  " + t;
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("That task doesn't exist.");
        }
    }
}
