package shadow.commands;

import shadow.storage.Storage;

/**
 * Represents the command to unmark a task as not done.
 * This command interacts with the {@link Storage} class to update the task status.
 */
public class UnmarkTask extends Command {

    public static final String ERROR_MESSAGE = "Usage: unmark <Task Number>";

    private final int taskIndex;

    /**
     * Private constructor to create an UnmarkTask command for unmarking a task as not done.
     * <p>
     * Instances should be created via the factory method {@code UnmarkTask.create(int)}.
     *
     * @param taskIndex the index of the task to be unmarked
     */
    private UnmarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark operation by marking the specified task as not done in storage.
     */
    @Override
    public String execute() {
        Storage.getInstance().unmarkTask(this.taskIndex);
        return "Noted, the following task has been unmarked:\n"
                + Storage.getInstance().getTasks().get(this.taskIndex).toString();
    }

    /**
     * Creates an instance of {@link UnmarkTask} based on the provided input parts.
     * <p>
     * This factory method expects the input array to contain exactly two elements:
     * the command type (e.g., "unmark") and the task number to unmark. The task number
     * is parsed, validated against the task list in {@link Storage}, and then used
     * to construct the {@code UnmarkTask}.
     * </p>
     *
     * @param parts an array of strings where:
     *              <ul>
     *                  <li>{@code parts[0]} is expected to be the "unmark" command keyword</li>
     *                  <li>{@code parts[1]} is the task number to unmark (1-based index)</li>
     *              </ul>
     *
     * @return a new {@code UnmarkTask} instance with the validated task index
     *
     * @throws IllegalArgumentException if:
     *     <ul>
     *         <li>{@code parts.length != 2}</li>
     *         <li>{@code parts[1]} is not a valid number</li>
     *         <li>the task number is out of bounds</li>
     *     </ul>
     */
    public static UnmarkTask of(String[] parts) throws IllegalArgumentException {
        assert(parts[0].equals("unmark"));
        if (parts.length != 2) {
            throw new IllegalArgumentException(UnmarkTask.ERROR_MESSAGE);
        }

        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= Storage.getInstance().getTasks().size() || taskIndex < 0) {
                throw new IllegalArgumentException("You have inputted an invalid task number");
            }
            return new UnmarkTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(UnmarkTask.ERROR_MESSAGE);
        }
    }
}
