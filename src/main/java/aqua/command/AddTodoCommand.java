package aqua.command;

import aqua.exception.InvalidArgumentException;
import aqua.exception.StorageException;
import aqua.task.Task;
import aqua.task.TaskList;
import aqua.task.ToDo;

/**
 * Command to add a Todo task to the task list.
 */
public class AddTodoCommand extends AddTaskCommand {
    private final String commandArgs;

    /**
     * Creates a command that adds a Todo task.
     *
     * @param commandArgs the string after the "todo" command word, representing the description
     */
    public AddTodoCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Adds a Todo task to the task list.
     *
     * @throws InvalidArgumentException if description is blank
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentException, StorageException {
        if (commandArgs.isBlank()) {
            throw new InvalidArgumentException("Description of a todo cannot be empty.");
        }

        Task task = new ToDo(commandArgs);
        return super.addTask(task, taskList);
    }
}
