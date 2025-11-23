
package stewie.command;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.task.ToDoTask;

/**
 * Represents a command to add a todo task.
 */
public class ToDoCommand implements Command {
    private final String args;

    /**
     * Creates a new todo command with the given arguments.
     *
     * @param args The command arguments containing task description.
     */
    public ToDoCommand(String args) {
        this.args = args;
    }

    /**
     * Handles the todo command to add a new todo task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        ToDoTask task = parseArgsToToDoTask(args);
        String response = taskList.addTask(task);
        storage.saveTasks(taskList);
        assert response != null : "Final response should not be null";
        return response;
    }

    /**
     * Parses the given arguments to create a {@link ToDoTask}.
     *
     * @param args The command arguments containing the task description.
     * @return A new {@link ToDoTask} with the given description.
     * @throws InvalidCommandException If the description is blank or missing.
     */
    public static ToDoTask parseArgsToToDoTask(String args) throws InvalidCommandException {
        if (args.isBlank()) {
            throw new InvalidCommandException("todo <description>");
        }
        return new ToDoTask(args.trim());
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.TODO;
    }
}
