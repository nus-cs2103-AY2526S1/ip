package nina.command;

import nina.CommandException;
import nina.task.Task;
import nina.task.TaskList;
/**
 * Represents a UnmarkCommand that can unmark a task's done status.
 */
public class UnmarkCommand implements Command {
    protected int index;

    /**
     * Constructs a UnmarkCommand object.
     *
     * @param index index of the task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) throws CommandException {
        try {
            Task t = tasks.get(index - 1);
            t.unmarkDone();
            return "Ok, I've marked this task as not done yet:\n" + t;
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("That task doesn't exist.");
        }
    }
}
