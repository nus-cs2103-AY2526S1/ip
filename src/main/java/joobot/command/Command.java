package joobot.command;
import joobot.main.JooException;
import joobot.main.Storage;
import joobot.task.TaskList;

/**
 * Represents a abstract class for command
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Executes the command and returns JooBot's response.
     *
     * @param tasks   the current task list
     * @param storage storage for saving tasks
     * @return a response message for the GUI
     */
    public abstract String execute(TaskList tasks, Storage storage) throws JooException;

    /**
     * Returns true if this command signals the bot should exit.
     */
    public boolean isExit() {
        return isExit;
    }
}
