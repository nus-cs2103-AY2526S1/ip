package shef.command;

import shef.exception.InvalidArgumentException;
import shef.storage.Storage;
import shef.tasklist.TaskList;

/**
 * Command that marks a task as done.
 */
public class MarkCommand extends Command {
    private static final String FORMAT = "mark TASK_INDEX";

    public MarkCommand(String args) {
        super(args);
    }

    public static String getFormat() {
        return FORMAT;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidArgumentException {
        try {
            int idx = Integer.parseInt(args);
            String res = tasks.markAsDone(idx);
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
