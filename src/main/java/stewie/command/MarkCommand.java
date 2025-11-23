package stewie.command;

import stewie.exceptions.CommandException;
import stewie.exceptions.OutOfRangeException;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.util.Helper;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private final String args;

    /**
     * Creates a new mark command with the given arguments.
     *
     * @param args The command arguments containing the task index to mark.
     */
    public MarkCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the mark command to mark a task as done.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        int idx = Helper.parseIndexOrThrow(args, "mark <index>\nExpected an integer index!");
        String response;
        try {
            response = taskList.markTask(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("There's no task at index " + idx);
        }
        storage.saveTasks(taskList);
        assert response != null : "Final response should not be null";
        return response;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.MARK;
    }
}
