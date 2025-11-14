package shef.command;

import shef.storage.Storage;
import shef.tasklist.TaskList;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {
    public static final String FORMAT = "bye";

    public ExitCommand() {
        super("");
    }

    public static String getFormat() {
        return FORMAT;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "bye!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
