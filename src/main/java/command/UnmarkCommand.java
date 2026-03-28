package command;

import exceptions.InvalidIndexException;
import exceptions.MarkExceptions;
import task.TaskList;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String arg, TaskList tasklist) {
        super(arg, tasklist);
    }

    /**
     * Unmarks the task at the given index.
     *
     * @throws InvalidIndexException if the index is not a number or out of range
     */
    public void execute() throws MarkExceptions {
        try {
            int index = Integer.parseInt(arg);
            if (!taskList.isValidIndex(index)) {
                throw new InvalidIndexException("This task does not exist!");
            }
        } catch (NumberFormatException e) {
            throw new InvalidIndexException("unmark requires an index");
        }

        taskList.unmark(arg);
    }
}
