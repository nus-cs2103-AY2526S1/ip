package scribbles.command;

import scribbles.Scribbles;
import scribbles.exception.ScribblesException;
import scribbles.exception.UnknownCommandException;
import scribbles.storage.Storage;
import scribbles.tasklist.TaskList;

/**
 * Provides the command logic when encountering an unknown command.
 */
public class UnknownCommand extends Command {
    private final String command;

    /**
     * Constructs a command to throw an UnknownCommandException.
     *
     * @param command Unknown command given.
     */
    public UnknownCommand(String command) {
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) throws ScribblesException {
        throw new UnknownCommandException(command);
    }
}
