package shef.command;

import shef.exception.InvalidArgumentException;
import shef.storage.Storage;
import shef.tasklist.TaskList;

/**
 * Command that deletes a task.
 */
public class DeleteCommand extends Command {
    private static final String FORMAT = "delete TASK_INDEX";

    public DeleteCommand(String args) {
        super(args);
    }

    public static String getFormat() {
        return FORMAT;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidArgumentException {
        try {
            String res = tasks.deleteTask(Integer.parseInt(args));
            storage.write(tasks.toCsvString());
            return res;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Error! Usage: " + FORMAT);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
