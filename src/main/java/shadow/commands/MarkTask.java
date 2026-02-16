package shadow.commands;

import shadow.storage.Storage;

/**
 * Represents a command to mark a specific task as done.
 * This class encapsulates the logic for identifying the task to be marked
 * based on its index and delegating the marking operation to {@code Storage}.
 */
public class MarkTask extends Command {

    public static final String ERROR_MESSAGE = "Usage: mark <Task Number>";

    private final int taskIndex;

    /**
     * Private constructor to create a MarkTask command for marking a task as done.
     * Instances should be created via the factory method {@code MarkTask.create(int)}.
     *
     * @param taskIndex the index of the task to be marked as done
     */
    private MarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark operation by marking the specified task as done in storage.
     */
    @Override
    public String execute() {
        Storage.getInstance().markTask(this.taskIndex);
        return "Noted, the following task has been marked:\n"
                + Storage.getInstance().getTasks().get(this.taskIndex).toString();
    }

    /**
     * Creates a new {@code MarkTask} command from the given input parts.
     * <p>
     * Expects exactly two parts: the command and the task number.
     * Parses the task number (1-based) and converts it to a zero-based index.
     * Validates the index against the current list of tasks in {@link Storage}.
     * Throws an {@link IllegalArgumentException} if the input is malformed or the index is invalid.
     * </p>
     *
     * @param parts the command parts parsed from user input
     * @return a new {@code MarkTask} instance targeting the specified task index
     * @throws IllegalArgumentException if the input format is invalid, or the task number is out of range
     */
    public static MarkTask of(String[] parts) throws IllegalArgumentException {
        assert(parts[0].equals("mark"));
        if (parts.length != 2) {
            throw new IllegalArgumentException(MarkTask.ERROR_MESSAGE);
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= Storage.getInstance().getTasks().size() || taskIndex < 0) {
                String invalidTaskNumberErrorMessage = "You have inputted an invalid task number";
                throw new IllegalArgumentException(invalidTaskNumberErrorMessage);
            }
            return new MarkTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MarkTask.ERROR_MESSAGE);
        }
    }
}
