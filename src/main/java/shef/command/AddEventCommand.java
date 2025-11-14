package shef.command;

import shef.exception.InvalidArgumentException;
import shef.storage.Storage;
import shef.task.EventTask;
import shef.tasklist.TaskList;

/**
 * Command that adds event tasks.
 */
public class AddEventCommand extends AddCommand {
    private static final String FORMAT = "event TASK_NAME /from START /to END";

    private static final String FROM_FLAG = " /from ";
    private static final String TO_FLAG = " /to ";

    public AddEventCommand(String arg) {
        super(arg);
    }

    public static String getFormat() {
        return FORMAT;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        int fromIndex = args.indexOf(FROM_FLAG);
        int toIndex = args.indexOf(TO_FLAG);

        // Check valid
        boolean hasFlags = fromIndex != -1 && toIndex != -1;
        boolean hasTaskDesc = fromIndex != 0;
        boolean hasFromArg = hasFlags && (fromIndex != toIndex - FROM_FLAG.length());
        boolean hasToArg = hasFlags && (toIndex != args.length() - TO_FLAG.length());

        if (!hasFlags || !hasTaskDesc || !hasFromArg || !hasToArg) {
            throw new InvalidArgumentException("Error! Usage: " + FORMAT);
        }

        // Create task
        String taskName = args.substring(0, fromIndex);
        String start = args.substring(fromIndex + FROM_FLAG.length(), toIndex);
        String end = args.substring(toIndex + TO_FLAG.length());
        String res = tasks.add(new EventTask(taskName, start, end));
        storage.write(tasks.toCsvString());
        return res;
    }
}
