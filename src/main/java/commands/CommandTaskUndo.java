package commands;

import app.Messages;
import app.TaskList;
import errors.BoopError;

/**
 * This command undoes the last task-modifying action.
 */
public class CommandTaskUndo extends Command {
    private String undoneCommand;
    private int taskSize;

    @Override
    public void execute(TaskList tasklist) throws BoopError {
        undoneCommand = tasklist.undo();
        taskSize = tasklist.getTaskslistLength();
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_UNDO.formatted(undoneCommand, taskSize);
    }
}
