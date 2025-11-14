package shef.command;

import shef.exception.InvalidArgumentException;
import shef.storage.Storage;
import shef.task.TodoTask;
import shef.tasklist.TaskList;

/**
 * Command that adds todo tasks.
 */
public class AddTodoCommand extends AddCommand {
    private static final String FORMAT = "todo TASK_NAME";

    public AddTodoCommand(String args) {
        super(args);
    }

    public static String getFormat() {
        return FORMAT;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws InvalidArgumentException {
        if (args.isEmpty()) {
            throw new InvalidArgumentException("Error! Usage: " + FORMAT);
        }

        String res = tasks.add(new TodoTask(args));
        storage.write(tasks.toCsvString());
        return res;
    }

}
