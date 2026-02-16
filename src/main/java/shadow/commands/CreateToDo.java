package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.ToDo;

/**
 * Represents a command for creating a new {@link ToDo} task and adding it to storage.
 * This class is a specific implementation of the {@link Command} class, designed
 * to handle the creation and addition of {@link ToDo} tasks.
 */
public class CreateToDo extends Command {
    private final ToDo todo;

    /**
     * Constructs a new {@code CreateToDo} command with the specified {@code ToDo} task.
     * This command is responsible for adding the provided {@code ToDo} task to storage.
     *
     * @param todo the {@code ToDo} task to be added
     */
    private CreateToDo(ToDo todo) {
        this.todo = todo;
    }

    /**
     * Executes the command to add the {@code ToDo} task to the storage.
     * The task is added to the list of existing tasks managed by the {@code Storage} singleton instance.
     *
     * @return a {@code String} message confirming the task has been added,
     *     including the string representation of the task
     */
    @Override
    public String execute() {
        Storage.getInstance().addTask(this.todo);
        return "Added:\n" + this.todo.toString();
    }

    /**
     * Creates a new {@code CreateToDo} command from the given input parts.
     * The input should be an array where the first element specifies the command
     * keyword ("todo") and the second element provides the task description.
     *
     * @param parts an array of strings where:
     *              - {@code parts[0]} is expected to be "todo"
     *              - {@code parts[1]} is the description of the task to be created
     * @return a new {@code CreateToDo} instance representing the command to create a {@code ToDo} task
     * @throws IllegalArgumentException if the input array does not have exactly two elements
     */
    public static CreateToDo of(String[] parts) {
        assert(parts[0].equals("todo"));
        if (parts.length != 2) {
            throw new IllegalArgumentException(ToDo.ERROR_MESSAGE);
        }
        return new CreateToDo(ToDo.of(parts[1]));
    }


}
