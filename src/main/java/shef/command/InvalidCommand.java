package shef.command;

import shef.storage.Storage;
import shef.tasklist.TaskList;

/**
 * Object to represent an invalid/unknown command.
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {
        super("");
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Unknown command!";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
