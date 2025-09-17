package commands;

import app.Messages;
import app.TaskList;
import errors.BoopError;
import tasks.Task;

/**
 * This command deletes a task from the task list by its index.
 */
public class CommandTaskDelete extends Command {
    private final int index;
    private Task task;
    private int taskSize;
    private String command;

    /**
     * Creates a Delete command using the given user input.
     *
     * @param input Raw user input string containing the index of the task to delete
     */
    public CommandTaskDelete(String input) {
        this.command = input;

        index = CommandHelpers.getIndexArgument(input);
    }

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        if (!tasklist.isValidIndex(index)) {
            throw new BoopError(Messages.ERROR_INVALID_INDEX);
        }

        task = tasklist.deleteTask(index);
        tasklist.setStateChangeCommmandString(command);
        taskSize = tasklist.getTaskslistLength();
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_DELETE.formatted(task.toString(), taskSize);
    }
}
