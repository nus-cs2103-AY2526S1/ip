package commands;

import app.Messages;
import app.TaskList;
import errors.BoopError;
import tasks.Task;

/**
 * This command marks a task in the task list as completed by its index.
 */
public class CommandTaskMark extends Command {
    private final int index;
    private Task task;
    private String command;

    /**
     * Creates a Mark command using the given user input.
     *
     * @param input Raw user input string containing the index of the task to mark
     */
    public CommandTaskMark(String input) {
        this.command = input;

        index = CommandHelpers.getIndexArgument(input);
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        if (!tasklist.isValidIndex(index)) {
            throw new BoopError(Messages.ERROR_INVALID_INDEX);
        }

        task = tasklist.mark(index);
        tasklist.setStateChangeCommmandString(command);
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_MARK.formatted(task.toString());
    }
}
