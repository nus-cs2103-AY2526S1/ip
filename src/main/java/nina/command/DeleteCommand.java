package nina.command;

import nina.CommandException;
import nina.task.Task;
import nina.task.TaskList;
/**
 * Represents a DeleteCommand that can delete a task from the TaskList.
 */
public class DeleteCommand implements Command {
    protected int index;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param index index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) throws CommandException {
        try {
            Task removed = tasks.delete(index);
            return "Noted. I've removed this task:\n"
                    + "  " + removed + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("That task doesn't exist");
        }
    }
}
