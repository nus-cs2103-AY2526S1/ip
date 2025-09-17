package commands;

import app.Messages;
import app.TaskList;
import errors.BoopError;
import tasks.Task;

/**
 * This command marks a task in the task list as not completed by its index.
 */
public class CommandTaskUnmark extends Command {
    private final int index;
    private Task task;
    private String command;

    /**
     * Creates a Unmark command using the given user input.
     *
     * @param input Raw user input string containing the index of the task to unmark
     */
    public CommandTaskUnmark(String input) {
        this.command = input;

        index = CommandHelpers.getIndexArgument(input);
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        if (!tasklist.isValidIndex(index)) {
            throw new BoopError(Messages.ERROR_INVALID_INDEX);
        }

        task = tasklist.unmark(index);
        tasklist.setStateChangeCommmandString(command);
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_UNMARK.formatted(task.toString());
    }
}
