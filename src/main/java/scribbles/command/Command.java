package scribbles.command;

import scribbles.Scribbles;
import scribbles.exception.ScribblesException;
import scribbles.storage.Storage;
import scribbles.tasklist.TaskList;

/**
 * Provides an abstract class for all types of Commands.
 */
public abstract class Command {

    private static final String[] COMMAND_LIST = {
        "bye",
        "list",
        "mark",
        "unmark",
        "delete",
        "todo",
        "deadline",
        "event",
        "find",
        "help"
    };

    public static String[] getCommandList() {
        return COMMAND_LIST;
    }

    /**
     * Executes the respective command based on given scribbles,
     * taskList, and storage.
     *
     * @param scribbles Instance of scribbles to operate on.
     * @param taskList Instance of taskList to operate on.
     * @param storage Instance of storage to operate on.
     * @return Output when command is executed
     * @throws ScribblesException When encountering error during execution.
     */
    public abstract String execute(Scribbles scribbles, TaskList taskList, Storage storage) throws ScribblesException;
}
