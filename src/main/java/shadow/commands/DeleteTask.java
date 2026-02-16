package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.Task;

/**
 * Represents a command to delete a specific task from the task list in storage.
 * This command removes the task at the specified index and provides feedback to the user upon successful removal.
 */
public class DeleteTask extends Command {

    public static final String ERROR_MESSAGE = "Usage: delete <Task Number>";

    private final int taskIndex;

    /**
     * Creates a new DeleteTask command to remove a task at the specified index.
     *
     * @param taskIndex the index of the task to be removed
     */
    private DeleteTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete operation by removing the task at the given index
     * from storage, then prints a confirmation message.
     */
    @Override
    public String execute() {
        Task removed = Storage.getInstance().removeTask(this.taskIndex);
        return String.format("Removed: %s", removed.toString());
    }

    /**
     * Creates an instance of {@link DeleteTask} based on the provided input parts.
     * <p>
     * This factory method expects exactly two input elements: the command keyword
     * (e.g., "delete") and the task number to delete. It parses the task number,
     * validates that it refers to a valid task in {@link Storage}, and constructs
     * the {@code DeleteTask} accordingly.
     * </p>
     *
     * @param parts an array of strings where:
     *              <ul>
     *                  <li>{@code parts[0]} is expected to be the "delete" command keyword</li>
     *                  <li>{@code parts[1]} is the task number to delete (1-based index)</li>
     *              </ul>
     *
     * @return a new {@code DeleteTask} instance with the validated task index
     *
     * @throws IllegalArgumentException if:
     *     <ul>
     *         <li>{@code parts.length != 2}</li>
     *         <li>{@code parts[1]} is not a valid number</li>
     *         <li>the task number is out of bounds</li>
     *     </ul>
     */
    public static DeleteTask of(String[] parts) throws IllegalArgumentException {
        assert(parts[0].equals("delete"));
        if (parts.length != 2) {
            throw new IllegalArgumentException(DeleteTask.ERROR_MESSAGE);
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= Storage.getInstance().getTasks().size() || taskIndex < 0) {
                String invalidTaskNumberErrorMessage = "You have inputted an invalid task number";
                throw new IllegalArgumentException(invalidTaskNumberErrorMessage);
            }
            return new DeleteTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DeleteTask.ERROR_MESSAGE);
        }
    }
}
