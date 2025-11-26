package banana.command;

import java.io.IOException;

import banana.exceptions.BananaException;
import banana.utils.Storage;
import banana.utils.TaskList;

/**
 * Represents command that can be executed by the BananaBot.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage) throws BananaException, IOException;
}
