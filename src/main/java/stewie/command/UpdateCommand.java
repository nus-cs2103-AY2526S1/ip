package stewie.command;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.exceptions.OutOfRangeException;
import stewie.storage.Storage;
import stewie.task.Task;
import stewie.task.TaskList;
import stewie.util.Helper;

/**
 * Represents a command to update a task.
 */
public class UpdateCommand implements Command {
    private static final String USAGE_MESSAGE = "update <index> <todo|deadline|event> <desc> ...";
    private final String args;

    public UpdateCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the update command to update a task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        String[] parts = args.trim().split("\\s+", 3);
        if (parts.length < 3) {
            throw new InvalidCommandException(USAGE_MESSAGE);
        }
        String idxString = parts[0];
        String taskType = parts[1];
        String taskArgs = parts[2];

        int index = Helper.parseIndexOrThrow(idxString, USAGE_MESSAGE + "\nExpected an integer index!");

        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = ToDoCommand.parseArgsToToDoTask(taskArgs);
            break;
        case "deadline":
            newTask = DeadlineCommand.parseArgsToDeadlineTask(taskArgs);
            break;
        case "event":
            newTask = EventCommand.parseArgsToEventTask(taskArgs);
            break;
        default:
            throw new InvalidCommandException(USAGE_MESSAGE);
        }

        String response;
        try {
            response = taskList.updateTask(index, newTask);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("There's no task at index " + index);
        }
        storage.saveTasks(taskList);

        assert response != null : "Final response should not be null";
        return response;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.UPDATE;
    }
}
